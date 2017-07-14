package com.MainController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HelloWorldController {
	@RequestMapping("/project")
	public ModelAndView helloWorld() {
		String message = "Hello World, Spring MVC @ Javatpoint";
		return new ModelAndView("project", "message", message);
	}
}
