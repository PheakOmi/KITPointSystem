package com.DaoClasses;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ModelClasses.Mail;

public class test {


public static void main(String[] args) {
ApplicationContext context = new ClassPathXmlApplicationContext(
    "com/DaoClasses/mail-config.xml");

  Mail mail = new Mail();
  mail.setMailFrom("sopheakdy23@gmail.com");
  mail.setMailTo("dysopheak15@kit.edu.kh");
  mail.setMailSubject("KIT Point Management System Password Reset");
  mail.setMailContent("Hello You,\n\nIt looks like you requested a new password.\nIf that sounds right, you can enter new password by clicking on the button below.\nhttps://www.google.com.kh/search?q=reset+password+email+template&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjAwenJxr3XAhXEHpQKHQIJBFkQ_AUICigB&biw=1366&bih=662#imgrc=qrL_4AQORqoW9M:");
  
  Mailer mailer = (Mailer) context.getBean("mailer");
  mailer.sendMail(mail);

 }
}
