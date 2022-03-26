import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe{
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args){
        char[][] gameBoard = {
                                {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '}
                            };
        printGameBoard(gameBoard);
        while(true) {
            String result = checkWinner();
            if (result != null) {
                System.out.println(result);
                break;
            }
            System.out.print("Enter your place (1-9): ");
            Scanner scan = new Scanner(System.in);
            int PlayerPos = scan.nextInt();
            while(playerPositions.contains(PlayerPos) || cpuPositions.contains(PlayerPos)) {
                System.out.print("again: ");
                PlayerPos = scan.nextInt();
            }
            placePiece(gameBoard, PlayerPos, "player");
            result = checkWinner();
            if (result != null) {
                System.out.println(result);
                break;
            }
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "");
            printGameBoard(gameBoard);
        }
        
    }

    private static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void placePiece(char[][] gameBoard, int pos, String user){
        char symbol = 'O';
        if(user.equals("player")) {
            symbol='X';
            playerPositions.add(pos);
        } else {
            cpuPositions.add(pos);
        }
  
        switch(pos) {
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
                break;
        }
    }

    public static  String checkWinner(){
        List toprow = Arrays.asList(1,2,3);
        List midrow = Arrays.asList(4,5,6);
        List botrow = Arrays.asList(7,8,9);
        List topcol = Arrays.asList(1,4,7);
        List midcol = Arrays.asList(2,5,8);
        List botcol = Arrays.asList(3,6,9);
        List leftcross = Arrays.asList(1,5,9);
        List rightcross = Arrays.asList(3,5,7);
        List<List> winning = new ArrayList<List>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(botrow);
        winning.add(topcol);
        winning.add(midcol);
        winning.add(botcol);
        winning.add(leftcross);
        winning.add(rightcross);
        for (List l : winning) {
            if (playerPositions.containsAll(l)){
                return "Congratulations you won ;)";
            } else if (playerPositions.containsAll(l)){
                return "CPU wins :(";
            } else if(playerPositions.size() + cpuPositions.size() == 9){
                return "CAT!";
            }
        }
        return null;
    }
}