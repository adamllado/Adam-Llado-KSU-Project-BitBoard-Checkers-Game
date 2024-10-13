/* Name: Adam Llado
 * Class: CS 3505
 * Teacher: Professor Regan
 * Due Date: Oct 13th 2024
 */

 /** AdamLladoBoard class that defines how the checkersBoard is stored and converted to an array. Provides functionality
  *  for checking possible Pawn movements, ensuring legal Pawn and King moves, checking win conditions, and 
  *  coverting methods to convert and display the bitboards in binary or hex. 
  *
  * @author Adam Llado
  *
  */

public class AdamLladoBoard {
    /****************************************/
    /** Define variables for the bitboards **/
    /****************************************/

    // Defines the bitboards used in the constructor as 32 bit integers
    private int whitePawns;
    private int blackPawns;
    private int whiteKings;
    private int blackKings;
    
    /*****************************************************************************************************/
    /** Define the 2D Array for Mapping from Bitboards to an Array to Visually Represent the Game State **/
    /*****************************************************************************************************/

    /** Defines a 64 bit 2D array.
     * Will be used to map bits from the bitboards to their respective placement on the array board
     * 
     */
    private static final int[][] checkersSquareMap = {
        {-1, 0, -1, 1, -1, 2, -1, 3},
        {4, -1, 5, -1, 6, -1, 7, -1},
        {-1, 8, -1, 9, -1, 10, -1, 11},
        {12, -1, 13, -1, 14, -1, 15, -1},
        {-1, 16, -1, 17, -1, 18, -1, 19},
        {20, -1, 21, -1, 22, -1, 23, -1},
        {-1, 24, -1, 25, -1, 26, -1, 27},
        {28, -1, 29, -1, 30, -1, 31, -1}
    };

    /** Defines a constructor that holds and initializes the bitboards.
     * White pieces start on rows 6-8
     * Black pieces start on rows 1-3
     * White & Black Kings will initialize to 0
     * 
     */
    public AdamLladoBoard() {
        whitePawns = 0x00000FFF;     
        blackPawns = 0xFFF00000;      
        whiteKings = 0x0;               
        blackKings = 0x0;               
    }

    /********************************************************************************/
    /** Method that uses checkersSquareMap and BitBoards to Display the Game State **/
    /********************************************************************************/

