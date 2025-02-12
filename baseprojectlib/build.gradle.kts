import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("maven-publish")
    id("signing")
    id("com.vanniktech.maven.publish") version "0.30.0"

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("kotlin-android")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

group = "io.github.huongltt1113"
version = "1.0.2"

android {
    namespace = "io.github.huongltt1113"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.databinding:databinding-runtime:8.6.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.2")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("com.tbuonomo:dotsindicator:5.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.10"))

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.airbnb.android:lottie:6.5.0")

    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    implementation("com.google.firebase:firebase-ml-vision:24.0.3")
    implementation("com.google.firebase:firebase-ml-vision-image-label-model:20.0.1")

    implementation("com.google.firebase:firebase-ads:23.4.0")
    implementation("com.google.ads.mediation:pangle:6.2.0.7.0")
    implementation("com.google.ads.mediation:facebook:6.18.0.0")
    implementation("com.google.ads.mediation:vungle:7.4.1.0")
    implementation("com.google.ads.mediation:ironsource:8.4.0.0")
    implementation("com.unity3d.ads:unity-ads:4.12.3")
    implementation("com.google.ads.mediation:unity:4.12.3.0")
    implementation("com.google.ads.mediation:applovin:13.0.0.1")
    implementation("com.google.ads.mediation:mintegral:16.8.61.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
    implementation("androidx.fragment:fragment-ktx:1.8.3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.google.android.flexbox:flexbox:3.0.0")

    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation("androidx.work:work-runtime-ktx:2.9.1")
    androidTestImplementation("androidx.work:work-testing:2.9.1")

    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation("androidx.navigation:navigation-fragment-ktx:2.8.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.1")

    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-rxjava2:2.6.1")
    implementation("androidx.room:room-guava:2.6.1")
    implementation("androidx.work:work-rxjava2:2.9.1")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("com.tbuonomo:dotsindicator:5.0")
    implementation("com.facebook.infer.annotation:infer-annotation:0.18.0")

    implementation("com.google.android.ump:user-messaging-platform:3.0.0")

    implementation("com.vungle:vungle-ads:7.4.1")
    implementation("com.android.billingclient:billing-ktx:7.1.1")

    implementation("com.google.code.gson:gson:2.11.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")

    implementation("io.github.elye:loaderviewlibrary:3.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.zeugmasolutions.localehelper:locale-helper-android:1.5.1")
    implementation("com.github.fracpete:musicg:1.4.2.2")
    implementation("androidx.multidex:multidex:2.0.1")

    implementation("io.github.ShawnLin013:number-picker:2.4.13")
    implementation("io.github.huongltt1113:mylibrary:1.1.1")
}


mavenPublishing {
    coordinates(
        groupId = "io.github.huongltt1113",
        artifactId = "baseprojectlib",
        version = "1.0.2"
    )

    pom{
        name.set("Base Project Library")
        description.set("A base project library for Android development")
        url.set("https://github.com/huongltt1113/BaseProject")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("huongltt1113")
                name.set("Luong Thi Thu Huong")
                email.set("21020337@vnu.edu.vn")
            }
        }

        scm {
            url.set("https://github.com/huongltt1113/BaseProject")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}