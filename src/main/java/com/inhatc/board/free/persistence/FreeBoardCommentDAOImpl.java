package com.inhatc.board.free.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inhatc.board.free.domain.FreeBoardCommentVO;

@Repository
public class FreeBoardCommentDAOImpl implements FreeBoardCommentDAO {
	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.inhatc.mapper.freeCommentMapper";
	
	@Override
	public List<FreeBoardCommentVO> commentSearch(int fb_no) {
		// TODO Auto-generated method stub
		return sql.selectList(namespace+".commentSearch", fb_no);
	}
	
	@Override
	public int commentUpdate(int fbc_no, String fbc_text) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fbc_no", fbc_no);
		map.put("fbc_text", fbc_text);
		return sql.update(namespace+".commentUpdate" , map);
	}
	
	@Override
	public int commentDelete(int fbc_no) {
		// TODO Auto-generated method stub
		return sql.delete(namespace+".commentDelete", fbc_no);
	}
	
	@Override
	public int commentInsert(FreeBoardCommentVO vo) {
		// TODO Auto-generated method stub
		return sql.insert(namespace+".commentInsert", vo);
	}
	
	@Override
	public int commentCount(int fb_no) {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".commentCount", fb_no);
	}
}
