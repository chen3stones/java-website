package com.oreo.website.service;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class CheckMailService {
    @Value("${mail.address}")
    private String sendMail;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.server}")
    private String server;
    @Value("${blogName}")
    private String blogName;
    public void sendCode(String receiverMail,String code){
        Properties properties = new Properties();
        //协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //设置自己的服务器
        properties.setProperty("mail.smtp.host",server);
        //认证
        properties.setProperty("mail.smtp.auth", "true");
        //配置服务器
        final String smtpPort = "465";
        properties.setProperty("mail.smtp.port", smtpPort);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", smtpPort);
        //导入设置
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        System.out.println(sendMail);
        try{
            //创建邮件
            MimeMessage message = createMessage(session,sendMail,receiverMail,code);
            //根据session获取传输对象
            Transport transport = session.getTransport();
            transport.connect(sendMail,password);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private MimeMessage createMessage(Session session,String sendMail,String receiver, String code) throws Exception{
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(sendMail,blogName,"UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiver,"客户","UTF-8"));
        message.setSubject(blogName+"注册邮件","UTF-8");
        message.setContent("尊敬的"+receiver +":\n + 欢迎注册" + blogName +"您的验证码为："+ code+" 。如不是您注册，请忽略","text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
