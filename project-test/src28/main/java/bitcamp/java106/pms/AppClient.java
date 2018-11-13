package bitcamp.java106.pms;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AppClient {
    static String serverAddr;
    static int port;
    static String requestPath;
    
    public static void main(String[] args) throws Exception {
        Scanner keyScan = new Scanner(System.in);
        while (true) {
            System.out.print("요청> ");
            
            // 예) http://192.168.0.7:8888/board/list
            String str = keyScan.nextLine().replace("http://", "");
            
            int colonIndex = str.indexOf(':'); // 11
            int slashIndex = str.indexOf('/'); // 16

            if (colonIndex != -1) {
                // 예시) 192.168.0.7:8888/board/list
                serverAddr = str.substring(0, colonIndex);
                port = Integer.parseInt(str.substring(colonIndex + 1, slashIndex));
            } else {
                // 예시) 192.168.0.7/board/list
                serverAddr = str.substring(0, slashIndex);
                port = 80;
            }
            
            requestPath = str.substring(slashIndex); // 예시) /board/list
            
            if (str.equals("quit")) 
                break;
            
            send();
        }
        keyScan.close();
    }

    static void send() {
        try (
                Socket socket = new Socket(serverAddr, port);
                PrintStream out = new PrintStream(socket.getOutputStream());
                Scanner in = new Scanner(socket.getInputStream());
                ) {
            
        } catch (Exception e) {
            System.out.println("서버 요청 중 오류 발생");
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
}
