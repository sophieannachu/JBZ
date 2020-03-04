package iii.ron.server.spots;

import java.util.*;

public interface GroupInfoDAO_interface {
//        public int insert(GroupInfoVO groupInfoVO);
          public int delete(int group_no);
	      public List<GroupInfoVO> findByForeignKey(int memno);
          public List<GroupInfoVO> getAll();
//          public int update(GroupInfoVO groupInfoVO);
//          public void insertOne(GroupInfoVO groupInfoVo);
          public byte[] getImg(int group_no);

          
          /**/
//          public GroupInfoVO getOneGroup(int group_no);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<foodVo> getAll(Map<String, String[]> map); 
}
