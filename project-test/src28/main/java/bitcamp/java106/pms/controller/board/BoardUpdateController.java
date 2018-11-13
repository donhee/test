package bitcamp.java106.pms.controller.board;

import java.io.PrintWriter;
import java.sql.Date;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/board/update")
public class BoardUpdateController implements Controller {
    BoardDao boardDao;
    
    public BoardUpdateController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    
    
    public void service(ServerRequest request, ServerResponse response) {
        PrintWriter out = response.getWriter();
        
        Board updateBoard = new Board();
        
        updateBoard.setNo(Integer.parseInt(request.getParameter("no")));
        updateBoard.setTitle(request.getParameter("title"));
        updateBoard.setContent(request.getParameter("content"));
        updateBoard.setCreatedDate(new Date(System.currentTimeMillis()));
        
        Board board = boardDao.get(updateBoard.getNo());
        
        if (board == null) {
            out.println("유효하지 않은 게시물 번호입니다.");
        } else {
            int index = boardDao.indexOf(updateBoard.getNo());
            boardDao.update(index, updateBoard);
            out.println("변경하였습니다.");
        }
    }

}
