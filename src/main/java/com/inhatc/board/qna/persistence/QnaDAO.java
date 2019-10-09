package com.inhatc.board.qna.persistence;

import java.util.List;

import com.inhatc.board.qna.domain.QnaPager;
import com.inhatc.board.qna.domain.QnaSearch;
import com.inhatc.board.qna.domain.QnaVO;



public interface QnaDAO {
        public List<QnaVO> allSelect() throws Exception;
        public QnaVO read(int qb_no) throws Exception;
        public int viewHit(int qb_no) throws Exception;
        public int updateQna(QnaVO vo) throws Exception;
        public int deleteQna(int qb_no) throws Exception;
        public int writeQna(QnaVO vo) throws Exception;
        public int getCount() throws Exception;
        public List<QnaVO> selectPage(QnaPager cpa) throws Exception;
        public List<QnaVO> searchQna(QnaSearch sch,QnaPager cpa) throws Exception;
        public int searchQnaCount(QnaSearch sch) throws Exception;
}
