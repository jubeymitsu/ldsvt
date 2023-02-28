package ru.stomprf.main;

public class Customer {

    public Customer(){}

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, String phoneNumber, int discount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.discount = discount;
    }

    private Integer id;
    private String name;
    private String phoneNumber;
    private Integer discount;

    public Customer(Integer id, String name, String phoneNumber, int discount) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", discount=" + discount +
                '}';
    }
}
