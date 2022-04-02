package leinne.java.sudoku.util;

import java.util.ArrayList;

public class SudokuUtils{

    public static int[][] parse(String sudoku){
        if(sudoku.length() != 81){
            throw new IllegalArgumentException("Wrong problem. Sudoku must consist of 81 numbers.");
        }

        var sudokuArray = new int[9][9];
        var sudokuSplit = sudoku.trim().split("");
        for(int i = 0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                sudokuArray[i][j] = Utils.parseInt(sudokuSplit[i * 9 + j]);
            }
        }
        return sudokuArray;
    }

    public static String serialize(int[][] sudoku){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                s.append(sudoku[i][j]);
            }
        }
        return s.toString();
    }

    public static int[][] solve(final int[][] board){
        var solveBoard = new int[9][9];
        for(int i = 0; i < 9; ++i){
            System.arraycopy(board[i], 0, solveBoard[i], 0, 9);
        }

        var boardBlank = new ArrayList<int[]>();
        for(int i = 0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                var num = solveBoard[i][j];
                if(num <= 0 || num > 9){
                    boardBlank.add(new int[]{i, j});
                }
            }
        }

        // overwrites sudoku to its solution
        for(int b = 0, len = boardBlank.size(); b < len;){
            // if all possible cases failed
            if(b <= -1){
                return null;
            }
            var value = boardBlank.get(b);
            var currentNum = ++solveBoard[value[0]][value[1]];

            // if all nums in b'th blank cell failed
            if(currentNum >= 10){
                --b;
                solveBoard[value[0]][value[1]] = 0;
                continue;
            }

            // check if curNum is valid candidate
            int boxTop = value[1] / 3 * 3;
            int boxLeft = value[0] / 3 * 3;
            for(int i = 0; i < 9; ++i){
                if(
                    solveBoard[value[0]][i] == currentNum && i != value[1] ||
                    solveBoard[i][value[1]] == currentNum && i != value[0] ||
                    solveBoard[boxLeft + i / 3][boxTop + i % 3] == currentNum && boxLeft + i / 3 != value[0] && boxTop + i % 3 != value[1]
                ){
                    // case fail; again curNum++ in next loop
                    --b;
                    break;
                }
            }
            ++b;
        }
        return solveBoard;
    }

    public static boolean isValidProblem(int[][] problem){
        if(problem.length != 9){
            return false;
        }

        for(int i = 0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                if(problem[i].length != 9 || (problem[i][j] > 0 && isNestedNumber(i, j, problem))){
                    return false;
                }
            }
        }
        return solve(problem) != null;
    }

    private static boolean isNestedNumber(int row, int column, int[][] board){
        for(int i = 0; i < 9; ++i){
            if(i != column && board[row][i] == board[row][column]) return true;
            if(i != row && board[i][column] == board[row][column]) return true;
        }

        for(int i = row / 3 * 3, rowEnd = i + 3; i < rowEnd; ++i){
            for(int j = column / 3 * 3, colEnd = j + 3; j < colEnd; ++j){ // 3 * 3
                if(i != row && j != column && board[i][j] == board[row][column]) return true;
            }
        }
        return false;
    }
}