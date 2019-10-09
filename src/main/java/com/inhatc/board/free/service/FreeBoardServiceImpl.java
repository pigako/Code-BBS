package com.inhatc.board.free.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inhatc.board.free.domain.FreeBoardPager;
import com.inhatc.board.free.domain.FreeBoardSearchKey;
import com.inhatc.board.free.domain.FreeBoardVO;
import com.inhatc.board.free.persistence.FreeBoardDAO;

@Repository
public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Autowired
	FreeBoardDAO dao;

	@Override
	public List<FreeBoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public FreeBoardVO read(int fb_no) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(fb_no);
	}

	@Override
	public int addHit(int fb_no) throws Exception {
		// TODO Auto-generated method stub
		return dao.addHit(fb_no);
	}

	@Override
	public int updateBoard(FreeBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateBoard(vo);
	}

	@Override
	public int deleteBoard(int fb_no) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteBoard(fb_no);
	}

	@Override
	public int writeBoard(FreeBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.writeBoard(vo);
	}

	@Override
	public int getCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.getCount();
	}

	@Override
	public List<FreeBoardVO> selectPage(FreeBoardPager pager) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectPage(pager);
	}

	@Override
	public List<FreeBoardVO> search(FreeBoardSearchKey key, FreeBoardPager pager) throws Exception {
		// TODO Auto-generated method stub
		return dao.search(key, pager);
	}

	@Override
	public int getSearchBoardCount(FreeBoardSearchKey key) throws Exception {
		// TODO Auto-generated method stub
		return dao.getSearchBoardCount(key);
	}

	@Override
	public List<FreeBoardVO> searchNotice() throws Exception {
		// TODO Auto-generated method stub
		return dao.searchNotice();
	}

	@Override
	public int getBoardNo(int mem_no) throws Exception {
		// TODO Auto-generated method stub
		return dao.getBoardNo(mem_no);
	}
	
	
}
