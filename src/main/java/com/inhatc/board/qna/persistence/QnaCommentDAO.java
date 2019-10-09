package com.inhatc.board.qna.persistence;

import java.util.List;

import com.inhatc.board.qna.domain.QnaCommentVO;


public interface QnaCommentDAO {
        public int commentCount(int qb_no);
        public List<QnaCommentVO> commentSearch(int qb_no);
        public int commentInsert(QnaCommentVO vo);
        public int commentUpdate(QnaCommentVO vo);
        public int commentDelete(int qbc_no);
}
