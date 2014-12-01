package com.example.qr_codescan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONTokener;

public class CheckQRId {

	public static String login(String uri_qrId) {

		String token = "fb57b174f668ae42e116db4cb1eef1b3";
		System.out.println(uri_qrId + "/" + token);
		String html = executeHttpGet(uri_qrId + "/" + token);

		JSONTokener jsonParser = new JSONTokener(html);
		try {
			JSONObject lin = (JSONObject) jsonParser.nextValue();
			String type = lin.getString("type");
			if ("2".equals(type)) {
				String session = lin.getString("session");
				if (check(session)) {
					LoginOK.token = token;
					LoginOK.session = session;
					LoginOK.grid = lin.getString("qrid");
					return session;
				} else {
					return "ERROR_LOGIN_SESSION_EXCEPTION";

				}
			} else {
				return "ERROR_LOGIN_SESSION_EXCEPTION";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "ERROR_JSON_EXCEPTION";
		}
	}

	public static boolean check(String session) {
		return !"".equals(session);
	}

	public static String executeHttpGet(String url) {
		String result = null;
		URL uri = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			uri = new URL(url);
			connection = (HttpURLConnection) uri.openConnection();
			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line);
			}
			result = strBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

}
