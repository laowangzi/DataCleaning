package clean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CleanMapper extends Mapper<LongWritable,Text,Text,NullWritable> {

    Text k=new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String s = value.toString();

        //数据清理
        String s1 = CleanTool.FormatString(s);

        //传递数据
        if(s1==null) return;
        System.out.println(s1);
        k.set(s1);

        context.write(k, NullWritable.get());
    }
}

