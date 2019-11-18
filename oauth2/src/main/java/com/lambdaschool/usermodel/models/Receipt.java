package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "receipts")
@JsonIgnoreProperties(value = {"receiptid", "user"})
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long receiptid;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String merchantname;

    private String imageurl;

    @Transient
    @ManyToOne
    @JsonIgnoreProperties("receipts")
    private User user;

    @Transient
    public boolean hasAmountSet = false;

    public Receipt() {
    }

    public Receipt(String date, double amount, String category, String merchantname, String imageurl, User user) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.merchantname = merchantname;
        this.imageurl = imageurl;
        this.user = user;
    }

    public long getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(long receiptid) {
        this.receiptid = receiptid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        hasAmountSet = true;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
