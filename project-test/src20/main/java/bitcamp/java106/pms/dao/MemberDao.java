package bitcamp.java106.pms.dao;

import java.util.LinkedList;

import bitcamp.java106.pms.domain.Member;

public class MemberDao {
    private LinkedList<Member> collection = new LinkedList<>();
    
    public void insert(Member member) {
        collection.add(member);
    }
    
    public Member[] list() {
        Member[] arr = new Member[collection.size()];
        for (int i = 0; i < collection.size(); i++) {
            arr[i] = collection.get(i);
        }
        return arr;
    }

    public Member get(String id) {
        int i;
        if ((i = getMemberIndex(id)) != -1) 
            return collection.get(i);
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
                    collection.get(i).getId().toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}
