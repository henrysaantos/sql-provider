plugins {
    java
}

group = "com.henryfabio"
version = "1.0.1"

allprojects {

    plugins.apply("java")

    repositories {
        mavenCentral()
    }

    dependencies {
        val lombokVersion = "1.18.16"
        val jetbrainsAnnotationsVersion = "20.1.0"

        compileOnly("org.projectlombok:lombok:$lombokVersion")
        compileOnly("org.jetbrains:annotations:$jetbrainsAnnotationsVersion")

        annotationProcessor("org.projectlombok:lombok:$lombokVersion")
        annotationProcessor("org.jetbrains:annotations:$jetbrainsAnnotationsVersion")

        testImplementation("org.xerial:sqlite-jdbc:3.32.3.2")
        testImplementation("mysql:mysql-connector-java:8.0.15")
    }

}
