package moorcommon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class Utils {

    @Autowired
    private static MessageSource messageSource;

    public static ValidationResult getBindingResult(BindingResult bindingResult) {
        ValidationResult validationResult = new ValidationResult();
        List<String> errorMessages = new ArrayList<>();
        if (bindingResult.getAllErrors() != null && bindingResult.getAllErrors().size() > 0) {
            for (Object object : bindingResult.getAllErrors()) {

                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    errorMessages.add(fieldError.getDefaultMessage());
                } else if (object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;
                    errorMessages.add(objectError.getDefaultMessage());
                }
            }
        }
        validationResult.setSuccessful(false);
        validationResult.setMessages(errorMessages);

        return validationResult;
    }

    public static Locale getLocale() {
        try {
            return new Locale(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader(HttpHeaders.ACCEPT_LANGUAGE));
        } catch (Exception e) {
            return new Locale("en");
        }
    }

    public static String createMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateToken() {
        UUID token = UUID.randomUUID();
        return token.toString();
    }

    public static String getBase64OfPhoto(String photo) {
        if (photo == null || photo.isEmpty())
            return null;
        File serverFile = null;
        serverFile = new File(photo);

        if (serverFile != null) {
            try {
                return Base64.getEncoder().encodeToString(Files.readAllBytes(serverFile.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    public static String generateRandomNo(int capacity) {
        SecureRandom rnd = new SecureRandom();
        String AB = "0123456789";
        StringBuilder sb = new StringBuilder(capacity);
        for (int i = 0; i < capacity; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String generateRandomLetters(int capacity) {
        SecureRandom rnd = new SecureRandom();
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(capacity);
        for (int i = 0; i < capacity; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String getToken() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader(HttpHeaders.AUTHORIZATION);
    }

    public static String convertSectoDay(long seconds, Locale locale) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);

        String daysS = "";
        String hoursS = "";
        String minuteS = "";
        String secondS = "";

        if (day != 0)
            daysS = day + " " + messageSource.getMessage("day", null, " days ", locale);
        if (hours != 0)
            hoursS = hours + " " + messageSource.getMessage("hour", null, " hours ", locale);
        if (minute != 0)
            minuteS = minute + " " + messageSource.getMessage("minute", null, " minutes ", locale);


        return daysS + hoursS + minuteS;
    }

    public static Boolean stringIsNotNullAndEmpty(String string) {
        return string != null && !string.isEmpty();
    }

    public static Boolean listIsNotNullAndEmpty(List objectList) {
        return objectList != null && !objectList.isEmpty();
    }

    public static String getFromHeaders(String headerName) {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader(headerName);
    }

    public static Boolean listIsNullOrEmpty(List objectList) {
        return objectList == null || objectList.isEmpty();
    }

    public static Boolean stringIsNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isVowel(String c){
        String vowels = "aeiouAEIOU";
        return vowels.contains(c);
    }

    public static boolean isConsanant(String c){
        String cons = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        return cons.contains(c);
    }

    private static String getApplicationId() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("ApplicationId");
    }

    public static Long applicationId() {
        try {
            String applicationId = getApplicationId();
            return Long.valueOf(applicationId);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getSomethingFromHeader(String something) {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader(something);
    }
}
