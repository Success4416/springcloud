package com.lenily.dream.manager.message;

import com.lenily.dream.manager.content.ExceptionNotice;
import com.lenily.dream.manager.properties.EmailExceptionNoticeProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.regex.Pattern;

public class EmailNoticeSendComponent implements INoticeSendComponent {

	private MailSender mailSender;

	private MailProperties mailProperties;

	private EmailExceptionNoticeProperty emailExceptionNoticeProperty;

	public EmailNoticeSendComponent(MailSender mailSender, MailProperties mailProperties,
                                    EmailExceptionNoticeProperty emailExceptionNoticeProperty) {
		this.mailSender = mailSender;
		this.mailProperties = mailProperties;
		this.emailExceptionNoticeProperty = emailExceptionNoticeProperty;
		checkAllEmails();

	}

	public EmailNoticeSendComponent() {
	}

	@Override
	public void send(ExceptionNotice exceptionNotice) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		String fromEmail = emailExceptionNoticeProperty.getFrom();
		fromEmail = fromEmail == null ? mailProperties.getUsername() : fromEmail;
		mailMessage.setFrom(fromEmail);
		mailMessage.setTo(emailExceptionNoticeProperty.getTo());
		String[] cc = emailExceptionNoticeProperty.getCc();
		if (cc != null && cc.length > 0)
			mailMessage.setCc(cc);
		String[] bcc = emailExceptionNoticeProperty.getBcc();
		if (bcc != null && bcc.length > 0)
			mailMessage.setBcc(bcc);
		mailMessage.setText(exceptionNotice.createText());
		mailMessage.setSubject(String.format("来自%s的异常提醒", exceptionNotice.getProject()));
		mailSender.send(mailMessage);
	}

	private boolean isEmail(String email) {
		if (email != null)
			return Pattern.matches("^[A-Za-z0-9_\\-]+@[a-zA-Z0-9_\\-]+(\\.[a-zA-Z]{2,4})+$", email);
		return false;
	}

	private void checkAllEmails() {
		String fromEmail = emailExceptionNoticeProperty.getFrom();
		if (fromEmail != null && !isEmail(fromEmail))
			throw new IllegalArgumentException("发件人邮箱错误");
		String[] toEmail = emailExceptionNoticeProperty.getTo();
		if (toEmail != null) {
			for (String email : toEmail) {
				if (!isEmail(email))
					throw new IllegalArgumentException("收件人邮箱错误");
			}
		}
		String[] ccEmail = emailExceptionNoticeProperty.getCc();
		if (ccEmail != null) {
			for (String email : ccEmail) {
				if (!isEmail(email))
					throw new IllegalArgumentException("抄送人邮箱错误");
			}
		}
		String[] bccEmail = emailExceptionNoticeProperty.getBcc();
		if (bccEmail != null) {
			for (String email : bccEmail) {
				if (!isEmail(email))
					throw new IllegalArgumentException("秘密抄送人邮箱错误");
			}
		}
	}
}
