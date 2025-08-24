import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[3][3];
        for (int row = 0; row < board.length; row++) {
            Arrays.fill(board[row], ' ');
        }

        char player = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard(board);
            System.out.println("Player " + player + " enter your move (row and col between 0-2): ");

            int row, col;
            try {
                row = sc.nextInt();
                col = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter numbers only!");
                sc.nextLine(); 
                continue;
            }

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position! Try again.");
                continue;
            }

            if (board[row][col] == ' ') {
                board[row][col] = player;
                if (haveWon(board, player)) {
                    printBoard(board);
                    System.out.println("Player " + player + " has won!!");
                    gameOver = true;
                } else if (isBoardFull(board)) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    if(player=='X') player = 'O';
                    else player='X';
                }
            } else {
                System.out.println("Cell already taken. Try again!");
            }
        }

        sc.close();
    }

    public static void printBoard(char[][] board) {
        System.out.println("  0   1   2");
        for (int row = 0; row < board.length; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
                if (col < 2) System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) System.out.println("  ---------");
        }
    }

    public static boolean haveWon(char[][] board, char player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player)
                return true;
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player)
                return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }
}
