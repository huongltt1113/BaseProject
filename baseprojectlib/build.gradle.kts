import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("maven-publish")
    id("signing")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

group = "io.github.huongltt1113"
version = "1.0.7"

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
}

dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

mavenPublishing {
    coordinates(
        groupId = "io.github.huongltt1113",
        artifactId = "baseprojectlib",
        version = "1.0.7"
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