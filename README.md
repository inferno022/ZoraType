# ZoraText - Advanced Android Keyboard

## System-Wide Font Customization

ZoraText provides system-wide font customization capabilities across Android applications without requiring root access. The keyboard integrates with manufacturer-specific theme engines and accessibility services to enable font modifications in messaging, social media, and system applications.

![ZoraText Logo](logo.png)

---

## Core Features

### Font Management System
- System-wide font application across Android applications
- Integration with OEM theme engines (Samsung, Xiaomi, Huawei, OPPO, Vivo, OnePlus)
- Accessibility service overlay for universal device compatibility
- Google Fonts API integration with 1000+ font options
- Custom font file import functionality

### Theme Engine
- Professional gradient themes with customizable color schemes
- Material Design 3 compliance
- Dynamic theme adaptation based on system preferences
- Custom theme creation and import capabilities

### Typography Features
- Font preview system with real-time rendering
- Professional font curation for system compatibility
- Font package management with compression optimization
- Typeface rendering with proper kerning and spacing

---

## Technical Architecture

### Font Application Methods

#### OEM Integration
- **Samsung Galaxy**: Samsung Theme Engine API integration
- **Xiaomi/MIUI**: MIUI Theme System compatibility
- **Huawei/Honor**: EMUI Theme System support
- **OPPO/Realme**: ColorOS Theme System integration
- **Vivo/iQOO**: FuntouchOS Theme compatibility
- **OnePlus**: OxygenOS Theme System support

#### Universal Compatibility
- **Accessibility Service**: System-wide font overlay for Android 8.0+
- **Reflection APIs**: Direct system property access where available
- **Automatic Detection**: Device-specific method selection

### System Requirements
- Android 8.0+ (API level 26+)
- 80MB storage space (optimized from 200MB through asset compression)
- Internet connection for Google Fonts downloads
- Accessibility service permission for universal font overlay

---

## Device Compatibility

### Supported Manufacturers
| Manufacturer | Integration Method | Font Application Scope |
|--------------|-------------------|------------------------|
| Samsung Galaxy | Theme Engine | System-wide |
| Xiaomi/Redmi | MIUI Theme System | System-wide |
| Huawei/Honor | EMUI Theme System | System-wide |
| OPPO/Realme | ColorOS Theme System | System-wide |
| Vivo/iQOO | FuntouchOS Theme | System-wide |
| OnePlus | OxygenOS Theme System | System-wide |
| Generic Android 8.0+ | Accessibility Service | Application overlay |

---

## Font Collection

### Included Fonts
- **Roboto Mono**: Monospace typeface for technical applications
- **Playfair Display**: High-contrast serif with distinctive letterforms
- **Quicksand**: Geometric sans-serif with rounded characteristics
- **Lobster**: Script typeface with calligraphic elements
- **Creepster**: Display font with decorative styling
- **BBH Bartle**: Custom display typeface

### Google Fonts Integration
- Direct API access to Google Fonts library
- Curated selection of system-compatible typefaces
- Automatic font optimization for mobile rendering
- Progressive download with caching

---

## Installation and Setup

### Basic Installation
1. Download and install the APK package
2. Enable ZoraText in system keyboard settings
3. Configure accessibility permissions if using generic Android device
4. Access font settings through the application interface

### Font Application Process
1. Navigate to Settings → System Fonts
2. Select desired font from available options or download from Google Fonts
3. Apply font selection (method varies by device manufacturer)
4. Font changes take effect immediately in supported applications

---

## Application Size Optimization

### Size Comparison
- **ZoraText**: 80MB (optimized from 200MB)
- **Standard keyboards**: ~33MB average
- **Size justification**: Advanced font engine, theme system, and comprehensive font library

### Optimization Techniques
- Asset compression and optimization
- Font subset generation for reduced file sizes
- Progressive font loading
- Efficient resource management
- Modular architecture for selective feature loading

---

## Technical Specifications

### Core Components
- **NonRootFontChanger**: Multi-method font application system
- **FontOverlayAccessibilityService**: Universal accessibility-based font overlay
- **SystemFontInstaller**: Font management interface
- **GoogleFontsService**: Google Fonts API integration
- **FontPackageManager**: Font asset management

### Permissions Required
- `INTERNET`: Google Fonts downloads
- `SYSTEM_ALERT_WINDOW`: Font overlay functionality
- `BIND_ACCESSIBILITY_SERVICE`: Universal font application
- `BIND_INPUT_METHOD`: Keyboard functionality

---

## Documentation

- **[System Fonts Guide](SYSTEM_FONTS_GUIDE.md)**: Comprehensive font system documentation
- **[Implementation Details](FONTS_IMPLEMENTATION_COMPLETE.md)**: Technical implementation reference
- **[Release Notes](RELEASE_NOTES_V2.0.0.md)**: Version 2.0.0 feature documentation

---

## Support and Contact

- **Email**: mail.zoratext@gmail.com
- **Issues**: GitHub Issues tracker
- **Documentation**: Comprehensive guides included with application

---

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

---

*ZoraText provides advanced typography capabilities for Android devices through innovative font management technology.*