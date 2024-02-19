package com.storage.model.pojos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private double salary;
    private ContactInfo contactInfo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private boolean gender;
    private Employee employee;

    public Employee() {
    }

    public Employee(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public Employee(int id, String name, int eId, String eName) {
        this(id, name);
        this.employee = new Employee(eId, eName);
    }

    public Employee(int id, String firstName, String lastName, double salary, ContactInfo contactInfo, LocalDate birthday, boolean gender) {
        this(id, firstName);
        this.lastName = lastName;
        this.salary = salary;
        this.contactInfo = contactInfo;
        this.birthday = birthday;
        this.gender = gender;
    }

    public Employee(Employee employee) {
        this.employee = employee;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getEmployeeId(){
        if (this.employee.getId() == 0){
            return 0;
        }
        return this.employee.getId();
    }

    public void setEmployeeId(int id){
        this.employee.setId(id);
    }

    public String getEmployeeName(){
        return this.employee.getFirstName();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = LocalDate.parse(birthday);
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
