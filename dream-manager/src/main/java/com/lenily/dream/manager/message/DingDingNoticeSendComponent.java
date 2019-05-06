package com.lenily.dream.manager.message;

import com.lenily.dream.manager.content.ExceptionNotice;
import com.lenily.dream.manager.httpclient.SimpleHttpClient;
import com.lenily.dream.manager.pojos.dingding.DingDingNotice;
import com.lenily.dream.manager.pojos.dingding.DingDingResult;
import com.lenily.dream.manager.properties.DingDingExceptionNoticeProperty;
import com.lenily.dream.manager.properties.ExceptionNoticeProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DingDingNoticeSendComponent implements INoticeSendComponent {

	private SimpleHttpClient simpleHttpClient;

	private ExceptionNoticeProperty exceptionNoticeProperty;

	private DingDingExceptionNoticeProperty dingDingExceptionNoticeProperty;

	private final Log logger = LogFactory.getLog(getClass());

	public DingDingNoticeSendComponent(SimpleHttpClient simpleHttpClient,
			ExceptionNoticeProperty exceptionNoticeProperty,
			DingDingExceptionNoticeProperty dingDingExceptionNoticeProperty) {
		this.simpleHttpClient = simpleHttpClient;
		this.exceptionNoticeProperty = exceptionNoticeProperty;
		this.dingDingExceptionNoticeProperty = dingDingExceptionNoticeProperty;
	}

	/**
	 * @return the simpleHttpClient
	 */
	public SimpleHttpClient getSimpleHttpClient() {
		return simpleHttpClient;
	}

	/**
	 * @return the exceptionNoticeProperty
	 */
	public ExceptionNoticeProperty getExceptionNoticeProperty() {
		return exceptionNoticeProperty;
	}

	/**
	 * @param simpleHttpClient the simpleHttpClient to set
	 */
	public void setSimpleHttpClient(SimpleHttpClient simpleHttpClient) {
		this.simpleHttpClient = simpleHttpClient;
	}

	/**
	 * @param exceptionNoticeProperty the exceptionNoticeProperty to set
	 */
	public void setExceptionNoticeProperty(ExceptionNoticeProperty exceptionNoticeProperty) {
		this.exceptionNoticeProperty = exceptionNoticeProperty;
	}

	@Override
	public void send(ExceptionNotice exceptionNotice) {
		DingDingNotice dingDingNotice = new DingDingNotice(exceptionNotice.createText(),
				dingDingExceptionNoticeProperty.getPhoneNum());
		DingDingResult result = simpleHttpClient.post(dingDingExceptionNoticeProperty.getWebHook(), dingDingNotice,
				DingDingResult.class);
		logger.debug(result);
	}

}
