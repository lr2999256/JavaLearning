package com.learn.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.midi.ControllerEventListener;

public class Test {

    public static boolean isSuccess(String msgCd){
        String lastNo = msgCd.substring(msgCd.length()-5,msgCd.length());
        if("00000".equals(lastNo)){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        boolean ste = isSuccess("GTS00678");

        String testStr = "ordNo|ordAmt|";
        String splitStr = "\\|";
        String[] splitStrs = testStr.split(splitStr);
        int index = 0;
        for(String tmp : splitStrs){
            if("dd".equals(tmp)){
                break;
            }
            index++;
        }
        System.out.print("==========="+index);
//        new Thread(()-> System.out.println("1"));
        new Thread(()->System.out.println("too much code, for too little to do")).start();;
        // Java 8之前：
//        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        for (String feature : features) {
//            System.out.println(feature);
//        }
//        features.forEach(feature->System.out.println(feature));
//        features.forEach(action->System.out.println("你好"+action));
//        
//        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//        
//        System.out.println("Languages which starts with J :");
//        filter(languages, (x)->(x.length()==3));
//        filterLambda(languages,(x)->(x.length()==3));
        
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//        for (Integer cost : costBeforeTax) {
//            double price = cost + .12*cost;
//            System.out.println(price);
//        }
//        costBeforeTax.forEach(c->{
//            double p = c + 0.12*c;
//            System.out.println(p);
//        });
        List<Double> xx = costBeforeTax.stream().map(c -> (c + 0.12*c)).collect(Collectors.toList());
        double yy = xx.stream().reduce((a, b) -> a + b).get();
        double zz = xx.stream().reduce(Math::max).get();
        double zz1 = xx.stream().reduce((a,b)->Math.max(a, b)).get();
        System.out.println(zz1);
    }
    
    public static void filter(List<String> names, Predicate<String> condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
    
    public static void filterLambda(List<String> names, Predicate<String> condition) {
//        names.forEach((name)->{
//            System.out.println(name);
//        });
        List<String> x = names.stream().filter((name)->(condition.test(name))).collect(Collectors.toList());
//        names.stream().filter((name)->(condition.test(name))).forEach((name)->{
//            System.out.println(name);
//        });;
        x.forEach(n->System.out.println(n));
    }
}
