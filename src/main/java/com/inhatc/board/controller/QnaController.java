package com.inhatc.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inhatc.board.qna.domain.QnaPager;
import com.inhatc.board.qna.domain.QnaSearch;
import com.inhatc.board.qna.domain.QnaVO;
import com.inhatc.board.qna.service.QnaCommentService;
import com.inhatc.board.qna.service.QnaService;




@Controller
public class QnaController {
        private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
        @Inject
        QnaService service;
        @Inject
        QnaCommentService com_service;
        @RequestMapping(value = "/qna", method = RequestMethod.GET)
		public String qnaSearch(Model model, QnaSearch sch,QnaPager cpa, HttpSession session) throws Exception {
                        cpa.setSizeOfPage(10);
			cpa.setNumberOfRecords(service.searchQnaCount(sch));
                        cpa.makePaging();
			if(session.getAttribute("mem_no") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
                        }
                        System.out.println(cpa.getMaxPost());
			model.addAttribute("page", service.searchQna(sch, cpa));
                        model.addAttribute("pager", cpa);
			model.addAttribute("search", sch);
			return "qna/board";
                }
		@RequestMapping(value = "/qna/read", method = RequestMethod.GET)
		public String qnaRead(Model model, @RequestParam("qb_no") int qb_no, QnaPager cpa, QnaSearch sch,HttpSession session) throws Exception {
			logger.info("Qna 글 읽기 페이지");
			service.viewHit(qb_no);
			model.addAttribute("post", service.read(qb_no));
			model.addAttribute("comment", com_service.commentSearch(qb_no));
			model.addAttribute("cpa", cpa);
                        model.addAttribute("search", sch);
                        model.addAttribute("totCom",com_service.commentCount(qb_no));
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
				model.addAttribute("mem_no", session.getAttribute("mem_no"));
                        }
                        return "qna/read";
		}
		
		@RequestMapping(value = "/qna/modify", method = RequestMethod.GET)
		public String qnaModify(Model model, @RequestParam("qb_no") int qb_no, QnaPager cpa, QnaSearch sch, HttpSession session) throws Exception {
			logger.info("Qna 글 수정 페이지");
			model.addAttribute("post", service.read(qb_no));
			model.addAttribute("cpa", cpa);
			model.addAttribute("search", sch);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
                        }
                        return "qna/modify";
		}
		
		@RequestMapping(value = "/qna/modify", method = RequestMethod.POST)
		public String qnaModifyPost(Model model, QnaVO vo, HttpSession session) throws Exception {
			logger.info("Qna 글 수정");
			vo.setMem_no((Integer)session.getAttribute("mem_no"));
			service.updateQna(vo);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}
			return "redirect:/qna/read?qb_no="+vo.getQb_no();
		}
		
		@RequestMapping(value = "/qna/delete", method = RequestMethod.GET)
		public String qnaDelete(Model model, QnaVO vo, HttpSession session) throws Exception {
			logger.info("Qna 글 삭제");
			service.deleteQna(vo.getQb_no());
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}
			return "redirect:/qna";		
		}
		
		@RequestMapping(value = "/qna/write", method = RequestMethod.GET)
		public String qnaWrite(Model model, QnaPager cpa, HttpSession session) throws Exception {
			logger.info("Qna 글 작성 페이지");
			model.addAttribute("cpa", cpa);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
                        }
                        return "qna/write";
		}
		
		@RequestMapping(value = "/qna/write", method = RequestMethod.POST)
		public String qnsPost(Model model,QnaVO vo, HttpSession session) throws Exception {
			logger.info("Qna 글 작성");
			vo.setMem_no((Integer)session.getAttribute("mem_no"));
			service.writeQna(vo);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}
			return "redirect:/qna";		
		}	
}