    /** Defines a char[][] method that converts the bitboards to a 2D array.
     * Uses checkbits for each board to determine if that bit is on at that square index
     * For Example: checkersSquareMap = 12 which is row 3 col 0. We check if bit 12 is on for each bitboard
     * A dot will be placed wherever the 2D array = -1, representing out of bounds
     * 
     * @return the bitboards into a char[][] 2D array used to print the game state.
     */
    public char[][] bitboardTo2DArray() {
        int whitePawnsCheckBit;
        int blackPawnsCheckBit;
        int whiteKingsCheckBit;
        int blackKingsCheckBit;
        char[][] board = new char[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = '.';
                int squarePosition = checkersSquareMap[row][col];
                if (squarePosition != -1) {
                    int bit = (1 << squarePosition);
                    int indexBit = Utility.checkBit(bit, squarePosition);
                    whitePawnsCheckBit = Utility.checkBit(whitePawns, squarePosition);
                    blackPawnsCheckBit = Utility.checkBit(blackPawns, squarePosition);
                    whiteKingsCheckBit = Utility.checkBit(whiteKings, squarePosition);
                    blackKingsCheckBit = Utility.checkBit(blackKings, squarePosition);
                    if ((whiteKings != 0 && whiteKingsCheckBit == indexBit)){
                        board[row][col] = 'K'; 
                    }else if ((whitePawnsCheckBit == indexBit && row != 7)) {
                        board[row][col] = 'W'; 
                    } 
                    if ((blackKings != 0 && blackKingsCheckBit == indexBit)){
                        board[row][col] = 'k';
                    }else if ((blackPawnsCheckBit == indexBit && row != 0)) {
                        board[row][col] = 'B'; 
                    }   
                }
            }
        }
        return board;
        }

    /** Defines a search algorithm used to determine the char at the startRow and startCol.
     * 
     * @param startRow    Represents the starting row of the piece
     * @param startCol    Represents the starting col of the piece
     * 
     * @return the char at that starting position used to determine valid moves.
     */
    public char bitBoardSearch(int startRow, int startCol) {
        char[][] board = bitboardTo2DArray();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                }
            }
            return board[startRow][startCol];
        }

    /** Defines a boolean checking algorithm used to determine if the current player
     *  is jumping over their own pieces.
     * 
     * @param startRow    Represents the starting row of the piece
     * @param startCol    Represents the starting col of the piece
     * @param endRow      Represents the ending row of the piece
     * @param endCol      Represents the ending col of the piece
     * @param player      Represents the char at the starting position
     * @param isWhiteTurn Determines the current player's turn (White/Black)
     * 
     * @return true if the player is trying to jump over their own pieces and false otherwise.
     */
    public boolean sameColorJumpCheck(int startRow, int startCol, int endRow, int endCol, char player, boolean isWhiteTurn){
        // isWhiteKing and isBlackKing used in conditional statements to dinstingish between pawn jumps and king jumps
        char isWhiteKing = bitBoardSearch(startRow, startCol);
        char isBlackKing = bitBoardSearch(startRow, startCol);

        // Int variables used in bitBoardSearch to check the upper and lower movement spots from starting position
        int lowerRightJumpedRow = startRow + 1;
        int lowerRightJumpedCol = startCol + 1;
        int lowerLeftJumpedRow = startRow + 1;
        int lowerLeftJumpedCol = startCol - 1;
        int upperLeftJumpedRow = startRow - 1;
        int upperLeftJumpedCol = startCol - 1;
        int upperRightJumpedRow = startRow - 1;
        int upperRightJumpedCol = startCol + 1;

    /***************************************************************************************************/
    /** Algorithm uses bitBoardSearch to Check if the Jumped Square char = the Starting Position char **/
    /***************************************************************************************************/

        // We Ensure we are not searching for out of bounds squares by checking the edges 
        // and using the right checks if we are on the edges so as not to check for negative
        // or out of bounds values.
        if (isWhiteTurn && isWhiteKing != 'K'){
            if (startCol == 0){
                if (bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) == player){
                    return true;
                }
            }else if (startCol == 7){
                if (bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) == player){
                    return true;
                }
            }else{
                if(startCol < endCol){
                    if (bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) == player){
                        return true;
                    } 
                }else if(startCol > endCol){
                    if (bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) == player){
                        return true;
                    }
                }
            }
        }else if(!isWhiteTurn && isBlackKing != 'k'){
            if (startCol == 0){
                if (bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) == player){
                    return true;
                }
            }else if (startCol == 7){
                if (bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) == player){
                    return true;
                }
            }else{
                if(startCol < endCol) {
                    if (bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) == player){
                        return true;
                    }
                }else if(startCol > endCol){
                    if (bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) == player){
                        return true;
                        }
                    }
            }
        }else if(isBlackKing == 'K' || isWhiteKing == 'k'){
            if (startCol == 0){
                if (bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) == player){
                    return true;
                }else if (bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) == player){
                    return true;
                }
            }else if (startCol == 7){
                if (bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) == player){
                    return true;
                }else if (bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) == player){
                    return true;
                }
            }else if (startRow == 0){
                if (bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) == player){
                    return true;
                }else if (bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) == player){
                    return true;
                }
            }else if (startRow == 7){
                if (bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) == player){
                    return true;
                }else if (bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) == player){
                    return true;
                }
            }else{
                if (bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) == player){
                    return true;
                }else if (bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) == player){
                    return true;
                }else if (bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) == player){
                    return true;
                }else if (bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) == player){
                    return true;
                }
            }
        }
        return false;
            
    }

    /******************************************************************/
    /** Methods used to Determine a Double Jump for All Piece Types  **/
    /******************************************************************/
    
    /** Defines a boolean checking algorithm used to determine if a double jump
     *  to the lower right is possible for white pawns.
     * Checks the lower right ending position from starting positon and the jumped position
     * to determine if the lower right ending position is empty and the jumped position 
     * is not empty or the current player's pieces.
     * Ensures the rows and columns are not out bounds before using bitBoardSearch.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * 
     * @return true if the double jump is possible and false otherwise
     */
    public boolean isWhitePawnDoubleJumpRight(int row, int col, char player){
        int lowerRightRow = row + 2;
        int LowerRightCol = col + 2;
        int lowerRightJumpedRow = row + 1;
        int lowerRightJumpedCol = col + 1;
        if (lowerRightRow >= 0 && lowerRightRow < 8 && LowerRightCol >= 0 && LowerRightCol < 8){
            char dot = '.';
            if (bitBoardSearch(lowerRightRow, LowerRightCol) == dot) {
                if (bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) != dot) {
                    // If/if else statements to check the jumped square depending on 
                    // the current player being a black king, white king, or a white pawn
                    if (player == 'k' && bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) != 'B' 
                        && bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) != 'k'){
                            return true;
                    }else if (player == 'K' && bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) != 'W'
                        && bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) != 'K'){
                            return true;
                    }else if (player == 'W' && bitBoardSearch(lowerRightJumpedRow, lowerRightJumpedCol) != player){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Defines a boolean checking algorithm used to determine if a double jump
     *  to the lower left is possible for white pawns.
     * Checks the lower left ending position from starting positon and the jumped position
     * to determine if the lower left ending position is empty and the jumped position 
     * is not empty or the current player's pieces.
     * Ensures the rows and columns are not out bounds before using bitBoardSearch.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * 
     * @return true if the double jump is possible and false otherwise
     */
    public boolean isWhitePawnDoubleJumpLeft(int row, int col, char player){
        int lowerLeftRow = row + 2;
        int LowerLeftCol = col - 2;
        int lowerLeftJumpedRow = row + 1;
        int lowerLeftJumpedCol = col - 1;
        if (lowerLeftRow >= 0 && lowerLeftRow < 8 && LowerLeftCol >= 0 && LowerLeftCol < 8){
            char dot = '.';
            if (bitBoardSearch(lowerLeftRow, LowerLeftCol) == dot) {
                if (bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) != dot) {
                    // If/if else statements to check the jumped square depending on 
                    // the current player being a black king, white king, or a white pawn
                    if (player == 'k' && bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) != 'B' 
                        && bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) != 'k'){
                            return true;
                    }else if (player == 'K' && bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) != 'W'  
                        && bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) != 'K'){
                            return true;
                    }else if (player == 'W' && bitBoardSearch(lowerLeftJumpedRow, lowerLeftJumpedCol) != player){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Defines a boolean checking algorithm used to determine if a double jump
     *  is possible for white pawns when they are not at column 0 or column 7.
     * Reuses both the isWhitePawnDoubleJumpLeft and isWhitePawnDoubleJumpRight methods.
     * Ensures the rows and columns are not out bounds before using bitBoardSearch.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * 
     * @return true if isWhitePawnDoubleJumpLeft or isWhitePawnDoubleJumpRight methods return true and false otherwise
     */
    public boolean isWhitePawnDoubleJumpMiddle(int row, int col, char player){
        boolean isTrue = true;
        boolean doubleJumpLeft = isWhitePawnDoubleJumpLeft(row, col, player);
        boolean doubleJumpRight = isWhitePawnDoubleJumpRight(row, col, player);
        if (isTrue == doubleJumpLeft || isTrue == doubleJumpRight){
            return true;
        }
        return false;
    }

    /** Defines a boolean checking algorithm used to determine if a double jump
     *  to the upper right is possible for black pawns.
     * Checks the upper right ending position from starting positon and the jumped position
     * to determine if the upper right ending position is empty and the jumped position 
     * is not empty or the current player's pieces.
     * Ensures the rows and columns are not out bounds before using bitBoardSearch.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * 
     * @return true if the double jump is possible and false otherwise
     */
    public boolean isBlackPawnDoubleJumpRight(int row, int col, char player){
        int UpperRightRow = row - 2;
        int UpperRightCol = col + 2;
        int upperRightJumpedRow = row - 1;
        int upperRightJumpedCol = col + 1;
        if (UpperRightRow >= 0 && UpperRightRow < 8 && UpperRightCol >= 0 && UpperRightCol < 8){
            char dot = '.';
            if (bitBoardSearch(UpperRightRow, UpperRightCol) == dot) {
                if (bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) != dot) {
                    // If/if else statements to check the jumped square depending on 
                    // the current player being a black king, white king, or a black pawn
                    if (player == 'k' && bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) != 'B'  
                        && bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) != 'k'){
                            return true;
                    }else if (player == 'K' && bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) != 'W' 
                        && bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) != 'K'){
                            return true;
                    }else if (player == 'B' && bitBoardSearch(upperRightJumpedRow, upperRightJumpedCol) != player){
                        return true;
                    }
                }
            }
            
        }
        return false;
    }

    /** Defines a boolean checking algorithm used to determine if a double jump
     *  to the upper left is possible for black pawns.
     * Checks the upper left ending position from starting positon and the jumped position
     * to determine if the upper left ending position is empty and the jumped position 
     * is not empty or the current player's pieces.
     * Ensures the rows and columns are not out bounds before using bitBoardSearch.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * 
     * @return true if the double jump is possible and false otherwise
     */
    public boolean isBlackPawnDoubleJumpLeft(int row, int col, char player){
        int UpperLeftRow = row - 2;
        int UpperLeftCol = col - 2;
        int upperLeftJumpedRow = row - 1;
        int upperLeftJumpedCol = col - 1;
        if (UpperLeftRow >= 0 && UpperLeftRow < 8 && UpperLeftCol >= 0 && UpperLeftCol < 8){
            char dot = '.';
            if (bitBoardSearch(UpperLeftRow, UpperLeftCol) == dot) {
                if (bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) != dot) {
                    // If/if else statements to check the jumped square depending on 
                    // the current player being a black king, white king, or a black pawn
                    if (player == 'k' && bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) != 'B'
                        && bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) != 'k'){
                            return true;
                    }else if (player == 'K' && bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) != 'W'
                        && bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) != 'K'){
                            return true;
                    }
                    if (player == 'B' && bitBoardSearch(upperLeftJumpedRow, upperLeftJumpedCol) != player){
                        return true;
                    }
                }
            }
            
        }
        return false;
    }

    /** Defines a boolean checking algorithm used to determine if a double jump
     *  is possible for black pawns when they are not at column 0 or column 7.
     * Reuses both the isBlackPawnDoubleJumpLeft and isBlackPawnDoubleJumpRight methods.
     * Ensures the rows and columns are not out bounds before using bitBoardSearch.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * 
     * @return true if isBlackPawnDoubleJumpLeft or isBlackPawnDoubleJumpRight methods return true and false otherwise
     */
    public boolean isBlackPawnDoubleJumpMiddle(int row, int col, char player){
        boolean isTrue = true;
        boolean doubleJumpLeft = isBlackPawnDoubleJumpLeft(row, col, player);
        boolean doubleJumpRight = isBlackPawnDoubleJumpRight(row, col, player);
        if (isTrue == doubleJumpLeft || isTrue == doubleJumpRight){
            return true;
        }
        return false;
    }

    /** Defines a boolean checking algorithm used to determine if a double jump
     *  is possible for black pawns when they are not at column 0 or column 7.
     * Reuses both the isBlackPawnDoubleJumpLeft and isBlackPawnDoubleJumpRight methods.
     * Ensures the rows and columns are not out bounds before using bitBoardSearch.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * 
     * @return true if isBlackPawnDoubleJumpLeft or isBlackPawnDoubleJumpRight methods return true and false otherwise
     */
    public boolean isKingDoubleJump(int row, int col, char player, AdamLladoBoard board){
        boolean isTrue = true;
        if (row == 0){
            boolean whiteDoubleJumpRight = board.isWhitePawnDoubleJumpRight(row, col, player);
            boolean blackDoubleJumpRight = board.isBlackPawnDoubleJumpRight(row, col, player);
            if (isTrue == whiteDoubleJumpRight || isTrue == blackDoubleJumpRight){
                return true;
            }
        }else if (row == 7){
            boolean whiteDoubleJumpLeft = board.isWhitePawnDoubleJumpLeft(row, col, player);
            boolean blackDoubleJumpLeft = board.isBlackPawnDoubleJumpLeft(row, col, player);
            if (isTrue == whiteDoubleJumpLeft || isTrue == blackDoubleJumpLeft){
                return true;
            }
        }else if (row != 0 || row != 7){
            boolean whiteDoubleJumpMiddle = board.isWhitePawnDoubleJumpMiddle(row, col, player);
            boolean blackDoubleJumpMiddle = board.isBlackPawnDoubleJumpMiddle(row, col, player);
            if (isTrue == whiteDoubleJumpMiddle || isTrue == blackDoubleJumpMiddle){
                return true;
            }
        }
        return false;
    }

    /****************************************************************/
    /**  Methods used to print the 2D array board to the Terminal  **/
    /****************************************************************/

    /** Defines a void for loop algorithm used to print the converted 2D array board
     *  to a viewable representation on the terminal. 
     * Shows the Binary formats of the bitboards
     * Allows the user to visually see the board to make moves
     * Keeps track of the number of pieces on the board
     * Uses the Utility algorithm binaryString to convert the bitboards to a String that represents them in binary.
     * 
     * @param whiteCheckersLeft   Represents the # of white checkers on the board
     * @param blackCheckersLeft   Represents the # of black checkers on the board
     * 
     */
    public void printBoardBinary(int whiteCheckersLeft, int blackCheckersLeft) {
        char[][] board = bitboardTo2DArray();
        System.out.println("  0 1 2 3 4 5 6 7");
        System.out.print(" +----------------+");
        System.out.println(" Checkers Pieces Left:");
        for (int row = 0; row < 8; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < 8; col++) {
                System.out.print(board[row][col] + " ");
                if (row == 0 && col == 7){
                    System.out.print("|");
                    System.out.println(" White pieces left: " + whiteCheckersLeft);
                }else if (row == 1 && col == 7){
                    System.out.print("|");
                    System.out.println(" Black pieces left: " + blackCheckersLeft);
                }else if (row == 3 && col == 7){
                    System.out.print("|");
                    System.out.print(" Binary BitBoards:");
                    System.out.println(       );
                }else if (row == 4 && col == 7){
                    System.out.print("|");
                    System.out.println(" White Pawns = " + Utility.binaryString(whitePawns));
                }else if (row == 5 && col == 7){
                    System.out.print("|");
                    System.out.println(" Black Pawns = " + Utility.binaryString(blackPawns));
                }else if (row == 6 && col == 7){
                    System.out.print("|");
                    System.out.println(" White Kings = " + Utility.binaryString(whiteKings));
                }else if (row == 7 && col == 7){
                    System.out.print("|");
                    System.out.println(" Black Kings = " + Utility.binaryString(blackKings));
                }
            }
            if (row == 2){
                System.out.println("|");
            }
        }
        System.out.println(" +----------------+");
    }

    /** Defines a void for loop algorithm used to print the converted 2D array board
     *  to a viewable representation on the terminal.
     * Shows the Hex formats of the bitboards 
     * Allows the user to visually see the board to make moves
     * Keeps track of the number of pieces on the board
     * Uses the Utility algorithm HexString to convert the bitboards to a String that represents them in hexadecimal.
     * 
     * @param whiteCheckersLeft   Represents the # of white checkers on the board
     * @param blackCheckersLeft   Represents the # of black checkers on the board
     * 
     */
    public void printBoardHex(int whiteCheckersLeft, int blackCheckersLeft) {
        char[][] board = bitboardTo2DArray();
        System.out.println("  0 1 2 3 4 5 6 7");
        System.out.print(" +----------------+");
        System.out.println(" Checkers Pieces Left:");
        for (int row = 0; row < 8; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < 8; col++) {
                System.out.print(board[row][col] + " ");
                if (row == 0 && col == 7){
                    System.out.print("|");
                    System.out.println(" White pieces left: " + whiteCheckersLeft);
                }else if (row == 1 && col == 7){
                    System.out.print("|");
                    System.out.println(" Black pieces left: " + blackCheckersLeft);
                }else if (row == 3 && col == 7){
                    System.out.print("|");
                    System.out.print(" Hex BitBoards:");
                    System.out.println(       );
                }else if (row == 4 && col == 7){
                    System.out.print("|");
                    System.out.println(" White Pawns = " + Utility.HexString(whitePawns));
                }else if (row == 5 && col == 7){
                    System.out.print("|");
                    System.out.println(" Black Pawns = " + Utility.HexString(blackPawns));
                }else if (row == 6 && col == 7){
                    System.out.print("|");
                    System.out.println(" White Kings = " + Utility.HexString(whiteKings));
                }else if (row == 7 && col == 7){
                    System.out.print("|");
                    System.out.println(" Black Kings = " + Utility.HexString(blackKings));
                }
            }
            if (row == 2){
                System.out.println("|");
            }
        }
        System.out.println(" +----------------+");
    }

    /** Defines an int[] array searching algorithm to determine the piece
     *  at the capture Square or the square the player is going to jump over.
     * Method used in conditional statements to ensure players are not jumping 
     * over empty spaces.
     * 
     * @param captureSquare  Represents the position being jumped over
     * 
     * @return a 2 item sized int[] array that converts the captureSquare from the squareMapping array 
     * to its specific coordinates.
     */
    public int[] getBitboardCoordinates(int captureSquare) {
        int row = 0;
        int col = 0;
        int[] coordinates = new int[2];
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            for (row = 0; row < 8; row++) {
                for (col = 0; col < 8; col++) {
                    if (captureSquare == checkersSquareMap[row][col]){
                        coordinates[0] = row;
                        coordinates[1] = col;
                    }
                    }
                }
        }
        return coordinates;
    }

    /** Defines an int array searching algorithm that takes the row and column
     *  of the current position and determines the square that piece is on from
     *  the Square Mapping 2D array.
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * 
     * @return an int number that is used by variables in other methods to initalize the start or endSquares
     * and flip or check bits as they act like exponents when flipping or checking bits.
     */
    public int getBitboardIndex(int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            return checkersSquareMap[row][col];
        }
        return -1;
    }

    /** Defines a boolean algorithm that maintains movement integrity and moves
     *  a piece when the move is valid.
     * 
     * @param startRow    Represents the starting row of the piece
     * @param startCol    Represents the starting col of the piece
     * @param endRow      Represents the ending row of the piece
     * @param endCol      Represents the ending col of the piece
     * @param player      Represents the char at the starting position
     * @param isWhiteTurn Determines the current player's turn (White/Black)
     * 
     * @return true if movement integrity is maintained and false otherwise.
     *
     */
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol, boolean isWhite, char player) {
        // isWhiteKing and isBlackKing used in conditional statements to dinstingish between pawn jumps and king jumps
        char isWhiteKing = bitBoardSearch(startRow, startCol);
        char isBlackKing = bitBoardSearch(startRow, startCol);

        // Variables to define the starting, ending, and jumped squares that check for movement integrity
        // and move the pieces to the correct squares. 
        int startSquare = getBitboardIndex(startRow, startCol);
        int endSquare = getBitboardIndex(endRow, endCol);
        int captureSquare = (Utility.binaryAdd(startSquare, endSquare)) / 2;
        // The captureSquare must add 1 for even rows as the SquareMapping array will choose the wrong captureSquare otherwise
        if(startRow == 2 || startRow == 4 || startRow == 0 || startRow == 6){
            captureSquare += 1;
        }
        int whitePawnsShiftSet = 0;

        // Varibales used to ensure players do not jump empty squares
        int[] bitBoardCoordinates = getBitboardCoordinates(captureSquare);
        int coordinateRow = bitBoardCoordinates[0];
        int coordinateCol = bitBoardCoordinates[1];

        // Conditional statement to ensure players do not move off the board
        if (startSquare == -1 || endSquare == -1) {
            System.out.println("Invalid move: out of bounds.");
            return false;
        }

        // Variables used to compare the bitboards to and determine if there is actually a piece at that position 
        // on the 2D array.
        // For Example: startSquare is 12, we set the bit at 2^12 for the startBit 
        //              endSquare is 17, we set the bit at 2^17 for the endBit
        //              We use these variables to compare with the bitboards 
        //              And determine what pieces are where on the bitboards
        int startBit = Utility.flipBit(0, startSquare);
        int endBit = Utility.setBit(0, endSquare);

        /*********************************************************/
        /**  Conditional Statements to Check Movement Integrity **/
        /*********************************************************/

        // Determines if there is a piece at the square and if it corresponds to the correct player turn
        if (isWhite) {
            if ((whitePawns & startBit) == 0 && (whiteKings & startBit) == 0) {
                System.out.println("Invalid move: no white piece at this Square.");
                return false;
            }
        } else {
            if ((blackPawns & startBit) == 0 && (blackKings & startBit) == 0) {
                System.out.println("Invalid move: no black piece at this Square.");
                return false;
            }
        }

        // Checks that the destination square is empty using bitBoardSearch and checking
        // if the value of that square is a dot
        if (bitBoardSearch(endRow, endCol) != '.') {
            System.out.println("Invalid move: destination is not empty.");
            return false;
        }

        // Determines if the player attempts to move backward with pawns 
        // For White Pawns, this is true if the startBit is less than the endBit
        // For Black Pawns, this is true if the endBit is less than the startBit
        if (isWhite && isWhiteKing != 'K') {
            if(Utility.binarySubtract(whitePawns, startBit) < Utility.binarySubtract(whitePawns, endBit)){
                System.out.println("Invalid move: White Pawns cannot move backwards.");
                return false;
            }
        }else if(!isWhite && isBlackKing != 'k'){
            if(Utility.binarySubtract(blackPawns, endBit) < Utility.binarySubtract(blackPawns, startBit) && startSquare != 31){
                System.out.println("Invalid move: Black Pawns cannot move backwards.");
                return false;
            }
        }
        
        // Determines if the player attempts to move in a straight line
        // when the row or column is the same from starting to ending position
        if(startCol == endCol || startRow == startCol){
            System.out.println("Invalid move: You cannot mave in a straight line.");
            return false;
        }
        
        // Determines if the player is trying to jump empty spaces when they're not making adjacent moves
        // and the jumped square = a dot
        // Checking the difference of the start and end squares to the difference of the numbers on the squareMapping
        // array when making adjacent moves shows that we should exclude moves with a difference of 3,4, or 5
        if(Math.abs(Utility.binarySubtract(startSquare, endSquare)) != 3 && Math.abs(Utility.binarySubtract(startSquare, endSquare)) != 4
                        && Math.abs(Utility.binarySubtract(startSquare, endSquare)) != 5 && bitBoardSearch(coordinateRow, coordinateCol) == '.'){
            System.out.println("Invalid move: You cannot jump empty spaces.");
            return false;
        }

        // Determines if the current player is trying to jump their own pieces
        if(sameColorJumpCheck(startRow, startCol, startCol, endCol, player, isWhite)){
            System.out.println("Invalid move: You cannot jump your pieces.");
            return false;
        }
        
        // Determines if the user is trying to jump multiple squares by seeing if the 
        // difference between the start and end squares is greater than 9 (which should be the highest possible move)
        if(Math.abs(Utility.binarySubtract(startSquare, endSquare)) > 9){
            System.out.println("Invalid move: You cannot jump that far.");
            return false;
        }

        /***************************/
        /** Logic to Move Pieces  **/
        /***************************/

        // First Determine what turn it is (Black/White) and then check 
        // if the whiteKings has a bit at that position
        // if not, we then manipulate the Pawn bitboards by simply clearing 
        // and setting a bit at the corresponding square in squareMapping
        // using the squareMapping as the exponent for binary to determine 
        // which bit to clear and set.
        if (isWhite) {
            if ((Utility.binaryAND(whiteKings, startBit)) != 0) {
                whiteKings = Utility.clearBit(whiteKings, startSquare);
                whiteKings = Utility.setBit(whiteKings, endSquare);   
            } else {
                whitePawns = Utility.clearBit(whitePawns, startSquare);
                // 3 lines of code used as a more complex way to set bits to the new square position
                // using binaryMult. We use another int variable called whitePawnsShiftSet to set a 
                // 1 at 0, and then shift the number using binaryMult to the endSquare the user
                // specified. we finally Or whitePawns and whitePawnsShiftSet together.
                whitePawnsShiftSet = Utility.setBit(whitePawnsShiftSet, 0);
                whitePawnsShiftSet = Utility.binaryMult(whitePawnsShiftSet, endSquare);
                whitePawns = Utility.binaryORed(whitePawnsShiftSet, whitePawns);
                // Check if a white pawn made it to row 7, if so, promote it to a white king
                if (endRow == 7) {
                    whitePawns = Utility.clearBit(whitePawns, startSquare);
                    whiteKings = Utility.setBit(whiteKings, endSquare); 
                }
            }
        } else {
            if ((Utility.binaryAND(blackKings, startBit)) != 0) {
                blackKings = Utility.clearBit(blackKings, startSquare);
                blackKings = Utility.setBit(blackKings, endSquare); 
            } else {
                blackPawns = Utility.clearBit(blackPawns, startSquare);
                blackPawns = Utility.setBit(blackPawns, endSquare); 
                // Check if a black pawn made it to row 7, if so, promote it to a black king
                if (endRow == 0) {
                    blackPawns = Utility.clearBit(blackPawns, startSquare);
                    blackKings = Utility.setBit(blackKings, endSquare); 
                }
            }
        }

        return true;
    }

    /************************************************************/
    /** Method used to check Double Jumps for each Piece Type  **/
    /************************************************************/

    /** Defines a boolean checking algorithm that determines if a double jump is 
     * possible for all piece types after an initial capture has been made.
     * Uses the isPawnDoubleJump methods for each piece type
     * 
     * @param row    Represents the starting row of the piece
     * @param col    Represents the starting col of the piece
     * @param player Represents the char at the starting position
     * @param isWhiteTurn Determines the current player's turn (White/Black)
     * @param board  Represents the current gamestate of the bitboards
     * 
     * @return true if a double jump is possible and false otherwise
     *
     */
    public boolean canDoubleJump(int row, int col, char player, boolean isWhiteTurn, AdamLladoBoard board){
        char isWhiteKing = bitBoardSearch(row, col);
        char isBlackKing = bitBoardSearch(row, col);
        boolean isTrue = true;
        if (isWhiteTurn && isWhiteKing != 'K'){
            if (col == 0){
                boolean whiteDoubleJumpRight = board.isWhitePawnDoubleJumpRight(row, col, player);
                if (isTrue == whiteDoubleJumpRight){
                    return true;
                }
            }else if (col == 7){
                boolean whiteDoubleJumpLeft = board.isWhitePawnDoubleJumpLeft(row, col, player);
                if (isTrue == whiteDoubleJumpLeft){
                    return true;
                }
            }else{
                boolean whiteDoubleJumpMiddle = board.isWhitePawnDoubleJumpMiddle(row, col, player);
                if (isTrue == whiteDoubleJumpMiddle){
                    return true;
                }
            }
        }
            else if(!isWhiteTurn && isBlackKing != 'k'){
                if (col == 0){
                    boolean blackDoubleJumpRight = board.isBlackPawnDoubleJumpRight(row, col, player);
                if (isTrue == blackDoubleJumpRight){
                    return true;
                }
                }else if (col == 7){
                    boolean blackDoubleJumpLeft = board.isBlackPawnDoubleJumpLeft(row, col, player);
                    if (isTrue == blackDoubleJumpLeft){
                        return true;
                    }
                }else{
                    boolean blackDoubleJumpMiddle = board.isBlackPawnDoubleJumpMiddle(row, col, player);
                    if (isTrue == blackDoubleJumpMiddle){
                        return true;
                    }
                }
            }else if(isBlackKing == 'K' || isWhiteKing == 'k'){
                boolean kingDoubleJump = board.isKingDoubleJump(row, col, player, board);
                if (isTrue == kingDoubleJump){
                    return true;
                }
            }
        return false;
    }

    /************************************************************/
    /** Method used to check Double Jumps for each Piece Type  **/
    /************************************************************/

    /** Defines a void algorithm used to capture the piece that has 
     *  been jumped over
     * 
     * @param startRow    Represents the starting row of the piece
     * @param startCol    Represents the starting col of the piece
     * @param endRow      Represents the ending row of the piece
     * @param endCol      Represents the ending col of the piece
     * @param player      Represents the char at the starting position
     * @param isWhiteTurn Determines the current player's turn (White/Black)
     * 
     */
    public void capturePiece(int startRow, int startCol, int endRow, int endCol, boolean isWhite) {
        // Variables to determine the char at the start, capture, and end squares
        int startSquare = getBitboardIndex(startRow, startCol);
        int endSquare = getBitboardIndex(endRow, endCol);
        // captureSquare is determined by adding the startSquare and endSquare
        // And then shifting all bits to the right by using binaryDiv
        int captureSquare = (Utility.binaryAdd(startSquare, endSquare)); 
        captureSquare = Utility.binaryDiv(captureSquare, 1);

        // The captureSquare must add 1 for even rows as the SquareMapping array will choose the wrong captureSquare otherwise
        if(startRow == 2 || startRow == 4 || startRow == 0 || startRow == 6){
            captureSquare += 1;
        }

        // Clear bits by using the Utility clearBit method and changing the bit that corresponds to the captureSquare
        if (isWhite) {
            blackPawns = Utility.clearBit(blackPawns, captureSquare); 
            blackKings = Utility.clearBit(blackKings, captureSquare);
        } else {
            whitePawns = Utility.clearBit(whitePawns, captureSquare);
            whiteKings = Utility.clearBit(whiteKings, captureSquare);
        }
    }

    /************************************************/
    /** String Method to Determine Who Won and How **/
    /************************************************/
     
     /** Defines Who won the game and How by checking the number of Checkers Left
      *  And if there are any possible move conditions left for both sides
      * 
      * @return  A String that tells the user who and how they won the game 
      */
     public static void winner(int whiteCheckersLeft, int blackCheckersLeft) {
         
         //Determine if one side has lost all pieces
         if(whiteCheckersLeft == 0)
             System.out.println("Black has won by taking White's pieces!");
             
         if(blackCheckersLeft == 0)
            System.out.println("White has won by taking Black's pieces!");
     }
}
