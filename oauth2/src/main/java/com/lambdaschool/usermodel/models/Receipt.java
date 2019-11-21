package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ApiModel(value = "Receipt", description = "This holds the basic information of a receipt.")
@Entity
@Table(name = "receipts")
@JsonIgnoreProperties(value = {"user", "hasAmountSet"})
public class Receipt extends Auditable {
    @ApiModelProperty(name = "Receipt Id", value = "Primary Key for Receipt", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Size(min = 2, max = 30, message = "must be this size.")
    private long receiptid;

    @ApiModelProperty(name = "Date", value = "String field that holds the date.", required = true, example = "11/11/2019")
    @Column(nullable = false)
    private String date;

    @ApiModelProperty(name = "Amount", value = "Double field that holds the spendature.", required = true, example = "16.36")
    @Column(nullable = false)
    private double amount;

    @ApiModelProperty(name = "Category", value = "String field that holds the category.", required = true, example = "Eat Out")
    @Column(nullable = false)
    private String category;

    @ApiModelProperty(name = "Merchant Name", value = "String field that holds the name of merchant.", required = true, example = "Amazon")
    @Column(nullable = false)
    private String merchantname;

    @ApiModelProperty(name = "Image URL", value = "String field that holds the image url.")
    private String imageurl;

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
