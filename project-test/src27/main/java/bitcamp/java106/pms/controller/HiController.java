package bitcamp.java106.pms.controller;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;

@Component("hi")
public class HiController implements Controller {
    Scanner keyScan;
    
    public HiController(Scanner scanner) {
        this.keyScan = scanner;
    }
    
    public void service(String menu, String option) {
        System.out.printf("'%s'님 안녕하세요!", option);
    }

}
