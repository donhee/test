package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Member;

public class MemberDao {
    
    Member[] members = new Member[100];
    int memberIndex = 0;
    
    public void insert(Member member) {
        // 회원 정보가 담겨있는 객체의 주소를 배열에 보관한다.
        this.members[this.memberIndex++] = member;
    }
    
    public Member[] list() {
        Member[] arr = new Member[this.memberIndex];
        for (int i = 0; i < this.memberIndex; i++) {
            arr[i] = members[i];
        }
        return arr;
    }
    
    public Member get(String id) {
        int i = this.getMemberIndex(id);
        if (i == -1)
            return null;
        return this.members[i];
    }
    
    public void update(Member member) {
        int i = this.getMemberIndex(member.id);
        if (i != -1)
            this.members[i] = member;
    }
    
    public void delete(Member member) {
        int i = this.getMemberIndex(member.id);
        if (i != -1)
            this.members[i] = null;
    }
    
    private int getMemberIndex(String id) {
        for (int i = 0; i < this.memberIndex; i++) {
            if (this.members[i] == null) continue;
            if (id.equals(this.members[i].id.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
    
}
