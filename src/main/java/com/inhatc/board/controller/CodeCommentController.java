package com.inhatc.board.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inhatc.board.domain.CodeCommentVO;
import com.inhatc.board.service.CodeCommentService;

@Controller
public class CodeCommentController {
        @Inject
        CodeCommentService service;
        @RequestMapping(value = "/code/commentCount", method = RequestMethod.POST)
		public @ResponseBody int commentCount(Model model, int cb_no, HttpSession session) throws Exception {
                return service.commentCount(cb_no);
        }
        @RequestMapping(value = "/code/commentInput", method = RequestMethod.POST)
		public @ResponseBody int commentInput(Model model, CodeCommentVO vo, HttpSession session) throws Exception {
                vo.setMem_no((Integer)session.getAttribute("mem_no"));
                return service.commentInsert(vo);
        }
        @RequestMapping(value = "/code/commentModify", method = RequestMethod.POST)
        public @ResponseBody int commentModify(Model model, CodeCommentVO vo) throws Exception {
                return service.commentUpdate(vo);
        }		
        @RequestMapping(value = "/code/commentDelete", method = RequestMethod.POST)
        public @ResponseBody Object commentDelete(Model model, CodeCommentVO vo) throws Exception {
                int result = service.commentDelete(vo.getCbc_no());
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("result", result);
                return map;
        }
        @RequestMapping(value = "/code/commentRead", method = RequestMethod.POST)
        public @ResponseBody Object commentRead(Model model, CodeCommentVO vo) throws Exception {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("result", service.commentSearch(vo.getCb_no()));
                return map;
        }
	
}
