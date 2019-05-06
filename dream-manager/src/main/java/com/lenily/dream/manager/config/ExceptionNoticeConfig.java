package com.lenily.dream.manager.config;

import com.google.gson.Gson;
import com.lenily.dream.manager.aop.ExceptionNoticeAop;
import com.lenily.dream.manager.exceptionhandle.ExceptionHandler;
import com.lenily.dream.manager.httpclient.SimpleHttpClient;
import com.lenily.dream.manager.message.DingDingNoticeSendComponent;
import com.lenily.dream.manager.message.INoticeSendComponent;
import com.lenily.dream.manager.properties.DingDingExceptionNoticeProperty;
import com.lenily.dream.manager.properties.EmailExceptionNoticeProperty;
import com.lenily.dream.manager.properties.ExceptionNoticeProperty;
import com.lenily.dream.manager.redis.ExceptionRedisStorageComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailSender;

@Configuration
@EnableConfigurationProperties({ ExceptionNoticeProperty.class, DingDingExceptionNoticeProperty.class,
		EmailExceptionNoticeProperty.class })
@ConditionalOnMissingBean({ ExceptionHandler.class })
class ExceptionNoticeConfig {

	@Autowired
	private ExceptionNoticeProperty exceptionNoticeProperty;

	@Bean
	@ConditionalOnProperty(name = "exceptionnotice.notice-type", havingValue = "dingding")
	@ConditionalOnMissingBean(INoticeSendComponent.class)
	public INoticeSendComponent dingDingNoticeSendComponent(SimpleHttpClient simpleHttpClient,
			DingDingExceptionNoticeProperty dingDingExceptionNoticeProperty) {
		INoticeSendComponent component = new DingDingNoticeSendComponent(simpleHttpClient, exceptionNoticeProperty,
				dingDingExceptionNoticeProperty);
		return component;
	}

	@Bean
	@ConditionalOnProperty(name = "exceptionnotice.notice-type", havingValue = "email")
	@ConditionalOnMissingBean({ INoticeSendComponent.class })
	public INoticeSendComponent EmailNoticeSendComponent(MailSender mailSender, MailProperties mailProperties,
                                                         EmailExceptionNoticeProperty emailExceptionNoticeProperty) {
		INoticeSendComponent component = new com.lenily.dream.manager.message.EmailNoticeSendComponent(mailSender, mailProperties,
				emailExceptionNoticeProperty);
		return component;
	}

	@Bean
	@ConditionalOnProperty(name = "exceptionnotice.enable-redis-storage", havingValue = "true")
	@ConditionalOnMissingBean(ExceptionRedisStorageComponent.class)
	public ExceptionRedisStorageComponent exceptionRedisStorageComponent(StringRedisTemplate stringRedisTemplate,
                                                                         Gson gson, ExceptionHandler exceptionHandler) {
		ExceptionRedisStorageComponent exceptionRedisStorageComponent = new ExceptionRedisStorageComponent(
				exceptionNoticeProperty, stringRedisTemplate, gson);
		exceptionHandler.setExceptionRedisStorageComponent(exceptionRedisStorageComponent);
		return exceptionRedisStorageComponent;
	}

	@Bean
	@ConditionalOnProperty(name = "exceptionnotice.enable-check-annotation", havingValue = "true", matchIfMissing = true)
	@ConditionalOnMissingBean(ExceptionNoticeAop.class)
	public ExceptionNoticeAop exceptionNoticeAop(ExceptionHandler exceptionHandler) {
		ExceptionNoticeAop aop = new ExceptionNoticeAop(exceptionHandler);
		return aop;
	}

	@Bean
	@ConditionalOnMissingBean({ ExceptionHandler.class })
	public ExceptionHandler exceptionHandler(INoticeSendComponent noticeSendComponent) {
		ExceptionHandler exceptionHandler = new ExceptionHandler(noticeSendComponent, exceptionNoticeProperty);
		return exceptionHandler;
	}

	@Bean
	@ConditionalOnProperty(name = "exceptionnotice.notice-type", havingValue = "dingding")
	public SimpleHttpClient simpleHttpClient(Gson gson) {
		SimpleHttpClient httpClient = new SimpleHttpClient(gson);
		return httpClient;
	}

}
