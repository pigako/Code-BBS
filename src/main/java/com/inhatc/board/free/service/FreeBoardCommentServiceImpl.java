package com.inhatc.board.free.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inhatc.board.free.domain.FreeBoardCommentVO;
import com.inhatc.board.free.persistence.FreeBoardCommentDAO;

@Service
public class FreeBoardCommentServiceImpl implements FreeBoardCommentService {
	
	@Autowired
	FreeBoardCommentDAO dao;
	
	@Override
	public List<FreeBoardCommentVO> commentSearch(int fb_no) {
		// TODO Auto-generated method stub
		return dao.commentSearch(fb_no);
	}
	
	@Override
	public int commentUpdate(int fbc_no, String fbc_text) {
		// TODO Auto-generated method stub
		return dao.commentUpdate(fbc_no, fbc_text);
	}
	
	@Override
	public int commentDelete(int fbc_no) {
		// TODO Auto-generated method stub
		return dao.commentDelete(fbc_no);
	}
	
	@Override
	public int commentInsert(FreeBoardCommentVO vo) {
		// TODO Auto-generated method stub
		return dao.commentInsert(vo);
	}
	
	@Override
	public int commentCount(int fb_no) {
		// TODO Auto-generated method stub
		return dao.commentCount(fb_no);
	}
}
