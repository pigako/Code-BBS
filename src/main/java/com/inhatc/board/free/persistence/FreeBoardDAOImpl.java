package com.inhatc.board.free.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inhatc.board.free.domain.FreeBoardPager;
import com.inhatc.board.free.domain.FreeBoardSearchKey;
import com.inhatc.board.free.domain.FreeBoardVO;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {
	
	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.inhatc.mapper.freeBoardMapper";
	
	@Override
	public List<FreeBoardVO> listAll() throws Exception {
		return sql.selectList(namespace+".listAll");
	}
	
	@Override
	public FreeBoardVO read(int fb_no) throws Exception {
		return sql.selectOne(namespace+".read", fb_no);
	}
	
	@Override
	public int addHit(int fb_no) throws Exception {
		return sql.update(namespace + ".addHit", fb_no);
	}
	
	@Override
	public int updateBoard(FreeBoardVO vo) throws Exception {
		return sql.update(namespace+".updateBoard", vo);
	}
	
	@Override
	public int deleteBoard(int fb_no) throws Exception {
		return sql.delete(namespace+".deleteBoard", fb_no);
	}

	@Override
	public int writeBoard(FreeBoardVO vo) throws Exception {
		return sql.insert(namespace+".writeBoard", vo);
	}

	@Override
	public int getCount() throws Exception {
		return sql.selectOne(namespace+".getCount");
	}
	
	@Override
	public List<FreeBoardVO> selectPage(FreeBoardPager pager) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(pager.getNumberOfRecords() != 0) {
			map.put("start", (pager.getCurrentPageNo() - 1) * pager.getMaxPost());
			map.put("end", pager.getMaxPost());
		} else {
			map.put("start", 0);
			map.put("end", pager.getMaxPost());
		}
		
		return sql.selectList(namespace+".selectPage", map);
	}
	
	@Override
	public List<FreeBoardVO> search(FreeBoardSearchKey key, FreeBoardPager pager) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(pager.getNumberOfRecords() != 0) {
			map.put("start", (pager.getCurrentPageNo() - 1) * pager.getMaxPost());
			map.put("end", pager.getMaxPost());
		} else {
			map.put("start", 0);
			map.put("end", pager.getMaxPost());
		}
		
		map.put("searchType", key.getSearchType());
		map.put("search", key.getSearch());
		return sql.selectList(namespace+".search", map);
	}
	
	@Override
	public int getSearchBoardCount(FreeBoardSearchKey key) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", key.getSearchType());
		map.put("search", key.getSearch());
		return sql.selectOne(namespace+".getSearchBoardCount", map);
	}
	
	@Override
	public List<FreeBoardVO> searchNotice() throws Exception {
		return sql.selectList(namespace+".searchNotice");
	}
	
	@Override
	public int getBoardNo(int mem_no) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".getBoardNo", mem_no);
	}
}
