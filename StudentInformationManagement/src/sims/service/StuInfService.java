package sims.service;

import sims.domain.Student;


import java.util.HashMap;

/**
 * 1. 完成对学生信息的各种操作 增删改查(crud)
 * （1）主要功能：
 * ①系统以菜单方式工作
 * ②学生信息录入功能（学生信息用文件保存）
 * ③学生信息浏览功能
 * ④学生信息查询功能（按学号查询、按姓名查询）
 * ⑤学生信息的删除与修改
 * ⑥学生信息的排序（按学号，按年龄）
 * （2）要求：使用文件方式存储数据。
 * 2. 响应 StuInfView 的调用
 */
public class StuInfService {

    //Key: cno 学号, Value: Student  存放学生对象
    private HashMap<String, Student> students;


    /**
     * 添加学生信息
     */
    public boolean add(Student student) {
        return true;
    }

    /**
     * 修改学生信息
     */
    public void update(String cno, Student student) {

    }

    /**
     * 删除学生信息
     */
    public boolean del(String cno) {
        return true;
    }

    /**
     * 通过学号查询学生信息
     */
    public boolean findByCno(String cno) {
        return true;
    }

    /**
     * 通过姓名查询学生信息
     */
    public boolean finByName(String name) {
        return true;
    }

    /**
     * 通过学号排序
     */
    public void sortByCno() {

    }

    /**
     * 通过年龄排序
     */
    public void sortByAge() {

    }
}
