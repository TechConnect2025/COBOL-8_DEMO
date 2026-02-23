@echo off
REM Build script for Java-ConVersion project (Windows)

echo ===================================
echo Building COBOL to Java 21 Conversion
echo ===================================

REM Check for Java
java -version >nul 2>&1
if errorlevel 1 (
    echo Error: Java not found. Please install Java 21 or later.
    exit /b 1
)

echo.

REM Check if Maven is available
where mvn >nul 2>&1
if %errorlevel% equ 0 (
    echo Using Maven build...
    call mvn clean compile
) else (
    echo Maven not found. Using javac directly...
    
    REM Create output directory
    if not exist "target\classes" mkdir target\classes
    
    REM Compile all Java files
    echo Compiling Java files...
    javac -d target\classes src\main\java\com\cobolconversion\*.java
)

if %errorlevel% equ 0 (
    echo.
    echo ===================================
    echo Build successful!
    echo ===================================
    echo.
    echo To run the programs:
    echo   java -cp target\classes com.cobolconversion.HelloWorld
    echo   java -cp target\classes com.cobolconversion.MyFirstCobol
    echo   java -cp target\classes com.cobolconversion.CsvConverter
    echo   java -cp target\classes com.cobolconversion.PlusOuMoins
    echo   java -cp target\classes com.cobolconversion.PlusOuMoins2
) else (
    echo.
    echo ===================================
    echo Build failed!
    echo ===================================
    exit /b 1
)
