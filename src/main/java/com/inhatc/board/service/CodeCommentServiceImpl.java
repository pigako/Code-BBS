package com.inhatc.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.board.domain.CodeCommentVO;
import com.inhatc.board.persistence.CodeCommentDAO;
@Service
public class CodeCommentServiceImpl implements CodeCommentService{
        @Inject
        private CodeCommentDAO dao;
        @Override
        public int commentCount(int cb_no) {
                return dao.commentCount(cb_no);
        }
        @Override
        public List<CodeCommentVO> commentSearch(int cb_no) {
                return dao.commentSearch(cb_no);
        }
        @Override
        public int commentInsert(CodeCommentVO vo) {
                return dao.commentInsert(vo);
        }
        @Override
        public int commentUpdate(CodeCommentVO vo) {
                return dao.commentUpdate(vo);
        }
        @Override
        public int commentDelete(int cbc_no) {
                return dao.commentDelete(cbc_no);
        }
}
