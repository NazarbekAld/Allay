import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer
import org.gradle.internal.os.OperatingSystem
import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform

plugins {
    `java-library`
    jacoco
    application
    id("me.champeau.jmh") version "0.7.2"
    id("com.gorylenko.gradle-git-properties") version "2.4.2"
    id("buildlogic.common")
}

group = "org.allaymc"
description = "server"
version = "1.0.0"

application {
    mainClass.set("org.allaymc.server.Allay")
}

fun isArm(): Boolean = DefaultNativePlatform.getCurrentArchitecture().isArm
fun isWindows(): Boolean = DefaultNativePlatform.getCurrentOperatingSystem().isWindows
fun isLinux(): Boolean = DefaultNativePlatform.getCurrentOperatingSystem().isLinux
fun isMac(): Boolean = DefaultNativePlatform.getCurrentOperatingSystem().isMacOsX

dependencies {
    api(project(":Allay-API"))
    implementation(libs.mcterminal)
    implementation(libs.bundles.logging)
    implementation(libs.disruptor)
    implementation(libs.progressbar) {
        exclude(group = "org.apache.logging.log4j", module = "log4j-core")
    }
    implementation(libs.bytebuddy) {
        exclude(group = "org.jline", module = "jline")
    }
    implementation(libs.libdeflate)
    implementation(libs.leveldbjni)
    implementation(libs.netty.epoll)
    implementation(libs.netty.kqueue)
    implementation(libs.jegenerator)
    implementation(libs.fastreflect)
    implementation(libs.reflections)
    implementation(libs.caffeine)
    implementation(libs.polyglot)
    implementation(libs.bundles.graalvm)
    implementation(libs.oshi)
    implementation(libs.flatlaf)
    implementation(libs.formsrt)

    // https://github.com/caoccao/Javet
    implementation("com.caoccao.javet:javet:3.1.3")
    implementation("com.caoccao.javet:javet-linux-arm64:3.1.3")
    implementation("com.caoccao.javet:javet-macos:3.1.3")

    if (isWindows()) {
        testImplementation("com.caoccao.javet:javet:3.1.3")
    } else if (isLinux() && !isArm()) {
        testImplementation("com.caoccao.javet:javet:3.1.3")
    } else if (isLinux() && isArm()) {
        testImplementation("com.caoccao.javet:javet-linux-arm64:3.1.3")
    } else if (isMac()) {
        testImplementation("com.caoccao.javet:javet-macos:3.1.3")
    } else {
        error("Unknown OS for Javet.")
    }

    // https://github.com/mvnpm/esbuild-java
    implementation("io.mvnpm:esbuild-java:1.4.3")

    testImplementation(libs.bytebuddy)
}

gitProperties {
    gitPropertiesName = "git.properties"
    gitPropertiesResourceDir.set(file("${rootProject.projectDir}/Allay-Data/resources"))
}

tasks.processResources {
    dependsOn("generateGitProperties")
    // input directory
    from("${rootProject.projectDir}/Allay-Data/resources")
    // exclude unpacked folder and block palette.nbt
    exclude("**/unpacked/**")
}

// disable, please use `java -jar` to start directly
tasks.startScripts {
    enabled = false
}
// disable
tasks.startShadowScripts {
    enabled = false
}
// disable
tasks.distTar {
    enabled = false
}
// disable
tasks.distZip {
    enabled = false
}

tasks.sourcesJar {
    dependsOn("generateGitProperties")
}

tasks.runShadow {
    workingDir = File("${rootProject.projectDir}/.run/")
    this.jarFile = file("build/libs/Allay-Server-$version-all.jar")
}

tasks.installShadowDist {
    enabled = false
}

tasks.jar {
    manifest {
        attributes["Multi-Release"] = "true"
    }
}

tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
}

tasks.test {
    finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.required = false
    }
    dependsOn("test")
    additionalClassDirs(file("${rootProject.projectDir}/Allay-API/build/classes/java/main"))
    additionalSourceDirs(file("${rootProject.projectDir}/Allay-API/src/main/java"))
}

jacoco {
    reportsDirectory = layout.buildDirectory.dir("${rootProject.projectDir}/.jacoco")
}