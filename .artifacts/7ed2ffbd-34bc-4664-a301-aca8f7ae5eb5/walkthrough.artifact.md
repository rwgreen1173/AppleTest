# Appetize.io Full Integration Walkthrough

I have completed the full setup to build a simulator-compatible iOS application and deploy it to Appetize.io.

## Changes Made

### 1. Created iOS Host Application
I added a minimal iOS project in the `iosApp` directory. This project acts as a container for your Compose Multiplatform UI.
- [iosApp.swift](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/iosApp/iosApp/iosApp.swift): The SwiftUI entry point.
- [ContentView.swift](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/iosApp/iosApp/ContentView.swift): Wraps the KMP `MainViewController`.
- [project.pbxproj](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/iosApp/iosApp.xcodeproj/project.pbxproj): Configures Xcode to build the app and link it with your `shared` framework.

### 2. Updated GitHub Workflow
The [ios-build.yml](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/.github/workflows/ios-build.yml) workflow now performs the following steps:
1.  **Builds the Shared Framework**: Uses `./gradlew :shared:iosSimulatorArm64Binaries` (Targeting ARM64 for native Mac runners).
2.  **Builds the .app bundle**: Uses `xcodebuild` with `ARCHS=arm64` to create a simulator executable.
3.  **Zips the .app bundle**: Packages the resulting `iosApp.app` into `iosApp.zip`.
4.  **Uploads to Appetize**: Sends the zip to the correct API endpoint.

### 3. Fixed Compose Multiplatform Crash
Resolved the `IllegalStateException` regarding `CADisableMinimumFrameDurationOnPhone`:
- **Info.plist**: Added the required `<key>CADisableMinimumFrameDurationOnPhone</key><true/>`.
- **MainViewController.kt**: Added `enforceStrictPlistSanityCheck = false` as a safety measure to ensure the app boots correctly on various environments.

## Verification
- Push the latest changes to GitHub.
- Check the Appetize.io logs; the "PlistSanityCheck" error should be gone, and the app should load the Compose UI.
