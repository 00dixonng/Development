package com.glassbox.webinvoice.server.service;

import java.io.File;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.stereotype.Service;
import com.glassbox.webinvoice.shared.DTOentity.RealEmail;


@Service("mailService")
public class MailServiceImpl {

	public void sendEmail(RealEmail email, String host, String user,
			String fromEmail, String password) throws Exception {
            
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", fromEmail);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);      

		Message message = new MimeMessage(session);

		try {
			Address from = new InternetAddress(fromEmail);

			message.setFrom(from);

			message.setSubject(email.getSubject());

			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();

			mbp.setContent(email.getContent(), "text/plain; charset=UTF-8");
			mp.addBodyPart(mbp);
			for (File attachFile : email.getAttachFiles()) {
				if (attachFile != null && attachFile.exists()) {
					MimeBodyPart mbpAttach = new MimeBodyPart();
					mbpAttach.attachFile(attachFile);
					mbpAttach.setFileName(attachFile.getName());
					mp.addBodyPart(mbpAttach);
				}
			}
			message.setContent(mp);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			// List<Address> addresses = new ArrayList<Address>();
			int recSize = email.getReceipients().size();
			Address[] addresses = new Address[recSize];
			for (int i = 0; i < recSize; i++)
				addresses[i] = new InternetAddress(email.getReceipients()
						.get(i));
			transport.sendMessage(message, addresses);
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to send email");
		}
	}

}
