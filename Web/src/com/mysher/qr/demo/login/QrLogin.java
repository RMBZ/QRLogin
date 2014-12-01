package com.mysher.qr.demo.login;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysher.qr.demo.session.ContextMap;
import com.mysher.qr.demo.session.EntitySession;
import com.mysher.qr.demo.session.SessionUtil;

@Controller
public class QrLogin {

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		EntitySession eSession = SessionUtil.getSession();
		if (null == ContextMap.Session) {
			ContextMap.Session = new ConcurrentHashMap<String, EntitySession>();
		}
		ContextMap.Session.put(eSession.getQrId(), eSession);
		modelMap.put("session", eSession);
		return new ModelAndView("index");
	}

	@RequestMapping(value = "isLoginType/{qrid}")
	public ModelAndView login(@PathVariable("qrid") String qrId,
			ModelMap modelMap) {
		try {
			EntitySession es = ContextMap.Session.get(qrId);
			modelMap.put("type", es.getType());
			return new ModelAndView("tips_login", modelMap);
		} catch (Exception e) {
			modelMap.put("type", 4);
			return new ModelAndView("tips_login", modelMap);
		}
	}

	@RequestMapping(value = "appLogin/{qrid}/{token}")
	public ModelAndView login_app(@PathVariable("qrid") String qrId,
			@PathVariable("token") String token, ModelMap modelMap) {
		try {
			if (!"fb57b174f668ae42e116db4cb1eef1b3".equals(token)) {
				modelMap.put("type", 4);
				return new ModelAndView("tips_login", modelMap);
			}
			EntitySession es = ContextMap.Session.get(qrId);
			if (1 != es.getType()) {
				modelMap.put("type", 4);
				return new ModelAndView("tips_login", modelMap);
			} else {
				modelMap.put("session", es.getSessionId());
				modelMap.put("qrid", es.getQrId());
				modelMap.put("type", 2);
				es.setType(2);
				ContextMap.Session.put(es.getQrId(), es);
				return new ModelAndView("tips_login", modelMap);
			}
		} catch (Exception e) {
			modelMap.put("type", 4);
			return new ModelAndView("tips_login", modelMap);
		}
	}
	
	@RequestMapping(value = "btnLogin/{qrid}/{token}/{session}")
	public ModelAndView login_btn_ok(@PathVariable("qrid") String qrId,
			@PathVariable("token") String token, @PathVariable("session") String session,
			ModelMap modelMap) {
		try {
			if (!"fb57b174f668ae42e116db4cb1eef1b3".equals(token)) {
				modelMap.put("type", 4);
				return new ModelAndView("tips_login", modelMap);
			}
			EntitySession es = ContextMap.Session.get(qrId);
			if(!es.getSessionId().equals(session))
			{
				modelMap.put("type", 4);
				return new ModelAndView("tips_login", modelMap);
			}
			
			if (2 != es.getType()) {
				modelMap.put("type", 4);
				return new ModelAndView("tips_login", modelMap);
			} else {
				modelMap.put("type", "OK");
				es.setType(3);
				ContextMap.Session.put(es.getQrId(), es);
				return new ModelAndView("tips_login", modelMap);
			}
		} catch (Exception e) {
			modelMap.put("type", 4);
			return new ModelAndView("tips_login", modelMap);
		}
	}
}
