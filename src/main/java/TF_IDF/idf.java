package TF_IDF;

import static TF_IDF.base.*;
import static TF_IDF.tf.TF;

/**
 * Created by Administrator on 2017/7/10.
 */
public class idf {
    public static double[] IDF(){
        String id="248645";
        double result[]=new double[getList(id)];
        for(int i=0;i<getList(id);i++){
            result[i]=Math.log(filecount("电影")/(filecount(id)+1));
        }
        return result;
    }

    public static void main(String[] args) {
        double[] idf=IDF();
        double[] tf=TF();
        for(int i=0;i<tf.length;i++){
            System.out.println(tf[i]*idf[i]);
        }
    }
}
