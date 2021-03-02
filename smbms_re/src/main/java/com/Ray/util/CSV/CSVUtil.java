package com.Ray.util.CSV;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static char separator = ',';

    public static void main(String[] args) {
        //测试导出
//        String filePath = "./scoreInfo.csv";
//        List<String[]> dataList = new ArrayList<String[]>();
//        //添加标题
//        dataList.add(new String[]{"学号", "姓名", "分数"});
//        for (int i = 0; i < 10; i++) {
//            dataList.add(new String[]{"20180200100"+i, "张三"+i, "8"+i});
//        }
//        try {
//            createCSV(dataList, filePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String filePath = "D:/java/JavaProject/AutoShop/smbms_re/test.csv";
        List<String[]> strings = null;
        try {
            strings = readCSV(filePath);
            System.out.println(strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        filePath = "D:/java/JavaProject/AutoShop/test.csv";
        try {
            createCSV(strings, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> readCSV(String filePath) throws Exception{
        CsvReader reader = null;
        //追加写
        List<String[]> dataList = new ArrayList<String[]>();
        try {
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            reader = new CsvReader(filePath, separator, Charset.forName("GBK"));

            // 读取表头
            reader.readHeaders();
            String[] headArray = reader.getHeaders();//获取标题
            //System.out.println(headArray[0] + headArray[1] + headArray[2]);
            //System.out.println(headArray.length);
            dataList.add(headArray);
            // 逐条读取记录，直至读完
            while (reader.readRecord()) {
                // 读一整行
                //System.out.println(reader.getRawRecord());
                // 读这行的第一列
                //System.out.println(reader.get("学号"));
                // 读这行的第二列
                //System.out.println(reader.get(1));
                dataList.add(reader.getRawRecord().split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        return dataList;
    }

    /**
     * 生成CSV文件
     * @param dataList:数据集
     * @param filePath:全路径名
     */
    public static boolean createCSV(List<String[]> dataList, String filePath) throws Exception{
        boolean isSuccess = false;
        CsvWriter writer = null;
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(filePath, true);
            writer = new CsvWriter(out, separator, Charset.forName("GBK"));
            for(String[] strs : dataList){
                writer.writeRecord(strs);
            }
            isSuccess = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
            if(out != null){
                try{
                    out.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

}
