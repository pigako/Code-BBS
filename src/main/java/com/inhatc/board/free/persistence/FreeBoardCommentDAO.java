package com.inhatc.board.free.persistence;

import java.util.List;

import com.inhatc.board.free.domain.FreeBoardCommentVO;

public interface FreeBoardCommentDAO {
	public List<FreeBoardCommentVO> commentSearch(int fb_no);
	public int commentUpdate(int fbc_no, String fbc_text);
	public int commentDelete(int fbc_no);
	public int commentInsert(FreeBoardCommentVO vo);
	public int commentCount(int fb_no);
}