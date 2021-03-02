package com.Ray.util.recommend;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class CosineSim {
    public static double threshHold = 0.001 ;

    public static double getCosineSim(Vector<String> A, Vector<String> B) throws Exception{
        int size1=0, size2=0;
        if(A!=null && (size1=A.size())>0 && B!=null && (size2=B.size())>0){
            Map<String, Double[]> InterAB = new HashMap<>();
            for (String a :
                    A) {
                Double[] vec = new Double[2];
                vec[0] = 1.0;
                vec[1] = threshHold;
                InterAB.put(a, vec);
            }
            for (String b:
                    B) {
                if(InterAB.get(b)!=null){
                    Double[] vec = InterAB.get(b);
                    vec[1] = 1.0;
                }else {
                    Double[] vec = new Double[2];
                    vec[0] = threshHold;
                    vec[1] = 1.0;
                    InterAB.put(b, vec);
                }
            }
            Iterator<String> iterator = InterAB.keySet().iterator();
            Double dotV = 0.0, lenA = 0.0, lenB = 0.0;
            while (iterator.hasNext()){
                Double[] vec = InterAB.get(iterator.next());
                lenA += vec[0]*vec[0];
                lenB += vec[1]*vec[1];
                dotV += vec[0]*vec[1];
            }
            return dotV / Math.sqrt(lenA*lenB);
        }else {
            throw new Exception("传入的词向量有问题");
        }
    }

    public static void main(String[] args) {
        Vector<String> A = new Vector<>();
        Vector<String> B = new Vector<>();
        String[] toA = "汤面 方便面 日式 好吃".split(" ");
        String[] toB = "方便面 好吃 鸡 蘑菇".split(" ");
        for (String a:
             toA) {
            A.add(a);
        }
        for (String b:
                toB) {
            B.add(b);
        }
        try {
            double similarity = getCosineSim(A, B);
            System.out.println(similarity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
