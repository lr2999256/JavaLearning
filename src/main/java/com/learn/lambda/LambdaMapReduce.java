package com.learn.lambda;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class LambdaMapReduce {
    private static List<User> users = Arrays.asList(
            new User(1, "张三", 12, User.Sex.MALE),
            new User(2, "李四", 21, User.Sex.FEMALE),
            new User(3, "王五", 32, User.Sex.MALE),
            new User(4, "赵六", 32, User.Sex.FEMALE));

    public static void main(String[] args) {
        //与stream.reduce方法不同，Stream.collect修改现存的值，而不是每处理一个元素，创建一个新值 
        //获取所有男性用户的平均年龄 
        double maleagv = users.stream().filter(s->s.getGender().equals(User.Sex.MALE)).mapToDouble(User::getAge).average().getAsDouble();
        List<User> matchList = users.stream().filter(u->u.getName().equals("张三")).collect(Collectors.toList());
        //获取年龄大于12的用户列表 
        List<User> list = users.stream().filter(s -> s.getAge() > 12).collect(Collectors.toList());
        //按性别统计用户数
        Map<User.Sex, Integer> map = users.stream().collect(Collectors.groupingBy(User::getGender, Collectors.summingInt(s -> 1)));
        //按性别获取用户名称 
        Map<User.Sex, List<String>> map1 = users.stream()
                .collect(
                        Collectors.groupingBy(
                                User::getGender,
                                Collectors.mapping(User::getName,
                                        Collectors.toList())));
        System.out.println(map1);
        Button button = new Button();
        list.sort(Comparator.comparing(User::getAge));
        list.replaceAll(user ->  user.getAge() == 32 ? user : null );
        list.forEach(user -> System.out.print(user.getAge()));
        ActionListener oneArgument = event -> System.out.println("button clicked");
        button.addActionListener(oneArgument);
        BinaryOperator<Long> longLongObjectBiFunction = (Long x, Long y) -> x + y;
        Runnable aaa= () -> System.out.println("Hello World");
        //按性别求年龄的总和 
        Map<User.Sex, Integer> map2 = users.stream()
                .collect(
                        Collectors.groupingBy(
                                User::getGender,
                                Collectors.reducing(0, User::getAge, (a, b) -> (a + b))
                        )
                );
        //按性别求年龄的平均值
        Map<User.Sex, Double> map5 = users.stream().collect(
                Collectors.groupingBy(User::getGender,
                        Collectors.averagingInt(User::getAge)));
        //求年龄总和
        // 注意，reduce操作每处理一个元素总是创建一个新值，
        // Stream.reduce适用于返回单个结果值的情况
        int sum = users.stream().map(User::getAge).reduce((a, b) -> (a + b)).get();
        int sum1 = users.parallelStream().mapToInt(User::getAge).sum();
        //获取所有用户的平均年龄
        double avg = users.stream().mapToDouble(User::getAge).average().getAsDouble();
        //按年龄分组
        Map<Integer, List<User>> xyz = users.stream().collect(Collectors.groupingBy(user -> user.getAge()));
        System.out.println(avg);
    }
}