package com.inhatc.board.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.board.domain.CodePager;
import com.inhatc.board.domain.CodeSearch;
import com.inhatc.board.domain.CodeVO;
@Repository
public class CodeDaoImpl implements CodeDAO {
        @Inject
        private SqlSession sql;
        @Inject
        private static String namespace="com.inhatc.mapper.codeMapper";
	@Override
	public List<CodeVO> allSelect() throws Exception {
		return sql.selectList(namespace+".allselect");
	}

	@Override
	public List<CodeVO> getLang() throws Exception {
		return sql.selectList(namespace+".get_lang");
	}

	@Override
	public CodeVO read(int cb_no) throws Exception {
		return sql.selectOne(namespace+".read",cb_no);
	}

	@Override
	public int viewHit(int cb_no) throws Exception {
		return sql.update(namespace+".view_hit",cb_no);
	}

	@Override
	public int updateCode(CodeVO vo) throws Exception {
		return sql.update(namespace+".update_code",vo);
	}

	@Override
	public int deleteCode(int cb_no) throws Exception {
		return sql.delete(namespace+".delete_code",cb_no);
	}

	@Override
	public int writeCode(CodeVO vo) throws Exception {
		return sql.insert(namespace+".write_code",vo);
	}

	@Override
	public int getCount() throws Exception {
		return sql.selectOne(namespace+".get_count");
	}

	@Override
	public List<CodeVO> selectPage(CodePager cpa) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(cpa.getNumberOfRecords() != 0)
		{
			map.put("start", (cpa.getCurrentPageNo() - 1) * cpa.getMaxPost());
			map.put("end", cpa.getMaxPost());
		}
		else
		{
			map.put("start", 0);
			map.put("end", cpa.getMaxPost());			
		}
		return sql.selectList(namespace+".select_page", map);
	}

	@Override
	public List<CodeVO> searchCode(CodeSearch sch,CodePager cpa) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(cpa.getNumberOfRecords() != 0)
		{
			map.put("start", (cpa.getCurrentPageNo() - 1) * cpa.getMaxPost());
			map.put("end", cpa.getMaxPost());
		}
		else
		{
			map.put("start", 0);
			map.put("end", cpa.getMaxPost());			
		}
		map.put("type", sch.getType());
		map.put("search", sch.getSearch());
		map.put("language", sch.getLanguage());
		// System.out.println("searchtype"+sch.getSearchType());
		// System.out.println("search"+sch.getSearch());
		// System.out.println("language"+sch.getLanguage());
		return sql.selectList(namespace+".search_code", map);
	}
	@Override
	public int searchCodeCount(CodeSearch sch) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// System.out.println(sch.getSearchType());
		// System.out.println(sch.getSearch());
		// System.out.println(sch.getLanguage());
		map.put("type", sch.getType());
		map.put("search", sch.getSearch());
		map.put("language", sch.getLanguage());
		// System.out.println(map.get("language"));
		return sql.selectOne(namespace+".search_code_count", map);
	}
}
