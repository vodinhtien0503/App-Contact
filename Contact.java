package com.example.contact;

import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable {
    private String name, Company, Mobile, Title, Email,Avatar;
    private Date DateCreate;
    private int Id;
    private String CreateAt;

    public Contact(int Id, String name, String Company, String Mobile, String Title, String Email) {
        this.name = name;
        this.Id=Id;
        this.Company = Company;
        this.Mobile = Mobile;
        this.Email = Email;
        this.Title = Title;
        //this.CreateAt=Create;
    }

    public Contact() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public Date getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        DateCreate = dateCreate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String createAt) {
        CreateAt = createAt;
    }
}
