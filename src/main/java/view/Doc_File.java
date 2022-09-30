package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Doc_File {

    private static String url;
    private static FileReader file;
    private static BufferedReader bf;

    //private char[][] listAray = new char[100][100];

    public Doc_File(String url) {

        this.url = url;
    }

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
                    listAray[i][j] = line.charAt(j);
                }
                i++;
            }

        } catch(Exception e) {
            System.out.print("Lỗi Đọc file");
        }
    }
}
