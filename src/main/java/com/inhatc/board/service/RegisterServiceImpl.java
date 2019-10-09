package com.inhatc.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.board.domain.RegisterVO;
import com.inhatc.board.persistence.RegisterDAO;
@Service
public class RegisterServiceImpl implements RegisterService{
        @Inject
        private RegisterDAO dao;
        @Override
        public int register(RegisterVO vo) {
                return dao.register(vo);
        }
        @Override
        public int idCheck(RegisterVO vo) {
                return dao.idCheck(vo);
        }
        @Override
        public int emailCheck(RegisterVO vo) {
                return dao.emailCheck(vo);
        }
        @Override
        public RegisterVO findId(RegisterVO vo) {
                return dao.findId(vo);
        }
        @Override
        public RegisterVO findPw(RegisterVO vo) {
                return dao.findPw(vo);
        }
}
