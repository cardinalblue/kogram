import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kogramVersion by project

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    val kotlinVersion by project

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    }
}

tasks.withType<Wrapper> {
    gradleVersion = "4.6"
    distributionType = Wrapper.DistributionType.ALL
}

allprojects {
    group = "com.cardinalblue.kogram"
    version = "$kogramVersion"

    repositories {
        jcenter()
        mavenLocal()
    }


}

subprojects {
    apply {
        plugin("kotlin")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    val kotlinVersion by project
    val kotlinImplementation by configurations.creating

    dependencies {
        kotlinImplementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    }

}
