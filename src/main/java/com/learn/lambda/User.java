package com.learn.lambda;

public class User {
    
    private int id;
    private String name;
    private int age;
    private Sex gender;
    public User(int id, String name, int age, Sex gender) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    } 
    public enum Sex {  
        MALE(0), FEMALE(1);
        // 成员变量   
        private int index;  
        // 构造方法  
        Sex( int index) {
            this.index = index;  
        }  
        
        public int getIndex() {  
            return index;  
        }  
        public void setIndex(int index) {  
            this.index = index;  
        }  
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Sex getGender() {
        return gender;
    }
    public void setGender(Sex sex) {
        this.gender = gender;
    }
}
