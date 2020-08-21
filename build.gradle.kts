import java.util.Date
import java.util.Properties
import java.nio.file.Paths
import java.nio.file.Files

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.3.70"
    id("com.jfrog.bintray") version "1.8.4"
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.4.0-rc"
}

group = "com.github.xadkile"
version = "1.0.5-beta"
val myArtifactId = "reusables"
repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.junit.platform:junit-platform-launcher:1.4.0")
    testImplementation("org.junit.platform:junit-platform-runner:1.4.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.4.0")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.4.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.70")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
}

val localProperties = {
    val z = Properties()
    z.load(Files.newInputStream(Paths.get("local.properties")))
    z
}()

fun getLocalPropertyAsString(key:String):String{
    return localProperties.get(key) as String
}
val binTrayUserName = getLocalPropertyAsString("BinTray.UserName")
bintray {
    user = binTrayUserName
    key = getLocalPropertyAsString("BinTray.Key")
    this.setPublications("reusablePublication")
    this.setConfigurations("archives")
    publish = true
    this.pkg = {
        val z = this.PackageConfig()
        z.repo = myArtifactId
        z.name = myArtifactId
        z.userOrg = binTrayUserName
        z.setLicenses("Apache-2.0")
        z.vcsUrl = "https://github.com/xadkile/reusables.git"
        z.version = {
            val vc = this.VersionConfig()
            vc.name = version as String
            vc.desc = "${myArtifactId} version ${version as String}"
            vc.released = Date().toString()
            vc.vcsTag = version as String
            vc
        }()
        z
    }()
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    val javadocJar by creating(Jar::class) {
        dependsOn.add(javadoc)
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(jar)
    }

}
//https://docs.gradle.org/current/dsl/org.gradle.api.publish.maven.MavenPublication.html
publishing {
    publications {
        create<MavenPublication>("reusablePublication") {
            groupId = group as String
            artifactId = myArtifactId
            version = version as String
        }
    }
}
