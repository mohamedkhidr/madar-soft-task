plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
    kotlin("kapt")
}

android {
    namespace = "com.khidrew.task"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.khidrew.task"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    kapt{
        correctErrorTypes = true
    }

    dataBinding { enable = true }
    buildFeatures { viewBinding = true }

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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lifecycle
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt (libs.room.compiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v283)

    // Navigation Components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)


    // Activity KTX for viewModels()
    implementation(libs.activity.ktx)

// Fragment KTX
    implementation(libs.androidx.fragment.ktx)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt (libs.hilt.android.compiler)

    // Epoxy
    implementation(libs.epoxy)
    kapt ("com.airbnb.android:epoxy-processor:5.1.4")
    implementation(libs.epoxyDatabinding)


    implementation(project(":data"))
    implementation(project(":domain"))



}