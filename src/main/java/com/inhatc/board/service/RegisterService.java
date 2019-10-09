package com.inhatc.board.service;

import com.inhatc.board.domain.RegisterVO;

public interface RegisterService {
        public int register(RegisterVO vo);
        public int idCheck(RegisterVO vo);
        public int emailCheck(RegisterVO vo);
        public RegisterVO findId(RegisterVO vo);
        public RegisterVO findPw(RegisterVO vo);
}
