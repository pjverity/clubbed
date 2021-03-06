package uk.co.vhome.clubbed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.co.vhome.clubbed.security.SecurityConfiguration;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer
{
	private final MessageSource messageSource;

	@Autowired
	public WebMvcConfiguration(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/world/coach-profile").setViewName("world/coach-profile");
		registry.addViewController("/world/privacy-policy").setViewName("world/privacy-policy");
	}

	@Override
	public Validator getValidator()
	{
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
		factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
		factoryBean.setValidationMessageSource(messageSource);
		return factoryBean;
	}
}
