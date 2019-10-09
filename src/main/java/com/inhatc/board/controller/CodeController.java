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

import com.inhatc.board.domain.CodePager;
import com.inhatc.board.domain.CodeSearch;
import com.inhatc.board.domain.CodeVO;
import com.inhatc.board.service.CodeCommentService;
import com.inhatc.board.service.CodeService;

@Controller
public class CodeController {
        private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
        @Inject
        CodeService service;
        @Inject
        CodeCommentService com_service;
        @RequestMapping(value = "/", method = RequestMethod.GET)
		public String boardSearch(Model model, CodeSearch sch,CodePager cpa, HttpSession session) throws Exception {
                        cpa.setSizeOfPage(10);
			cpa.setNumberOfRecords(service.searchCodeCount(sch));
                        cpa.makePaging();
			if(session.getAttribute("mem_no") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
                        }
                        System.out.println(cpa.getMaxPost());
			model.addAttribute("langs",service.getLang());
			model.addAttribute("page", service.searchCode(sch, cpa));
                        model.addAttribute("pager", cpa);
			model.addAttribute("search", sch);
			return "code/board";
                }

		@RequestMapping(value = "/code/read", method = RequestMethod.GET)
		public String read(Model model, @RequestParam("cb_no") int cb_no, CodePager cpa, CodeSearch sch,HttpSession session) throws Exception {
			logger.info("글 읽기 페이지");
			service.viewHit(cb_no);
			model.addAttribute("post", service.read(cb_no));
			model.addAttribute("comment", com_service.commentSearch(cb_no));
			model.addAttribute("cpa", cpa);
                        model.addAttribute("search", sch);
                        model.addAttribute("totCom",com_service.commentCount(cb_no));
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
				model.addAttribute("mem_no", session.getAttribute("mem_no"));
                        }
                        return "code/read";
		}
		
		@RequestMapping(value = "/code/modify", method = RequestMethod.GET)
		public String modify(Model model, @RequestParam("cb_no") int cb_no, CodePager cpa, CodeSearch sch, HttpSession session) throws Exception {
			logger.info("글 수정 페이지");
			model.addAttribute("post", service.read(cb_no));
			model.addAttribute("cpa", cpa);
			model.addAttribute("search", sch);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
                        }
                        return "code/modify";
		}
		
		@RequestMapping(value = "/code/modify", method = RequestMethod.POST)
		public String modify_post(Model model, CodeVO vo, HttpSession session) throws Exception {
			logger.info("글 수정");
			vo.setMem_no((Integer)session.getAttribute("mem_no"));
			service.updateCode(vo);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}
			return "redirect:/code/read?cb_no="+vo.getCb_no();
		}
		
		@RequestMapping(value = "/code/delete", method = RequestMethod.GET)
		public String delete(Model model, CodeVO vo, HttpSession session) throws Exception {
			logger.info("글 삭제");
			service.deleteCode(vo.getCb_no());
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}
			return "redirect:/";		
		}
		
		@RequestMapping(value = "/code/write", method = RequestMethod.GET)
		public String write(Model model, CodePager cpa, HttpSession session) throws Exception {
			logger.info("글 작성 페이지");
			model.addAttribute("cpa", cpa);
			// model.addAttribute("search", sch);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
                        }
                        return "code/write";
		}
		
		@RequestMapping(value = "/code/write", method = RequestMethod.POST)
		public String write_post(Model model,CodeVO vo, HttpSession session) throws Exception {
			logger.info("글 작성");
			vo.setMem_no((Integer)session.getAttribute("mem_no"));
			service.writeCode(vo);
			if(session.getAttribute("id") != null){
				model.addAttribute("session", "yes");
				model.addAttribute("id", session.getAttribute("id"));
			}
			return "redirect:/";		
		}	
}
