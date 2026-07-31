# Appetize.io Upload Fix Walkthrough

I have updated the GitHub Workflow to use the correct Appetize.io API endpoint, which resolves the `405 Not Allowed` error.

## Changes Made

### GitHub Workflow Fix
Updated [ios-build.yml](file:///C:/Users/rwgre/OneDrive/Desktop/Code/android studio/AppleTest/.github/workflows/ios-build.yml) to use the official API endpoint:
```yaml
      - name: Upload to Appetize.io
        run: |
          curl --request POST \
               --url "https://api.appetize.io/v1/apps" \
               --user "${{ secrets.APPETIZE_API_TOKEN }}:" \
               --form "file=@shared/build/bin/iosX64/debugFramework/iosApp.zip" \
               --form "platform=ios"
```

## Next Steps: Resolving the `.app` Bundle Issue

> [!WARNING]
> **Upload will succeed, but App won't run**:
> Currently, your workflow is zipping `shared.framework`. Appetize.io requires a simulator-compatible `.app` bundle to actually execute the application.

To fix this, you need a minimal iOS application project (usually in an `iosApp` directory) that:
1. Embeds the `shared.framework`.
2. Has a `main` entry point that calls `MainViewController`.
3. Is built using `xcodebuild` in the GitHub workflow.

### Example Xcode Build Step
If you add an `iosApp` project, you would add a step like this to your workflow:
```yaml
      - name: Build iOS App (.app)
        run: |
          xcodebuild -project iosApp/iosApp.xcodeproj \
                     -scheme iosApp \
                     -configuration Debug \
                     -sdk iphonesimulator \
                     -derivedDataPath build
```
Then you would zip the resulting `.app` folder inside `build/Build/Products/Debug-iphonesimulator/`.
