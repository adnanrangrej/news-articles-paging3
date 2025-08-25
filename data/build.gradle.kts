plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    // KSP plugin
    alias(libs.plugins.ksp)

    // Dagger hilt
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.github.adnanrangrej.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)

    // Dagger hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // RoomDB
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Paging 3
    implementation(libs.androidx.paging.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}