package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;

@Component
public class BoardDao extends AbstractDao<Board> {
    public BoardDao() throws Exception {
        load();
    }
    
    public void load() throws Exception {
        Scanner in = new Scanner(new FileReader("data/board.data"));
        while (true) {
            // 저장된 데이터를 한 줄 읽는다.
            // 한 줄에 한 개의 게시물 데이터가 있다.
            // 데이터 형식은 다음과 같다.
            // "번호,제목,내용,등록일"
            //
            try {
                String[] arr = in.nextLine().split(",");
                Board board = new Board();
                board.setNo(Integer.parseInt(arr[0]));
                board.setTitle(arr[1]);
                board.setContent(arr[2]);
                board.setCreatedDate(Date.valueOf(arr[3]));
                this.insert(board);
            } catch (Exception e) { // 데이터를 모두 읽었거나 파일 형식에 문제가 있다면,
                //e.printStackTrace();
                break; // 반복문을 나간다.
            }
        }
        in.close();
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/board.data"));
        
        Iterator<Board> boards = this.list();
        
        // List에 보관된 데이터를 board.csv 파일에 저장한다.
        // 기존에 저장된 데이터를 덮어쓴다. 즉 처음부터 다시 저장한다.
        while (boards.hasNext()) {
            Board board = boards.next();
            out.printf("%d,%s,%s,%s\n", board.getNo(), board.getTitle(),
                    board.getContent(), board.getCreatedDate());
        }
        out.close();
    }
    
    public int indexOf(Object key) {
        int no = (Integer) key; // Integer ==> int : auto-unboxing
        for (int i = 0; i < collection.size(); i++) {
            Board originBoard = collection.get(i);
            if (originBoard.getNo() == no) {
                return i;
            }
        }
        return -1;
    }
    
}
