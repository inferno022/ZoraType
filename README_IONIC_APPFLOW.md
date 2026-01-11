# ZoraText Ionic Appflow Setup

This project is configured to work with **Ionic Appflow** for automated native Android builds.

## Appflow Configuration
- **Project Type**: Custom (Native Android)
- **Build Command**: `npm run build` (which executes `./gradlew assembleRelease`)
- **Environment**: Ensure you have defined the following **Environment Variables** in your Appflow Dashboard to enable signed builds:

### Signing Credentials (Optional but recommended for Release)
- `ANDROID_KEYSTORE_FILE`: Path to your `.jks` file (relative to root, e.g., `key.jks`)
- `ANDROID_KEY_ALIAS`: Your key alias
- `ANDROID_KEY_PASSWORD`: Your key password
- `ANDROID_STORE_PASSWORD`: Your keystore password

> **Note**: You must upload your keystore file to the repository (if private) or provide a way for the build server to access it.

### Crash Reporting (Optional)
- `ACRA_ENABLED`: `true` or `false`
- `ACRA_URL`: Your ACRA endpoint
- `ACRA_USER`: ACRA username
- `ACRA_PASSWORD`: ACRA password

## Files added for Appflow:
1. `ionic.config.json`: Identifies the project for the Ionic CLI/Appflow.
2. `package.json`: provides the build script for the Appflow pipeline.
3. Updated `build.gradle`: Modified to support Environment Variables for CI builds.

## How to Build
1. Connect this GitHub repository to your Ionic Appflow account.
2. Navigate to **Builds** -> **New Build**.
3. Select the **Release** build type.
4. Add the Environment Variables mentioned above in the **Automations** or **Environment** section.
