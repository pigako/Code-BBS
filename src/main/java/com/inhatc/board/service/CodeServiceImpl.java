package com.inhatc.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.board.domain.CodePager;
import com.inhatc.board.domain.CodeSearch;
import com.inhatc.board.domain.CodeVO;
import com.inhatc.board.persistence.CodeDAO;
@Service
public class CodeServiceImpl implements CodeService {
        @Inject
        private CodeDAO dao;
	@Override
	public List<CodeVO> allSelect() throws Exception {
		return dao.allSelect();
	}
	@Override
	public int deleteCode(int cb_no) throws Exception {
		return dao.deleteCode(cb_no);
	}
	@Override
	public int getCount() throws Exception {
		return dao.getCount();
	}
	@Override
	public List<CodeVO> getLang() throws Exception {
		return dao.getLang();
	}
	@Override
	public CodeVO read(int cb_no) throws Exception {
		return dao.read(cb_no);
	}
	@Override
	public List<CodeVO> searchCode(CodeSearch sch, CodePager cpa) throws Exception {
		return dao.searchCode(sch, cpa);
	}
	@Override
	public List<CodeVO> selectPage(CodePager cpa) throws Exception {
		return dao.selectPage(cpa);
	}
	@Override
	public int updateCode(CodeVO vo) throws Exception {
		return dao.updateCode(vo);
	}
	@Override
	public int viewHit(int cb_no) throws Exception {
		return dao.viewHit(cb_no);
	}
	@Override
	public int writeCode(CodeVO vo) throws Exception {
		return dao.writeCode(vo);
    }
    @Override
    public int searchCodeCount(CodeSearch sch) throws Exception {
        return dao.searchCodeCount(sch);
    }
}
