# About the fork
<img src='https://github.com/Atomofiron/Flipper-Android-App-SubGHz/blob/region-bands/metadata/flpr-ww.png' width=258 />

Added some custom regions

```kotlin
// components/core/log/src/commonMain/kotlin/com/flipperdevices/core/Custom.kt

// must be 2 characters
const val ZERO = "00"
const val WW = "WW"
const val DOUBLE_Q = "??"
```
Forced the region to WW
```kotlin
// components/updater/subghz/src/main/kotlin/com/flipperdevices/updater/subghz/model/RegionProvisioning.kt

fun provideRegion(): Pair<String?, RegionProvisioningSource?> {
    if (true) return (WW to RegionProvisioningSource.UWU)
    // ...
}
```
Defined three custom bands according to the CC1101 datasheet (300-348 MHz, 387-464 MHz, 779-928 MHz) for the all regions, added some custom regions and specified one in the end.\
You can set anyone or add your own
```kotlin
// components/updater/downloader/src/main/java/com/flipperdevices/updater/downloader/api/DownloaderApiImpl.kt

override suspend fun getSubGhzProvisioning(): SubGhzProvisioningModel {
    // ...
    val pwr = 20
    val dc = 50u
    val fullBand = listOf(SubGhzProvisioningBand(start = 0u, end = 1000000000u, maxPower = pwr, dutyCycle = dc))
    val dataSheetBands = listOf(
        SubGhzProvisioningBand(start = 300000000u, end = 348000000u, maxPower = pwr, dutyCycle = dc),
        SubGhzProvisioningBand(start = 387000000u, end = 464000000u, maxPower = pwr, dutyCycle = dc),
        SubGhzProvisioningBand(start = 779000000u, end = 928000000u, maxPower = pwr, dutyCycle = dc),
    )
    val countriesBands = buildMap {
        successfulResponse.countriesBands.forEach { entry ->
            this[entry.key.uppercase()] = dataSheetBands
        }
        set(WW, dataSheetBands)
        set(ZERO, fullBand)
        set(DOUBLE_Q, fullBand)
    }
    if (true) return SubGhzProvisioningModel(
        countries = countriesBands,
        country = WW,
        defaults = dataSheetBands,
    )
    // ...
}
```

# Flipper Android App [![Flipper App Status](https://github.com/flipperdevices/Flipper-Android-App/actions/workflows/internal.yml/badge.svg)](https://github.com/Flipper-Zero/Flipper-Android-App/releases) [![Discord](https://img.shields.io/discord/740930220399525928.svg?label=&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](http://flipperzero.one/discord)

Mobile app to rule all Flipper's family

![dolphin-dark](.github/dark_theme_banner.png#gh-dark-mode-only)
![dolphin-light](.github/light_theme_banner.png#gh-light-mode-only)

## Download


[<img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png"
     alt="Get it on Google Play"
     height="80">](https://play.google.com/store/apps/details?id=com.flipperdevices.app)
[<img height="80" alt='Get it on F-Droid' src='https://fdroid.gitlab.io/artwork/badge/get-it-on.png'/>](https://f-droid.org/en/packages/com.flipperdevices.app/)

Or get the app from the [Releases Section](https://github.com/flipperdevices/Flipper-Android-App/releases/latest).

## Module arch

```
├── instances
│   ├── app
├── components
│   ├── core
│   ├── bridge
│   ├── feature1
│   ├── feature2
```

- `app` - Main application module with UI
- `components/core` - Core library with deps and utils
- `components/bridge` - Communication between android and Flipper
- `components/*` - Features modules, which connect to root application
