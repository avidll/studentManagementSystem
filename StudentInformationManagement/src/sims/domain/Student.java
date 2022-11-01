package sims.domain;


import java.io.Serializable;

/**
 * 学生信息包括：学号，姓名，年龄，性别，出生年月，地址，电话，E-mail等。
 * （1）主要功能：
 * ①系统以菜单方式工作
 * ②学生信息录入功能（学生信息用文件保存）
 * ③学生信息浏览功能
 * ④学生信息查询功能（按学号查询、按姓名查询）
 * ⑤学生信息的删除与修改
 * ⑥学生信息的排序（按学号，按年龄）
 * （2）要求：使用文件方式存储数据。
 */
public class Student implements Serializable {
    private String sno;          //学号
    private String name;        //姓名
    private int age;            //年龄
    private String sex;         //性别
    private String birthday;  //出生年月
    private String address;     //地址
    private String phone;       //电话
    private String email;       //E-mail

    public String getSno() {
        return sno;
    }

    public void setSno(String no) {
        this.sno = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return sno + "\t\t" + name + "\t\t" + age + "\t\t" +
                sex + "\t\t" + birthday + "\t\t" + address + "\t\t" +
                phone + "\t\t" + email + "\t\t";
    }
}
