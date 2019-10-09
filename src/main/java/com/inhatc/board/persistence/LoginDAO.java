package com.inhatc.board.persistence;

import com.inhatc.board.domain.LoginVO;

public interface LoginDAO {
        public LoginVO loginCheck(LoginVO vo);
}
