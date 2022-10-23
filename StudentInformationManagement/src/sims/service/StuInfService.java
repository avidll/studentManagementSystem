package sims.service;

import sims.domain.Student;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    private HashMap<String, Student> students = new HashMap<>();
    //数据存放的路径
    private String path = "e:\\data.dat";

    /**
     * 添加学生信息
     * 判断添加学生学号是否存在,存在 return false
     */
    public boolean add(Student student) {
        //获取添加学生学号
        String no = student.getNo();
        //遍历学生Map,判断是否有学号相同的数据
        Set entrySet = students.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String cno = (String) entry.getKey();
            if (no == cno) return false;
        }
        students.put(no, student);
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


    /**
     * 将数据保存到文件中
     */
    public void outputToFile() throws IOException {
        //清空文件数据,重新写入
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        //创建对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        //将students存入数据
        oos.writeObject(students);
        oos.close();
    }

    /**
     * 读出数据,将数据添加到students集合中
     */
    public void inputFromFile() throws IOException, ClassNotFoundException {
        //检查文件中是否为空,如果为空则不执行
        File file = new File(path);
        if (null == file || 0 == file.length() || file.exists()) return;
        //对象输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        //读取数据
        HashMap studentsFromFile = (HashMap) ois.readObject();
        Set keySet = studentsFromFile.keySet();
        for (Object key : keySet) {
            Student student = (Student) studentsFromFile.get(key);
            String no = student.getNo();
            students.put(no,student);
        }
        ois.close();
    }


    /**
     * 输出学生信息
     */
    public void show() {
        Set<Map.Entry<String, Student>> entrySet = students.entrySet();
        Iterator<Map.Entry<String, Student>> iterator = entrySet.iterator();
        System.out.println("学号\t姓名\t年龄\t性别\t出生年月\t地址\t电话\tE-mail");
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Student student = (Student) entry.getValue();
            System.out.println(student.toString());
        }
    }

}
