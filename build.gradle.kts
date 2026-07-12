plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

group = "com.shopkart"
version = "1.0.0"


// ============================================================
// VERSION MANAGEMENT
// ============================================================

val junitVersion = "5.13.4"
val cucumberVersion = "7.34.3"
val restAssuredVersion = "5.5.7"
val selenideVersion = "7.16.2"
val allureVersion = "2.33.0"
val slf4jVersion = "2.0.17"
val testcontainersVersion = "2.0.5"
val flywayVersion = "10.22.0"
val mysqlVersion = "9.3.0"


// ============================================================
// JAVA CONFIGURATION
// ============================================================

java {

    sourceCompatibility =
        JavaVersion.VERSION_22

    targetCompatibility =
        JavaVersion.VERSION_22
}


// ============================================================
// REPOSITORIES
// ============================================================

repositories {

    mavenCentral()
}


// ============================================================
// DEPENDENCIES
// ============================================================

dependencies {


    // ========================================================
    // JUNIT 5
    // ========================================================

    testImplementation(
        platform(
            "org.junit:junit-bom:$junitVersion"
        )
    )

    testImplementation(
        "org.junit.jupiter:junit-jupiter"
    )

    testRuntimeOnly(
        "org.junit.platform:junit-platform-launcher"
    )


    // ========================================================
    // CUCUMBER
    // ========================================================

    testImplementation(
        platform(
            "io.cucumber:cucumber-bom:$cucumberVersion"
        )
    )

    testImplementation(
        "io.cucumber:cucumber-java"
    )

    testImplementation(
        "io.cucumber:cucumber-junit-platform-engine"
    )

    testImplementation(
        "io.cucumber:cucumber-picocontainer"
    )

    testImplementation(
        "org.junit.platform:junit-platform-suite"
    )


    // ========================================================
    // REST ASSURED
    // ========================================================

    testImplementation(
        "io.rest-assured:rest-assured:$restAssuredVersion"
    )

    testImplementation(
        "io.rest-assured:json-schema-validator:$restAssuredVersion"
    )


    // ========================================================
    // SELENIDE
    // ========================================================

    testImplementation(
        "com.codeborne:selenide:$selenideVersion"
    )


    // ========================================================
    // MYSQL
    // ========================================================

    testImplementation(
        "com.mysql:mysql-connector-j:$mysqlVersion"
    )


    // ========================================================
    // TESTCONTAINERS
    // ========================================================

    testImplementation(
        platform(
            "org.testcontainers:testcontainers-bom:$testcontainersVersion"
        )
    )

    testImplementation(
        "org.testcontainers:testcontainers"
    )

    testImplementation(
        "org.testcontainers:testcontainers-mysql"
    )

    testImplementation(
        "org.testcontainers:testcontainers-junit-jupiter"
    )


    // ========================================================
    // FLYWAY
    // ========================================================

    testImplementation(
        "org.flywaydb:flyway-core:$flywayVersion"
    )

    testImplementation(
        "org.flywaydb:flyway-mysql:$flywayVersion"
    )


    // ========================================================
    // ALLURE REPORTING
    // ========================================================

    testImplementation(
        platform(
            "io.qameta.allure:allure-bom:$allureVersion"
        )
    )

    testImplementation(
        "io.qameta.allure:allure-cucumber7-jvm"
    )

    testImplementation(
        "io.qameta.allure:allure-junit5"
    )

    testImplementation(
        "io.qameta.allure:allure-rest-assured"
    )


    // ========================================================
    // LOGGING
    // ========================================================

    testImplementation(
        "org.slf4j:slf4j-simple:$slf4jVersion"
    )
}


// ============================================================
// JAVA COMPILATION
// ============================================================

tasks.withType<JavaCompile>().configureEach {

    options.encoding =
        "UTF-8"

    options.release.set(22)
}


// ============================================================
// COMMON TEST CONFIGURATION
// ============================================================

tasks.withType<Test>().configureEach {

    useJUnitPlatform()


    // --------------------------------------------------------
    // TEST ENVIRONMENT
    // --------------------------------------------------------

    systemProperty(
        "test.env",
        providers.gradleProperty("testEnv")
            .orElse("local")
            .get()
    )


    // --------------------------------------------------------
    // HEADLESS CONFIGURATION
    // --------------------------------------------------------

    systemProperty(
        "headless",
        providers.gradleProperty("headless")
            .orElse("false")
            .get()
    )


    // --------------------------------------------------------
    // BROWSER CONFIGURATION
    // --------------------------------------------------------

    systemProperty(
        "browser",
        providers.gradleProperty("browser")
            .orElse("chrome")
            .get()
    )


    // --------------------------------------------------------
    // BUILD LABEL
    // --------------------------------------------------------

    systemProperty(
        "build.label",
        providers.gradleProperty("buildLabel")
            .orElse("gradle-local")
            .get()
    )


    // --------------------------------------------------------
    // CUCUMBER
    // --------------------------------------------------------

    systemProperty(
        "cucumber.publish.quiet",
        "true"
    )


    // --------------------------------------------------------
    // TEST LOGGING
    // --------------------------------------------------------

    testLogging {

        events(
            "passed",
            "skipped",
            "failed"
        )

        exceptionFormat =
            org.gradle.api.tasks.testing.logging
                .TestExceptionFormat.SHORT
    }
}


// ============================================================
// MAIN JUNIT TEST TASK
// ============================================================

