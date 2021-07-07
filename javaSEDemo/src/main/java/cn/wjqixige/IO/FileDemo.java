package cn.wjqixige.IO;

import java.io.File;
import java.io.IOException;

/** java.io.File类是文件和目录路径名的抽象表示，主要用于文件和目录的创建、查找和删除等操作。*/
public class FileDemo {

    public static void main(String[] args) throws IOException {

        /**
         * 构造方法
         * public File(String pathname)：通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
         * public File(String parent, String child)：从父路径名字符串和子路径名字符串创建新的 File实例。
         * public File(File parent, String child)：从父抽象路径名和子路径名字符串创建新的 File实例。
         */
        File file = new File("javaSEDemo/1.txt");
//        System.out.println(file);

        File file1 = new File("javaSEDemo", "1.txt");
//        System.out.println(file1);

        File parentDir = new File("javaSEDemo");
        File file2 = new File(parentDir, "1.txt");
//        System.out.println(file2);

        /**
         * 获取功能方法
         * public String getAbsolutePath()：返回此File的绝对路径名字符串。
         * public String getPath()：将此File转换为路径名字符串。
         * public String getName()：返回由此File表示的文件或目录的名称。
         * public long length()：返回由此File表示的文件的长度。
         */
//        System.out.println("绝对路径：" + file.getAbsolutePath());
//        System.out.println("路径名字符串： " + file.getPath());
//        System.out.println("文件或目录名称：" + file.getName());
//        System.out.println("文件长度为：" + file.length());

        /**
         * 判断功能方法
         * public boolean exists()：此File表示的文件或目录是否实际存在。
         * public boolean isDirectory()：此File表示的是否为目录。
         * public boolean isFile() ：此File表示的是否为文件。
         */
//        System.out.println("文件或目录是否存在： " + file.exists());
//        System.out.println("是否为目录： " + file.isDirectory());
//        System.out.println("是否为文件：" + file.isFile());

        /**
         * 删除功能方法
         * public boolean createNewFile()：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。
         * public boolean delete()：删除由此File表示的文件或目录。
         * public boolean mkdir()：创建由此File表示的目录。
         * public boolean mkdirs()：创建由此File表示的目录，包括任何必需但不存在的父目录。
         */
        //创建文件
        File f = new File("javaSEDemo\\3.txt");
//        System.out.println("f是否存在：" + f.exists());
//        System.out.println("f是否创建：" + f.createNewFile());
//        System.out.println("f是否存在：" + f.exists());

        //创建目录
        File f1 = new File("javaSEDemo\\testDir");
//        System.out.println("f1是否存在：" + f1.exists());
//        System.out.println("f1是否创建：" + f1.mkdir());
//        System.out.println("f1是否存在：" + f1.exists());
        //多级目录
        File f2 = new File("javaSEDemo\\testDir1\\testDir2");
//        System.out.println("f2是否存在：" + f2.exists());
//        System.out.println("f2是否创建：" + f2.mkdirs());
//        System.out.println("f2是否存在：" + f2.exists());

        //删除
//        System.out.println(f.delete());
//        System.out.println(f1.delete());
//        System.out.println(f2.delete());  //delete方法，如果此File表示目录，则目录必须为空才能删除。

        /**
         * public String[] list()：返回一个String数组，表示该File目录中的所有子文件或目录。
         * public File[] listFiles() ：返回一个File数组，表示该File目录中的所有的子文件或目录。
         */
        File dir = new File("javaSEDemo\\src");
//        String[] names = dir.list();
//        for (String name : names) {
//            System.out.println(name);
//        }

        File[] names1 = dir.listFiles();
        for (File name : names1) {
            System.out.println(name);
        }


    }

}
