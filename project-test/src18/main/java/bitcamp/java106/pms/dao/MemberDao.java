package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.util.ArrayList;

public class MemberDao {
    private ArrayList collection = new ArrayList();
    
    public void insert(Member member) {
        collection.add(member);
    }
    
    public Member[] list() {
        Member[] arr = new Member[collection.size()];
        for (int i = 0; i < collection.size(); i++) {
            arr[i] = (Member) collection.get(i);
        }
        return arr;
    }

    public Member get(String id) {
        int i;
        if ((i = getMemberIndex(id)) != -1) 
            return (Member) collection.get(i);
        return null;
    }

    public void update(Member member) {
        int i = getMemberIndex(member.getId());
        if (i != -1) 
            collection.set(i, member);
    }
    
    public void delete(String id) {
        int i = getMemberIndex(id);
        if (i != -1) 
            collection.remove(i);
    }

    private int getMemberIndex(String id) {
        for (int i = 0; i < collection.size(); i++) {
            if (id.toLowerCase().equals(
                    ((Member)collection.get(i)).getId().toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}
