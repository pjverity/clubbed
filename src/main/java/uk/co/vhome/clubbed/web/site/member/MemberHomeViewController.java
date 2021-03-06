package uk.co.vhome.clubbed.web.site.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.vhome.clubbed.entities.Event;
import uk.co.vhome.clubbed.web.site.member.services.MemberService;

import java.util.List;
import java.util.Set;

/**
 * Member home screen
 */
@Controller
@RequestMapping("member/home")
public class MemberHomeViewController
{
	private final MemberService memberService;

	public MemberHomeViewController(MemberService memberService)
	{
		this.memberService = memberService;
	}

	@SuppressWarnings("unused")
	@GetMapping
	void get()
	{
	}

	@SuppressWarnings("unused")
	@ModelAttribute("completedEvents")
	Set<Event> completedEvents()
	{
		// TODO - Page this data
		return memberService.completedEvents();
	}

	@SuppressWarnings("unused")
	@ModelAttribute("upcomingEvents")
	List<Event> upcomingEvents()
	{
		return memberService.findUpcomingEvents();
	}

	@SuppressWarnings("unused")
	@ModelAttribute("totalDistance")
	Double totalDistance()
	{
		// TODO - move this to the DB
		return memberService.completedEvents().stream()
				       .mapToDouble(e -> e.getEventDetails().getDistance().doubleValue())
				       .sum();
	}
}
