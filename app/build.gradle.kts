plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.autohub"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.autohub"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    val navVersion = "2.8.0"
    val retrofitVersion = "2.11.0"
    val lifecycleVersion = "2.8.5"
    val roomVersion = "2.6.1"
    val koinVersion = "3.5.6"
    val dataStoreVersion = "1.1.1"

    implementation("androidx.datastore:datastore:$dataStoreVersion")
    implementation("androidx.datastore:datastore-preferences:$dataStoreVersion")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    implementation("io.coil-kt:coil:2.7.0")

    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.0")

    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.2")
    testImplementation("io.insert-koin:koin-test:$koinVersion")

    //JUnit dependency for unit tests
    testImplementation("junit:junit:4.13.2")

    // Mockito dependency for unit tests
    testImplementation("org.mockito:mockito-core:5.13.0")

    // Optional: Mockito Kotlin extension if you use Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")

    // AndroidX Test - Core library
    testImplementation("androidx.test:core:1.6.1")

    // AndroidX Test - JUnit support
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

    // Optional: AndroidX Test - Mockito Android support (for instrumented tests)
    androidTestImplementation("org.mockito:mockito-android:5.13.0")

    implementation("androidx.test:monitor:1.7.2")
    androidTestImplementation("org.testng:testng:7.10.2")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}