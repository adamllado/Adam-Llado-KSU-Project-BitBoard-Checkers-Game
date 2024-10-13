import java.util.Scanner;

/* Name: Adam Llado
 * Class: CS 3505
 * Teacher: Professor Regan
 * Due Date: Oct 13th 2024
 */

/** AdamLladoGame class that defines how the AdamLladoBoard class is implemented and how 
    *   how the game state is managed using a while loop. There is a try/catch to ensure the 
    *   game does not crash with any user input, and it uses parsing methods to take user 
    *   movement input to call our movement and movement integrity methods with.
    *   It keeps track of turns, and allows users to toggle between binary and hex bitboard
    *   representations with new game and exit game features.
    * 
    *
    * @author Adam Llado
    *
    */

public class AdamLladoGame {

    private AdamLladoBoard board;
    private Scanner scanner;
    private int whiteCheckersLeft;
    private int blackCheckersLeft;

    // Constructor to initialize the bitboards and the Scanner for user input
    public AdamLladoGame() {
        board = new AdamLladoBoard();
        scanner = new Scanner(System.in);
    }

    /** Main game Loop that takes user input and calls methods from AdamLladoBoard.
    *
    */
    public void startGame() {
        System.out.println("Welcome to Terminal Checkers!");

        // Variable to keep track of the player turn (Black/White)
        boolean isWhiteTurn = true;
        
        // Variable used to continue the current player turn if a double jump is possible
        boolean isDoubleJump = false;

        // Variable to print the hex board representation as long as it's true
        boolean hexBitBoards = false;

        // Variables to initialize the starting number of pawns for each side
        whiteCheckersLeft = 12;
        blackCheckersLeft = 12;

        // Print the initial board with the binary representation
        board.printBoardBinary(whiteCheckersLeft, blackCheckersLeft);

        /*********************/
        /**  Main Game Loop **/
        /*********************/
        
        
        while (true) {
            if (hexBitBoards == true) {
                System.out.println((isWhiteTurn ? "White" : "Black") + "'s turn. Enter move as 'startRow startCol endRow endCol' (eg. 2 1 3 0)" 
                                            + "\nType: 'exit' to quit | 'binary' to display binary formats | 'new' to start new game");
            }else {
                System.out.println((isWhiteTurn ? "White" : "Black") + "'s turn. Enter move as 'startRow startCol endRow endCol' (eg. 2 1 3 0)" 
                + "\nType: 'exit' to quit | 'hex' to display hex formats | 'new' to start new game");
            }
      
            // Takes user input and trims leading and trailing space if necessary
            String input = scanner.nextLine().trim();
            
        /**************************************************************/
        /** Conditional Statements to Handle Different Game Features **/
        /**************************************************************/

            // If the user types 'exit', quit the game
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            // If the user types 'hex', convert hexBitBoards to true and print out the hex representation 
            // as long as it is true
            if (input.equalsIgnoreCase("hex")) {
                hexBitBoards = true;
                board.printBoardHex(whiteCheckersLeft, blackCheckersLeft);
            }

            // If the user types 'binary', convert hexBitBoards back to false and print out the binary representation 
            if (input.equalsIgnoreCase("binary")) {
                hexBitBoards = false;
                board.printBoardBinary(whiteCheckersLeft, blackCheckersLeft);
            }

            // If the user types 'new', restart the game using the AdamLladoGame Constructor and the startGame loop
            // Reset the checkers pieces
            if (input.equalsIgnoreCase("new")) {
                AdamLladoGame game = new AdamLladoGame();
                game.startGame();
                whiteCheckersLeft = 12;
                blackCheckersLeft = 12;
            }

            // If all white or black checkers are captured, end the game
            if (whiteCheckersLeft == 0 || blackCheckersLeft == 0){
                System.out.println("Thanks for playing!");
                break;
            }

            // Check if the user types hex or binary first so as not to trigger an exception
            if (!(input.equalsIgnoreCase("hex")) || (input.equalsIgnoreCase("binary"))){
            
                // Try/catch to update the game state calling methods from AdamLladoBoard
                try {

                    // Parse the input from the user calling the parseInt method
                    String[] move = input.split(" ");
                    int startRow = Integer.parseInt(move[0]);
                    int startCol = Integer.parseInt(move[1]);
                    int endRow = Integer.parseInt(move[2]);
                    int endCol = Integer.parseInt(move[3]);

                    // Call the movePiece method once the input has been parsed to move a piece
                    if (board.movePiece(startRow, startCol, endRow, endCol, isWhiteTurn, board.bitBoardSearch(startRow, startCol))) {
                        // If jumps are made where the difference of column and row from start to finish
                        // is 2, then call the capturePiece method to capture the middle piece
                        if (Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 2) {
                            board.capturePiece(startRow, startCol, endRow, endCol, isWhiteTurn);
                            if (isWhiteTurn){
                                //If white turn, decremement the black checkers and check if there is a double jump
                                blackCheckersLeft--;
                                if (board.canDoubleJump(endRow, endCol, board.bitBoardSearch(endRow, endCol), isWhiteTurn, board)) {
                                    isDoubleJump = true;
                                }
                            }else{
                                //If black turn, decremement the white checkers and check if there is a double jump
                                whiteCheckersLeft--;
                                if (board.canDoubleJump(endRow, endCol, board.bitBoardSearch(endRow, endCol), isWhiteTurn, board)) {
                                    isDoubleJump = true;
                                }
                            }
                        }

                        // If hexBitBoards is true, keep printing out the hex board representation
                        if (hexBitBoards == true){
                            board.printBoardHex(whiteCheckersLeft, blackCheckersLeft);
                        }else{
                        // Print the updated board with Binary formats if hexBitBoards is not true
                        board.printBoardBinary(whiteCheckersLeft, blackCheckersLeft);
                        }

                        // Conditional statement to continue the current turn if there is a double jump
                        // by checking if isDoubleJump is true and change the turn otherwise
                        if(isDoubleJump == true){
                            System.out.println("Double jump possible! Make another move.");
                            isDoubleJump = !isDoubleJump;
                        }else{
                            // Switch turns
                            isWhiteTurn = !isWhiteTurn;
                        }
                    }

                    // Determine if anyone has won the game yet by calling the winner method from AdamLladoBoard
                    AdamLladoBoard.winner(whiteCheckersLeft, blackCheckersLeft);
                    
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter the move in format 'startRow (space) startCol (space) endRow (space) endCol'.");
                }
            }
        
        }
    }

    // Main method
    public static void main(String[] args) {
        AdamLladoGame game = new AdamLladoGame();
        game.startGame();
    }
}

