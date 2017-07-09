/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.utility;

import com.kunleawotunbo.gameplay.model.User;
import freemarker.template.Configuration;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TunborUtility {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    Configuration freemarkerConfiguration;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    public void sendMail(Object object) {
        User user = (User) object;
        MimeMessagePreparator preparator = getMessagePreparator(user);
        try {
            mailSender.send(preparator);
            System.out.println("Message has been sent....................");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final User user) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject("New User Created");
                helper.setFrom("newuser@mywebsite.com");
                helper.setTo(user.getEmail());

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("user", user);

                String text = getFreeMarketTemplateContent(model);
                System.out.println("Template content : " + text);

                //user the true flag to indicate you need a multipart message
                helper.setText(text, true);

                // Additionally, let's add a resource as an attachment as well
                helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
            }

        };
        return preparator;
    }

    public String getFreeMarketTemplateContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        // String template = "fm_mailTemplate.txt";
        String templateName = "registration_verification.txt";
        try {

            // content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
            //  freemarkerConfiguration.getTemplate(template), model));
            //    content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
            //            freemarkerConfiguration.getTemplate("fm_mailTemplate.txt"), model));
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(templateName), model));
            System.out.println("templateName ::" + templateName);
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }

    public void createVerificationToken(Object object, String appUrl) {
        String token = UUID.randomUUID().toString();
        final String confirmationUrl = appUrl + "/registrationConfirm.html?token=" + token;
        // save user and token
        // service.createVerificationTokenForUser(user, token);
        User user = (User) object;
        user.setUserName(confirmationUrl);
        sendMail(user);
    }

    public int gameWeek() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Week number:"
                + calendar.get(Calendar.WEEK_OF_YEAR));

        return weekNo;
    }

    public int gameWeekNoByDate(Date gameDate) {
        Calendar calendar = new GregorianCalendar();
        //Date trialTime = new Date();
        calendar.setTime(gameDate);
        int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Week number:"
                + calendar.get(Calendar.WEEK_OF_YEAR));

        return weekNo;
    }

    public String imageToBase64tring(String imgPath) {
        String encodedString = "";

        if (imgPath != null && imgPath != "") {
            File file = new File(imgPath);
            FileInputStream fis;
            ByteArrayOutputStream bos;
            try {
                fis = new FileInputStream(file);
                bos = new ByteArrayOutputStream();
                int b;
                byte[] buffer = new byte[1024];
                while ((b = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, b);
                }
                byte[] fileBytes = bos.toByteArray();
                fis.close();
                bos.close();

                byte[] encoded = Base64.encodeBase64(fileBytes);
                encodedString = new String(encoded);
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (IOException ex) {
                //Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }

        }

        return encodedString;
    }
}
