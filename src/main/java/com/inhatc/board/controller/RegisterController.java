package com.inhatc.board.controller;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inhatc.board.domain.RegisterVO;
import com.inhatc.board.service.RegisterService;
@Controller
public class RegisterController {
        @Inject
        RegisterService service;
        @RequestMapping(value = "/login/register", method = RequestMethod.GET)
		public void login() {
		}
		@RequestMapping(value = "/login/register", method = RequestMethod.POST)
		public String register(RegisterVO vo, Model model) throws Exception {
			int result=service.register(vo);
			if(result==1){
				model.addAttribute("regi","true");
				return "/login/login";
			}else{
				model.addAttribute("regi","false");
				return "/login/register";	
			}
		}	
		@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
		public @ResponseBody Object idcheck(Model model, RegisterVO vo) throws Exception {
			int result = service.idCheck(vo);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			return map;
		}
		@RequestMapping(value = "/emailcheck", method = RequestMethod.POST)
		public @ResponseBody Object emailcheck(Model model, RegisterVO vo) throws Exception {
			int result = service.emailCheck(vo);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			return map;
		}		
		@RequestMapping(value = "/idfind", method = RequestMethod.POST)
		public @ResponseBody Object findid(Model model, RegisterVO vo) throws Exception {
                        RegisterVO id = service.findId(vo);
                        HashMap<String, Object> map = new HashMap<String, Object>();
			if(id==null){
                                map.put("result", null);
                        }else{
                                map.put("result", id.getMem_id());
                        }
			return map;
		}
		@RequestMapping(value = "/pwfind", method = RequestMethod.POST)
		public @ResponseBody Object findpw(Model model, RegisterVO vo) throws Exception {
			RegisterVO pw = service.findPw(vo);
                        HashMap<String, Object> map = new HashMap<String, Object>();
			if(pw==null){
                                map.put("result", null);
			}else{
                                map.put("result", pw.getMem_pw());
                        }
			return map;
		}
}
