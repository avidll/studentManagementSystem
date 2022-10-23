package sims;

import sims.view.StuInfView;

import java.io.IOException;

public class StuInfMagSysApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new StuInfView().mainMenu();
        System.out.println("=====你退出学生信息管理系统=====");
    }
}
