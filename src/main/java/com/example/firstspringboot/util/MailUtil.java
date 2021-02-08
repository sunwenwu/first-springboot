package com.example.firstspringboot.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by admin on 2018/6/7.
 */
public class MailUtil {

    /**
     * host=smtp.exmail.qq.com
     * port=25
     * userName=no-reply@ipaynow.cn
     * password=1008Ipaynow
     */
    private static Logger logger = LoggerFactory.getLogger(MailUtil.class);
    private String host;
    private String port;
    private String userName;
    private String password;

    private static class MailUtilHolder {
        private static MailUtil instance = new MailUtil("smtp.exmail.qq.com", "465", "agg-no-replay@ipaynow.cn", "oaSY4XIDLs01i07o");
    }

    private MailUtil(String host, String port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public static MailUtil getInstance() {
        return MailUtilHolder.instance;
    }

    /**正常:统一发邮件方法*/
    public boolean sendMail(String fromNameDisplay, String Subject, String content, String sendTo, String copyTo) {
        Properties props = System.getProperties();
        // 创建信件服务器
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        // 使用 STARTTLS安全连接
        props.put("mail.smtp.starttls.enable", "true");
        // 得到默认的对话对象
        Authenticator a = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        BodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        //创建Session实例
        Session session = Session.getDefaultInstance(props, a);
        //创建MimeMessage实例对象
        MimeMessage msg = new MimeMessage(session);

        try {
            Address address = new InternetAddress(userName, fromNameDisplay);
            //设置发信人
            msg.setFrom(address);
            //设置发送日期
            msg.setSentDate(new Date());
            //设置邮件主题
            msg.setSubject(Subject);
            //设置邮件正文
            msg.setText(content);

            File efile = new File("D:\\workspace\\alipay-prod\\alipayRootCert.crt");
            MimeBodyPart mdpFile = new MimeBodyPart() ;

            String fileName = efile.toString() ;
            FileDataSource fds = new FileDataSource(fileName) ;
            mdpFile.setDataHandler(new DataHandler(fds)) ;
            //这个方法可以解决乱码问题
            String fileName1 = new String(fds.getName().getBytes(),"ISO-8859-1") ;
            mdpFile.setFileName(fileName1) ;
            multipart.addBodyPart(mdpFile) ;


            //给BodyPart对象设置内容和格式/编码方式
            messageBodyPart.setContent(content, "text/html;charset=utf-8");
            multipart.addBodyPart(messageBodyPart);


            msg.setContent(multipart);
            //发送邮件
            if (!StringUtils.isBlank(sendTo)) {
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
            }
            if (!StringUtils.isBlank(copyTo)) {
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyTo));
            }
            //保存对于e-mail的修改
            msg.saveChanges();
            Transport.send(msg);
            logger.info("发送邮件成功");
            return true;
        }catch (SendFailedException e){
            //邮件发送失败过滤
            logger.error("发送邮件失败,地址有误,去掉无效地址,继续发送!");
            Address[] invalid = e.getInvalidAddresses();
            if(invalid!=null) {
                for (int i = 0; i < invalid.length; i++) {
                    logger.info("无效地址:" + invalid[i]);
                    sendTo = delInvalidAddress(sendTo,invalid[i].toString());
                    copyTo = delInvalidAddress(copyTo,invalid[i].toString());
                }
                if(StringUtils.isNotBlank(sendTo)||StringUtils.isNotBlank(copyTo)){

                    logger.info("过滤掉无效地址重新发送,sendTo:{}",sendTo);
                    logger.info("过滤掉无效地址重新发送,copyTo:{}",copyTo);
                    logger.info("邮件重新发送:");
                    sendMail("系统邮件（正式）", "微众二类户数据统计",  content, sendTo, copyTo);
                }else {
                    logger.info("邮件重新发送过滤错误地址以后 sendTo:{},copyTo{},没有收信地址,邮件不发送");
                }

            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("发送邮件失败",e);
            return false;
        }
    }


