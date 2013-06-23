package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.glassbox.webinvoice.shared.model.AuthenticationResult;
import com.glassbox.webinvoice.client.service.LoginService;

public class WebinvoiceTest {

	private LoginService loginService;
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		loginService = (LoginService) context.getBean("login");
		System.out.println("testing");
		AuthenticationResult result;
		
		result = loginService.authenticateUser("asdasd", "asdasd");
		print(result.isAuthenticated());
		result = loginService.authenticateUser("123456@", "asdasd");
		print(result.isAuthenticated());
		result = loginService.authenticateUser("123@", "3SMeYpi6");
		print(result.isAuthenticated());
	}


	private void print(Object msg) {
		System.out.println(msg);
	}
}
