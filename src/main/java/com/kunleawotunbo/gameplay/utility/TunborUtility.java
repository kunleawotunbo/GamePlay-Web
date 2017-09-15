/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.utility;

import com.kunleawotunbo.gameplay.bean.FileBucket;
import com.kunleawotunbo.gameplay.bean.GameBean;
import com.kunleawotunbo.gameplay.bean.SMSConfigBean;
import com.kunleawotunbo.gameplay.interfaces.Definitions;
import com.kunleawotunbo.gameplay.model.GameWinner;
import com.kunleawotunbo.gameplay.model.MatchPredictionAnswer;
import com.kunleawotunbo.gameplay.model.MatchPredictionWinner;
import com.kunleawotunbo.gameplay.model.User;
import com.kunleawotunbo.gameplay.model.VerificationToken;
import com.kunleawotunbo.gameplay.model.WeeklyGamesAnswers;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.http.ResponseEntity;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

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

    @Autowired
    private SMSConfigBean smsConfigBean;

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

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public Date getDate(String timeZone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));

        Date currentDate = calendar.getTime();

        logger.info("date :: " + currentDate);

        String startDateString = "2017/08/09";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date startDate;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(currentDate);
            System.out.println(newDateString);
            logger.info("newDateString :: " + newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return currentDate;
    }

    public String getFormattedDate(String timeZone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));

        Date currentDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY HH:mm:ss");

        return dateFormat.format(currentDate);
    }

    public void logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }

    }

    public void sendSMS2() {

        System.out.println("Uri :: " + smsConfigBean.getUri());
        System.out.println("getUsername :: " + smsConfigBean.getUsername());

    }

    @Async
    public void sendSMSSingle(String recepientPhone, String message) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("About to send sms up");

        /*
        try {
            Thread.sleep(30000);
            System.out.println("I just want to delay this ni");
            // return new AsyncResult<String>("hello world !!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", smsConfigBean.getUsername());
        params.add("password", smsConfigBean.getPassword());
        params.add("sender", smsConfigBean.getSender());
        params.add("to", recepientPhone);
        params.add("message", message);
        params.add("reqid", smsConfigBean.getReqid());
        params.add("format", smsConfigBean.getFormat());
        // params.add("route_id", smsConfigBean.getRoute_id());
        params.add("callback", smsConfigBean.getCallback());
        params.add("unique", smsConfigBean.getUnique());
        params.add("sendondate", smsConfigBean.getUnique());
        params.add("msgtype", smsConfigBean.getUnique());

        System.out.println("params :: " + params);
        // Add the Jackson message converter
        // restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<String> response = restTemplate.postForEntity(smsConfigBean.getUri(), params, String.class);

        System.out.println("response :: " + response);
        System.out.println("response.getBody() :: " + response.getBody());
        // System.out.println("result :: " + result);

        System.out.println("SMS sent down down");

    }

    public Object getTimeZone(HttpServletRequest request) {

        TimeZone timeZone = RequestContextUtils.getTimeZone(request);
        return (timeZone != null ? timeZone : TimeZone.getDefault());
    }

    public List<GameWinner> weeklyGamesAnswersListToGameWinnerList(List<WeeklyGamesAnswers> weeklyGamesAnswers) {
        List<GameWinner> gameWinners = null;

        if (weeklyGamesAnswers != null && !weeklyGamesAnswers.isEmpty()) {
            gameWinners = new ArrayList<GameWinner>();
            GameWinner gameWinner = null;

            for (WeeklyGamesAnswers item : weeklyGamesAnswers) {
                gameWinner = new GameWinner();

                gameWinner.setGameId(item.getGameId());
                gameWinner.setDateAnswered(item.getDateAnswered());
                gameWinner.setProccessedDate(getDate(Definitions.TIMEZONE));
                gameWinner.setUserAnswer(item.getUserAnswer());
                gameWinner.setUserPhoneNo(item.getUserPhoneNo());

                gameWinners.add(gameWinner);
            }
        }

        return gameWinners;
    }
    
    public List<MatchPredictionWinner> matchPredictionsListToGameWinnerList(List<MatchPredictionAnswer> matchPredictionAnswerList) {
        List<MatchPredictionWinner> gameWinners = null;

        if (matchPredictionAnswerList != null && !matchPredictionAnswerList.isEmpty()) {
            gameWinners = new ArrayList<MatchPredictionWinner>();
            MatchPredictionWinner gameWinner = null;

            for (MatchPredictionAnswer item : matchPredictionAnswerList) {
                System.out.println("I am here");
                
                gameWinner = new MatchPredictionWinner();

                gameWinner.setGameId(item.getGameId());
                gameWinner.setDateAnswered(item.getDateAnswered());
                gameWinner.setProccessedDate(getDate(Definitions.TIMEZONE));
                gameWinner.setUserAnswer(item.getUserAnswer());
                gameWinner.setUserPhoneNo(item.getUserPhoneNo());

                gameWinners.add(gameWinner);
            }
           
             logger.info("gameWinners.size() :: " +gameWinners.size());
        }else {
            logger.info("matchPredictionAnswerList is null or empty");
        }

        return gameWinners;
    }

}
