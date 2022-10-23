package sims.view;

import sims.domain.Birthday;
import sims.domain.Student;
import sims.service.StuInfService;
import sims.utils.Utility;

import java.io.IOException;
import java.util.Scanner;

/**
 * 1. 显示界面
 * 2. 接收用户的输入
 * 3. 调用 StuInfService 完成对学生信息的各种操作
 */
public class StuInfView {

    private boolean loop = true;    //控制显示菜单
    private char key = ' ';         //接收用户选择
    private StuInfService stuInfService = new StuInfService();
    private Scanner scanner = new Scanner(System.in);
    private Student student = null;

    public void delStudent() {
    }

    public void findStudent() {
    }

    public void addStudent() throws IOException, ClassNotFoundException {
        //读取文件数据
        stuInfService.inputFromFile();
        String name, sex, birthday, address, phone, email;
        int age;
        int count = 0; //标识是否有学生加入
        System.out.println("请输入学生信息:");
        System.out.println("学号\t姓名\t年龄\t性别\t出生年月\t地址\t电话\tE-mail");
        boolean flag = true;
        do {
            //输入
            String no = scanner.next();
            if (no.equals("#")) {
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
            student.setNo(no);
            student.setName(name);
            student.setAge(age);
            student.setSex(sex);
            student.setBirthday(birthday);
            student.setAddress(address);
            student.setPhone(phone);
            student.setEmail(email);
            //调用StuInfService add方法
            if (!stuInfService.add(student)) {
                System.out.println(student.toString() + "添加失败," + no + "已存在");
            } else {
                count = 1;
            }
        } while (flag);
        //如果有学生添加,则把数据添加到文件中
        if (count == 1) {
            stuInfService.outputToFile();
        }
    }

    public void update() {
    }

    public void listStudent() {
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
            System.out.println("\n=============学生信息管理系统============");
            System.out.println("\t\t\t1 学 生 信 息 录 入");
            System.out.println("\t\t\t2 查 找 学 生 信 息");
            System.out.println("\t\t\t3 删 除 学 生 信 息");
            System.out.println("\t\t\t4 修 改 学 生 信 息");
            System.out.println("\t\t\t5 学 生 信 息 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.print("请输入你的选择(1-6): ");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addStudent();
                    break;
                case '2':
                    findStudent();
                    break;
                case '3':
                    delStudent();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    listStudent();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }
}
