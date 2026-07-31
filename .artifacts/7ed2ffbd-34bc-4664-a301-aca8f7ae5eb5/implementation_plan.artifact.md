# Fix iOS Build Architecture Mismatch

The `Ld` (linker) error occurs because the GitHub Action is running on a `macos-14` runner (Apple Silicon/ARM64), but the workflow was only building the `iosX64` (Intel) version of the shared framework. Xcode was then trying to build the app for the `arm64` simulator and couldn't find the compatible framework.

## Proposed Changes

### GitHub Workflow

#### [MODIFY] [ios-build.yml](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/.github/workflows/ios-build.yml)
- Change the Gradle task from `:shared:iosX64Binaries` to `:shared:iosSimulatorArm64Binaries`.
- Update the zipping and path logic to reflect the `iosSimulatorArm64` output directory.

### Xcode Project

#### [MODIFY] [project.pbxproj](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/iosApp/iosApp.xcodeproj/project.pbxproj)
- Update `FRAMEWORK_SEARCH_PATHS` to include the `iosSimulatorArm64` directory.
- Update the file reference path for `shared.framework`.

## Verification Plan

### Automated Verification
- Push changes and verify the GitHub Action completes the `xcodebuild` step successfully.
- Ensure the linker no longer complains about missing `arm64` symbols.
