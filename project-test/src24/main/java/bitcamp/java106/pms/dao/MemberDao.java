package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Member;

@Component
public class MemberDao extends AbstractDao<Member> {
    
    public MemberDao() throws Exception {
        load();
    }
    
    public void load() throws Exception {
        Scanner in = new Scanner(new FileReader("data/member.data"));
        while (true) {
            try {
                String[] arr = in.nextLine().split(",");
                Member member = new Member();
                member.setId(arr[0]);
                member.setEmail(arr[1]);
                member.setPassword(arr[2]);
                this.insert(member);
            } catch (Exception e) { // 데이터를 모두 읽었거나 파일 형식에 문제가 있다면,
                //e.printStackTrace();
                break; // 반복문을 나간다.
            }
        }
        in.close();
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/member.data"));
        
        Iterator<Member> members = this.list();
        
        while (members.hasNext()) {
            Member member = members.next();
            out.printf("%s,%s,%s\n", member.getId(), member.getEmail(),
                    member.getPassword());
        }
        out.close();
    }
    
    public int indexOf(Object key) {
        String id = (String) key;
        for (int i = 0; i < collection.size(); i++) {
            Member originMember = collection.get(i);
            if (originMember.getId().toLowerCase().equals(id.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}
