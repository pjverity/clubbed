package uk.co.vhome.clubbed.mail.service;

import java.util.Map;

public interface MailService
{
	void sendEMail(String toAddress, String subject, String templateName, Map<String, Object> templateValues);

	void sendEMailAndBccAdmin(String toAddress, String subject, String templateName, Map<String, Object> templateValues);
}
