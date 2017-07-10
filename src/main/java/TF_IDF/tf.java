package TF_IDF;

import static TF_IDF.base.*;

/**
 * Created by Administrator on 2017/7/10.
 */
public class tf {
    public static double[] TF(){
        String id="248645";
        double result[]=new double[getList(id)];
        int maxWordCount=maxWordCount(id);
        for(int i=0;i<getList(id);i++){
            result[i]=WordCount(id,i)*1.0/maxWordCount;
        }
        return result;
    }
}
