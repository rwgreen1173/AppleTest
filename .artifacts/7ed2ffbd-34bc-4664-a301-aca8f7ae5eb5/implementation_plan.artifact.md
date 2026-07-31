# Fix iOS Compose Runtime Crash

The app is crashing because Compose Multiplatform performs a sanity check on `Info.plist` for performance-related keys. Specifically, it requires `CADisableMinimumFrameDurationOnPhone` to be set to `true` to ensure high refresh rate support on modern iPhones.

## Proposed Changes

### iOS Application

#### [MODIFY] [Info.plist](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/iosApp/iosApp/Info.plist)
- Add `<key>CADisableMinimumFrameDurationOnPhone</key><true/>`.
- Add a basic `UIApplicationSceneManifest` to resolve the scene configuration warnings.

#### [MODIFY] [MainViewController.kt](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/shared/src/iosMain/kotlin/com/gmail/rwgreen1173/appletest/MainViewController.kt)
- Optionally disable strict sanity checks as a fallback.

## Verification Plan

### Automated Verification
- Push changes and verify that the app no longer crashes in the Appetize.io logs.
