#!/bin/bash

# Build script for Java-ConVersion project

echo "==================================="
echo "Building COBOL to Java 21 Conversion"
echo "==================================="

# Check for Java (Java 17+ required for records feature)
java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$java_version" -lt 17 ]; then
    echo "Error: Java 17 or later is required (Java 21 recommended)"
    echo "Current version: $java_version"
    exit 1
fi

echo "Java version: $(java -version 2>&1 | head -n 1)"
echo ""

# Check if Maven is available
if command -v mvn &> /dev/null; then
    echo "Using Maven build..."
    mvn clean compile
    build_status=$?
else
    echo "Maven not found. Using javac directly..."
    
    # Create output directory
    mkdir -p target/classes
    
    # Compile all Java files
    echo "Compiling Java files..."
    javac -d target/classes src/main/java/com/cobolconversion/*.java
    build_status=$?
fi

if [ $build_status -eq 0 ]; then
    echo ""
    echo "==================================="
    echo "Build successful!"
    echo "==================================="
    echo ""
    echo "To run the programs:"
    echo "  java -cp target/classes com.cobolconversion.HelloWorld"
    echo "  java -cp target/classes com.cobolconversion.MyFirstCobol"
    echo "  java -cp target/classes com.cobolconversion.CsvConverter"
    echo "  java -cp target/classes com.cobolconversion.PlusOuMoins"
    echo "  java -cp target/classes com.cobolconversion.PlusOuMoins2"
else
    echo ""
    echo "==================================="
    echo "Build failed!"
    echo "==================================="
    exit 1
fi
