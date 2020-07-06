package clean;
import java.util.*;
public class CleanTool {


    public static String FormatString(String s){

        s=s.substring(0,s.length()-3);    //针对本数据集的特异性处理

        //切割数据
        String[] split = s.split(",");

        //过滤脏数据 超过四列的数据均过滤去除
        if(split.length>4){
            return null;
        }
        //去除空值
        for(int i=0;i<split.length;i++)
        {
            if (split[i].indexOf(' ')!=-1)
                return null;
        }
        //去除不符合范围的数据
      Double Longitude=new Double(split[2]);
        Double  Latitude=new Double(split[1]);
        if(Longitude<102.54||Longitude>104.53)
        {
            return null;
        }
        if(Latitude<30.05||Latitude>31.26)
        {
            return null;
        }

     return s;

    }

}

