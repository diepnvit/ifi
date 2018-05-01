package com.ifi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@RequestMapping("/")
	public ModelAndView home(ModelAndView view) {
		view.setViewName("index");
		return view;
	}
}
