package com.tss.Creational.builder.model;

public class User {
    private final String name;
    private final int age;
    private final String email;
    private final String phone;
    private final String address;
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }
    public static class Builder{
        private String name;
        private int age;
        private String email;
        private String phone;
        private String address;
        public Builder(String name,int age){
            this.name = name;
            this.age = age;
        }
        public Builder email(String email){
            this.email=email;
            return this;
        }
        public Builder phone(String phone){
            this.phone=phone;
            return this;
        }
        public Builder address(String address){
            this.address=address;
            return this;
        }
        public User build(){
            return new User(this);
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
