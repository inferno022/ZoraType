# Simulate CI Environment Locally
Write-Host "Simulating GitHub Actions CI Environment" -ForegroundColor Green

# Backup current AAR files
Write-Host "Backing up current AAR files..." -ForegroundColor Yellow
if (Test-Path "libs/mozc-release.aar") { Copy-Item "libs/mozc-release.aar" "libs/mozc-release.aar.backup" }
if (Test-Path "libs/vad-release.aar") { Copy-Item "libs/vad-release.aar" "libs/vad-release.aar.backup" }

# Create minimal AAR files like CI does
Write-Host "Creating minimal AAR files (like CI)..." -ForegroundColor Yellow
if (!(Test-Path "libs")) { New-Item -ItemType Directory -Path "libs" }

# Create tiny stub AAR files (like CI environment)
"PK" | Out-File -FilePath "libs/mozc-release.aar" -Encoding ASCII -NoNewline
"PK" | Out-File -FilePath "libs/vad-release.aar" -Encoding ASCII -NoNewline

Write-Host "AAR file sizes (simulating CI):" -ForegroundColor Cyan
Get-ChildItem "libs/*.aar" | ForEach-Object { 
    Write-Host "  $($_.Name): $($_.Length) bytes" -ForegroundColor White
}

# Test compilation with stub environment
Write-Host "Testing compilation with CI-like environment..." -ForegroundColor Yellow
$output = ./gradlew compilePlaystoreDebugKotlin --info --stacktrace 2>&1 | Out-String

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation succeeded in simulated CI environment!" -ForegroundColor Green
} else {
    Write-Host "Compilation failed - analyzing errors..." -ForegroundColor Red
    $output -split "`n" | Where-Object { $_ -match "error:|Error|Exception|Unresolved" } | ForEach-Object {
        Write-Host "  $_" -ForegroundColor Yellow
    }
}

# Restore real AAR files
Write-Host "Restoring real AAR files..." -ForegroundColor Yellow
if (Test-Path "libs/mozc-release.aar.backup") { 
    Move-Item "libs/mozc-release.aar.backup" "libs/mozc-release.aar" -Force
}
if (Test-Path "libs/vad-release.aar.backup") { 
    Move-Item "libs/vad-release.aar.backup" "libs/vad-release.aar" -Force
}