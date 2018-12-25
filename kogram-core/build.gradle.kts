
val kotlinVersion by project

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.2")

}