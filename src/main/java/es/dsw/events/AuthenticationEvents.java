package es.dsw.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class AuthenticationEvents {

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent success) {
		
		RequestContextHolder.getRequestAttributes().setAttribute("DataUser", "Se ha autenticado", RequestAttributes.SCOPE_SESSION);
	}
	
	@EventListener
	public void onFailure(AbstractAuthenticationFailureEvent failure) {
		
	}
}
