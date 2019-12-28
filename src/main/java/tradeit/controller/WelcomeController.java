package tradeit.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tradeit.config.WelcomeProps;

@RestController
@RequestMapping(path="/",
produces="application/json")
@CrossOrigin(origins="*")
public class WelcomeController {
	
	private WelcomeProps welcomeProps;
	
	public WelcomeController(WelcomeProps welcomeProps) {
		this.welcomeProps = welcomeProps;
	}
	
	@GetMapping
	public String hello() {
		return welcomeProps.getWelcome();
	}
}
