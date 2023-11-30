package com.example.entity;

public class Employee {
    protected int id;
    protected String fname;
    protected String lname;
    protected String company;
    protected String email;
    protected String role;
    protected String dept;
    public Employee() {

    }

    public Employee(String fname, String lname, String company, String email, String role, String dept) {
        super();
        this.fname = fname;
        this.lname = lname;
        this.company = company;
        this.email = email;
        this.role = role;
        this.dept = dept;
    }

    public Employee(int id, String fname, String lname, String company, String email, String role, String dept) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.company = company;
        this.email = email;
        this.role = role;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

}
