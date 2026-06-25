import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val osName = System.getProperty("os.name").lowercase()
val osClassifier = when {
    osName.contains("win") -> "win"
    osName.contains("mac") -> "mac"
    osName.contains("linux") || osName.contains("nix") -> "linux"
    else -> error("Unsupported OS: $osName")
}

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.googleservices)
    kotlin("plugin.serialization") version "1.9.24"
}

repositories {
    google()
    mavenCentral()
    maven("https://jogamp.org/deployment/maven")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    js(IR) {
        browser {
            binaries.executable()
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("androidx.activity:activity-compose:1.8.2")
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:33.5.1"))
            implementation("io.ktor:ktor-client-okhttp:2.3.12")
            implementation(libs.ktor.client.okhttp)
            implementation("com.google.android.material:material:1.12.0")
            implementation("com.google.android.gms:play-services-auth:21.0.0")
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation("cafe.adriel.voyager:voyager-navigator:1.1.0-beta02")
            implementation("cafe.adriel.voyager:voyager-screenmodel:1.1.0-beta02")
            implementation("cafe.adriel.voyager:voyager-tab-navigator:1.1.0-beta02")
            implementation("cafe.adriel.voyager:voyager-transitions:1.1.0-beta02")
            implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:1.1.0-beta02")
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation("com.benasher44:uuid:0.7.0")
//            implementation("com.russhwolf:multiplatform-settings:1.1.1")
            implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            implementation("dev.gitlive:firebase-database:2.1.0")
            implementation("dev.gitlive:firebase-auth:2.1.0")
            implementation("dev.gitlive:firebase-messaging:2.1.0")
            implementation("io.ktor:ktor-client-core:2.3.12")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.12")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
            implementation("io.ktor:ktor-client-logging:2.3.12")
            implementation("io.insert-koin:koin-core:4.0.0")
            implementation("io.insert-koin:koin-test:4.0.0")
            implementation("io.insert-koin:koin-compose:4.0.0")
            api("io.github.kevinnzou:compose-webview-multiplatform:2.0.3")
            implementation("io.github.kevinnzou:compose-webview-multiplatform:2.0.3")
//            implementation("io.github.khubaibkhan4:mediaplayer-kmp:2.0.9")
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.web.core)
                implementation("io.ktor:ktor-client-js:2.3.12")
                implementation(libs.ktor.client.js)
            }
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.ktor.client.cio)
            implementation("org.openjfx:javafx-base:19:$osClassifier")
            implementation("org.openjfx:javafx-graphics:19:$osClassifier")
            implementation("org.openjfx:javafx-controls:19:$osClassifier")
            implementation("org.openjfx:javafx-swing:19:$osClassifier")
            implementation("org.openjfx:javafx-web:19:$osClassifier")
            implementation("org.openjfx:javafx-media:19:$osClassifier")
        }
    }
}

android {
    namespace = "com.a2004256_ahmedmohamed.movieapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.a2004256_ahmedmohamed.movieapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

dependencies {
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(compose.preview)
    implementation(libs.androidx.activity.ktx)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.a2004256_ahmedmohamed.movieapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.a2004256_ahmedmohamed.movieapp"
            packageVersion = "1.0.0"
        }

        jvmArgs("--add-modules=javafx.controls,javafx.web,javafx.media")
        jvmArgs("--add-opens", "java.desktop/sun.awt=ALL-UNNAMED")
        jvmArgs("--add-opens", "java.desktop/java.awt.peer=ALL-UNNAMED")
    }
}

configurations.all {
    resolutionStrategy {
        force("io.ktor:ktor-client-core:2.3.12")
        force("io.ktor:ktor-client-content-negotiation:2.3.12")
        force("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
        force("io.ktor:ktor-client-okhttp:2.3.12")
        force("io.ktor:ktor-utils:2.3.12")
        force("io.ktor:ktor-io:2.3.12")
    }
}