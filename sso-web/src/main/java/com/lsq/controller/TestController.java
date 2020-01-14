package com.lsq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class TestController {
	

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String test(HttpServletRequest request) throws Exception {
		String url = request.getRequestURI();
		HttpSession session = request.getSession(false);
		if (session == null){
			return "login";
		}

		String token =String.valueOf(session.getAttribute("token"));

		return "redirect:http://"+url+"?token="+token;
	}

	@RequestMapping(value = "/checkToken", method = RequestMethod.GET)
	@ResponseBody
	public String checkToken(HttpServletRequest request) throws Exception {

		return "true";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) throws Exception {
		String url = request.getRequestURI();

		String name = request.getParameter("username");
		String psw = request.getParameter("passwork");

		if (name.equals("name") && psw.equals("psw")) {

			String token = UUID.randomUUID().toString();
			return "redirect:http://" + url + "?token=" + token;
		}
		return "login";
	}
}
