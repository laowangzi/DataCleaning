package com.web;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Component
@RestController
public class HdfsUtils {

    @Value("${hdfs.uploadPath}")
    private static String userPath;

    //@Value("${hdfs.host}")
    private static String hdfsPath="hdfs://localhost:9000//user/wcinput/CleanData.csv";

    public void test1() {
        System.out.println(userPath);
        System.out.println(hdfsPath);
    }

    /**
     * @return
     * @功能 获取HDFS配置信息
     */

    private static Configuration getHdfsConfig() {
        Configuration config = new Configuration();
        config.set("fs.defaultFS",hdfsPath);
        return config;
    }

    /**
     * @功能 获取FS对象
     */
    private static FileSystem getFileSystem() throws Exception {
        //客户端去操作hdfs时，是有一个用户身份的,默认情况下，hdfs客户端api会从jvm中获取一个参数来作为自己的用户身份：-DHADOOP_USER_NAME=hadoop
        //FileSystem hdfs = FileSystem.get(getHdfsConfig());
        //也可以在构造客户端fs对象时，通过参数传递进去
        FileSystem hdfs = FileSystem.get(new URI(hdfsPath), getHdfsConfig(), "hadoop");
        return hdfs;
    }

    /**
     * 读取HDFS文件内容
     */

    public static <ufferedReader> void readFile(String filePath) throws Exception {
        FileSystem fs = getFileSystem();
        Path path = new Path(filePath);
        InputStream in = null;
        try {
            in = fs.open(path);
            //复制到标准输出流
            IOUtils.copyBytes(in, System.out, 4096, false);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            System.out.println("\n读取文件成功！");
        } catch (Exception e) {
            System.out.println("\n读取文件失败！");
        } finally {
            IOUtils.closeStream(in);
        }
    }
    public static <ufferedReader> List<Pos> readFileClass(String filePath,Integer id) throws Exception {
        FileSystem fs = getFileSystem();
        Path path = new Path(filePath);
        InputStream in = null;
        try {
            in = fs.open(path);
            //复制到标准输出流
      //      IOUtils.copyBytes(in, System.out, 4096, false);
           InputStreamReader r = new InputStreamReader(in, "UTF-8");
            BufferedReader reader = new BufferedReader(r);

            String str="";
            List<Pos> positions=new LinkedList<>();
            while ((str=reader.readLine())!=null)
            {
                String []split=str.split(",");
                if(split[0].equals(id.toString())) {
                    Pos p = new Pos();
                    p.id = split[0];
                    p.setLat(split[1]);
                    p.setLng(split[2]);
                    p.time = split[3];
                    positions.add(p);
                }
            }
          /*  for(int i=0;i<positions.size();i++)
            {
                System.out.println(positions.get(i).id);
            }
         */
            System.out.println("\n读取文件成功！");
            return positions;
        } catch (Exception e) {
            System.out.println("\n读取文件失败！");
            LinkedList po=new LinkedList();
            return po;
        } finally {
            IOUtils.closeStream(in);

        }
    }

    public static <ufferedReader> List<Pos> readFileRedis(String filePath,Integer id) throws Exception {
        try {
           Jedis jedis=new Jedis("localhost");
            System.out.println("连接成功");
            String str="";
            List<String> list=new LinkedList<>();
            List<Pos> positions=new LinkedList<>();
            list=jedis.lrange(id.toString(),0,jedis.llen("1")-1);
             for(int i=0;i<list.size();i++)
             {
                 str=list.get(i);
                 String []split=str.split(",");
                     Pos p = new Pos();
                     p.id = split[0];
                     p.setLat(split[1]);
                     p.setLng(split[2]);
                     p.time = split[3];
                     positions.add(p);
             }
            System.out.println("\n读取Redis成功！");
            return positions;
        } catch (Exception e) {
            System.out.println("\n读取Redis失败！");
            LinkedList po=new LinkedList();
            return po;
        }
    }

    public static <ufferedReader> void readFileToRedis(String filePath) throws Exception {
        FileSystem fs = getFileSystem();
        Path path = new Path(filePath);
        InputStream in = null;
        try {
            in = fs.open(path);
            //复制到标准输出流
            //      IOUtils.copyBytes(in, System.out, 4096, false);
            InputStreamReader r = new InputStreamReader(in, "UTF-8");
            BufferedReader reader = new BufferedReader(r);

            String str="";
            List<Pos> positions=new LinkedList<>();
            Jedis jedis=new Jedis("localhost");
            System.out.println("连接成功");
            while ((str=reader.readLine())!=null)
            {
                String []split=str.split(",");
               jedis.lpush(split[0],str);
            }
            System.out.println("\n读入Redis成功！");
            //return positions;
        } catch (Exception e) {
            System.out.println("\n读取文件失败！");
            LinkedList po=new LinkedList();
          //  return po;
        } finally {
            IOUtils.closeStream(in);

        }
    }

   /*public static void main(String[] args) {
        try
        {
            readFileToRedis(hdfsPath);
        }catch (Exception e)
        {}
    }   */

        /**
         * 递归创建目录
         */
   /* public void mkdir(String path) throws Exception {
        FileSystem fs = getFileSystem();

        Path srcPath = new Path(path);

        boolean isOk = fs.mkdirs(srcPath);

        if (isOk) {
            System.out.println("create dir success...");

        } else {

            System.out.println("create dir failure...");
        }
        fs.close();
    }
*/

        /**
         * 在HDFS创建文件，并向文件填充内容
         */

 /*   public void createFile(String filePath, byte[] files) {
        try {
            FileSystem fs = getFileSystem();
            //目标路径
            Path path = new Path(filePath);
            //打开一个输出流
            FSDataOutputStream outputStream = fs.create(path);
            outputStream.write(files);
            outputStream.close();
            fs.close();
            System.out.println("创建文件成功！");
        } catch (Exception e) {
            System.out.println("创建文件失败！");
        }
    }

*/

    }




