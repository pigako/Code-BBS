package com.inhatc.board.qna.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.board.qna.domain.QnaCommentVO;

@Repository
public class QnaCommentDAOImpl implements QnaCommentDAO{
        @Inject
        private SqlSession sql;
        @Inject
        private static String namespace="com.inhatc.mapper.qnaCommentMapper";
        @Override
        public int commentCount(int qb_no) {
                return sql.selectOne(namespace+".comment_count",qb_no);
        }
        @Override
        public List<QnaCommentVO> commentSearch(int qb_no) {
                return sql.selectList(namespace+".comment_search",qb_no);
        }
        @Override
        public int commentInsert(QnaCommentVO vo) {
                return sql.insert(namespace+".comment_insert",vo);
        }
        @Override
        public int commentUpdate(QnaCommentVO vo) {
                return sql.update(namespace+".comment_update",vo);
        }
        @Override
        public int commentDelete(int qbc_no) {
                return sql.delete(namespace+".comment_delete",qbc_no);
        }
}
