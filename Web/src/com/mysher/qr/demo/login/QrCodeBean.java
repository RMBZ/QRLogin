package com.mysher.qr.demo.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mysher.qr.demo.qr.QrCodeCore;
import com.mysher.qr.demo.session.ContextMap;
import com.mysher.qr.demo.session.EntitySession;

@Controller
@RequestMapping("QrCode")
public class QrCodeBean {
	@Autowired
	QrCodeCore qr;
	
	@RequestMapping(value="/{qrid}")
	public void image(@PathVariable("qrid") String qrId,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		try {
			String uri = "http://192.168.1.109:7080/Web_Spring_Test/appLogin/";
			EntitySession es = ContextMap.Session.get(qrId);
			if (es.getType() == 1) {
				InputStream is = qr.getImageStream(uri+qrId);
				response.setContentType("image/png");
				OutputStream stream = response.getOutputStream();
				byte[] buff = new byte[2048];
				int size = 0;
				while (is != null && (size = is.read(buff)) != -1) {
					stream.write(buff, 0, size);
		        }
		        stream.flush();
		        stream.close();
			}
		} catch (Exception e) {
			
		}

	}
}
