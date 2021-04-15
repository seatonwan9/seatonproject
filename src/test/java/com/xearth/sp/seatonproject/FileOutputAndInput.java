package com.xearth.sp.seatonproject;

import java.io.*;

public class FileOutputAndInput {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");
        fileOutput(file); // 输出
        fileInput(file); // 输入

        // 测试异常类
        /*try{
            int a[] = new int[2];
            System.out.println("Access element three :" + a[3]);
        }catch(RuntimeException e){
            System.out.println("Exception thrown  :" + e);
        }
        System.out.println("Out of the block");*/
    }

    public static void fileOutput(File file) throws IOException {
        // 创建文件
        FileOutputStream fop = new FileOutputStream(file);
        // 设置输出编码
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");

        String str;
        // 获取控制台输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入 “quit” 退出...");
        do {
            str = br.readLine();
            if(!str.equals("quit")) {
                System.out.println("写入：" + str);
                writer.append(str + "\r\n");
            }else {
                System.out.println("关闭..");
            }

        } while (!str.equals("quit"));

        // 关闭写入流
        writer.close();
        // 关闭输出流
        fop.close();
    }

    public static void fileInput(File file) throws IOException {
        FileInputStream fip = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fip,"UTF-8");

        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
        }
        System.out.println(sb.toString());

        fip.close();
    }

}
