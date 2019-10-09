package com.inhatc.board.qna.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.board.qna.domain.QnaCommentVO;
import com.inhatc.board.qna.persistence.QnaCommentDAO;

@Service
public class QnaCommentServiceImpl implements QnaCommentService{
        @Inject
        private QnaCommentDAO dao;
        @Override
        public int commentCount(int qb_no) {
                return dao.commentCount(qb_no);
        }
        @Override
        public List<QnaCommentVO> commentSearch(int qb_no) {
                return dao.commentSearch(qb_no);
        }
        @Override
        public int commentInsert(QnaCommentVO vo) {
                return dao.commentInsert(vo);
        }
        @Override
        public int commentUpdate(QnaCommentVO vo) {
                return dao.commentUpdate(vo);
        }
        @Override
        public int commentDelete(int qbc_no) {
                return dao.commentDelete(qbc_no);
        }
}
