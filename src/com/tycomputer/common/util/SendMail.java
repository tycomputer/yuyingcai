package com.tycomputer.common.util;

/**
 * @author 张刘华
 * @version 1.0
 */

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class SendMail {
	private static Logger logger = Logger.getLogger(SendMail.class);

	private static MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象

	private Properties props; // 系统属性

	private static boolean needAuth = true; // smtp是否需要认证

	private static String username = "mail@yuyingcai.com.cn"; // smtp认证用户名和密码

	private static String password = "yuyingcaimail";

	private static String smtp = "smtp.exmail.qq.com";

	private static String sendfrom = "mail@yuyingcai.com.cn";

	private static String service;

	private static String tycomputer = "tycomputer@gmail.com";

	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

	// static {
	// ResourceBundle rb = ResourceBundle.getBundle("mail");
	// smtp = rb.getString("default.smtp");
	// username = rb.getString("default.name");
	// password = rb.getString("default.password");
	// sendfrom = rb.getString("default.sendfrom");
	// service = rb.getString("default.service");
	// tycomputer = rb.getString("default.tycomputer");
	// }

	public SendMail() {
		setSmtpHost(smtp);
		setNeedAuth(needAuth);
	}

	/**
	 * @param hostName
	 *            String
	 */
	public void setSmtpHost(String hostName) {
		// System.out.println("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = System.getProperties(); // 获得系统属性对象

		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		try {
			// System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
			logger.error(e);
			// System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}

		// System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象

			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			logger.error(e);
			// System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * @param need
	 *            boolean
	 */
	public void setNeedAuth(boolean need) {
		// System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();

		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/**
	 * @param mailSubject
	 *            String
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject) {
		// System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			logger.error(e);
			// System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * @param mailBody
	 *            String
	 */
	public boolean setWebBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=gb2312>" + mailBody, "text/html;charset=GB2312");
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			logger.error(e);
			// System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param mailBody
	 *            String
	 */
	public boolean setTextBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setText(mailBody);
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			logger.error(e);
			// System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean addFileAffix(Object filename) {

		// System.out.println("增加邮件附件：" + filename);

		try {
			BodyPart bp = new MimeBodyPart();

			if (filename instanceof String) {
				FileDataSource fileds = new FileDataSource((String) filename);
				bp.setDataHandler(new DataHandler(fileds));
				bp.setFileName(fileds.getName());

				mp.addBodyPart(bp);
			} else {
				String[] filenames = (String[]) filename;
				for (int i = 0; i < filenames.length; i++) {
					FileDataSource fileds = new FileDataSource(filenames[i]);
					bp.setDataHandler(new DataHandler(fileds));
					bp.setFileName(fileds.getName());

					mp.addBodyPart(bp);
				}
			}

			return true;
		} catch (Exception e) {
			logger.error(e);
			// System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setFrom(String from) {
		// System.out.println("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;

		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean sendout() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			// System.out.println("正在发送邮件....");

			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username, password);
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			// transport.send(mimeMsg);

			// System.out.println("发送邮件成功！");
			transport.close();

			return true;
		} catch (Exception e) {
			logger.error(e);
			// System.err.println("邮件发送失败！" + e);
			return false;
		}
	}

	/**
	 * 发送HTML 邮件
	 */
	public boolean sendWebMail(String sendTo, String subject, Object file, String mailbody) {
		boolean b = false;
		createMimeMessage();
		if ((subject != null) && (!subject.equals(""))) {
			if (!setSubject(subject)) {
				return b;
			}
		}

		if (!setWebBody(mailbody)) {
			return b;
		}

		if (!setTo(sendTo)) {
			return b;
		}
		setFrom(sendfrom);
		if ((file != null) && (!"".equals(file))) {
			if (!addFileAffix(file)) {
				return b;
			}
		}

		if (!sendout()) {
			return b;
		}
		return !b;
	}

	/**
	 * 发送纯文件邮件
	 */
	public boolean sendTextMail(String sendTo, String subject, Object file, String mailbody) {
		boolean b = false;
		createMimeMessage();
		if ((subject != null) && (!subject.equals(""))) {
			if (!setSubject(subject)) {
				return b;
			}
		}

		if (!setTextBody(mailbody)) {
			return b;
		}

		if (!setTo(sendTo)) {
			return b;
		}
		setFrom(sendfrom);
		if ((file != null) && (!"".equals(file))) {
			if (!addFileAffix(file)) {
				return b;
			}
		}

		if (!sendout()) {
			return b;
		}
		return !b;
	}

	/**
	 * 发送纯文件邮件
	 */
	public boolean sendServiceMail(String subject, Object file, String mailbody) {
		boolean b = false;
		createMimeMessage();
		if ((subject != null) && (!subject.equals(""))) {
			if (!setSubject(subject)) {
				return b;
			}
		}

		if (!setTextBody(mailbody)) {
			return b;
		}

		if (!setTo(service)) {
			return b;
		}
		setFrom(sendfrom);
		if ((file != null) && (!"".equals(file))) {
			if (!addFileAffix(file)) {
				return b;
			}
		}

		if (!sendout()) {
			return b;
		}
		return !b;
	}

	/**
	 * 发送纯文件邮件
	 */
	public boolean sendToTycomputer(String subject, Object file, String mailbody) {
		boolean b = false;
		createMimeMessage();
		if ((subject != null) && (!subject.equals(""))) {
			if (!setSubject(subject)) {
				return b;
			}
		}

		if (!setTextBody(mailbody)) {
			return b;
		}

		if (!setTo(tycomputer)) {
			return b;
		}
		setFrom(sendfrom);
		if ((file != null) && (!"".equals(file))) {
			if (!addFileAffix(file)) {
				return b;
			}
		}

		if (!sendout()) {
			return b;
		}
		return !b;
	}

	public static SendMail getInstants() {
		return new SendMail();
	}

	// public static String madeLink(String title, String www) {
	// StringBuffer sb = new StringBuffer();
	// sb
	// .append(
	// "<meta http-equiv=Content-Type content=text/html; charset=gb2312><div
	// align=center><a href='")
	// .append(www).append("'> ").append(title).append(" </a></div>");
	// return sb.toString();
	// }
	//
	// public static String madeLink(String[] title, String[] www) {
	// StringBuffer sb = new StringBuffer();
	// sb
	// .append("<meta http-equiv=Content-Type content=text/html;
	// charset=gb2312>");
	// if ((title != null) && (www != null) && (title.length == www.length)) {
	// for (int i = 0; i < title.length; i++) {
	// sb.append("<a href='").append(www[i]).append("'>").append(
	// title[i]).append("</a>");
	// }
	// }
	// return sb.toString();
	// }

	public static void main(String[] args) {

		StringBuffer mailbody = new StringBuffer("您好!");
		// String[] title = null;
		// String[] www = null;
		SendMail.getInstants().sendToTycomputer("test", null, "测试一下子");
		// SendMail.getInstants().sendWebMail("tycomputer@126.com", "test", new
		// String[] { "c:\\a.txt", "c:\\b.txt" }, mailbody.toString());

	}
}
