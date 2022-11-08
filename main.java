package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to my Simple Tic-Tac-Toe game!");
        System.out.println("Two players can play from the beginning (with an empty grid) through to the end (until there is a draw, or one of the players wins).");
        System.out.println("The first player has to play as X and their opponent plays as O.");
        System.out.println("Use coordinates to place your field (1 -> Column 1 -> Row)(2 2) ...");

        //declaration of two dimensional Array
        Character[][] gridArray = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        Character playerLetter = 'O';

        boolean winnerFound = false;
        int roundCounter = 0;

        do {
            roundCounter++;
            playerLetter = playerLetter == 'O' ? 'X' : 'O';
            printGrid(gridArray, playerLetter);
            winnerFound = searchWinner(gridArray);

            if (roundCounter > 9 && winnerFound == false) {
                System.out.println("Draw");
                break;
            }


            if (winnerFound != true) {
                getCoordinates(gridArray, playerLetter);
            }

        } while (winnerFound != true);
    }

    static void printGrid(Character[][] gridArray, Character playerLetter) {
        //Print grid
        System.out.println("---------");

        for (int i = 0; i < gridArray.length; i++) {
            for (int j = 0; j < gridArray[i].length; j++) {
                System.out.println("| " + gridArray[i][j] + " " + gridArray[i][++j] + " " + gridArray[i][++j] + " |");
            }
        }
        System.out.println("---------");
    }


    public static boolean searchWinner(Character[][] gridArray) {

        int[][] winnerArray = {
                {0, 0}, {0, 1}, {0, 2},       //First Column
                {1, 0}, {1, 1}, {1, 2},       //Second Column
                {2, 0}, {2, 1}, {2, 2},       //Third Column
                {0, 0}, {1, 0}, {2, 0},       //First Row
                {0, 1}, {1, 1}, {2, 1},       //Middle Row
                {0, 2}, {1, 2}, {2, 2},       //Last Row
                {0, 0}, {1, 1}, {2, 2},       //Diagonal
                {0, 2}, {1, 1}, {2, 0},         //Diagonal
                {0 , 0}
        };

        boolean response = false;
        int xCounter = 0;
        int oCounter = 0;
        int roundCounter = 0;

        for (int[] a : winnerArray) {

            if (roundCounter == 3) {
                if (xCounter == 3) {
                    System.out.println("X wins");
                    response = true;
                    break;
                } else if (oCounter == 3) {
                    System.out.println("O wins");
                    response = true;
                    break;
                } else {
                    xCounter = 0;
                    oCounter = 0;
                    roundCounter = 0;
                }
            }

            if (gridArray[a[0]][a[1]] == 'X') {
                xCounter++;
                roundCounter++;
            } else if (gridArray[a[0]][a[1]] == 'O') {
                oCounter++;
                roundCounter++;
            } else {
                roundCounter++;
            }
        }
        return response;
    }

    static void getCoordinates(Character[][] gridArray, Character playerLetter) {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;

        int coordinateY = 0;
        int coordinateX = 0;

        do {
            //Prompt the user to make next move
            System.out.println("Input 2 coordinate numbers that represent the cell");

            try {
                coordinateY = scanner.nextInt(); //Column up, down
                coordinateX = scanner.nextInt(); //Row left, right

                coordinateX--;
                coordinateY--;

                if (coordinateX <= 3 && coordinateY <= 3) {
                    if (gridArray[coordinateY][coordinateX] == ' ') {
                        gridArray[coordinateY][coordinateX] = playerLetter;
                        inputValid = true;
                    } else if (gridArray[coordinateY][coordinateX] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        } while (inputValid == false);
    }
}
