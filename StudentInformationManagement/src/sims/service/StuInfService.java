package sims.service;

import sims.domain.Student;


import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

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

    //Key: sno 学号, Value: Student  存放学生对象
    private HashMap<String, Student> students = new HashMap<>();
    //数据存放的路径
    private String path;

    public StuInfService() {
        students = new HashMap<>();
        path = "StudentInformationManagement/src/sims/data.dat";
    }

    /**
     * 判断学号是否存在在数据中,存在返回true
     * 参数: 学号
     */
    public boolean snoIsExist(String sno) {
        Set<Map.Entry<String, Student>> entrySet = students.entrySet();
        Iterator<Map.Entry<String, Student>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Student> entry = iterator.next();
            if (sno.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加学生信息
     * 判断添加学生学号是否存在,存在 return false
     */
    public boolean add(Student student) {
        //获取添加学生学号
        String sno = student.getSno();
        //如果存在
        if (snoIsExist(sno))
            return false;
        else
            students.put(sno, student);
        return true;
    }

    /**
     * 修改学生信息
     */

    /**
     * 从txt文件添加学生信息
     */
    public void addfromtxt(String path) throws IOException{
        String s;
        String[] spil;
        Student student;
        FileInputStream fileInputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((s=bufferedReader.readLine())!=null){
            spil = s.split(" ");
            student  = new Student();
            student.setSno(spil[0]);
            student.setName(spil[1]);
            student.setAge(Integer.parseInt(spil[2]));
            student.setSex(spil[3]);
            student.setBirthday(spil[4]);
            student.setAddress(spil[5]);
            student.setPhone(spil[6]);
            student.setEmail(spil[7]);
            students.put(spil[0],student);
        }
    }
    public void update(String sno, Student student) {
        students.put(sno, student);
    }

    /**
     * 删除学生信息: 如果学号存在,则能删除,返回true,否则返回false
     */
    public boolean del(String sno) {
        //判断要删除学号是否存在
        if (snoIsExist(sno))
            students.remove(sno);
        else
            return false;
        return true;
    }

    /**
     * 通过学号查询学生信息
     */
    public Student findBySno(String sno) {
        Student student = null;
        if (snoIsExist(sno))
            student = students.get(sno);
        return student;
    }

    /**
     * 通过姓名查询学生信息
     */
    public Student finByName(String name) {
        Student student = null;
        //遍历学生map
        Set<String> keySet = students.keySet();
        for (Object key : keySet) {
            Student student1 = students.get(key);
            if (student1.getName().equals(name))
                student = student1;
        }
        return student;
    }

    /**
     * 通过学号排序
     */
    public void sortBySno() {
        // 因为key为sno,遍历students时,以sno的hash值顺序遍历,即show()是用学号排序
        show();
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
        if (0 == file.length()) return;
        //对象输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        //读取数据
        HashMap studentsFromFile = (HashMap) ois.readObject();
        Set keySet = studentsFromFile.keySet();
        for (Object key : keySet) {
            Student student = (Student) studentsFromFile.get(key);
            String no = student.getSno();
            students.put(no, student);
        }
        ois.close();
    }

    /**
     * 输出学生信息
     */
    public void show() {
        Set<Map.Entry<String, Student>> entrySet = students.entrySet();
        Iterator<Map.Entry<String, Student>> iterator = entrySet.iterator();
        System.out.println("学号\t\t姓名\t\t年龄\t\t性别\t\t出生年月\t\t地址\t\t电话\t\tE-mail");
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Student student = (Student) entry.getValue();
            System.out.println(student);
        }
    }

    public HashMap<String, Student> getStudents() {
        return students;
    }
}
