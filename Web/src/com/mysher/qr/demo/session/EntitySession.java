package com.mysher.qr.demo.session;

import java.util.Date;

public class EntitySession {

	private String qrId;
	private String sessionId;
	private Date date;
	private int type;
	public String getQrId() {
		return qrId;
	}
	public void setQrId(String qrId) {
		this.qrId = qrId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
