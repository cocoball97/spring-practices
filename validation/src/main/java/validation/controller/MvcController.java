package validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import validation.domain.User;

@Controller
public class MvcController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/result")
	public String result() {
		return "result";
	}
	
	@GetMapping("/ex01")
	public String ex01(@ModelAttribute User user) {		
		return "form/ex01";
	}
	
	@PostMapping("/ex01")
	public String ex01(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
//			List<ObjectError> errors = result.getAllErrors();
//			for(ObjectError error : erorrors) {
//				System.out.println(error);
//			}
			
//			// 전달되면 파싱되서 view
//			Map<String, Object> map = result.getModel();
			
//			Set<String> s = map.keySet();
//			for(String key : s) {
//				model.addAttribute(key, map.get(key));
//			}
			// map을 풀어서 전달. 위 코드와 일치. jsp에서는 list로 사용하면 됨
			model.addAllAttributes(result.getModel());
			
			return "form/ex01";
		}
		
		return "redirect:/result";
	}
	
	@GetMapping("/ex02")
	public String ex02() {
		return "form/ex02";
	}
	
	@PostMapping("/ex02")
	public String ex02(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "form/ex02";
		}
		
		return "redirect:/result";
	}
	
	@GetMapping("/ex03")
	public String ex03(@ModelAttribute User user) {
		return "form/ex03";
	}
	
	@PostMapping("/ex03")
	public String ex03(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "form/ex03";
		}
		
		return "redirect:/result";
	}	
}
