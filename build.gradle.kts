plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.mnr.intellij.plugin"
version = "4.0.1"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.3.2")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("252.*")
        changeNotes.set("""
            version 4.0.1:
            <ul>
                <li>Fix issue with pop-up menu not showing in some contexts</li>
            </ul>
            
            version 4.0.0:
            <ul>
                <li>Using new Intellij <a href="https://plugins.jetbrains.com/docs/intellij/general-threading-rules.html">threading</a></li>
            </ul>
    
            version 3.0.0:
            <ul>
                <li>Using new Intellij plugin framework</li>
            </ul>
            
            version 2.0.4:
            <ul>
                <li>Update version of Intellij</li>
            </ul>
            
            version 2.0.3:
            <ul>
                <li>Fix pop-up menu handler</li>
            </ul>
            
            version 2.0.2:
            <ul>
                <li>Remove build number limitation</li>
            </ul>
            
            version 2.0.1:
            <ul>
                <li>Icon added for the plugin</li>
            </ul>
    
            version 2.0:
            <ul>
                <li>Convert project to gradle-based intellij platform plugin</li>
                <li>Fix issue with reverting conversion</li>
                <li>It does not show popup/menu-item, if there is no text selected</li>
            </ul>
    
            version 1.8:
            <ul>
                <li>Bug fix, the undo doesn't work sometimes</li>
                <li>Update the license info</li>
            </ul>
    
            version 1.7:
            <ul>
                <li>Fix the typos inside the plugin.xml</li>
            </ul>
    
            version 1.6:
            <ul>
                <li>Get back to use JDK 6, to be compatible with older projects</li>
            </ul>
    
            version 1.5:
            <ul>
                <li>Bug fix, shouldn't try to modify a read-only document</li>
            </ul>
    
            version 1.4:
            <ul>
                <li>Bug fix, wrong line separators exception</li>
            </ul>
    
            version 1.3:
            <ul>
                <li>Modify structure to support independent popup menu</li>
                <li>Change shortcut because last one had conflict with IntelliJ default shortcut (Ctrl+Alt+6)</li>
            </ul>
    
            version 1.2:
            <ul>
                <li>Add shortcuts (Ctrl+Alt+B)</li>
            </ul>
    
            version 1.1:
            <ul>
                <li>Supports undo/redo in IDE editor</li>
            </ul>
    
            version 1.0:
            <ul>
                <li>Initial release</li>
            </ul>
        """.trimIndent())
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("JETBRAIN_TOKEN"))
    }
}
