package com.inhatc.board.persistence;

import com.inhatc.board.domain.RegisterVO;

public interface RegisterDAO {
        public int register(RegisterVO vo);
        public int idCheck(RegisterVO vo);
        public int emailCheck(RegisterVO vo);
        public RegisterVO findId(RegisterVO vo);
        public RegisterVO findPw(RegisterVO vo);
}
