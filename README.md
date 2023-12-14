# Roman Numeral Kata
Convert from numeral numbers to Roman numerals and convert in the other direction

## Main Features
* Convert a numeral number into the Roman numeral format

* Convert also in the other way from the roman numeral format to the number.

## Example
| Number       | Roman        |
|--------------|--------------|
| 3            | III |
| 33 | XXXIII |
| 2021 | MMXXI |
| 3999 | MMMCMXCIX |

# How To use
build the project usin mvn package

run the Main class

usage 

java Main arg1 arg2

arg1 = 'roman' for Roman conversion way or 'number' for conversion from a Roman numeral

arg2 = roman numeral or an integer value

**Example:**

java -cp target\kata-roman-numeral-1.0-SNAPSHOT.jar com.lcl.kata.Main number 78

78 -> LXXVIII

java -cp target\kata-roman-numeral-1.0-SNAPSHOT.jar com.lcl.kata.Main roman MMLII

MMLII -> 2052

# install
mvn clean install
# Test unit
mvn test

# Test Report 
a surefire report is generated 
