val kotlinVersion by project

dependencies {

    implementation(project(":kogram-core"))
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.2")

}