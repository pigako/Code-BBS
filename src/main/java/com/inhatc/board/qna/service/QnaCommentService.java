package com.inhatc.board.qna.service;

import java.util.List;

import com.inhatc.board.qna.domain.QnaCommentVO;


public interface QnaCommentService {
        public int commentCount(int qb_no);
        public List<QnaCommentVO> commentSearch(int qb_no);
        public int commentInsert(QnaCommentVO vo);
        public int commentUpdate(QnaCommentVO vo);
        public int commentDelete(int qbc_no);
}
