package com.inhatc.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.board.domain.LoginVO;
import com.inhatc.board.persistence.LoginDAO;
@Service
public class LoginServiceImpl implements LoginService{
        @Inject
        private LoginDAO dao;
        @Override
        public LoginVO loginCheck(LoginVO vo) {
                return dao.loginCheck(vo);
        }
}
