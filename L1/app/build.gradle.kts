plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.kapt") // Make sure kapt is enabled for annotation processing
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.helloworld.onetoonerelationship"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeBom.get()
    }
}

dependencies {
    // Core dependencies
    implementation(libs.androidx.core-ktx)
    implementation(libs.androidx.lifecycle-runtime-ktx)
    implementation(libs.androidx.activity-compose)

    // Compose dependencies
    implementation(platform(libs.androidx.compose-bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)

    // Room Database dependencies
    implementation(libs.room-runtime)
    implementation(libs.room-ktx)
    kapt(libs.room-compiler)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx-junit)
    androidTestImplementation(libs.androidx.espresso-core)
    androidTestImplementation(libs.androidx.ui-test-junit4)
}
