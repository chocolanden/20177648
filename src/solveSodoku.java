import java.io.File;
import java.io.FileWriter;

import static java.lang.Math.sqrt;

public class solveSodoku {

    private int[][] board;
    private int rank;  //阶数
    private String outputFilename;
    private boolean flag = false;

    public solveSodoku(int[][] board, int rank, String outputFilename) {
        this.board = board;
        this.rank = rank;
        this.outputFilename = outputFilename;
    }

    public solveSodoku() {

    }

    public void isValid() {
        backTrace(0, 0);
        //check(board);
        if (flag) {
            System.out.println("Success!!!");
        }else {
            System.out.println("This test is no solution!!!");
            printf();
        }
    }

    public void backTrace(int i, int j) {
        if (i == (rank - 1) && j == rank) {
            flag = true;
            printf();
            return;
        }

        if (j == rank) {
            i++;
            j = 0;
        }

        if (board[i][j] == 0) {
            for (int num = 1; num <= rank; num++) {
                if (check(i, j, num)) {
                    board[i][j] = num;
                    backTrace(i,j+1);
                    board[i][j] = 0;
                }
            }
        }else {
            backTrace(i,j+1);
        }
    }


    private boolean check(int row, int col, int num) {
        for (int i = 0; i < rank; i++) {
            if (board[row][i] == num) {
                return false;
            }
            if (board[i][col] == num) {
                return false;
            }
        }

        return true;
    }

    private void printf() {
        File file = new File(outputFilename);

        try {
            if (! file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file,true);

            if (flag) {
                for (int i = 0 ;i < rank ;i ++) {
                    for (int j = 0 ;j < rank; j++) {
                        fw.write(board[i][j]+" ");
                    }
                    fw.write("\r\n");
                }
            }else {
                fw.write("The test is no solution!!!\r\n");
            }

            fw.write("\r\n");
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
