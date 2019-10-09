package com.inhatc.board.qna.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.board.qna.domain.QnaPager;
import com.inhatc.board.qna.domain.QnaSearch;
import com.inhatc.board.qna.domain.QnaVO;
import com.inhatc.board.qna.persistence.QnaDAO;


@Service
public class QnaServiceImpl implements QnaService {
        @Inject
        private QnaDAO dao;
	@Override
	public List<QnaVO> allSelect() throws Exception {
		return dao.allSelect();
	}
	@Override
	public int deleteQna(int qb_no) throws Exception {
		return dao.deleteQna(qb_no);
	}
	@Override
	public int getCount() throws Exception {
		return dao.getCount();
	}
	@Override
	public QnaVO read(int qb_no) throws Exception {
		return dao.read(qb_no);
	}
	@Override
	public List<QnaVO> searchQna(QnaSearch sch, QnaPager cpa) throws Exception {
		return dao.searchQna(sch, cpa);
	}
	@Override
	public List<QnaVO> selectPage(QnaPager cpa) throws Exception {
		return dao.selectPage(cpa);
	}
	@Override
	public int updateQna(QnaVO vo) throws Exception {
		return dao.updateQna(vo);
	}
	@Override
	public int viewHit(int qb_no) throws Exception {
		return dao.viewHit(qb_no);
	}
	@Override
	public int writeQna(QnaVO vo) throws Exception {
		return dao.writeQna(vo);
        }
        @Override
        public int searchQnaCount(QnaSearch sch) throws Exception {
                return dao.searchQnaCount(sch);
        }
}
