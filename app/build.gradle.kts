import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    // needed for non-primitive classes
    id("kotlin-parcelize")
    alias(libs.plugins.ktlint)
    alias(libs.plugins.room)
}

tasks.check {
    dependsOn("ktlintFormat")
    dependsOn("ktlintCheck")
}

ktlint {
    android = true
    ignoreFailures = false
    reporters {
        reporter(ReporterType.JSON)
    }
}

android {
    namespace = "com.tommunyiri.doggo.discover"
    compileSdk = 35

    val properties = Properties()
    properties.load(rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "com.tommunyiri.doggo.discover"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "DOG_API_KEY", properties.getProperty("DOG_API_KEY"))
        buildConfigField("String", "DOG_API_BASE_URL", properties.getProperty("DOG_API_BASE_URL"))
        buildConfigField("String", "DOG_IMAGE_URL", properties.getProperty("DOG_IMAGE_URL"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // coroutines
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    // navigation
    implementation(libs.androidx.navigation.compose)
    // koin
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.android.test)
    implementation(libs.koin.test.junit4)
    // lottie
    implementation(libs.lottie.compose)
    implementation(libs.kotlinx.serialization.json)
    // material extended icons
    implementation(libs.androidx.material.icons.extended)
    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    testImplementation(libs.room.testing)
    // splash screen api
    implementation(libs.splash.screen)
    // networking
    implementation(libs.bundles.networking)
    // chucker
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    // coil
    implementation(libs.coil.compose)
    // testing
    testImplementation(libs.bundles.testing)
}