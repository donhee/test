package bitcamp.java106.pms;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner keyScan = new Scanner(System.in);

        String a;
        String b;
        int i;
        String start;
        String end;

        System.out.print("팀명? ");
        a = keyScan.nextLine();
        System.out.print("설명? ");
        b = keyScan.nextLine();
        System.out.print("최대인원? ");
        // i = keyScan.nextInt(); // 오류!
        i = Integer.parseInt(keyScan.nextLine()); // http://sexy.pe.kr/tc/496 주소로 들어가서 맨 밑에 참고
        // keyScan.nextLine();
        // 숫자 뒤에 줄바꿈 코드를 읽는다
        // 읽고 난 뒤에 아무것도 안하기 때문에
        // 일종의 줄바꿈 코드를 제거하는 효과가 있다.
        System.out.print("시작일? ");
        start = keyScan.nextLine();
        System.out.print("종료일? ");
        end = keyScan.nextLine();

        System.out.println("------------------");

        System.out.println("팀명: "+ a);
        System.out.println("설명: \n" + b );
        System.out.println("최대인원: "+ i +"명");
        System.out.println("일자: "+ start +" ~ "+ end);
    }
}