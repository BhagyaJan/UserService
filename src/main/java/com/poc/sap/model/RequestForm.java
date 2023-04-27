package com.poc.sap.model;

import org.springframework.stereotype.Component;

@Component
public class RequestForm {
	private String jobName;
	private String url;
	private String execution;
	private String body;

	public RequestForm(String jobName, String url, String execution, String body) {
		super();
		this.jobName = jobName;
		this.url = url;
		this.execution = execution;
		this.body = body;
	}

	public RequestForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "RequestForm [jobName=" + jobName + ", url=" + url + ", execution=" + execution + ", body=" + body + "]";
	}

}
