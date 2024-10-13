

/* Name: Adam Llado
 * Class: CS 3505
 * Teacher: Professor Regan
 * Due Date: Oct 13th 2024
 */

 /** Utility class that provides bit manipulation methods to AdamLladoBoard using bitwise operators.
  * There are methods to check bits, set bits, clear bits, and to convert and represent the bitboards
  * in hexadecimal and binary.
  *
  * @author Adam Llado
  *
  */

public class Utility {


//############################################//
//##########     INT Methods      ############//
//############################################//

    /** Defines a static int method used to flip a bit at a specific position.
     * This is done by using the bitwise operator XOR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the flipped bit at the specified position
     * 
     */
    public static int flipBit(int num, int position)
    {
        return num ^= (1 << position);
    }

    /** Defines a static int method used to set a bit at a specific position.
     * This is done by using the bitwise operator OR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the set bit at the specified position
     * 
     */
    public static int setBit(int num, int position)
    {
        return num |= (1 << position);
    }

    /** Defines a static int method used to OR two bitboards and determine where 
     *  each number has a bit set.
     * 
     * @param num1  Represents the first bitboard or number being manipulated
     * @param num2  Represents the second bitboard or number being manipulated
     * 
     * @return a number or bitboard with the combination of all bits from num1 & num2
     * 
     */
    public static int binaryORed(int num1, int num2)
    {
        return num1 |= num2;
    }

    /** Defines a static int method used to AND two bitboards and determine where 
     *  both numbers have a bit set.
     * 
     * @param num1  Represents the first bitboard or number being manipulated
     * @param num2  Represents the second bitboard or number being manipulated
     * 
     * @return a number or bitboard where the bits are set for both num1 & num2
     * 
     */
    public static int binaryAND(int num1, int num2)
    {
        return num1 &= num2;
    }

    /** Defines a static int method used to clear a bit at a specific position.
     * This is done by using the bitwise operators & and ~ and flipping the bit
     * off at the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the cleared bit at the specified position
     * 
     */
    public static int clearBit(int num, int position)
    {
        return num &= ~(1 << position);

    }

    /** Defines a static int method used to check a bit at a specific position.
     * This is done by using the bitwise operator & and shifting a varied 
     * number of positions to check if that bit is set or not.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a 1 or 0 to determine if the bit is set or not at the specified position
     * 
     */
    public static int checkBit(int num, int position)
    {
        return num = (num >> position) & 1;

    }

    /** Defines a static String method used convert a bitboard to a string.
     * Iterate through the 32 bit binary number using a for loop 
     * and parse and add each bit to the string.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a 1 or 0 to determine if the bit is set or not at the specified position
     * 
     */
    public static String binaryString(int num)
    {
        String binaryString = "";

        for (int i = 31; i >= 0; i--) { 
            binaryString += Integer.toString((num >> i)&1);
            } 
        return binaryString; 
    }

    /** Defines a static String method used convert a bitboard to a hex string.
     * we use the format method to specify the format of our number where X defines 
     * that the following number will be in hex We then check the length of the string,
     * and if it is less than 8, we will add leading zeros to ensure a proper 8 bit
     * format in accordance with the 32 bit logic.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a hex string of the specified bitboard.
     * 
     */
    public static String HexString(int num)
    {
        String hexString = String.format("%X", num);
        int hexLength = hexString.length();
        if(hexLength < 8){
            for (int i = 7; i >= 0; i--) {
                hexString = "0" + hexString;
                hexLength = hexString.length();
                if(hexLength == 8){
                    break;
                }
            }
        }
        return hexString;
    }

    /** Defines a static int method used to add two numbers or bitboards together.
     * This is done using the bitwise operators XOR and &.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber 
     * 
     * @return the sum of the numbers or bitboards.
     * 
     */
    public static int binaryAdd(int num1, int num2)
    {
        if (num2 == 0){
            return num1;
        }
        return binaryAdd(num1^num2, (num1&num2) << 1);
    }