    public static void main(String[] args) {
        MailUtil mailUtil = getInstance();

        mailUtil.sendMail("系统邮件test", "test 附件",  "test 附件", "sunwenwu@ipaynow.cn", "sunwenwu@ipaynow.cn");

    }

    private String  delInvalidAddress(String sendTo,String deleMail){
        String newToStr = "";
        List<String> newTo = new ArrayList<String>();
        String[] sendToArray = sendTo.split(",");
        if(sendToArray.length>0){
            for(int i = 0;i<sendToArray.length;i++){
                if(!sendToArray[i].equals(deleMail)){
                    newTo.add(sendToArray[i]);
                }
            }
        }

        //重新排列
        if(CollectionUtils.isNotEmpty(newTo)){
            newToStr = StringUtils.join(newTo.toArray(), ",");
        }
        return newToStr;

    }


    /**正常:邮件带附件*/
    public boolean sendMailAttach(String fromNameDisplay, String Subject, String content, String sendTo, String copyTo,String path) {
        Properties props = System.getProperties();
        // 创建信件服务器
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        // 使用 STARTTLS安全连接
        props.put("mail.smtp.starttls.enable", "true");

        // 得到默认的对话对象
        Authenticator a = new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,password);
            }
        };

        //创建Session实例
        Session session = Session.getDefaultInstance(props, a);
        //创建MimeMessage实例对象
        MimeMessage msg=new MimeMessage(session);

        try {
            Address address = new InternetAddress(userName,fromNameDisplay);
            //设置发信人
            msg.setFrom(address);
            //设置发送日期
            msg.setSentDate(new Date());
            //设置邮件主题
            msg.setSubject(Subject);
            //设置邮件正文
            msg.setText(content);

            FileDataSource fileds = new FileDataSource(path);



            BodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart.setDataHandler(new DataHandler(fileds));
            // 解决附件名称乱码
            messageBodyPart.setFileName(MimeUtility.encodeText(fileds.getName(),"utf-8",null));
            multipart.addBodyPart(messageBodyPart);

            //给BodyPart对象设置内容和格式/编码方式
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent(content, "text/html;charset=gbk");
            multipart.addBodyPart(messageBodyPart1,1);


            msg.setContent(multipart);

            //保存对于e-mail的修改
            msg.saveChanges();

            //发送邮件
            if (!StringUtils.isBlank(sendTo)) {
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
            }
            if (!StringUtils.isBlank(copyTo)) {
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyTo));
            }
            Transport.send(msg);
            logger.info("开户未充值:发送邮件成功sendTo:{} copyTo:{}",sendTo,copyTo);
            return true;
        }catch (SendFailedException e){
            //邮件发送失败过滤
            logger.error("开户未充值:发送邮件失败,地址有误,去掉无效地址,继续发送!");
            Address[] invalid = e.getInvalidAddresses();
            if(invalid!=null) {
                for (int i = 0; i < invalid.length; i++) {
                    logger.info("开户未充值:无效地址:" + invalid[i]);
                    sendTo = delInvalidAddress(sendTo,invalid[i].toString());
                    copyTo = delInvalidAddress(copyTo,invalid[i].toString());
                }
                if(StringUtils.isNotBlank(sendTo)||StringUtils.isNotBlank(copyTo)){

                    logger.info("开户未充值:过滤掉无效地址重新发送,sendTo:{}",sendTo);
                    logger.info("开户未充值:过滤掉无效地址重新发送,copyTo:{}",copyTo);
                    logger.info("开户未充值:邮件重新发送:");
                    sendMailAttach(fromNameDisplay, Subject, content, sendTo, copyTo,path);

                }else {
                    logger.info("开户未充值:邮件重新发送过滤错误地址以后 sendTo:{},copyTo{},没有收信地址,邮件不发送");
                }

            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("开户未充值:发送邮件失败",e);
            return false;
        }
    }


}
