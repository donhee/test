package bitcamp.java106.pms;

import java.util.Scanner;

import bitcamp.java106.pms.domain.Team;


public class App {
    public static void main(String[] args) {
        Scanner keyScan = new Scanner(System.in);
        
        Team[] teams = new Team[5];
        
        int count = 0;
        for (int i = 0; i < teams.length; i++) {
            count++; // i = 0; count = 0; i == teams.length -1
            teams[i] = new Team();
            
            System.out.print("팀명? ");
            teams[i].teamName = keyScan.nextLine();
            
            System.out.print("설명? ");
            teams[i].description = keyScan.nextLine();
            
            System.out.print("최대인원? ");
            teams[i].maxQty = keyScan.nextInt();
            keyScan.nextLine(); // 숫자 뒤에 줄바꿈 코드를 읽는다
            // 읽고 난 뒤에 아무것도 안하기 때문에
            // 일종의 줄바꿈 코드를 제거하는 효과가 있다.
            
            System.out.print("시작일? ");
            teams[i].startDate = keyScan.nextLine();
            
            System.out.print("종료일? ");
            teams[i].endDate = keyScan.nextLine();
            
            if (count == teams.length) // i == teams.length
                break;
            
            System.out.print("계속 입력하시겠습니까?(Y/n)");
            String str = keyScan.nextLine();
            if (str.toLowerCase().equals("n"))
                break;
        }
        
        System.out.println("----------------");
        for (int i = 0; i < count; i++) {
        System.out.printf("%s, %d명, %s ~ %s\n", 
                teams[i].teamName, teams[i].maxQty, teams[i].startDate, teams[i].endDate);
        }
    }
}