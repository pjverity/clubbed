package uk.co.vhome.rmj.services;

import org.springframework.security.access.annotation.Secured;
import uk.co.vhome.rmj.Role;
import uk.co.vhome.rmj.entities.SupplementalUserDetails;

import java.util.Collection;

/**
 * Interface for sending mails of predefined content
 */
public interface MailService extends ServiceAvailabilityReporter
{
	@Secured({Role.SYSTEM, Role.RUN_AS_SYSTEM})
	void sendRegistrationMail(SupplementalUserDetails newUserDetails);

	@Secured({Role.SYSTEM, Role.RUN_AS_SYSTEM})
	void sendAdministratorNotification(Collection<SupplementalUserDetails> administrators, SupplementalUserDetails newUserDetails);
}
