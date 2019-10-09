package com.inhatc.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.inhatc.board.free.domain.FreeBoardCommentVO;
import com.inhatc.board.free.domain.FreeBoardPager;
import com.inhatc.board.free.domain.FreeBoardSearchKey;
import com.inhatc.board.free.domain.FreeBoardVO;
import com.inhatc.board.free.service.FreeBoardCommentService;
import com.inhatc.board.free.service.FreeBoardService;
import com.inhatc.board.free.service.FreeFileService;

@Controller
public class FreeController {
	
	@Autowired
	FreeBoardService boardService;
	
	@Autowired
	FreeBoardCommentService commentService;
	
	@Autowired
	FreeFileService fileService;
	
    @RequestMapping(value = "/free")
    public String freeBoard(Model model, FreeBoardPager pager, FreeBoardSearchKey key) throws Exception {
		pager.setSizeOfPage(10);
		pager.setNumberOfRecords(boardService.getSearchBoardCount(key));
		pager.makePaging();
		System.out.println("서치 : ~~~~~~ : " + key.getSearch());
		model.addAttribute("notice", boardService.searchNotice());
		model.addAttribute("board", boardService.search(key, pager));
		model.addAttribute("pager", pager);
		model.addAttribute("key", key);
		return "/free/board";
	}
    
    @RequestMapping("free/read")
	public String freeRead(Model model, int board_no) throws Exception {
		boardService.addHit(board_no);
		
		model.addAttribute("board", boardService.read(board_no));
		model.addAttribute("totCom", commentService.commentCount(board_no));
		model.addAttribute("comment", commentService.commentSearch(board_no));
		model.addAttribute("file", fileService.getFileList(board_no));
		
		return "/free/board_read";
	}
	
	@RequestMapping("free/write")
	public String freeWrite(Model model) throws Exception {
		return "free/board_write";
	}
	
	@RequestMapping(value="free/write", method = RequestMethod.POST)
	public String freeWritePost(FreeBoardVO vo, HttpSession session, MultipartHttpServletRequest files) throws Exception {
		vo.setMem_no((Integer)(session.getAttribute("mem_no")));
		boardService.writeBoard(vo);
		int fb_no = boardService.getBoardNo((Integer)(session.getAttribute("mem_no")));
		List<MultipartFile> file = files.getFiles("file");
		if (file.size() == 1 && file.get(0).getOriginalFilename().equals("")) {
            // 파일이 없었을 때
        } else {
        	fileService.registration(file, fb_no);
        }
		
		return "redirect:/free";
	}
	
	@RequestMapping(value="free/update")
	public String freeUpdate(Model model, int fb_no) throws Exception {
		model.addAttribute("board", boardService.read(fb_no));
		return "free/board_update";
	}
	
	@RequestMapping(value="free/update", method=RequestMethod.POST)
	public String freeUpdateBoard(FreeBoardVO vo, HttpSession session) throws Exception {
		vo.setMem_no((Integer)(session.getAttribute("mem_no")));
		boardService.updateBoard(vo);
		return "redirect:/free";
	}
	
	@RequestMapping("free/delete")
	public String freeDeleteBoard(int fb_no) throws Exception {
		boardService.deleteBoard(fb_no);
		return "redirect:/free";
	}
	
	
	/* 댓글 */
	@RequestMapping(value="free/commentInsert", method=RequestMethod.POST)
	@ResponseBody
	public int addComment(FreeBoardCommentVO vo) throws Exception {
		int result = commentService.commentInsert(vo);
		return result;
	}
	
	@RequestMapping(value="free/commentDelete", method=RequestMethod.POST)
	@ResponseBody
	public int commentDelete(int fbc_no) throws Exception {
		int result = commentService.commentDelete(fbc_no);
		return result;
	}
	
	@RequestMapping(value="free/commentUpdate", method=RequestMethod.POST)
	@ResponseBody
	public int commentUpdate(int fbc_no, String fbc_text) throws Exception {
		int result = commentService.commentUpdate(fbc_no, fbc_text);
		return result;
	}
    
    
}
