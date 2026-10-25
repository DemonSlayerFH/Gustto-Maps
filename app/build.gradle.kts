import java.util.Properties

plugins { id("com.android.application") }

val localConfig = Properties().apply {
    val props = rootProject.file("local.properties")
    if (props.exists()) props.inputStream().use { load(it) }
}
// Maps es opcional para compilar y para probar RecyclerView / CardView.
val mapsKey = localConfig.getProperty("MAPS_API_KEY", "SIN_CONFIGURAR")

android {
    namespace = "com.example.gustto"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.gustto"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-demo"
        manifestPlaceholders["MAPS_API_KEY"] = mapsKey
        buildConfigField("boolean", "MAPS_CONFIGURADO", (mapsKey.startsWith("AIza") && mapsKey.length > 20).toString())
    }
    buildFeatures { buildConfig = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.gms:play-services-maps:19.2.0")
}
