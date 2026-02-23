# Quick Reference Guide

## Build Commands

### Using Maven
```bash
mvn clean compile          # Compile all Java files
mvn package               # Create JAR file
mvn exec:java -Dexec.mainClass="com.cobolconversion.HelloWorld"
```

### Using Build Scripts
```bash
./build.sh                # Mac/Linux
build.bat                 # Windows
```

### Using javac Directly
```bash
mkdir -p target/classes
javac -d target/classes src/main/java/com/cobolconversion/*.java
```

---

## Run Commands

### HelloWorld
```bash
java -cp target/classes com.cobolconversion.HelloWorld
```
**Output:**
```
Hello world!
test
```

### MyFirstCobol
```bash
java -cp target/classes com.cobolconversion.MyFirstCobol
```
**Interaction:**
```
          Hey !

          Quel est ton nom ? [Enter your name]
          
          Salut [Your Name]
.
```

### CsvConverter
```bash
# Default files (datain.txt → dataout.txt)
java -cp target/classes com.cobolconversion.CsvConverter

# Custom files
java -cp target/classes com.cobolconversion.CsvConverter input.csv output.txt
```

**Sample Input** (datain.txt):
```
Smith~John~123 Main Street~Springfield~IL~62701
Doe~Jane~456 Oak Avenue~Portland~OR~97201
```

### PlusOuMoins (Number Guessing Game)
```bash
java -cp target/classes com.cobolconversion.PlusOuMoins
```
**Gameplay:**
- Guess a number between 1 and 100
- Get feedback (too high/too low)
- Win message shows number of attempts

### PlusOuMoins2 (Enhanced Game)
```bash
java -cp target/classes com.cobolconversion.PlusOuMoins2
```
**Gameplay:**
- Same as PlusOuMoins
- Option to replay (O/N)
- Special message if you guess on first try

---

## Quick File Reference

| COBOL File | Java Class | Purpose |
|------------|------------|---------|
| helloworld.cbl | HelloWorld.java | Basic output |
| myfirstcobol.cbl | MyFirstCobol.java | Interactive greeting |
| csvconverter.cbl | CsvConverter.java | File processing |
| plusoumoins.cbl | PlusOuMoins.java | Guessing game |
| plusoumoins2.cbl | PlusOuMoins2.java | Game with replay |

---

## Common Tasks

### Clean Build
```bash
mvn clean                 # Maven
rm -rf target/            # Manual
```

### Rebuild Everything
```bash
./build.sh
```

### Create Sample CSV Data
```bash
cat > datain.txt << 'EOF'
Smith~John~123 Main St~Springfield~IL~62701
Doe~Jane~456 Oak Ave~Portland~OR~97201
EOF
```

### View Compilation Results
```bash
ls -la target/classes/com/cobolconversion/
```

---

## Troubleshooting

### "Error: release version 21 not supported"
- Your Java version is older than 21
- The code works with Java 17+
- Already configured for Java 17 in pom.xml

### Programs not found
```bash
# Make sure you're in the right directory
cd Java-ConVersion

# Rebuild
./build.sh
```

### CSV file not found
```bash
# Create sample input file
cat > datain.txt << 'EOF'
Smith~John~123 Main St~Springfield~IL~62701
EOF

# Then run converter
java -cp target/classes com.cobolconversion.CsvConverter
```

### Colors not showing in games
- Your terminal may not support ANSI codes
- The games will still work, just without colors
- Try a different terminal (iTerm2, Windows Terminal, etc.)

---

## File Locations

```
Java-ConVersion/
├── Source:    src/main/java/com/cobolconversion/
├── Compiled:  target/classes/com/cobolconversion/
├── Config:    pom.xml
└── Docs:      README.md, CONVERSION_SUMMARY.md
```

---

## One-Liners

```bash
# Compile and run HelloWorld
javac -d target/classes src/main/java/com/cobolconversion/HelloWorld.java && java -cp target/classes com.cobolconversion.HelloWorld

# Test CSV converter pipeline
echo "Test~Data~123 Main~City~ST~12345" > datain.txt && java -cp target/classes com.cobolconversion.CsvConverter && cat dataout.txt
```

---

## Dependencies

**Runtime:** None (pure Java SE)  
**Build:** Maven (optional)  
**JDK:** 17+ required

---

## Package Structure

```
com.cobolconversion
├── HelloWorld           (main)
├── MyFirstCobol         (main)
├── CsvConverter         (main)
│   └── AddressData      (record)
├── PlusOuMoins          (main)
└── PlusOuMoins2         (main)
```

Each class has a `main()` method and can be run independently.
