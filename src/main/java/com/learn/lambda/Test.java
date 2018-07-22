package com.learn.lambda;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //单轴快排
    private static void deScanSwapSort(int[] items, int start, int end) {
        if(start<end) {
            int pivot = items[start];
            int i = start + 1;
            int j = end;
            for (; i <= j; i++) {
                if (items[i] > pivot) {
                    for (; i <= j; j--) {
                        if (items[j] < pivot) {
                            swap(items, i, j);
                            break;
                        }
                    }
                }
            }
            swap(items, start, j);
            deScanSwapSort(items, start, j - 1);
            deScanSwapSort(items, j + 1, end);
        }
    }

    //赋值填充方式
    public static void fillSort(int[] items, int start, int end) {
        if(start<end){
            int pivot =  items[start];
            int i = start;
            int j = end;
            while (i < j) {
                while (i < j) {
                    if (items[j] < pivot) {
                        items[i] = items[j];
                        break;
                    }else {
                        j--;
                    }
                }
                while (i < j) {
                    if (items[i] > pivot) {
                        items[j] = items[i];
                        break;
                    }else {
                        i++;
                    }
                }
            }
            items[i]=pivot;
            fillSort(items,start,j-1);
            fillSort(items,j+1,end);
        }
    }

    //单向扫描划分方式
    public static void forwardScanSort(int[] items, int start, int end) {
        if(start < end) {
            int pivot = items[start];
            int i = start;
            int j = start + 1;
            while (j <= end) {
                if (items[j] < pivot) {
                    i++;
                    swap(items, i, j);
                }
                j++;
            }
            swap(items, start, i);
            forwardScanSort(items, start, i - 1);
            forwardScanSort(items, i + 1, end);
        }
    }

    //三分单向扫描
    public static void div3ScanSort(int[] items, int start, int end) {
        if(start<end) {
            int pivot = items[start];
            int i = start;
            int k = start+1;
            int j = end;
            while(k<j){
                if(items[k]<pivot){
                    swap(items,i,k);
                    i++;
                    k++;
                }else if(items[k]>pivot){
                    swap(items,j,k);
                    j--;
                }else{
                    k++;
                }
            }
            div3ScanSort(items,start,i-1);
            div3ScanSort(items,j+1,end);
        }
    }

    //双轴快排
    public static void dualPivotQuickSort1(int[] items, int start, int end) {
        if(start<end) {
            int pivot1 = items[start];
            int pivot2 = items[end];
            //保证左边的比右边的小
            if(pivot1>pivot2){
                swap(items,start,end);
            }
            int i = start;
            int k = start+1;
            int j = end;
            while(k<=j){
                if(items[k]<pivot1){
                    i++;
                    swap(items,i,k);
                    k++;
                }else if(items[k]>pivot2){
                    j--;
                    swap(items,j,k);
                    k++;
                }else{
                    k++;
                }
            }
            swap(items, start, i);
            swap(items, end, j);
            dualPivotQuickSort(items,start,i-1);
            dualPivotQuickSort(items,i+1,j-1);
            dualPivotQuickSort(items,j+1,end);
        }
    }

    public static void dualPivotQuickSort(int[] items, int start, int end) {
        if (start < end) {
            if (items[start] > items[end]) {
                swap(items, start, end);
            }
            int pivot1 = items[start], pivot2 = items[end];
            int i = start, j = end, k = start + 1;
            while (k < j) {
                if (items[k] < pivot1) {
                    swap(items, ++i, k++);
                } else if (items[k] <= pivot2) {
                    k++;
                } else {
//                    while (items[--j] > pivot2) {
//                        if (j <= k) {
//                            // 扫描终止
//                            break;
//                        }
//                    }

                    if (items[j] < pivot1) {
                        swap(items, j, k);
                        swap(items, ++i, k);
                    } else {
                        swap(items, j, k);
                    }
                    k++;
                }
            }
            swap(items, start, i);
            swap(items, end, j);

            dualPivotQuickSort(items, start, i - 1);
            dualPivotQuickSort(items, i + 1, j - 1);
            dualPivotQuickSort(items, j + 1, end);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int right = 7;
        int left = 0;
        int[] arr = {9,20,4,5,3,5,6,9};


//        deScanSwapSort(arr,0,7);
//        fillSort(arr,0,7);
//        forwardScanSort(arr,0,7);
//        div3ScanSort(arr,0,7);
        dualPivotQuickSort1(arr,0,7);
        //插入排序
//        for (int i = 1; i < arr.length; i++) {
//            int temp = arr[i];
//            int j = i;
//            for(; j > 0 && arr[j-1] >= temp;j--) {
//                arr[j] = arr[j-1];
//            }
//            arr[j] = temp;
//        }

//        int len = right - left;
//        //数组长度如果很小（27），则直接用冒泡排序对其排序
//        if (len < 27) {
//            for (int i = left + 1; i <= right; i++) {
//                for (int j = i; j > left; j--) {
//                    if(arr[j] < arr[j - 1]) {
//                        swap(arr, j, j - 1);
//                    }
//                }
//            }
//        }

        //冒泡排序
//        for(int i=1; i< arr.length; i++){
//            for(int j=i; j>0; j--){
//                if(arr[j] < arr[j-1]){
//                    swap(arr,j,j-1);
//                }
//            }
//        }

        //冒泡排序
//        for(int i=0; i<arr.length; i++){
//            for(int j=i+1; j<arr.length; j++){
//                if(arr[i]>arr[j]){
//                    swap(arr,i,j);
//                }
//            }
//        }

        //选择排序
//        for(int i=0; i<arr.length; i++){
//            int temp = i;
//            for(int j=i+1; j<arr.length; j++){
//                if(arr[temp]>arr[j]){
//                    temp = j;
//                }
//            }
//            swap(arr,temp,i);
//        }

        ArrayList<String> arrays = new ArrayList<>();
        ArrayList<String> arrays1 = new ArrayList<>();
        arrays1.add("aab");
        boolean beat = arrays.addAll(0,arrays1);


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
        new Thread(()->System.out.println("too much code, for too little to do")).start();
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
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);
        blockingQueue.add(new TestRunable());
        Executor executor = new ThreadPoolExecutor(10, 60, 60, TimeUnit.MINUTES, blockingQueue);
        Runnable test = blockingQueue.poll();
        if(null != test) {
            synchronized(test) {
                executor.execute(test);
            }
        }
        ;
        File file = new File("C://a.txt");
        try (FileInputStream inputStream = new FileInputStream(file)){

        }catch (Exception e){

        }
        Lock lock = new ReentrantLock();
        lock.unlock();
        Object left1 = new Object();
        synchronized (left1){
            System.out.println("a");
        }

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
