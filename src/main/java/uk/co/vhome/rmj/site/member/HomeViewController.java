package uk.co.vhome.rmj.site.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.vhome.rmj.entities.Event;
import uk.co.vhome.rmj.services.controller.MemberService;

import java.util.List;

/**
 * Member home screen
 */
@Controller
@RequestMapping("member/home")
public class HomeViewController
{
	private final MemberService memberService;

	public HomeViewController(MemberService memberService)
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
	int completedEvents()
	{
		return memberService.completedEvents().size();
	}

	@SuppressWarnings("unused")
	@ModelAttribute("upcomingEvents")
	List<Event> upcomingEvents()
	{
		return memberService.findAllIncompleteEvents();
	}
}
