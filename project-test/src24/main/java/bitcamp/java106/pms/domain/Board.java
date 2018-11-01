package bitcamp.java106.pms.domain;

import java.sql.Date;

public class Board {
    private static int count = 1;
    
    private int no;
    private String title;
    private String content;
    private Date createdDate;
    
    public Board() {
        this.no = count++;
    }
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        // 외부에서 입력 받은 번호가 count 보다 클 때는 count의 값을 증가시켜야 한다.
        if (no >= count) {
            count = no + 1;
        }
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
}
