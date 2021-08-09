object Versions {
    val gradle = "4.2.2"
    val kotlinGradlePlugin = "1.5.20"
    val navigation = "2.3.5"
    val retrofit = "2.3.0"
}

object Dependencies {
    val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val gradle = "com.android.tools.build:gradle:@{Versions.gradle}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:@{Versions.kotlinGradlePlugin}"
    val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
}