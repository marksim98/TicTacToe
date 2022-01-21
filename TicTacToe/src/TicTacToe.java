import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        //creating and printing out the empty game-board
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printGameBoard(gameBoard);

        //Game loop between player and CPU
        while(true){
            //Getting user input
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your placement number (1-9): ");
            int playerPosition = input.nextInt();
            //Player checks to see if a position is taken; if it is, we prompt the player to select a new position
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
                System.out.println("Position taken! Enter a correct position!");
                playerPosition = input.nextInt();
            }

            System.out.println(playerPosition);

            /*
            We take our game-board, our selected piece, and our identity,
            and we place our assigned position on the game-board
            */
            placePiece(gameBoard, playerPosition, "player");

            printGameBoard(gameBoard);
            //We check if our winner string is empty; if it isn't, we print out the winner (in this case, player)
            String winner = checkWinner();
            if(winner.length() > 0){
                System.out.println(winner);
                break;
            }

            /*
            Getting a random number between 1 - 9, and if the position is occupied by either the player or the CPU,
            we prompt the CPU to select another unoccupied position
            */

            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                cpuPosition = rand.nextInt(9) + 1;
            }

            /*
            We take our game-board, our selected piece, and our identity,
            and we place our assigned position on the game-board
            */

            placePiece(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);
            //We check if our winner string is empty; if it isn't, we print out the winner (in this case, CPU)
            winner = checkWinner();
            if(winner.length() > 0){
                System.out.println(winner);
                break;
            }
        }
    }

    //This method takes care to print out the game-board
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row: gameBoard){
            for(char symbol: row){
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
    //This method takes care to place the according piece in the proper board space
    public static void placePiece(char[][] gameBoard, int position, String user){
        /* We assign a default symbol that changes depending on if the user is the player (X),
        or the CPU (0). Additionally, the position parameter is added to the user-specific ArrayList
         */
        char symbol = ' ';
        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if(user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        /*
        The switch statement takes the position parameter, and evaluates it against each case (1-9).
        For every case, the specific character in the 2D array "gameboard" is replaced
        with the specific character determined in the previous if-else if conditional statement.
        If the input does not match any of the specified cases,
        the default case warns the user that their turn is skipped.
        */
        switch(position){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                System.out.println("Your input is invalid! As punishment, your turn is skipped.");
                break;
        }
    }
    /*This method takes all possible win configurations,
    and compares them to the playerPositions and cpuPositions;
    if one of them has any one of these victory configurations, they are the winner
     */
    public static String checkWinner(){
        //All the possible win conditions in Tic-Tac-Toe
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftColumn = Arrays.asList(1, 4, 7);
        List<Integer> middleColumn = Arrays.asList(2, 5, 8);
        List<Integer> rightColumn = Arrays.asList(3, 6, 9);
        List<Integer> leftDiagonal = Arrays.asList(1, 5, 9);
        List<Integer> rightDiagonal = Arrays.asList(3, 5, 7);
        //List of Lists, holding the winning conditions
        List<List<Integer>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftColumn);
        winning.add(middleColumn);
        winning.add(rightColumn);
        winning.add(leftDiagonal);
        winning.add(rightDiagonal);
        /*
        We use the for-loop to check all winning configurations against the player and CPU ArrayList
        if either the Player or the CPU won,
        or if we ended up with a tie outside the for-loop because the total size of player and CPU ArrayLists is equal
        to the number of available spaces (9).
         */
        for (List<Integer> l: winning){
            if(playerPositions.containsAll(l)) {
                return "Congratulations! You won!";
            } else if(cpuPositions.containsAll(l)){
                return "CPU is the winner! Sorry :(, better luck next time!";
            }
        }
        if((playerPositions.size() + cpuPositions.size()) == 9){
            return "CAT!";
        }

        return "";
    }
}