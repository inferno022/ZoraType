# ZoraType Build Script with Full Output
Write-Host "🚀 Starting ZoraType Build with Full Gradle Output..." -ForegroundColor Green
Write-Host ("=" * 60) -ForegroundColor Cyan

# Clean first
Write-Host "`n🧹 Cleaning previous build..." -ForegroundColor Yellow
./gradlew clean

Write-Host "`n🏗️ Building ZoraType APK..." -ForegroundColor Yellow
Write-Host ("=" * 60) -ForegroundColor Cyan

# Run build and capture output
Write-Host "📋 Gradle Build Output:" -ForegroundColor Cyan
Write-Host ("=" * 60) -ForegroundColor Cyan

# Execute build with output
$buildResult = ./gradlew assemblePlaystoreDebug --console=plain 2>&1

# Display the output
$buildResult | ForEach-Object { Write-Host $_ }

# Check if build was successful by looking for APK
if (Test-Path "build/outputs/apk/playstore/debug/android-keyboard-master-playstore-debug.apk") {
    Write-Host "`n✅ BUILD SUCCESSFUL!" -ForegroundColor Green -BackgroundColor Black
    
    # Show APK details
    $apk = Get-Item "build/outputs/apk/playstore/debug/android-keyboard-master-playstore-debug.apk"
    Write-Host "`n📱 APK Generated:" -ForegroundColor Cyan
    Write-Host "   File: $($apk.Name)" -ForegroundColor White
    Write-Host "   Size: $([math]::Round($apk.Length / 1MB, 1)) MB" -ForegroundColor White
    Write-Host "   Path: $($apk.FullName)" -ForegroundColor White
} else {
    Write-Host "`n❌ BUILD FAILED!" -ForegroundColor Red -BackgroundColor Black
}

Write-Host "`n" + ("=" * 60) -ForegroundColor Cyan
Write-Host "🏁 Build process completed!" -ForegroundColor Green