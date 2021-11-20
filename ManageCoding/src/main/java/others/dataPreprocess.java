package others;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dataPreprocess{
    public static void main(String[] args) throws Exception{
        //System.out.println(getDate());
        int[][] a = readTrainFile(15, 50);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int[][] readTrainFile(int typeNum, int dayNum) throws Exception{
        int[][] useOfMachine = new int[dayNum][typeNum];

        File fin1 = new File("C:\\Users\\LJ\\Desktop\\HUAWEICompetition\\练习数据\\初赛文档\\用例示例\\TrainData_2015.1.1_2015.2.19.txt");
        BufferedReader bf = new BufferedReader(new FileReader(fin1));
        String text = bf.readLine();
        String firstDate = text.split("\t")[2].split(" ")[0];
        do{
            String[] str = text.split("\t");
            int typeIdx = getType(str[1]);
            int dayIdx = getDate(firstDate, str[2].split(" ")[0]);
            if(typeIdx-1 < typeNum){
                useOfMachine[dayIdx][typeIdx-1] += 1;
            }
        }while ((text = bf.readLine())!=null);
        bf.close();
        return useOfMachine;
    }
    public static int getDate(String date1, String date2) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        format.setLenient(false);
        Date d1 = format.parse(date1 + " 01:00:00");
        Date d2 = format.parse(date2 + " 01:00:00");
        return Long.valueOf(((d2.getTime()-d1.getTime())/(1000*60*60*24))).intValue();
    }
    public static int getType(String strType){
        return Integer.valueOf(strType.split("r")[1]);
    }
}

