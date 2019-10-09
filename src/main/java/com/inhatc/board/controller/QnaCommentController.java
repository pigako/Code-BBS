package com.inhatc.board.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inhatc.board.qna.domain.QnaCommentVO;
import com.inhatc.board.qna.service.QnaCommentService;


@Controller
public class QnaCommentController {
        @Inject
        QnaCommentService service;
        @RequestMapping(value = "/qna/commentCount", method = RequestMethod.POST)
		public @ResponseBody int qnaCommentCount(Model model, int qb_no, HttpSession session) throws Exception {
                return service.commentCount(qb_no);
        }
        @RequestMapping(value = "/qna/commentInput", method = RequestMethod.POST)
		public @ResponseBody int qnaCommentInput(Model model, QnaCommentVO vo, HttpSession session) throws Exception {
                vo.setMem_no((Integer)session.getAttribute("mem_no"));
                return service.commentInsert(vo);
        }
        @RequestMapping(value = "/qna/commentModify", method = RequestMethod.POST)
        public @ResponseBody int qnaCommentModify(Model model, QnaCommentVO vo) throws Exception {
                return service.commentUpdate(vo);
        }		
        @RequestMapping(value = "/qna/commentDelete", method = RequestMethod.POST)
        public @ResponseBody int qnaCommentDelete(Model model, QnaCommentVO vo) throws Exception {
                return service.commentDelete(vo.getQbc_no());
        }
        @RequestMapping(value = "/qna/commentRead", method = RequestMethod.POST)
        public @ResponseBody Object qnaCommentRead(Model model, QnaCommentVO vo) throws Exception {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("result", service.commentSearch(vo.getQb_no()));
                return map;
        }
	
}
