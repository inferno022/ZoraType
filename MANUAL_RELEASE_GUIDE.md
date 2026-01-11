# ZoraText Manual Release Guide

## Quick Release Process

### 1. Build APK Locally
```bash
./gradlew clean assemblePlaystoreDebug
```

### 2. Locate APK
The APK will be created at:
```
build/outputs/apk/playstore/debug/android-keyboard-master-playstore-debug.apk
```

### 3. Create GitHub Release
1. Go to: https://github.com/inferno022/ZoraType/releases
2. Click "Create a new release"
3. Tag: `v1.0.X` (increment X)
4. Title: `ZoraText v1.0.X - Professional Edition`
5. Upload the APK file manually
6. Use this description:

```markdown
## ZoraText Professional Edition v1.0.X

### Features:
- ✅ **Fonts Button**: Visible on home screen with import functionality
- ✅ **Professional Themes**: Mango Sunset, Berry Bliss, and more
- ✅ **Google Pay Integration**: QR code for donations
- ✅ **Complete Branding**: ZoraText throughout the app
- ✅ **Email Support**: mail.zoratext@gmail.com

### Installation:
1. Download the APK below
2. Enable "Install from Unknown Sources" in Android settings
3. Install and enjoy ZoraText!

### What's Fixed:
- Fonts button now prominently displayed
- Font import with + button works perfectly
- Professional theme names (no more generic names)
- All compilation errors resolved

**Support Email:** mail.zoratext@gmail.com
```

### 4. Test Installation
1. Download APK to your phone
2. Install and verify:
   - Fonts button is visible on home screen
   - Font import works with + button
   - Themes have fruity names
   - Google Pay QR shows in donation screen

## Current App Status ✅

### ✅ Fonts Button
- **Location**: Home screen, below Theme button
- **Style**: HomePrimary (same prominence as Theme)
- **Navigation**: Routes to "fonts" screen
- **Icon**: Type icon (R.drawable.type)

### ✅ Font Import
- **Feature**: + button in font picker
- **Supports**: .ttf, .otf, .zip files
- **Source**: Downloads folder
- **Storage**: Internal app storage

### ✅ Theme Names
- **Old**: "ZoraText Premium" → **New**: "Mango Sunset"
- **Old**: "ZoraText VI Theme" → **New**: "Berry Bliss"
- All other themes retain professional names

### ✅ Branding
- **App Name**: ZoraText throughout
- **Email**: mail.zoratext@gmail.com everywhere
- **Logo**: logo.png used consistently
- **Payment**: Google Pay QR integration

## Troubleshooting

### If CI Fails
The local build always works. Use this manual process:
1. Build locally: `./gradlew clean assemblePlaystoreDebug`
2. Upload APK manually to GitHub releases
3. Share download link directly

### APK Size
Current APK is optimized and includes:
- All necessary fonts in assets/fonts/
- Compressed dictionaries
- Optimized resources
- No unnecessary dependencies

### Distribution
- **GitHub Releases**: Primary distribution method
- **Direct Download**: Share APK file directly
- **Email**: Send APK to mail.zoratext@gmail.com for distribution

---

**Last Updated**: January 2026
**APK Location**: `build/outputs/apk/playstore/debug/android-keyboard-master-playstore-debug.apk`
**Build Command**: `./gradlew clean assemblePlaystoreDebug`