    /** Defines a static int method used to subtract two numbers or bitboards together.
     * This is done by using the bitwise operator XOR and ~.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber
     * 
     * @return a difference of the two numbers or bitboards.
     * 
     */
    public static int binarySubtract(int num1, int num2)
    {
        if (num2 == 0){
            return num1;
        }
        return binarySubtract(num1^num2, ((~num1)&num2) << 1);
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary multiplication by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the left by that many places, acting as binary
     * multiplication by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the product of the two numbers or bitboards.
     * 
     */
    public static int binaryMult(int num, int position) 
    { 
        return num << position;
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary division by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the right by that many places, acting as binary
     * divison by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the quotient of the two numbers or bitboards.
     * 
     */
    public static int binaryDiv(int num, int position) 
    { 
        return num >> position;
    }

//#############################################//
//##########     LONG Methods      ############//
//#############################################//

    /** Defines a static long method used to flip a bit at a specific position.
     * This is done by using the bitwise operator XOR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the flipped bit at the specified position
     * 
     */
    public static long flipBit(long num, long position)
    {
        return (num ^= (1 << position));
    }
    
    /** Defines a static long method used to set a bit at a specific position.
     * This is done by using the bitwise operator OR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the set bit at the specified position
     * 
     */
    public static long setBit(long num, long position)
    {
        return num |= (1 << position);
    }

    /** Defines a static long method used to OR two bitboards and determine where 
     *  each number has a bit set.
     * 
     * @param num1  Represents the first bitboard or number being manipulated
     * @param num2  Represents the second bitboard or number being manipulated
     * 
     * @return a number or bitboard with the combination of all bits from num1 & num2
     * 
     */
    public static long binaryORed(long num1, long num2)
    {
        return num1 |= num2;
    }

    /** Defines a static long method used to AND two bitboards and determine where 
     *  both numbers have a bit set
     * 
     * @param num1  Represents the first bitboard or number being manipulated
     * @param num2  Represents the second bitboard or number being manipulated
     * 
     * @return a number or bitboard where the bits are set for both num1 & num2
     * 
     */
    public static long binaryAND(long num1, long num2)
    {
        return num1 &= num2;
    }

    /** Defines a static long method used to clear a bit at a specific position.
     * This is done by using the bitwise operators & and ~ and flipping the bit
     * off at the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the cleared bit at the specified position
     * 
     */
    public static long clearBit(long num, long position)
    {
        return num &= ~(1 << position);
    }

    /** Defines a static long method used to check a bit at a specific position.
     * This is done by using the bitwise operator & and shifting a varied 
     * number of positions to check if that bit is set or not.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a 1 or 0 to determine if the bit is set or not at the specified position
     * 
     */
    public static long checkBit(long num, long position)
    {
        return num = (num << position) & 1;
    }

    /** Defines a static String method used convert a bitboard to a string.
     * Iterate through the 32 bit binary number using a for loop 
     * and parse and add each bit to the string.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a 1 or 0 to determine if the bit is set or not at the specified position
     * 
     */
    public static String binaryString(long num)
    {
        String binaryString = "";

        for (long i = 63; i >= 0; i--) { 
            binaryString += Long.toString((num >> i)&1);
            } 
        return binaryString; 
    }

    /** Defines a static String method used convert a bitboard to a hex string.
     * we use the format method to specify the format of our number where X defines 
     * that the following number will be in hex We then check the length of the string,
     * and if it is less than 8, we will add leading zeros to ensure a proper 8 bit
     * format in accordance with the 32 bit logic.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a hex string of the specified bitboard.
     * 
     */
    public static String HexString(long num)
    {
        String hexString = String.format("%X", num);
        int hexLength = hexString.length();
        if(hexLength < 16){
            for (int i = 15; i >= 0; i--) {
                hexString = "0" + hexString;
                hexLength = hexString.length();
                if(hexLength == 8){
                    break;
                }
            }
        }
        return hexString;
    }

    /** Defines a static long method used to add two numbers or bitboards together.
     * This is done using the bitwise operators XOR and &.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber 
     * 
     * @return the sum of the numbers or bitboards.
     * 
     */
    public static long binaryAdd(long num1, long num2)
    {
        if (num2 == 0){
            return num1;
        }
        return binaryAdd(num1^num2, (num1&num2) << 1);
    }

    /** Defines a static long method used to subtract two numbers or bitboards together.
     * This is done by using the bitwise operator XOR and ~.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber
     * 
     * @return a difference of the two numbers or bitboards.
     * 
     */
    public static long binarySubtract(long num1, long num2)
    {
        if (num2 == 0){
            return num1;
        }
        return binarySubtract(num1^num2, ((~num1)&num2) << 1);
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary multiplication by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the left by that many places, acting as binary
     * multiplication by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the product of the two numbers or bitboards.
     * 
     */
    public static long binaryMult(long num, long position) 
    { 
        return num << position;
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary division by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the right by that many places, acting as binary
     * divison by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the quotient of the two numbers or bitboards.
     * 
     */
    public static long binaryDiv(long num, long position) 
    { 
        return num >> position;
    }

//#############################################//
//##########     Short Methods      ###########//
//#############################################//

    /** Defines a static short method used to flip a bit at a specific position.
     * This is done by using the bitwise operator XOR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the flipped bit at the specified position
     * 
     */
    private static short flipBit(short num, short position)
    {
        return (num ^= (1 << position));
    }

    /** Defines a static short method used to set a bit at a specific position.
     * This is done by using the bitwise operator OR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the set bit at the specified position
     * 
     */
    private static short setBit(short num, short position)
    {
        return num |= (1 << position);
    }

    /** Defines a static short method used to OR two bitboards and determine where 
     *  each number has a bit set.
     * 
     * @param num1  Represents the first bitboard or number being manipulated
     * @param num2  Represents the second bitboard or number being manipulated
     * 
     * @return a number or bitboard with the combination of all bits from num1 & num2
     * 
     */
    public static short binaryORed(short num1, short num2)
    {
        return num1 |= num2;
    }

    /** Defines a static short method used to clear a bit at a specific position.
     * This is done by using the bitwise operators & and ~ and flipping the bit
     * off at the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the cleared bit at the specified position
     * 
     */
    private static short clearBit(short num, short position)
    {
        return num &= ~(1 << position);
    }


    /** Defines a static short method used to check a bit at a specific position.
     * This is done by using the bitwise operator & and shifting a varied 
     * number of positions to check if that bit is set or not.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a 1 or 0 to determine if the bit is set or not at the specified position
     * 
     */
    private static short checkBit(short num, short position)
    {
        return num = (short) ((num << position) & 1);
    }

    /** Defines a static String method used convert a bitboard to a string.
     * Iterate through the 32 bit binary number using a for loop 
     * and parse and add each bit to the string.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a 1 or 0 to determine if the bit is set or not at the specified position
     * 
     */
    private static String binaryString(short num)
    {
        String binaryString = "";

        for (short i = 15; i >= 0; i--) { 
            binaryString += Short.toString((short) ((num >> i)&1));
            } 
        return binaryString; 
    }

    /** Defines a static String method used convert a bitboard to a hex string.
     * we use the format method to specify the format of our number where X defines 
     * that the following number will be in hex We then check the length of the string,
     * and if it is less than 8, we will add leading zeros to ensure a proper 8 bit
     * format in accordance with the 32 bit logic.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a hex string of the specified bitboard.
     * 
     */
    public static String HexString(short num)
    {
        String hexString = String.format("%X", num);
        int hexLength = hexString.length();
        if(hexLength < 4){
            for (int i = 3; i >= 0; i--) {
                hexString = "0" + hexString;
                hexLength = hexString.length();
                if(hexLength == 8){
                    break;
                }
            }
        }
        return hexString;
    }

    /** Defines a static short method used to add two numbers or bitboards together.
     * This is done using the bitwise operators XOR and &.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber 
     * 
     * @return the sum of the numbers or bitboards.
     * 
     */
    private static short binaryAdd(short num1, short num2)
    {
        if (num2 == 0){
            return num1;
        }
        return (short) binaryAdd(num1^num2, (num1&num2) << 1);
    }

    /** Defines a static short method used to subtract two numbers or bitboards together.
     * This is done by using the bitwise operator XOR and ~.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber
     * 
     * @return a difference of the two numbers or bitboards.
     * 
     */
    private static short binarySubtract(short num1, short num2)
    {
        if (num2 == 0){
            return num1;
        }
        return (short) binarySubtract(num1^num2, ((~num1)&num2) << 1);
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary multiplication by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the left by that many places, acting as binary
     * multiplication by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the product of the two numbers or bitboards.
     * 
     */
    public static short binaryMult(short num, short position) 
    { 
        return (short) (num << position);
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary division by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the right by that many places, acting as binary
     * divison by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the quotient of the two numbers or bitboards.
     * 
     */
    public static short binaryDiv(short num, short position) 
    { 
        return (short) (num >> position);
    }

//############################################//
//##########     Byte Methods      ###########//
//############################################//

    /** Defines a static byte method used to flip a bit at a specific position.
     * This is done by using the bitwise operator XOR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the flipped bit at the specified position
     * 
     */
    private static byte flipBit(byte num, byte position)
    {
        return num ^= (1 << position);
    }

    /** Defines a static byte method used to set a bit at a specific position.
     * This is done by using the bitwise operator OR and shifting a 1
     * into the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the set bit at the specified position
     * 
     */
    private static byte setBit(byte num, byte position)
    {
        return num |= (1 << position);
    }

    /** Defines a static byte method used to OR two bitboards and determine where 
     *  each number has a bit set.
     * 
     * @param num1  Represents the first bitboard or number being manipulated
     * @param num2  Represents the second bitboard or number being manipulated
     * 
     * @return a number or bitboard with the combination of all bits from num1 & num2
     * 
     */
    public static byte binaryORed(byte num1, byte num2)
    {
        return num1 |= num2;
    }

    /** Defines a static byte method used to clear a bit at a specific position.
     * This is done by using the bitwise operators & and ~ and flipping the bit
     * off at the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the cleared bit at the specified position
     * 
     */
    private static byte clearBit(byte num, byte position)
    {
        return num &= ~(1 << position);

    }

    /** Defines a static byte method used to clear a bit at a specific position.
     * This is done by using the bitwise operators & and ~ and flipping the bit
     * off at the specified position.
     * 
     * @param num      Represents the bitboard or number being manipulated
     * @param position Represents the bit position to be manipulated
     * 
     * @return a number or bitboard with the cleared bit at the specified position
     * 
     */
    private static byte checkBit(byte num, byte position)
    {
        return num = (byte) ((num << position) & 1);

    }

    /** Defines a static String method used convert a bitboard to a string.
     * Iterate through the 32 bit binary number using a for loop 
     * and parse and add each bit to the string.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a 1 or 0 to determine if the bit is set or not at the specified position
     * 
     */
    private static String binaryString(byte num)
    {
        String binaryString = "";

        for (byte i = 7; i >= 0; i--) { 
            binaryString += Byte.toString((byte) ((num >> i)&1));
            } 
        return binaryString; 
    }

    /** Defines a static String method used convert a bitboard to a hex string.
     * we use the format method to specify the format of our number where X defines 
     * that the following number will be in hex We then check the length of the string,
     * and if it is less than 8, we will add leading zeros to ensure a proper 8 bit
     * format in accordance with the 32 bit logic.
     * 
     * @param num   Represents the bitboard or nummber 
     * 
     * @return a hex string of the specified bitboard.
     * 
     */
    public static String HexString(byte num)
    {
        String hexString = String.format("%X", num);
        int hexLength = hexString.length();
        if(hexLength < 2){
            for (int i = 1; i >= 0; i--) {
                hexString = "0" + hexString;
                hexLength = hexString.length();
                if(hexLength == 8){
                    break;
                }
            }
        }
        return hexString;
    }

    /** Defines a static byte method used to add two numbers or bitboards together.
     * This is done using the bitwise operators XOR and &.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber 
     * 
     * @return the sum of the numbers or bitboards.
     * 
     */
    private static byte binaryAdd(byte num1, byte num2)
    {
        if (num2 == 0){
            return num1;
        }
        return (byte) binaryAdd(num1^num2, (num1&num2) << 1);
    }

    /** Defines a static byte method used to subtract two numbers or bitboards together.
     * This is done by using the bitwise operator XOR and ~.
     * 
     * @param num1   Represents the first bitboard or nummber 
     * @param num2   Represents the second bitboard or nummber
     * 
     * @return a difference of the two numbers or bitboards.
     * 
     */
    private static byte binarySubtract(byte num1, byte num2)
    {
        if (num2 == 0){
            return num1;
        }
        return (byte) binarySubtract(num1^num2, ((~num1)&num2) << 1);
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary multiplication by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the left by that many places, acting as binary
     * multiplication by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the product of the two numbers or bitboards.
     * 
     */
    public static byte binaryMult(byte num, byte position) 
    { 
        return (byte) (num << position);
    }

    /** Defines a static int method used to shift all the bits of 
     * a number using binary division by 2^n. the function
     * asks for a number and a position, and the function will shift
     * all bits to the right by that many places, acting as binary
     * divison by 2^n.
     * 
     * @param num1   Represents the first number or bitboard
     * @param num2   Represents the second number or bitboard
     * 
     * @return the quotient of the two numbers or bitboards.
     * 
     */
    public static byte binaryDiv(byte num, byte position) 
    { 
        return (byte) (num >> position);
    }
}

