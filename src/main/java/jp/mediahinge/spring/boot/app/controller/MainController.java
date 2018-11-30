package jp.mediahinge.spring.boot.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	public String index(Model model) {
		System.out.println("DEBUG:MainController:01");
		return "";
	}

}
