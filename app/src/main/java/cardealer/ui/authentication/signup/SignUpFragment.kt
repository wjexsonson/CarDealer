package com.example.autohub.ui.authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.autohub.R
import com.example.autohub.databinding.FragmentSignUpBinding
import com.example.autohub.ui.activity.MainActivity
import com.example.autohub.ui.authentication.AuthState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val signUpViewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSignUpBinding.inflate(layoutInflater)
        .also {
            lifecycle.addObserver((activity as MainActivity).AuthBottomNavManager())
            _binding = it
            setListeners()
        }.root

    private fun setListeners() {

        binding.signUpBtn.setOnClickListener {

            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Enter email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Enter password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                signUpViewModel.signUpUser(email, password).collect { state ->
                    when (state) {
                        is AuthState.Loading -> {
                            // "Сделать индикатор загрузки или что-то подобное"
                        }

                        is AuthState.Success -> {
                            Toast.makeText(requireContext(), "Account created", Toast.LENGTH_SHORT)
                                .show()
                            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                        }

                        is AuthState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Authentication failed",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }
        binding.signInBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.continueAsAGuestBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}