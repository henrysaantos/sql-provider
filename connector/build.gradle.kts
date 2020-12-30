repositories {
    mavenCentral()
}

dependencies {
    val hikariVersion = "3.4.5"
    val slf4jVersion = "1.7.25"

    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
}
