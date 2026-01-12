@echo off
echo.
echo ========================================
echo    ZoraType Build - Full Output
echo ========================================
echo.

echo Cleaning previous build...
call gradlew.bat clean
echo.

echo Starting APK build with full output...
echo ========================================
call gradlew.bat assemblePlaystoreDebug --console=plain --info

echo.
echo ========================================
if exist "build\outputs\apk\playstore\debug\android-keyboard-master-playstore-debug.apk" (
    echo BUILD SUCCESSFUL!
    echo APK generated successfully
    for %%A in ("build\outputs\apk\playstore\debug\android-keyboard-master-playstore-debug.apk") do echo APK Size: %%~zA bytes
) else (
    echo BUILD FAILED!
)
echo ========================================
pause