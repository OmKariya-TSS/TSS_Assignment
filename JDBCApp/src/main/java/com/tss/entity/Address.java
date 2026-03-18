package com.tss.entity;

public class Address {
    private int addressId;
    private String city;
    private String state;
    private String pinCode;
    private int student_id;

    public Address(int addressId, String city, String state, String pinCode, int student_id) {
        this.addressId = addressId;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.student_id = student_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", student_id=" + student_id +
                '}';
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }


}
