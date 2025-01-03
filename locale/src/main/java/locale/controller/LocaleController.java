package locale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LocaleController {
	private LocaleResolver localeResovlver;
	
	public LocaleController(LocaleResolver localeResolver) {
		this.localeResovlver = localeResolver;
	}
	// Http가 들어오는걸 보니 인터셉터에서 하는게 좋아보임?
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		System.out.println("resolver-locale: "+ localeResovlver.resolveLocale(request));
		
		return "index";
	}
}
