# ZoraType v0.1.0 - Initial Release

## Overview

First working release of ZoraType - an advanced Android keyboard with system-wide font customization capabilities.

## Features

### Core Keyboard Functionality
- Full Android keyboard implementation
- Multi-language support
- Gesture typing support
- Voice input integration
- Emoji and symbol support

### Font System (Beta)
- Basic font selection interface
- Theme-based font picker UI
- Font preview functionality
- Custom font import (file manager integration)
- Google Fonts API integration (experimental)

### Theme System
- Professional gradient themes:
  - Cyber Violet
  - Modern Mint  
  - Cherry Glaze
  - Ivory Turquoise
- Material Design 3 compliance
- Dynamic theme adaptation

### ZoraType Branding
- Complete rebrand from original FUTO keyboard
- ZoraType logo integration
- Professional UI design
- Donation-focused payment system

## Technical Details

### Build Information
- **Version**: 0.1.0
- **Version Code**: 1
- **Target SDK**: 35 (Android 14)
- **Min SDK**: 24 (Android 7.0)
- **APK Size**: ~180MB

### Architecture
- Native Android application
- Kotlin + Compose UI
- CMake for native components
- Gradle build system

### Compatibility
- Android 7.0+ devices
- ARM64 and ARM32 architectures
- Works on all major Android OEMs

## Known Limitations

### Font System
- System-wide font changing is experimental
- May require accessibility permissions
- Limited OEM compatibility in this version
- Root access recommended for full functionality

### Performance
- Large APK size due to included fonts and assets
- Initial load time may be longer
- Memory usage higher than standard keyboards

## Installation

1. Download the APK from GitHub releases
2. Enable "Install from unknown sources" in Android settings
3. Install the APK
4. Go to Settings > System > Languages & input > Virtual keyboard
5. Enable ZoraType
6. Set ZoraType as default keyboard

## Next Steps (v0.2.0)

- Improve font system reliability
- Reduce APK size
- Add more font options
- Enhance OEM compatibility
- Performance optimizations
- Bug fixes based on user feedback

## Support

- GitHub Issues: Report bugs and feature requests
- Email: mail.zoratype@gmail.com
- Documentation: See project README

---

**Status**: ✅ INITIAL RELEASE - BASIC FUNCTIONALITY WORKING

This is the first release focusing on core functionality and establishing the foundation for advanced font features in future versions.