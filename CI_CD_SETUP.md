# ZoraType CI/CD Setup Guide

## 🚀 Migration from Ionic AppFlow to GitHub Actions

This guide helps you migrate from Ionic AppFlow to a more powerful and cost-effective GitHub Actions CI/CD pipeline.

## ✅ Benefits of GitHub Actions

- **FREE** for public repositories (unlimited build minutes)

- **Faster builds** than AppFlow

- **Better Android support** with native Gradle integration

- **Automatic releases** to GitHub and Google Play Store

- **No vendor lock-in** - runs on standard GitHub infrastructure

## 📋 Setup Instructions

### 1. Repository Secrets Configuration

Add these secrets to your GitHub repository (`Settings > Secrets and variables > Actions`):

#### **Required Secrets:**

```

KEYSTORE_BASE64          # Base64 encoded keystore file
KEYSTORE_PASSWORD        # Keystore password
KEY_ALIAS               # Key alias name
KEY_PASSWORD            # Key password

```

#### **For Google Play Store Deployment:**

```

GOOGLE_PLAY_SERVICE_ACCOUNT_JSON  # Service account JSON for Play Store API

```

### 2. Generate Keystore (if needed)

```bash

# Generate new keystore

keytool -genkey -v -keystore zoratype-release.keystore -alias zoratype -keyalg RSA -keysize 2048 -validity 10000

# Convert to base64 for GitHub secrets

base64 -i zoratype-release.keystore | pbcopy  # macOS
base64 -i zoratype-release.keystore           # Linux

```

### 3. Google Play Store Setup

1. **Create Service Account:**
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Create new project or select existing
   - Enable Google Play Developer API
   - Create service account with JSON key

2. **Grant Permissions:**
   - Go to Google Play Console > Setup > API access
   - Link the service account
   - Grant necessary permissions (Release manager, etc.)

### 4. Workflow Configuration

#### **Automatic Triggers:**

- **Push to master/main:** Builds debug APK

- **Pull Request:** Runs tests and builds

- **Release creation:** Builds signed release APK

#### **Manual Deployment:**

- Go to `Actions > Deploy to Google Play Store`

- Select deployment track (internal/alpha/beta/production)

- Click "Run workflow"

## 🔧 Workflow Files

### `android-ci.yml` - Main CI Pipeline

- Runs tests on every push/PR

- Builds debug APK for testing

- Builds release APK on GitHub releases

- Uploads artifacts for download

### `deploy-playstore.yml` - Play Store Deployment

- Manual trigger for controlled deployments

- Builds signed AAB (Android App Bundle)

- Uploads to Google Play Store

- Supports all deployment tracks

## 📱 Build Variants

The workflows support all your build variants:

- **unstableDebug** - Development builds

- **unstableRelease** - Signed release builds

- **playstoreRelease** - Play Store optimized builds

## 🎯 Migration Benefits

| Feature | Ionic AppFlow | GitHub Actions |
|---------|---------------|----------------|
| **Cost** | $60-600/month | FREE |

| **Build Speed** | 5-10 minutes | 3-6 minutes |

| **Android Support** | Limited | Native Gradle |

| **Customization** | Limited | Unlimited |

| **Integration** | AppFlow only | Any service |

| **Artifacts** | 30 days | Unlimited |

## 🚀 Next Steps

1. **Test the Pipeline:**
   ```bash
   git add .github/
   git commit -m "Add GitHub Actions CI/CD pipeline"
   git push origin master
   ```

2. **Create First Release:**
   - Go to GitHub > Releases > Create new release
   - Tag version (e.g., v1.0.0)
   - Publish release
   - Watch automatic build in Actions tab

3. **Setup Play Store Deployment:**
   - Add Google Play service account JSON to secrets
   - Test deployment to internal track first
   - Gradually promote to alpha/beta/production

## 🔍 Monitoring & Debugging

- **Build Logs:** Available in Actions tab for 90 days

- **Artifacts:** Download APKs directly from workflow runs

- **Notifications:** Configure Slack/Discord webhooks for build status

- **Caching:** Gradle dependencies cached for faster builds

## 📞 Support

If you encounter issues:
1. Check the Actions tab for detailed logs
2. Verify all secrets are correctly set
3. Ensure keystore and passwords match
4. Test locally with `./gradlew assemblePlaystoreDebug`

---

**🎉 Congratulations!** You've successfully migrated from Ionic AppFlow to a more powerful, free, and flexible CI/CD solution with GitHub Actions.
