package SudokuSolver;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        //args = new String[]{"0", "2", "1", "0", "3", "0", "0", "0", "5", "0", "9", "6", "8", "7", "0", "1", "0", "0", "0", "8", "0", "0", "2", "0", "0", "0", "0", "0", "0", "2", "7", "5", "0", "0", "0", "9", "1", "5", "9", "0", "0", "0", "3", "2", "7", "8", "0", "0", "0", "9", "1", "4", "0", "0", "0", "0", "0", "0", "1", "0", "0", "6", "0", "0", "0", "7", "0", "8", "6", "5", "9", "0", "5", "0", "0", "0", "4", "0", "7", "3", "0"};

        int[][] input = new int[9][9];
        if(args == null || !(args.length == 81)) {
            System.out.println("Bad input!");
        }

        int currentRow = 0;
        int currentColumn = 0;
        for(String argument : args) {
            try {
                if(currentColumn == 9) {
                    currentColumn = 0;
                    currentRow++;
                }
                input[currentRow][currentColumn] = Integer.valueOf(argument);
                currentColumn++;
            } catch(NumberFormatException exception) {
                exception.printStackTrace();
                System.out.println("Bad input!");
                return;
            }

        }

        showGrid(solve(input));
        //System.out.println(a + " total numbers in this sequence.");
    }

    public static void showGrid(int[][] grid) {
        System.out.println("|-----------------------------------|");
        for(int[] i : grid) {
            String lst = "| ";
            for(int j : i) {
                lst = lst + j + " | ";
            }
            System.out.println(lst);
            System.out.println("|-----------------------------------|");
        }
    }

    public static int[][] solve(int[][] grid) {
        ArrayList<Coordinate> list = new ArrayList<>();
        int x = 1;
        int y = 1;
        for(int[] i : grid) {
            for(int j : i) {
                if(j == 0) {
                    list.add(new Coordinate(x, y));
                }
                y++;
            }
            y = 1;
            x++;
        }

        for(Coordinate a : list) {
            ArrayList<Integer> list1 = new ArrayList<>();
            for(int j : grid[a.x - 1]) {
                if(j!=0 && !list1.contains(j)) {
                    list1.add(j);
                }
            }

            for(int c = 0; c < 9; c++) {
                int d = grid[c][a.y - 1];
                if(d != 0 && !list1.contains(d)) {
                    list1.add(d);
                }
            }

            for(Coordinate e : a.getBox()) {
                int f = grid[e.x - 1][e.y - 1];
                if(f != 0 && !list1.contains(f)) {
                    list1.add(f);
                }
            }
            if(list1.size() == 8) {
                for(int i = 1; i <=9; i++) {
                    if(!list1.contains(i)) {
                        grid[a.x - 1][a.y - 1] = i;
                    }
                }
            }
        }
        for(int[] a : grid) {
            for(int b : a) {
                if(b == 0) {
                    return solve(grid);
                }
            }
        }
        return grid;
    }

    static class Coordinate {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public ArrayList<Coordinate> getBox() {
            ArrayList<Coordinate> coords = new ArrayList<>();


            Coordinate leftTopMost = new Coordinate(x + getMove(x), y + getMove(y));
            for(int i = 0; i <= 2; i++) {
                for(int j = 0; j<= 2; j++) {
                    coords.add(new Coordinate(leftTopMost.x + i, leftTopMost.y + j));
                }
            }
            return coords;
        }

        private int getMove(int i) {
            if(i == 1 || i == 4 || i == 7) {
                return 0;
            }else if(i == 2 || i == 5 || i == 8) {
                return -1;
            }else if(i == 3 || i == 6 || i == 9) {
                return -2;
            }
            return 0;
        }
    }
}
