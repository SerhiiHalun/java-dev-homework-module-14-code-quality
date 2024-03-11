import java.util.Scanner;

public class Game {
    boolean boxAvailable;
    byte winner = 0;
    char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    boolean boxEmpty = false;
    Scanner scan = new Scanner(System.in);
   public Game(){
       System.out.println("Enter box number to select. Enjoy!\n");
       while (true) {

           printGameField(box);

           boxEmpty = isBoxEmpty(boxEmpty, box);

           if (winner > 0) {
               printWhoWin(winner);
               break;
           }

           checkOutField(scan, box);
           boxAvailable = checkBoxAvailable(box);

           winner = getWinUser(box);
           if (winner == 1) {
               continue;
           }

           if (!boxAvailable) {
               winner = 3;
               continue;
           }

           getBotStep(box);
           winner = getWinBot(box);

       }
   }
    private static boolean isBoxEmpty(boolean boxEmpty, char[] box) {
        byte i;
        if (!boxEmpty) {
            for (i = 0; i < 9; i++)
                box[i] = ' ';
            boxEmpty = true;
        }
        return boxEmpty;
    }

    private static void checkOutField(Scanner scan, char[] box) {
        byte input;
        while (true) {

            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                System.out.println("Invalid input. Enter again.");
        }
    }

    private static byte getWinBot(char[] box) {

        if (checkWin(box, 'O')) {
            return 2;
        }
        return 0;
    }

    private static byte getWinUser(char[] box) {

        if (checkWin(box, 'X')) {
            return 1;
        }
        return 0;
    }

    private static boolean checkWin(char[] box, char symbol) {

        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combo : winningCombinations) {
            if (box[combo[0]] == symbol && box[combo[1]] == symbol && box[combo[2]] == symbol) {
                return true;
            }
        }
        return false;
    }

    private static void getBotStep(char[] box) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private static boolean checkBoxAvailable(char[] box) {
        byte i;
        boolean boxAvailable;
        boxAvailable = false;
        for (i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }
        return boxAvailable;
    }

    private static void printGameField(char[] box) {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private static void printWhoWin(int winner) {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");

        }

    }
}
