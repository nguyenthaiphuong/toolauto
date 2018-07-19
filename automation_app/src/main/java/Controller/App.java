/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.SpringConfig;
import ConstantVariable.Constant;
import Service.*;
import Service.Codenvy;
import Service.CreateWebdriver;
import Service.CreateWebdriver;
import Service.Doc_file_kieu_txt;
import Views.JFrame;
import java.util.List;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static boolean flag = false;
    static CreateWebdriver createWebdriver;
    public static WebDriver webDriver;
    static Codenvy codenvy;

    public void start() {
        try {
//            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
            createWebdriver = (CreateWebdriver) context.getBean("createWebdriver");
            codenvy = (Codenvy) context.getBean("codenvy");
            startAuto();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }

    }

    public String startAuto() {
        try {
            if (!flag) {
                flag = true;
                List<String> lists = (List<String>) Doc_file_kieu_txt.readFile(JFrame.path_user);
                try {
                    for (int i = 0; i < lists.size(); i++) {
                        webDriver = createWebdriver.getGoogle(JFrame.path_user);
                        codenvy.Start(webDriver, lists.get(i));
                    }
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, "loi 1 "+e.getMessage());
                    e.getMessage();
                }
            }
            return null;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"loi 2 "+ e.getMessage());
            return "loi : " + e.getMessage();
        }

    }

}
