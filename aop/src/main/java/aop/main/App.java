package aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aop.domain.Product;
import aop.service.ProductService;

public class App {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		
		// Service 빈 가져오기
		ProductService ps = ac.getBean(ProductService.class);
		Product p = ps.find("TV");
		System.out.println(p);
		
		// close() 메서드를 ApplicationContext 인터페이스가 아닌 구현 클래스에서 제공하고 있음
		((AbstractApplicationContext)ac).close();
	}

}
