// gradle.properties
val kotlinVersion: String by project
val kotlinxSerializationVersion: String by project
val daggerVersion: String by project
val awsLambdaLibVersion: String by project
val junitVersion: String by project
val mockkVersion: String by project

group = "au.com.auspost.lambda"
version = "1.0.0-SNAPSHOT"

plugins {
  // It appears you can't use gradle.properties here: https://github.com/gradle/gradle/issues/1697
  kotlin("jvm") version "1.3.72"
  kotlin("plugin.serialization") version "1.3.72"
  kotlin("kapt") version "1.3.72"
  // id("com.github.johnrengelman.shadow") version "5.2.0"
}

dependencies {
  implementation(kotlin("stdlib-jdk8", version = org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinxSerializationVersion")
  implementation("com.google.dagger:dagger:$daggerVersion")
  implementation("com.amazonaws:aws-lambda-java-core:$awsLambdaLibVersion")
  kapt("com.google.dagger:dagger-compiler:$daggerVersion")

  testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  // this is very similar to mockito but without the need for reflections much better kotlin support
  // it also supports mocking static functions and object instantiation
  testImplementation("io.mockk:mockk:$mockkVersion")
}

repositories {
  mavenCentral()
}

// This is if you want to manually upload a jar
// tasks.getByName("jar").enabled = false
// tasks.getByName("build").dependsOn("shadowJar") // TODO: I'm sure there is a better way of doing this


tasks.test {
  useJUnitPlatform()
  maxHeapSize = "1G"
}
