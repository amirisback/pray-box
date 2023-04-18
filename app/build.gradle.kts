plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_APP_ID
    flavorDimensions += "version"

    defaultConfig {
        applicationId = ProjectSetting.PROJECT_APP_ID
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        targetSdk = ProjectSetting.PROJECT_TARGET_SDK
        versionCode = ProjectSetting.PROJECT_VERSION_CODE
        versionName = ProjectSetting.PROJECT_VERSION_NAME

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Naming APK // AAB
        setProperty("archivesBaseName", "${ProjectSetting.NAME_APK}-${versionCode}")

        buildConfigField("String", "NAME_APP", "\"${ProjectSetting.NAME_APP}\"")

        // Inject app name for debug
        resValue("string", "app_name", ProjectSetting.NAME_APP_DEBUG)

        // Inject admob id for debug
        resValue("string", "admob_app_id", ProjectAds.Admob.Debug.APP_ID)

        // Inject admob banner id for release
        resValue("string", "admob_banner_id", ProjectAds.Admob.Debug.BANNER_ID)

        // Inject admob interstitial id for debug
        resValue("string", "admob_interstitial_id", ProjectAds.Admob.Debug.INTERSTITIAL_ID)

    }

    productFlavors {

        ProjectVariant.listPlayStore.forEach {
            create(it.name) {
                // Application ID
                applicationId = it.appId

                // Dimension
                dimension = "version"

                // Declaration build config
                buildConfigField("String", "DB_ROOM", "\"${it.dbName}\"")
                buildConfigField("String", "PREF_NAME", "\"${it.prefName}\"")
            }
        }

    }

    signingConfigs {

        // You need to specify either an absolute path or include the
        // keystore file in the same directory as the build.gradle file.
        // [PROJECT FOLDER NAME/app/[COPY YOUT KEY STORE] .jks in here

        ProjectVariant.listPlayStore.forEach {
            create(it.name) {
                storeFile = file(it.playStoreStoreFile)
                storePassword = it.playStoreStorePassword
                keyAlias = it.playStoreKeyAlias
                keyPassword = it.playStoreKeyPassword
            }
        }

    }

    buildTypes {

        getByName("debug") {
            applicationIdSuffix = ".dev"
        }

        getByName("release") {
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false

            /** Still not working

            // Enables code shrinking, obfuscation, and optimization for only your project's release build type.
            // isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the Android Gradle plugin.
            // isShrinkResources = true

             **/

            ProjectVariant.listPlayStore.forEach {
                productFlavors.getByName(it.name) {
                    signingConfig = signingConfigs.getByName(it.name)
                }
            }

            // Inject app name for release
            resValue("string", "app_name", ProjectSetting.NAME_APP)

            // Inject admob id for release
            resValue("string", "admob_app_id", ProjectAds.Admob.APP_ID)

            // Inject admob banner id for release
            resValue("string", "admob_banner_id", ProjectAds.Admob.BANNER_ID)

            // Inject admob interstitial id for release
            resValue("string", "admob_interstitial_id", ProjectAds.Admob.INTERSTITIAL_ID)

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of("17"))
        }
    }

}

dependencies {

    implementation(Androidx.Core.ktx)
    implementation(Androidx.Work.runtimeKtx)

    implementation(Androidx.constraintLayout)
    implementation(Androidx.swipeRefreshLayout)

    implementation(Frogo.ui)
    implementation(Frogo.sdk)
    implementation(Frogo.admob)
    implementation(Frogo.recyclerView)
    implementation(Frogo.consumeApi)

    implementation(Util.hdodenhofCircleImageView)
    implementation("org.jbundle.util.osgi.wrapped:org.jbundle.util.osgi.wrapped.org.apache.http.client:4.1.2")
    implementation("com.github.PhilJay:MPAndroidChart:3.1.0")

    kapt(GitHub.glideCompiler)

}