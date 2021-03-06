package uk.co.vhome.clubbed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import uk.co.vhome.clubbed.security.AuthenticatedUser;
import uk.co.vhome.clubbed.usermanagement.UserAccountManagementService;

/**
 * Event handlers for various application/http events
 */
@Component
public class ApplicationEventHandler
{
	private final UserAccountManagementService userAccountManagementService;

	@Autowired
	public ApplicationEventHandler(UserAccountManagementService userAccountManagementService)
	{
		this.userAccountManagementService = userAccountManagementService;
	}

	@EventListener
	public void on(ContextRefreshedEvent contextStartedEvent)
	{
		if (contextStartedEvent.getApplicationContext().getParent() == null)
		{
			AuthenticatedUser.runWithSystemUser(userAccountManagementService::createBasicDefaultAccounts);
		}
	}

	/*
	 * Session attributes are added in the authentication success handler as we can't inject service
	 * interfaces that contain method security annotations there. See the handler comments in SecurityConfiguration
	 */
	@EventListener
	public void on(AuthenticationSuccessEvent authenticationSuccessEvent)
	{
		User user = ((User) authenticationSuccessEvent.getAuthentication().getPrincipal());

		AuthenticatedUser.runWithSystemUser(() -> userAccountManagementService.updateLastLogin(user.getUsername(),
		                                                                                       authenticationSuccessEvent.getTimestamp()));
	}
}
