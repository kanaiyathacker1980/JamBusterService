
package com.joomsite.jambuster.operator.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

public class Utility {
	public final static int ITERATION_NUMBER = 1000;

	public static String randomNumeric() {
		return RandomStringUtils.randomNumeric(4).toUpperCase();
	}
	
	public static String getRandomAlphanumeric() {
		return RandomStringUtils.randomAlphanumeric(30).toUpperCase();
	}
	
	public static void main(String[] args) {
//		System.out.println(getRandomAlphanumeric());
//		
//		Date dateObject = getDateObject("12-12-2019", "dd-MM-yyyy");
//		java.sql.Date sqlDate = getSQLDate(dateObject);
//		System.out.println(sqlDate);
		
		java.sql.Date todaysDateSQL = getTodaysDateSQL();
		System.out.println(getTodaysTimeSQL());
	}
	
	public static String[] getUserAndPassword(String auth) {
        String userpassEncoded = auth.substring(5);
        String userpassDecoded = new String(Base64.decodeBase64(userpassEncoded.getBytes()));
        String[] authParamArray = null;
        if (userpassDecoded != null) {
            authParamArray = userpassDecoded.split(":");
        }
        return authParamArray;
	}
	
//	public static boolean isAuth(String auth , LoginService ls) {
//		String authArray [] = Utility.getUserAndPassword(auth);
//		String email = authArray[0];
//		String password = authArray[1];
//		Response<Object> facultyLogin = ls.login(new Login(email , password));
//		return "SUCCESS".equals(facultyLogin.getStatus());
//	}
	
	public static java.sql.Date getTodaysDateSQL() {
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Calcutta");
		Calendar calendar = new GregorianCalendar(timeZone);
		return new java.sql.Date(calendar.getTimeInMillis());
	}
	
	public static String getTodaysTimeSQL() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Calcutta");
        Calendar calendar = new GregorianCalendar(timeZone);
		return new StringBuilder().append(calendar.get(Calendar.HOUR_OF_DAY))
								  .append(":")
								  .append(calendar.get(Calendar.MINUTE) < 10 ? ("0" + calendar.get(Calendar.MINUTE)) : calendar.get(Calendar.MINUTE))
								  .toString();
	}
	
	public static Date getDateObject(String date , String format) {
		if(StringUtils.isEmpty(date)) return null;
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static java.sql.Date getSQLDate(java.util.Date date) {
		if(date == null) return null;
		return new java.sql.Date(date.getTime());
	}
	
	public static String getDateTime(String format) {
		String str =  new SimpleDateFormat(format).format(new Date());
		return str;
	}
	
	public static void sendMail(String sub, String body, String to, final String fromEmail , final String password) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		 props.put("mail.debug", "false");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		  });

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(sub);
			message.setText(body);
		    message.setContent(body,"text/html;charset=UTF-8");
			message.setSentDate(new Date());
			
			
			message.saveChanges();
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static String getSQLDateAsString(java.sql.Date date , String format) {
		if(date == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(format);
		return sdf.format(date);
	}
}