package com.inhatc.board.qna.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.board.qna.domain.QnaPager;
import com.inhatc.board.qna.domain.QnaSearch;
import com.inhatc.board.qna.domain.QnaVO;


@Repository
public class QnaDaoImpl implements QnaDAO {
        @Inject
        private SqlSession sql;
        @Inject
        private static String namespace="com.inhatc.mapper.qnaMapper";
	@Override
	public List<QnaVO> allSelect() throws Exception {
		return sql.selectList(namespace+".allselect");
	}


	@Override
	public QnaVO read(int qb_no) throws Exception {
		return sql.selectOne(namespace+".read",qb_no);
	}

	@Override
	public int viewHit(int qb_no) throws Exception {
		return sql.update(namespace+".view_hit",qb_no);
	}

	@Override
	public int updateQna(QnaVO vo) throws Exception {
		return sql.update(namespace+".update_qna",vo);
	}

	@Override
	public int deleteQna(int qb_no) throws Exception {
		return sql.delete(namespace+".delete_qna",qb_no);
	}

	@Override
	public int writeQna(QnaVO vo) throws Exception {
		return sql.insert(namespace+".write_qna",vo);
	}

	@Override
	public int getCount() throws Exception {
		return sql.selectOne(namespace+".get_count");
	}

	@Override
	public List<QnaVO> selectPage(QnaPager cpa) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(cpa.getNumberOfRecords() != 0)
		{
			map.put("start", (cpa.getCurrentPageNo() - 1) * cpa.getMaxPost());
			map.put("end", cpa.getMaxPost());
		}
		else
		{
			map.put("start", 0);
			map.put("end", cpa.getMaxPost());			
		}
		return sql.selectList(namespace+".select_page", map);
	}

	@Override
	public List<QnaVO> searchQna(QnaSearch sch,QnaPager cpa) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(cpa.getNumberOfRecords() != 0)
		{
			map.put("start", (cpa.getCurrentPageNo() - 1) * cpa.getMaxPost());
			map.put("end", cpa.getMaxPost());
		}
		else
		{
			map.put("start", 0);
			map.put("end", cpa.getMaxPost());			
		}
		map.put("type", sch.getType());
		map.put("search", sch.getSearch());
		return sql.selectList(namespace+".search_qna", map);
	}
	@Override
	public int searchQnaCount(QnaSearch sch) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", sch.getType());
		map.put("search", sch.getSearch());
		return sql.selectOne(namespace+".search_qna_count", map);
	}
}
