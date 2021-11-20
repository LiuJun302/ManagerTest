package others;

import java.io.*;
import java.util.ArrayList;

public class GenerateLargerData {
    public static void main(String[] args) throws IOException {
        readAndWriteByteTT();

        System.out.println("==============");
    }

    public static void ReadIntWriteByte() throws IOException{
        InputStream in = new FileInputStream(new File("E:\\data\\img1.txt"));
        double[][] data = new double[8000][162];
    }

    public static void readAndWriteString() throws IOException{
        String line = null;
        ArrayList<String> list = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File("E:\\projects\\Change\\data\\img2016.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        OutputStream out = new FileOutputStream("C:\\Users\\LJ\\Desktop\\毕业\\img2016-20.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));

        int times = 20;
        int count = 0;

        while ((line = bufferedReader.readLine()) != null){
            list.add(line);
        }
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < list.size(); j++) {
                bufferedWriter.write(list.get(j));
                System.out.println(count ++);
                bufferedWriter.newLine();
            }
        }
        inputStream.close();
        out.close();
    }

    public static void readAndWriteByteTT() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("PaviaU.txt"));
        byte[] b = new byte[207400*103*4];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        int idx = 0;
        while ((str = bufferedReader.readLine()) != null) {
            String[] strs = str.split(",");
            for (int i = 0; i < strs.length; i++) {
                double t = Double.valueOf(strs[i]);
                int num = (int)t;
                System.out.println(num);
                for (int j = 0; j < 4; j++) {
                    b[idx++] = (byte) (num >> (24 - j * 8));
                }
            }
        }
        //write byte
        OutputStream out = new FileOutputStream("4P.txt");
        InputStream is = new ByteArrayInputStream(b);
        int times = 4;
        while(times > 0) {
            out.write(b);
            /*byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                out.write(buff, 0, len);
            }*/
            times --;
        }
        is.close();
        out.close();
        System.out.println();
    }

    public static void readAndWriteByte() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("E:\\projects\\Change\\data\\img2017.txt"));
        byte[] b = new byte[747000 * 3 * 4];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        int idx = 0;
        while ((str = bufferedReader.readLine()) != null) {
            String[] strs = str.split(",");
            for (int i = 0; i < strs.length; i++) {
                int num = Integer.valueOf(strs[i]);
                for (int j = 0; j < 4; j++) {
                    b[idx++] = (byte) (num >> (24 - j * 8));
                }
            }
        }
        //write byte
        OutputStream out = new FileOutputStream("C:\\Users\\LJ\\Desktop\\毕业\\c7-2017-80.txt");
        InputStream is = new ByteArrayInputStream(b);
        int times = 80;
        while(times > 0) {
            out.write(b);
            /*byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                out.write(buff, 0, len);
            }*/
            times --;
        }
        is.close();
        out.close();
        System.out.println();
    }

    public static double[][] readFromlabel(String path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        double[][] res = new double[280][162];
        int idx = 0;String line = null;
        try {
            while ((line=br.readLine()) != null){
                String[] tmp = line.split(" ");
                for (int i = 0; i < 162; i++) {
                    res[idx][i] = Double.valueOf(tmp[i]);
                }
                idx++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
