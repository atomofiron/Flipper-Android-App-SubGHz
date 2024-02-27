plugins {
    id("flipper.android-lib")
}

android.namespace = "com.flipperdevices.widget.api"

dependencies {
    implementation(projects.components.core.ui.decompose)
    implementation(projects.components.deeplink.api)

    implementation(libs.decompose)
}
