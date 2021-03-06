package clean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CleanDriver implements Tool {

    private Configuration configuration;

    public int run(String[] strings) throws Exception {
        //创建Job
        Job job = Job.getInstance(configuration);

        //设置运行环境
        job.setJarByClass(CleanDriver.class);

        //设置对应的MapperReduce类
        job.setMapperClass(CleanMapper.class);
        job.setReducerClass(CleanReduce.class);

        //设置Mapper输出的
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        //设置全局的输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //设置输出输入路径
        FileInputFormat.setInputPaths(job,new Path(strings[0]));
        FileOutputFormat.setOutputPath(job,new Path(strings[1]));
     /*   Path in = new Path("hdfs://localhost:9000/maoyan/in/");
        Path out = new Path("hdfs://localhost:9000/maoyan/out"); */

        //不需要reduce
     //   job.setNumReduceTasks(0);


        //提交
        job.submit();
        return 1;
    /*    boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);   */

    }

    public void setConf(Configuration configuration) {
        this.configuration=configuration;
    }

    public Configuration getConf() {
        return configuration;
    }

    //主函数
    public static void main(String[] args) throws Exception{
        ToolRunner.run(new CleanDriver(),args);
    }
}


