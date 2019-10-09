package com.inhatc.board.free.service;

import java.util.List;

import com.inhatc.board.free.domain.FreeBoardPager;
import com.inhatc.board.free.domain.FreeBoardSearchKey;
import com.inhatc.board.free.domain.FreeBoardVO;

public interface FreeBoardService {
	public List<FreeBoardVO> listAll() throws Exception;
	public FreeBoardVO read(int fb_no) throws Exception;
	public int addHit(int fb_no) throws Exception;
	public int updateBoard(FreeBoardVO vo) throws Exception;
	public int deleteBoard(int fb_no) throws Exception;
	public int writeBoard(FreeBoardVO vo) throws Exception;
	public int getCount() throws Exception;
	public List<FreeBoardVO> selectPage(FreeBoardPager pager) throws Exception;
	public List<FreeBoardVO> search(FreeBoardSearchKey key, FreeBoardPager pager) throws Exception;
	public int getSearchBoardCount(FreeBoardSearchKey key) throws Exception;
	public List<FreeBoardVO> searchNotice() throws Exception;
	public int getBoardNo(int mem_no) throws Exception;
}
