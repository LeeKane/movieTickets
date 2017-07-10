package TF_IDF;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import jexcelapi;
/**
 * Created by Administrator on 2017/7/10.
 */
public class base {
    public static int maxWordCount(String movieId){
        String fileAdd="E:/"+movieId+".xls";
        try {
            InputStream instream = new FileInputStream("dileAdd");
            readwb = Workbook.getWorkbook(instream);
            Sheet readsheet = readwb.getSheet(0);
//
//            //获取Sheet表中所包含的总列数
//
//            int rsColumns = readsheet.getColumns();
//
//            //获取Sheet表中所包含的总行数
//
//            int rsRows = readsheet.getRows();

            //获取指定单元格的对象引用
            Cell cell = readsheet.getCell(0, 1);
            return Integer.getInteger(cell.getContents());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int WordCount(String movieId,String key){
        String fileAdd="E:/"+movieId+".xls";
        try {
            InputStream instream = new FileInputStream("dileAdd");
            readwb = Workbook.getWorkbook(instream);
            Sheet readsheet = readwb.getSheet(0);
//
//            //获取Sheet表中所包含的总列数
//
//            int rsColumns = readsheet.getColumns();
//
//            //获取Sheet表中所包含的总行数
//
//            int rsRows = readsheet.getRows();

            //获取指定单元格的对象引用
            int reColums=readsheet.getColumsByKey("keey");
            Cell cell = readsheet.getCell(0, reColums);
            return Integer.getInteger(cell.getContents());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int filecount(String key){
        return 0;
    }
    public static int WordCount(String movieId,int key){
        String fileAdd="E:/"+movieId+".xls";
        try {
            InputStream instream = new FileInputStream("dileAdd");
            readwb = Workbook.getWorkbook(instream);
            Sheet readsheet = readwb.getSheet(0);
//
//            //获取Sheet表中所包含的总列数
//
//            int rsColumns = readsheet.getColumns();
//
//            //获取Sheet表中所包含的总行数
//
//            int rsRows = readsheet.getRows();

            //获取指定单元格的对象引用

            Cell cell = readsheet.getCell(0, key);
            return Integer.getInteger(cell.getContents());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String[] getKeyWords(String movieId){
        String fileAdd="E:/"+movieId+".xls";
        try {
            InputStream instream = new FileInputStream("dileAdd");
            readwb = Workbook.getWorkbook(instream);
            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数

            int rsRows = readsheet.getRows();

            //获取指定单元格的对象引用

            String result[]=new String[getColumns];

                for (int j = 0; j < rsColumns; j++) {

                    Cell cell = readsheet.getCell(j, 0);
                    result[j-1]=cell.getContents();


                }



            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getList(String movieId){
        String fileAdd="E:/"+movieId+".xls";
        try {
            InputStream instream = new FileInputStream("dileAdd");
            readwb = Workbook.getWorkbook(instream);
            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();





            return rsColumns;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
