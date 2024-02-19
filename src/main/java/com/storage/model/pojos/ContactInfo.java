package com.storage.model.pojos;

public class ContactInfo {
    private int id;
    private String city;
    private String street;
    private int streetNumber;
    private String country;
    private String phoneNumber;


    public ContactInfo() {
    }

    public ContactInfo(int id, String city, String street, int streetNumber, String country, String phoneNumber) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
