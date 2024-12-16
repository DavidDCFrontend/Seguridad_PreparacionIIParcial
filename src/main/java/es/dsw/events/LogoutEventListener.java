package es.dsw.events;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LogoutEventListener implements ApplicationListener<LogoutSuccessEvent>{
	
	@Override
	public void onApplicationEvent(LogoutSuccessEvent event) {
		System.out.println("El usuario se ha marchado.");
	}

}
