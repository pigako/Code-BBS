package com.inhatc.board.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.board.domain.LoginVO;
@Repository
public class LoginDAOImpl implements LoginDAO{
        @Inject
        private SqlSession sql;
        private static String namespace="com.inhatc.mapper.loginMapper";
        @Override
        public LoginVO loginCheck(LoginVO vo) {
                return sql.selectOne(namespace+".login_check",vo);
        }
}
