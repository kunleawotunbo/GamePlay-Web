/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.utility;

import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.GameBean;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.VerificationToken;
import freemarker.template.Configuration;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private MessageSource messages;

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

    public String imageToBase64String(String imgPath) {
        //logger.info("Converting path to imagestring");
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

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    public String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public GameBean fileUpload(GameBean fileBucket) {

        // MultipartFile[] files = fileBucket.getFiles();
        MultipartFile file = fileBucket.getFile();
        String originalImgPath = "";
        String resizedImgPath = "";
        //String serverFileName = "";
        String gameImage = "";
        String itemViewName = "";
        String imgLocation = "";
        int width = 580;
        int height = 450;
        boolean saved = false;
        String serverFileName = "";

        FileBucket fb = new FileBucket();

        if (file != null && !file.isEmpty()) {
            // for (int i = 0; i < files.length; i++) {
            try {

                byte[] bytes = null;
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                FilenameUtils fileUTIL = new FilenameUtils();

                //String path = req.getServletContext().getRealPath("/image");
                //String ext = fileUTIL.getExtension(file.getOriginalFilename());
                //String baseName = fileUTIL.getBaseName(file.getOriginalFilename());
                imgLocation = dir + File.separator;
                // get files name in the array

                gameImage = file.getOriginalFilename();
                bytes = file.getBytes();
                serverFileName = imgLocation + gameImage;
                System.out.println("gameImage:: " + gameImage);

                System.out.println("serverFileName :: " + serverFileName);

                // resize image
                //utility.resize(originalImgPath, resizedImgPath, width, height);
                //create the file on server
                File serverFile = new File(serverFileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            // }

            fileBucket.setGameImage(gameImage);
            fileBucket.setGameImgLocation(imgLocation);

        } else {
            System.out.println("File is empty / No image uploaded");
        }

        return fileBucket;

    }

    /**
     * Get base url
     *
     * @param request
     * @return
     * @throws MalformedURLException
     */
    public String getURLBase(HttpServletRequest request) throws MalformedURLException {

        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port + request.getContextPath();

    }

    public SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationToken newToken, final User user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user.getEmail());
    }

    public SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user.getEmail());
    }

    private SimpleMailMessage constructEmail(String subject, String body, String recipientEmail) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(recipientEmail);
        //email.setFrom(env.getProperty("support.email"));
        email.setFrom("info@tunbor.com");
        return email;
    }

    @Async
    public SimpleMailMessage mailSender2(String subject, String body, String recipientEmail) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(recipientEmail);
        //email.setFrom(env.getProperty("support.email"));
        email.setFrom("info@tunbor.com");
        return email;
    }

    @Async
    public void mailSender(SimpleMailMessage simpleMailMessage) {
        //User user = (User) object;
        //MimeMessagePreparator preparator = getMessagePreparator(user);
        try {
            mailSender.send(simpleMailMessage);
            System.out.println("Message has been sent....................");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @DateTimeFormat(pattern = "dd/MM/yyyy")
       public Date getDate(String timeZone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        
        Date currentDate = calendar.getTime();
        
        return currentDate;
    }
    
     public String getFormattedDate(String timeZone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        
        Date currentDate = calendar.getTime();
       DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY HH:mm:ss"); 
       
       
       
        return dateFormat.format(currentDate);
    }
     
     public void logoutUser(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }

    }


}
