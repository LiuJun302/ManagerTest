package others;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by LJ on 2017/7/12.
 */
public class readByteFile {
    public static void main(String[] args) throws IOException {
        System.out.println(1);
        readDataBytes();
    }
    public static float[][] readDataBytes(){
        String filePath = "F:\\Data\\c350.img";
        Object o = new Object();
        FileInputStream fsin = null;
        float[][] minMax = new float[2][192];
        Arrays.fill(minMax[0], Float.MAX_VALUE);
        Arrays.fill(minMax[1], Float.MIN_VALUE);
        try{
            fsin = new FileInputStream(filePath);
            byte[] bytes = new byte[192*2];
            int count = 0;
            while ((fsin.read(bytes))!=-1){
                //System.out.println(123);
                count ++;
                float[] pixelData = bytes2floats(bytes, 192);
                for (int i = 0; i < pixelData.length; i++) {
                    if(minMax[0][i] > pixelData[i])
                        minMax[0][i] = pixelData[i];
                    if(minMax[1][i] < pixelData[i])
                        minMax[1][i] = pixelData[i];
                }
            }
            System.out.println(count);
        }catch (Exception e){
            System.out.println("读取文件错误！");
        }
        return minMax;
    }
    public static float[] bytes2floats(byte[] data, int bands){

        float[] fdata = new float[bands];
        int n=0;
        for(int j=0;j<bands;j++) {
                fdata[j]=(float)((data[n++]&0xff) | (data[n++] <<8));}
        return fdata;
    }


}
