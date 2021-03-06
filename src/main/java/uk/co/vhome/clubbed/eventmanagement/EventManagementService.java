package uk.co.vhome.clubbed.eventmanagement;

import org.springframework.security.access.annotation.Secured;
import uk.co.vhome.clubbed.entities.Event;
import uk.co.vhome.clubbed.usermanagement.Role;

import java.time.LocalDateTime;
import java.util.List;

public interface EventManagementService
{
	@Secured({Role.ORGANISER})
	Event createNewEvent(LocalDateTime eventDateTime);

	@Secured({Role.ORGANISER})
	void completeEvent(Event event);

	@Secured({Role.ORGANISER})
	void cancelEvent(Event event);

	List<Event> findAllIncompleteEvents();

	List<Event> findTop10CompletedEvents();

	List<Event> fetchEventsAfter(LocalDateTime dateTime, boolean inclusive, boolean completed);

	List<Event> fetchEventsBefore(LocalDateTime dateTime, boolean inclusive, boolean completed);
}
