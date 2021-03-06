import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

val kakaoKey: String = gradleLocalProperties(rootDir).getProperty("kakao_app_key")

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "android.milestone"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            buildConfigField("String", "kakao_app_key", kakaoKey)
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    coreLibraryDesugaring(Dependencies.coreLibrary)

    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.coreKtx)

    // ui
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.swipeRefreshLayout)
    // test
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.testJunit)
    androidTestImplementation(Dependencies.testEspressoCore)

    // navigation
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)

    // hilt
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)

    // network
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshiConverter)
    implementation(Dependencies.okhttp3)
    implementation(Dependencies.okhttp3_logger)
    implementation(Dependencies.moshi)
    implementation(Dependencies.moshiKotlin)
    implementation(Dependencies.moshiCodegen)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.gson)

    // kakao
    implementation(Dependencies.kakaoUser)

    // lifecycle
    implementation(Dependencies.lifecycleLivedataKtx)

    // glide
    implementation(Dependencies.glide)
    annotationProcessor(Dependencies.glideCompiler)

    // cardStackView
    implementation(Dependencies.cardStackView)

    // lottie
    implementation(Dependencies.lottie)
}