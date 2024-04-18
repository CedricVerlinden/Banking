plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.palexdev:materialfx:11.17.0")
}

application {
    mainClass.set("com.cedricverlinden.banking.App")
    mainModule.set("banking")
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}
