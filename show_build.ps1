Write-Host "🚀 ZoraType Build with Full Output" -ForegroundColor Green
Write-Host "============================================" -ForegroundColor Cyan

Write-Host "`n🧹 Cleaning..." -ForegroundColor Yellow
./gradlew clean

Write-Host "`n🏗️ Building APK with full output..." -ForegroundColor Yellow
Write-Host "============================================" -ForegroundColor Cyan

./gradlew assemblePlaystoreDebug --console=plain

Write-Host "`n============================================" -ForegroundColor Cyan
if (Test-Path "build/outputs/apk/playstore/debug/android-keyboard-master-playstore-debug.apk") {
    Write-Host "✅ BUILD SUCCESSFUL!" -ForegroundColor Green
    $apk = Get-Item "build/outputs/apk/playstore/debug/android-keyboard-master-playstore-debug.apk"
    Write-Host "📱 APK: $([math]::Round($apk.Length / 1MB, 1)) MB" -ForegroundColor Cyan
} else {
    Write-Host "❌ BUILD FAILED!" -ForegroundColor Red
}