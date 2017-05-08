package lam.mail.send.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import lam.mail.model.LamMail;
import lam.mail.send.LamMailSender;

/**
* <p>
* TODO
* </p>
* @author linanmiao
* @date 2017年5月8日
* @version 1.0
*/
@Service(value="lamMailSender")
public class LamMailSenderImpl implements LamMailSender{
	
	private static Logger logger = LoggerFactory.getLogger(LamMailSenderImpl.class);

	@Resource(name="javaMailSenderImpl")
	private JavaMailSenderImpl javaMailSenderImpl;
	
	@Override
	public boolean send(LamMail lamMail) {
		boolean result;
		try{
			javaMailSenderImpl.send(lamMail.getSimpleMailMessage());
			result = Boolean.TRUE.booleanValue();
		}catch(Exception e){
			logger.error("send mail fail", e);
			result = Boolean.FALSE.booleanValue();
		}
		return result;
	}

}
