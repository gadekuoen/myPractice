package cn.wjqixige.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class getConnection {

    @Test
    public void getFileSystem() throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://yun18:8020"), configuration);
        System.out.println(fileSystem.toString());
    }

    @Test
    public void getFileToLocal()throws  Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://yun18:8020"), new Configuration());
        FSDataInputStream open = fileSystem.open(new Path("/tmp/1.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("H:\\hello.log"));
        IOUtils.copy(open,fileOutputStream );
        IOUtils.closeQuietly(open);
        IOUtils.closeQuietly(fileOutputStream);
        fileSystem.close();
    }

    @Test
    public void mkdirs() throws  Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://yun18:8020"), new Configuration());
        boolean mkdirs = fileSystem.mkdirs(new Path("/user/amrcloud/test/wj"));
        fileSystem.close();
    }



    @Test
    public void listFile() throws Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://yun18:8020"), new Configuration());
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/user/amrcloud/test"));
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isDirectory()){
                Path path = fileStatus.getPath();
                listAllFiles(fileSystem,path);
            }else{
                System.out.println("文件路径为"+fileStatus.getPath().toString());

            }
        }
    }
    public void listAllFiles(FileSystem fileSystem,Path path) throws  Exception{
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isDirectory()){
                listAllFiles(fileSystem,fileStatus.getPath());
            }else{
                Path path1 = fileStatus.getPath();
                System.out.println("文件路径为"+path1);
            }
        }
    }


    @Test
    public void demo1() {
        //第一步：注册hdfs 的url，让java代码能够识别hdfs的url形式
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());

        InputStream inputStream = null;
        FileOutputStream outputStream =null;
        //定义文件访问的url地址
        String url = "hdfs://yun18:8020/user/amrcloud/test/test.log";
        //打开文件输入流
        try {
            inputStream = new URL(url).openStream();
            outputStream = new FileOutputStream(new File("H:\\hello.txt"));
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }
}
