package com.inhatc.board.persistence;

import java.util.List;

import com.inhatc.board.domain.CodeCommentVO;

public interface CodeCommentDAO {
        public int commentCount(int cb_no);
        public List<CodeCommentVO> commentSearch(int cb_no);
        public int commentInsert(CodeCommentVO vo);
        public int commentUpdate(CodeCommentVO vo);
        public int commentDelete(int cbc_no);
}
