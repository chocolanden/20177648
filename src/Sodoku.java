import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sodoku {
    private static String inputFileName;  //指定输入文件名
    private static String outputFileName;  //指定输出文件名
    private static int stepNum;  //宫格阶级数
    private static int futuresNum;  //盘面数

    public static void main(String[] args) throws IOException {
        loading(args);

        File file = new File(inputFileName);

        int x[][] = new int[9][9];
        int row = 0;
        int col = 0;
        String str = "";
        FileReader fr = null;

        if (! file.exists()) {
            System.out.println("对不起，没有找到指定文件！");
        }else {
            try {
                fr = new FileReader(file);

                char[] data = new char[1024];
                int i = 0;  //当前下表
                int n = fr.read();

                while (n != -1) {
                    data[i] = (char)n;
                    i++;
                    n = fr.read();
                }

                str = new String(data, 0, i);

                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) >47 && str.charAt(j)<58) {
                        char c = str.charAt(j);
                        x[row][col++] = Character.getNumericValue(c);
                        if (col == stepNum) {
                            row++;
                            col = 0;
                        }
                    }
                    if (row == futuresNum) {
                        solveSodoku solveSodoku = new solveSodoku(x,stepNum,outputFileName);
                        solveSodoku.isValid();
                        row = 0;
                        col = 0;
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭流，释放资源
                    fr.close();
                }catch (Exception e) {

                }
            }
        }

    }

    static void loading(String[] args) {
        if (args.length > 0 && args != null) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]){
                    case "-i":
                        inputFileName = args[++i];
                        break;
                    case "-o":
                        outputFileName = args[++i];
                        break;
                    case "-m":
                        stepNum = Integer.valueOf(args[++i]);
                        break;
                    case "-n":
                        futuresNum = Integer.valueOf(args[++i]);
                        break;
                    default:
                        break;
                }
            }
        }else {
            System.out.println("No input parameters");
            System.exit(1);
        }
    }
}


