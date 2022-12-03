package sims.view;

import sims.domain.Student;
import sims.service.StuInfService;
import sims.utils.Utility;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * 1. 显示界面
 * 2. 接收用户的输入
 * 3. 调用 StuInfService 完成对学生信息的各种操作
 */
public class StuInfView {

    private boolean loop;    //控制显示菜单
    private char key;         //接收用户选择
    private StuInfService stuInfService;    //通过该对象调用StuInfService方法
    private Scanner scanner;
    private Student student;    //存储用于添加,修改,删除的学生对象

    public StuInfView() {
        loop = true;
        key = ' ';
        stuInfService = new StuInfService();
        scanner = new Scanner(System.in);
        student = null;
    }

    public boolean addStudent() {
        String name, sex, birthday, address, phone, email;
        int age;
        int count = 0; //标识是否有学生加入
        System.out.println("请输入学生信息:");
        System.out.println("学号\t\t姓名\t\t年龄\t\t性别\t\t出生年月\t\t地址\t\t电话\t\tE-mail");
        boolean flag = true;
        do {
            //输入
            String sno = scanner.next();
            if (sno.equals("#")) {
                flag = false;
                break;
            }
            name = scanner.next();
            age = scanner.nextInt();
            sex = scanner.next();
            birthday = scanner.next();
            address = scanner.next();
            phone = scanner.next();
            email = scanner.next();
            //初始化Student对象
            student = new Student();
            student.setSno(sno);
            student.setName(name);
            student.setAge(age);
            student.setSex(sex);
            student.setBirthday(birthday);
            student.setAddress(address);
            student.setPhone(phone);
            student.setEmail(email);
            //调用StuInfService add方法
            if (!stuInfService.add(student)) {
                System.out.println("[" + sno + "," + name + "]" + "添加失败,学号[" + sno + "]已存在");
            } else {
                count = 1;
            }
        } while (flag);
        //如果没有学生添加,返回false
        if (count == 0) {
            return false;
        }
        return true;
    }

    public boolean delStudent() {
        //输入
        System.out.print("请输入要删除学生学号:");
        String sno = scanner.next();
        if (stuInfService.snoIsExist(sno)) {
            String name = stuInfService.getStudents().get(sno).getName();
            if (stuInfService.del(sno)) {
                System.out.println("删除[学号:" + sno + ",姓名:" + name + "]成功");
            }
        } else {
            System.out.println("删除失败,该学生不存在!");
            return false;
        }
        return true;
    }

    public boolean updateStudent() {
        System.out.print("请输入要修改学生的学号:");
        String sno = scanner.next();
        if (stuInfService.snoIsExist(sno)) {
            System.out.println("\t\t\t学号\t姓名\t年龄\t性别\t出生年月\t地址\t电话\tE-mail");
            student = stuInfService.getStudents().get(sno);
            System.out.println("待修改学生信息:" + student.toString());
            String name, sex, birthday, address, phone, email;
            int age;
            System.out.print("修改后学生信息:" + sno);
            name = scanner.next();
            age = scanner.nextInt();
            sex = scanner.next();
            birthday = scanner.next();
            address = scanner.next();
            phone = scanner.next();
            email = scanner.next();
            //设置修改后属性
            student.setName(name);
            student.setAge(age);
            student.setSex(sex);
            student.setBirthday(birthday);
            student.setAddress(address);
            student.setPhone(phone);
            student.setEmail(email);
            stuInfService.update(sno, student);
            System.out.println("修改完成");
        } else {
            System.out.println("输入学号有误,不存在该学生");
            return false;
        }
        return true;
    }

    /**
     * 查找学生信息 ch=='1',按学号查找; ch=='2',按姓名查找
     *
     * @param ch
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void findStudent(char ch)  {
        student = null;
        if (ch == '1') {
            System.out.print("请输入学号:");
            String sno = scanner.next();
            student = stuInfService.findBySno(sno);
        } else if (ch == '2') {
            System.out.print("请输入姓名:");
            String name = scanner.next();
            student = stuInfService.finByName(name);
        } else {
            return;
        }
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("输入学号有误,不存在该学生");
        }
    }

    public void listStudent() {
        //展示学生信息
        stuInfService.show();
    }

    public void exit() {
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }

    /**
     * 显示菜单
     */
    public void mainMenu() throws IOException, ClassNotFoundException {
        do {
            //读取文件数据
            stuInfService.inputFromFile();
            System.out.println("\n=============学生信息管理系统============");
            System.out.println("\t\t\t1 学 生 信 息 录 入");
            System.out.println("\t\t\t2 查 找 学 生 信 息");
            System.out.println("\t\t\t3 删 除 学 生 信 息");
            System.out.println("\t\t\t4 修 改 学 生 信 息");
            System.out.println("\t\t\t5 学 生 信 息 列 表");
            System.out.println("\t\t\t6 从 文 件 录 入 信 息");
            System.out.println("\t\t\t7 退      出");
            System.out.print("请输入你的选择(1-7): ");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    if (addStudent()) {   //添加成功
                        //更新文件数据
                        stuInfService.outputToFile();
                    }
                    break;
                case '2':
                    System.out.println("\t\t\t1 按 学 号 查 找");
                    System.out.println("\t\t\t2 按 姓 名 查 找");
                    System.out.print("请输入你的选择(1-2): ");
                    key = Utility.readChar();
                    findStudent(key);
                    break;
                case '3':
                    if (delStudent()) {   //删除成功
                        //更新文件数据
                        stuInfService.outputToFile();
                    }
                    break;
                case '4':
                    if (updateStudent()) {  //修改成功
                        //更新文件数据
                        stuInfService.outputToFile();
                    }
                    break;
                case '5':
                    listStudent();
                    break;
                case '7':
                    exit();
                    break;
            }
        } while (loop);
    }
}
