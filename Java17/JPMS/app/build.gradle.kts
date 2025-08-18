plugins { application }
java { modularity.inferModulePath.set(true) }

dependencies {
    implementation(project(":core"))
    implementation(project(":utils"))
}
application {
    mainModule.set("org.example.app")
    mainClass.set("org.example.app.Main")
}