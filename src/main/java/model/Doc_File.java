package model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Doc_File {

    private String url;
    private FileReader file;
    private BufferedReader bf;

    public int[] Arr = new int[3]; // 3 số đầu ở hàng 1

    public int numberENEMY = 0;

    public Doc_File(String url) {
        this.url = url;
        ReaderOne();
    }


    /**
     * Đọc dòng đầu lấy số liệu ban đầu.
     */
    private void ReaderOne() {
        String line = "";
        String line1 = "";
        int j = 0;
        int index = 0;
        try {
            file = new FileReader(url);
            bf = new BufferedReader(file);
            line = bf.readLine();
            for (j = 0; j < line.length(); j++) {
                if (line.charAt(j) != ' ') {
                    line1 = line1 + line.charAt(j);
                }
                if (line.charAt(j) == ' ' || j == line.length() - 1){
                    Arr[index] = Integer.parseInt(String.valueOf(line1));
                    index ++;
                    line1 = "";
                }
            }
        } catch(Exception e) {
            System.out.print("Lỗi Đọc file");
        }
    }

    /**
     * Đọc file đưa vào mảng
     */
    public void ReaderFile(char[][] listAray)  {
        String line = "";
        int i = 0;
        int j = 0;
        try {
            file = new FileReader(url);
            bf = new BufferedReader(file);
            while (true) {
                line = bf.readLine();
                if (line == null) {
                    break;
                }
                for (j = 0; j < line.length(); j++) {
                    if (i != 0){
                        listAray[i-1][j] = line.charAt(j);
                        if (line.charAt(j) == '1' || line.charAt(j) == '2')
                            numberENEMY ++;
                    }
                }
                i++;
            }
        } catch(Exception e) {
            System.out.print("Lỗi Đọc file");
        }
    }
}
