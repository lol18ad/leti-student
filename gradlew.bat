@echo off
setlocal
set ROOT_DIR=%~dp0
set DIST=%ROOT_DIR%.gradle-bootstrap\gradle-8.9\bin\gradle.bat
if exist "%DIST%" goto run
if not exist "%ROOT_DIR%.gradle-bootstrap" mkdir "%ROOT_DIR%.gradle-bootstrap"
set ARCHIVE=%ROOT_DIR%.gradle-bootstrap\gradle-8.9-bin.zip
if not exist "%ARCHIVE%" powershell -NoProfile -ExecutionPolicy Bypass -Command "Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.9-bin.zip' -OutFile '%ARCHIVE%'"
powershell -NoProfile -ExecutionPolicy Bypass -Command "Expand-Archive -Force '%ARCHIVE%' '%ROOT_DIR%.gradle-bootstrap'"
:run
call "%DIST%" %*
endlocal
