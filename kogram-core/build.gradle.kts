
val kotlinVersion by project

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.2")
    testImplementation("org.assertj:assertj-core:3.11.1")


}