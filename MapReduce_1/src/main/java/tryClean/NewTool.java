package tryClean;

public class NewTool {
    public static String FormatString(String s){
        //切割数据
        String[] split = s.split(",");

        //过滤脏数据 超过四列的数据均过滤去除
        if(split.length>4){
            return null;
        }
        for(int i=0;i<4;i++)   //去除空值
        {
            if (split[i].equals(' '))
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
        //去除重复数据
        //数据替换
 /*       split[3]=split[3].replace(" ","");//将空格去掉

        StringBuilder sb=new StringBuilder();

        //类型拼接
        for(int i=0;i<split.length;i++){
            if(i<9){
                if(i==split.length-1){
                    sb.append(split[i]);
                }else{
                    sb.append(split[i]+"\t");
                }
            }else {
                if(i==split.length-1){
                    sb.append(split[i]);
                }else{
                    sb.append(split[i]+"&");
                }
            }
        }
        return sb.toString();   */
        return s;

    }

}
