plugins {
    id("flipper.android-lib")
}

android.namespace = "com.flipperdevices.bridge.connection.orchestrator.api"

dependencies {
    implementation(projects.components.bridge.connection.transport.common.api)

    implementation(projects.components.bridge.connection.config.api)

    implementation(libs.kotlin.coroutines)
}
