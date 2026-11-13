@echo off
setlocal
set SCRIPT_DIR=%~dp0
where mvn >NUL 2>&1
if errorlevel 1 (
  echo Maven is required but was not found in PATH.
  exit /b 1
)
call mvn %*
