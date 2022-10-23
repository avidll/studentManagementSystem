package sims.view;

import sims.service.StuInfService;
import sims.utils.Utility;

/**
 * 1. 显示界面
 * 2. 接收用户的输入
 * 3. 调用 StuInfService 完成对学生信息的各种操作
 */
public class StuInfView {

    private boolean loop = true;    //控制显示菜单
    private char key = ' ';         //接收用户选择
    private StuInfService stuInfService = new StuInfService();


    public void delStudent() {
    }

    public void findStudent() {
    }

    public void addStudent() {
    }

    public void update() {
    }

    public void listStudent() {

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
    public void mainMenu() {
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
