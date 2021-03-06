package uk.co.vhome.clubbed.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Entity
@Table(name = "events")
public class Event
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "event")
	@PrimaryKeyJoinColumn
	private EventDetails eventDetails;

	@Column(name = "event_datetime")
	private LocalDateTime eventDateTime;

	@ManyToMany
	@JoinTable(name = "events_users",
		joinColumns = @JoinColumn(name = "events_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "users_id"))
	private List<UserDetailsEntity> userDetailsEntities;

	@Basic
	private boolean completed;

	private transient boolean cancelled;

	protected Event()
	{
	}

	public Event(LocalDateTime eventDateTime, EventDetails eventDetails)
	{
		this.eventDateTime = eventDateTime;
		this.eventDetails = eventDetails;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public EventDetails getEventDetails()
	{
		return eventDetails;
	}

	public void setEventDetails(EventDetails eventDetails)
	{
		this.eventDetails = eventDetails;
	}

	public LocalDateTime getEventDateTime()
	{
		return eventDateTime;
	}

	public void setEventDateTime(LocalDateTime eventDateTime)
	{
		this.eventDateTime = eventDateTime;
	}

	public boolean isCompleted()
	{
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}

	public boolean isCancelled()
	{
		return cancelled;
	}

	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}

	public List<UserDetailsEntity> getUserDetailsEntities()
	{
		return userDetailsEntities;
	}

	public void setUserDetailsEntities(List<UserDetailsEntity> userDetailsEntities)
	{
		this.userDetailsEntities = userDetailsEntities;
	}

	public String getEventDateTimeText()
	{
		return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT).format(eventDateTime);
	}

	public String getEventDateTimeFullText()
	{
		return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT).format(eventDateTime);
	}

	public boolean hasStarted()
	{
		return !isCompleted() && eventDateTime.isBefore(LocalDateTime.now());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		Event event = (Event) o;

		return id != null ? id.equals(event.id) : event.id == null;
	}

	@Override
	public int hashCode()
	{
		return id != null ? id.hashCode() : 0;
	}
}
