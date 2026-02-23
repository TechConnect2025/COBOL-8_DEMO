# COBOL to Java 21 Conversion

This project contains Java conversions of COBOL programs from the original COBOL-8 repository, utilizing modern Java features.

## Overview

This conversion modernizes classic COBOL applications using modern Java features including:
- **Records** (Java 16+, replacing traditional data classes)
- **Pattern matching** and modern switch expressions
- **Text blocks** for improved readability
- **Enhanced try-with-resources** for automatic resource management
- **Modern I/O with NIO.2** (Files, Paths)

**Note:** While this project targets Java 21, it is compatible with Java 17+ due to the features used.

## Project Structure

```
Java-ConVersion/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── cobolconversion/
│                   ├── HelloWorld.java           (helloworld.cbl)
│                   ├── MyFirstCobol.java         (myfirstcobol.cbl)
│                   ├── CsvConverter.java         (csvconverter.cbl)
│                   ├── PlusOuMoins.java          (plusoumoins.cbl)
│                   └── PlusOuMoins2.java         (plusoumoins2.cbl)
├── pom.xml
└── README.md
```

## Programs

### 1. HelloWorld
**Original:** `helloworld.cbl`

Simple demonstration program that displays "Hello world!" and "test".

**Run:**
```bash
java -cp target/classes com.cobolconversion.HelloWorld
```

### 2. MyFirstCobol
**Original:** `myfirstcobol.cbl`  
**Author:** Michael Pirali

Interactive program that prompts for a user's name and displays a personalized greeting in French.

**Run:**
```bash
java -cp target/classes com.cobolconversion.MyFirstCobol
```

### 3. CsvConverter
**Original:** `csvconverter.cbl`  
**Author:** Queen of Cobol

Reads a CSV file with tilde (~) delimiters and converts it to a formatted fixed-width output file.

**Format:**
- Input: `lastName~firstName~streetAddress~city~state~zip`
- Output: Fixed-width formatted fields

**Run:**
```bash
# Using default files (datain.txt -> dataout.txt)
java -cp target/classes com.cobolconversion.CsvConverter

# Specify input and output files
java -cp target/classes com.cobolconversion.CsvConverter input.txt output.txt
```

**Sample Input File** (`datain.txt`):
```
Smith~John~123 Main Street~Springfield~IL~62701
Doe~Jane~456 Oak Avenue~Portland~OR~97201
Johnson~Robert~789 Pine Road~Seattle~WA~98101
```

### 4. PlusOuMoins
**Original:** `plusoumoins.cbl`  
**Author:** Michael Pirali

Number guessing game where the player tries to guess a random number between 1 and 100. Provides feedback on whether each guess is too high or too low.

**Run:**
```bash
java -cp target/classes com.cobolconversion.PlusOuMoins
```

### 5. PlusOuMoins2
**Original:** `plusoumoins2.cbl`  
**Author:** Michael Pirali

Enhanced version of the guessing game with:
- Replay functionality
- Special message for guessing on the first try
- Attempt counter

**Run:**
```bash
java -cp target/classes com.cobolconversion.PlusOuMoins2
```

## Building the Project

### Prerequisites
- **Java 17 or later** (Java 21 recommended)
- **Maven 3.6+** (optional, for Maven builds)

### Using Maven

```bash
# Compile the project
mvn clean compile

# Run a specific program
mvn exec:java -Dexec.mainClass="com.cobolconversion.HelloWorld"

# Package as JAR
mvn package
```

### Using Java CLI (without Maven)

```bash
# Create directories
mkdir -p target/classes

# Compile all Java files
javac -d target/classes src/main/java/com/cobolconversion/*.java

# Run any program
java -cp target/classes com.cobolconversion.HelloWorld
java -cp target/classes com.cobolconversion.MyFirstCobol
java -cp target/classes com.cobolconversion.CsvConverter
java -cp target/classes com.cobolconversion.PlusOuMoins
java -cp target/classes com.cobolconversion.PlusOuMoins2
```

## Conversion Notes

### Key Differences from COBOL

1. **Data Types:**
   - COBOL PIC clauses → Java primitive types and Strings
   - COBOL records → Java records (Java 21 feature)

2. **I/O Operations:**
   - COBOL file operations → Java NIO.2 (Files, Paths, BufferedReader/Writer)
   - COBOL DISPLAY/ACCEPT → Java System.out/Scanner

3. **Screen Handling:**
   - COBOL SCREEN SECTION → ANSI escape codes for colors
   - COBOL positioning → Manual spacing/formatting

4. **Control Flow:**
   - COBOL PERFORM UNTIL → Java while loops
   - COBOL GO TO → Structured loops and methods

5. **Random Numbers:**
   - COBOL FUNCTION RANDOM → Java Random class

### Java Features Used

- **Records (Java 16+):** Used in CsvConverter for data representation
- **Try-with-resources:** Automatic resource management for files and scanners
- **Text formatting:** String.format() and formatted output
- **Modern I/O:** NIO.2 APIs (java.nio.file.*)
- **Enhanced switch expressions:** (where applicable)

This code is compatible with Java 17+ but is optimized for Java 21.

## Testing

Each program can be tested individually. For the CsvConverter, create a sample `datain.txt` file with tilde-delimited data.

### Example Test for CsvConverter

```bash
# Create sample input
cat > datain.txt << EOF
Smith~John~123 Main Street~Springfield~IL~62701
Doe~Jane~456 Oak Avenue~Portland~OR~97201
EOF

# Run converter
java -cp target/classes com.cobolconversion.CsvConverter

# View output
cat dataout.txt
```

## Maintaining COBOL Behavior

The conversions maintain the original COBOL program behavior including:
- Input validation (REQUIRED fields)
- Screen formatting and positioning
- Color coding (where supported via ANSI codes)
- File format compatibility
- Game logic and rules

## License

Converted from original COBOL programs. See original source files for attribution.

## Original Repository

Based on COBOL-8 by mainframe-projects
- Original: [mainframe-projects/COBOL-8](https://github.com/mainframe-projects/COBOL-8)
- Conversion Date: February 2026
