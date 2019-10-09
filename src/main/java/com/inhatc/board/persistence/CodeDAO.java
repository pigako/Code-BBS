package com.inhatc.board.persistence;

import java.util.List;

import com.inhatc.board.domain.CodePager;
import com.inhatc.board.domain.CodeSearch;
import com.inhatc.board.domain.CodeVO;

public interface CodeDAO {
        public List<CodeVO> allSelect() throws Exception;
        public List<CodeVO> getLang() throws Exception;
        public CodeVO read(int cb_no) throws Exception;
        public int viewHit(int cb_no) throws Exception;
        public int updateCode(CodeVO vo) throws Exception;
        public int deleteCode(int cb_no) throws Exception;
        public int writeCode(CodeVO vo) throws Exception;
        public int getCount() throws Exception;
        public List<CodeVO> selectPage(CodePager cpa) throws Exception;
        public List<CodeVO> searchCode(CodeSearch sch,CodePager cpa) throws Exception;
        public int searchCodeCount(CodeSearch sch) throws Exception;
}
