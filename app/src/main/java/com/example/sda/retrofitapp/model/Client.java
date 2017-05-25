package com.example.sda.retrofitapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Client {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("TaxIdentityNo")
    @Expose
    private String taxIdentityNo;
    @SerializedName("Regon")
    @Expose
    private String regon;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("PhoneNo1")
    @Expose
    private String phoneNo1;
    @SerializedName("PhoneNo2")
    @Expose
    private String phoneNo2;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CountryCode")
    @Expose
    private Object countryCode;
    @SerializedName("KRS")
    @Expose
    private String kRS;
    @SerializedName("WWW")
    @Expose
    private String wWW;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("ContactIds")
    @Expose
    private List<Object> contactIds = null;
    @SerializedName("ContactExtIds")
    @Expose
    private List<Object> contactExtIds = null;
    @SerializedName("InsertDate")
    @Expose
    private String insertDate;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("ExtId")
    @Expose
    private String extId;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxIdentityNo() {
        return taxIdentityNo;
    }

    public void setTaxIdentityNo(String taxIdentityNo) {
        this.taxIdentityNo = taxIdentityNo;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNo1() {
        return phoneNo1;
    }

    public void setPhoneNo1(String phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Object countryCode) {
        this.countryCode = countryCode;
    }

    public String getKRS() {
        return kRS;
    }

    public void setKRS(String kRS) {
        this.kRS = kRS;
    }

    public String getWWW() {
        return wWW;
    }

    public void setWWW(String wWW) {
        this.wWW = wWW;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Object> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<Object> contactIds) {
        this.contactIds = contactIds;
    }

    public List<Object> getContactExtIds() {
        return contactExtIds;
    }

    public void setContactExtIds(List<Object> contactExtIds) {
        this.contactExtIds = contactExtIds;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", taxIdentityNo='" + taxIdentityNo + '\'' +
                ", regon='" + regon + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNo1='" + phoneNo1 + '\'' +
                ", phoneNo2='" + phoneNo2 + '\'' +
                ", country='" + country + '\'' +
                ", countryCode=" + countryCode +
                ", kRS='" + kRS + '\'' +
                ", wWW='" + wWW + '\'' +
                ", email='" + email + '\'' +
                ", contactIds=" + contactIds +
                ", contactExtIds=" + contactExtIds +
                ", insertDate='" + insertDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", extId='" + extId + '\'' +
                ", id=" + id +
                '}';
    }
}
