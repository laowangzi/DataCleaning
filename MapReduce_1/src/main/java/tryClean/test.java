package tryClean;
import java.util.*;
public class test {
    public static void main(String[] args) {
        String s=new String("402,30.744424, ,224609,,666");
        String split[]=s.split(",");
        int i=0;
        for(i=0;i<split.length;i++)
        {
            if(split[i].equals(" "))
            break;
        }
      //  for(int i=0;i<split.length;i++)
        System.out.println(i);
    }
}
