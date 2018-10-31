package bitcamp.java106.pms.dao;


import bitcamp.java106.pms.domain.Classroom;

public class ClassroomDao extends AbstractDao<Classroom>{

    @Override
    public int indexOf(Object key) {
        int classroomNo = (Integer) key;
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getNo() == classroomNo) {
                return i;
            }
        }
        return -1;
    }
    
    
    
}