tasks.test {

    description =
        "Runs ShopKart JUnit API and UI tests."

    group =
        "verification"

    useJUnitPlatform()

    include(
        "**/api/tests/**",
        "**/ui/tests/**"
    )

    exclude(
        "**/runners/**",
        "**/stepdefs/**"
    )

    maxParallelForks = 1
}


// ============================================================
// API TEST TASK
// ============================================================

val apiTest by tasks.registering(Test::class) {

    description =
        "Runs ShopKart REST Assured API tests."

    group =
        "verification"

    testClassesDirs =
        sourceSets.test.get()
            .output
            .classesDirs

    classpath =
        sourceSets.test.get()
            .runtimeClasspath

    useJUnitPlatform()

    include(
        "**/api/tests/**"
    )

    exclude(
        "**/runners/**"
    )

    maxParallelForks = 1
}


// ============================================================
// UI TEST TASK
// ============================================================

val uiTest by tasks.registering(Test::class) {

    description =
        "Runs ShopKart Selenide UI tests."

    group =
        "verification"

    testClassesDirs =
        sourceSets.test.get()
            .output
            .classesDirs

    classpath =
        sourceSets.test.get()
            .runtimeClasspath

    useJUnitPlatform()

    include(
        "**/ui/tests/**"
    )

    exclude(
        "**/runners/**"
    )

    maxParallelForks = 1
}


// ============================================================
// CUCUMBER SMOKE TASK
// ============================================================

val cucumberSmoke by tasks.registering(Test::class) {

    description =
        "Runs ShopKart smoke Cucumber scenarios."

    group =
        "verification"

    testClassesDirs =
        sourceSets.test.get()
            .output
            .classesDirs

    classpath =
        sourceSets.test.get()
            .runtimeClasspath

    useJUnitPlatform()

    include(
        "**/runners/TestRunner.class"
    )

    systemProperty(
        "cucumber.filter.tags",
        "@smoke"
    )

    maxParallelForks = 1
}


// ============================================================
// CUCUMBER API TASK
// ============================================================

val cucumberApi by tasks.registering(Test::class) {

    description =
        "Runs ShopKart API Cucumber scenarios."

    group =
        "verification"

    testClassesDirs =
        sourceSets.test.get()
            .output
            .classesDirs

    classpath =
        sourceSets.test.get()
            .runtimeClasspath

    useJUnitPlatform()

    include(
        "**/runners/TestRunner.class"
    )

    systemProperty(
        "cucumber.filter.tags",
        "@api"
    )

    maxParallelForks = 1
}


// ============================================================
// CUCUMBER UI TASK
// ============================================================

val cucumberUi by tasks.registering(Test::class) {

    description =
        "Runs ShopKart UI Cucumber scenarios."

    group =
        "verification"

    testClassesDirs =
        sourceSets.test.get()
            .output
            .classesDirs

    classpath =
        sourceSets.test.get()
            .runtimeClasspath

    useJUnitPlatform()

    include(
        "**/runners/TestRunner.class"
    )

    systemProperty(
        "cucumber.filter.tags",
        "@ui"
    )

    maxParallelForks = 1
}


// ============================================================
// CUCUMBER E2E TASK
// ============================================================

val cucumberE2E by tasks.registering(Test::class) {

    description =
        "Runs ShopKart end-to-end Cucumber scenarios."

    group =
        "verification"

    testClassesDirs =
        sourceSets.test.get()
            .output
            .classesDirs

    classpath =
        sourceSets.test.get()
            .runtimeClasspath

    useJUnitPlatform()

    include(
        "**/runners/TestRunner.class"
    )

    systemProperty(
        "cucumber.filter.tags",
        "@e2e"
    )

    maxParallelForks = 1
}


// ============================================================
// NEGATIVE AND SECURITY CUCUMBER TASK
// ============================================================

val cucumberNegative by tasks.registering(Test::class) {

    description =
        "Runs ShopKart negative and security Cucumber scenarios."

    group =
        "verification"

    testClassesDirs =
        sourceSets.test.get()
            .output
            .classesDirs

    classpath =
        sourceSets.test.get()
            .runtimeClasspath

    useJUnitPlatform()

    include(
        "**/runners/TestRunner.class"
    )

    systemProperty(
        "cucumber.filter.tags",
        "@negative or @security"
    )

    maxParallelForks = 1
}


// ============================================================
// ALLURE CONFIGURATION
// ============================================================

allure {

    version.set(
        allureVersion
    )
}


// ============================================================
// PROJECT COMMAND SUMMARY
// ============================================================

tasks.register(
    "projectBuildSummary"
) {

    description =
        "Prints ShopKart automation Gradle commands."

    group =
        "help"

    doLast {

        println(
            """

            ==================================================
            SHOPKART AUTOMATION COMMANDS
            ==================================================

            Compile Project:
            ./gradlew clean testClasses

            Run JUnit API and UI Tests:
            ./gradlew clean test

            Run REST Assured API Tests:
            ./gradlew clean apiTest

            Run Selenide UI Tests:
            ./gradlew clean uiTest

            Run Cucumber Smoke Tests:
            ./gradlew clean cucumberSmoke

            Run Cucumber API Tests:
            ./gradlew clean cucumberApi

            Run Cucumber UI Tests:
            ./gradlew clean cucumberUi

            Run Cucumber E2E Tests:
            ./gradlew clean cucumberE2E

            Run Negative and Security Tests:
            ./gradlew clean cucumberNegative

            Generate Allure Report:
            ./gradlew allureReport

            Open Allure Report:
            ./gradlew allureServe

            ==================================================

            """.trimIndent()
        )
    }
}