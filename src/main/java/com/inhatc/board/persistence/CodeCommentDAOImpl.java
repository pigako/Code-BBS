package com.inhatc.board.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.board.domain.CodeCommentVO;
@Repository
public class CodeCommentDAOImpl implements CodeCommentDAO{
        @Inject
        private SqlSession sql;
        @Inject
        private static String namespace="com.inhatc.mapper.codeCommentMapper";
        @Override
        public int commentCount(int cb_no) {
                return sql.selectOne(namespace+".comment_count",cb_no);
        }
        @Override
        public List<CodeCommentVO> commentSearch(int cb_no) {
                return sql.selectList(namespace+".comment_search",cb_no);
        }
        @Override
        public int commentInsert(CodeCommentVO vo) {
                return sql.insert(namespace+".comment_insert",vo);
        }
        @Override
        public int commentUpdate(CodeCommentVO vo) {
                return sql.update(namespace+".comment_update",vo);
        }
        @Override
        public int commentDelete(int cbc_no) {
                return sql.delete(namespace+".comment_delete",cbc_no);
        }
}
