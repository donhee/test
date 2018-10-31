package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Member;

public class MemberDao extends AbstractDao<Member> {
    
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
