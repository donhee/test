package bitcamp.java106.pms.domain;

import java.sql.Date;

public class Board {
    private static int count = 1;
    
    private int no;
    private String title;
    private String content;
    private Date createDate;
    
    public Board() {
        this.no = count++;
    }
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
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
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}
