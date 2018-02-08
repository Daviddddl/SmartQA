package com.smartQA.operation.dataobject;

/**
 * 描述:
 * 用户类
 *
 * @outhor didonglin
 * @create 2018-02-04 18:11
 */
public class User {

    //实体类的属性和表的字段名称一一对应
    private int id;
    private String nickName;
    private int gender;
    private String remark;
    private String lang;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String joinCourse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnickName() {
        return nickName;
    }

    public void setnickName(String nickName) {
        this.nickName = nickName;
    }

    public int getgender() {
        return gender;
    }

    public void setgender(int gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getJoinCourse() {
        return joinCourse;
    }

    public void setJoinCourse(String joinCourse) {
        this.joinCourse = joinCourse;
    }

    @Override
    public String toString() {
        return "User [id=" + id
                + ", nickName=" + nickName
                + ", gender=" + gender
                + ", remark=" + remark
                + ", lang=" + lang
                + ", city=" + city
                + ", province=" + province
                + ", country=" + country
                + ", avatarUrl=" + avatarUrl
                + ", joinCourse=" + joinCourse
                + "]";
    }
}