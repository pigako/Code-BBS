package com.inhatc.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inhatc.board.domain.LoginVO;
import com.inhatc.board.service.LoginService;
@Controller
public class LoginController {
        @Inject
        LoginService service;
        @RequestMapping(value = "/login/login", method = RequestMethod.GET)
		public void login() {
		}
		@RequestMapping(value = "/login/login", method = RequestMethod.POST)
		public String login_check(Model model, LoginVO vo, HttpSession session) {
                        String id = vo.getId();
                        vo=service.loginCheck(vo);
                        System.out.println(vo.getCount());
			if(vo.getCount()>0)
			{
				session.setAttribute("id", id);
				session.setAttribute("mem_no", vo.getMem_no());
				return "redirect:/";
			}
			else
			{
				model.addAttribute("check","false");
				return "/login/login";			
			}
		}	
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logout(HttpSession session) {
			session.removeAttribute("id");
			session.removeAttribute("mem_no");
			return "redirect:/";
			
		}	
}
