package com.inhatc.board.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.board.domain.RegisterVO;
@Repository
public class RegisterDAOImpl implements RegisterDAO{
        @Inject
        private SqlSession sql;
        @Inject
        private static String namespace="com.inhatc.mapper.registerMapper";

        @Override
        public int register(RegisterVO vo) {
                return sql.insert(namespace+".register",vo);
        }
        @Override
        public int idCheck(RegisterVO vo) {
                return sql.selectOne(namespace+".id_check",vo);
        }
        @Override
        public int emailCheck(RegisterVO vo) {
                return sql.selectOne(namespace+".email_check",vo);
        }
        @Override
        public RegisterVO findId(RegisterVO vo) {
                return sql.selectOne(namespace+".find_id",vo);
        }
        @Override
        public RegisterVO findPw(RegisterVO vo) {
                return sql.selectOne(namespace+".find_pw",vo);
        }
}
