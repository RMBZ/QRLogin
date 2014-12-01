package com.mysher.qr.demo.session;

import java.util.UUID;

public class SessionUtil {

	public static EntitySession getSession() {
		EntitySession eSession = new EntitySession();
		eSession.setQrId(getUUID());
		eSession.setSessionId(getUUID());
		eSession.setType(1);
		return eSession;
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
