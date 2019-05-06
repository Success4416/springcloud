package com.lenily.dream.manager.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;

@ConfigurationProperties(prefix = "exceptionnotice.dingding")
public class DingDingExceptionNoticeProperty {

	/**
	 * 电话信息
	 */
	private String[] phoneNum;

	/**
	 * 钉钉机器人web钩子
	 */
	private String webHook;

	/**
	 * @return the phoneNum
	 */
	public String[] getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String[] phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the webHook
	 */
	public String getWebHook() {
		return webHook;
	}

	/**
	 * @param webHook the webHook to set
	 */
	public void setWebHook(String webHook) {
		this.webHook = webHook;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DingDingExceptionNoticeProperty [phoneNum=" + Arrays.toString(phoneNum) + ", webHook=" + webHook + "]";
	}

	
}
