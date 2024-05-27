package artgarden.server.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilBean {

    /**
     * 파라메터가 NULL, 또는 빈값일 경우 공백문자("") 반환 이상없을 경우 원본Obejct의 문자형 since 2009. 4. 7.
     *
     * @param str
     *          원본Object
     * @return String 공백문자열 또는, 원본문자열
     */
    public static String checkNullString(Object str) {
        String strTmp;
        try {
            if (str != null && str.toString().length() > 0 && !str.equals("")) strTmp = str.toString().trim();
            else strTmp = "";
        } catch (Exception e) {
            strTmp = "";
        }
        return strTmp.trim();
    }

    /**
     * 배열의 null, 빈값을 공백으로 반환 since 2009. 4. 7.
     *
     * @param str
     * @return
     */
    public static String[] checkNullString(String str[]) {
        String[] newStr = null;
        try {
            if (str != null & str.length > 0) {
                newStr = new String[str.length];
                for (int i = 0; i < str.length; i++) {
                    newStr[i] = checkNullString(str[i]);
                }
            }
        } catch (Exception e) {
            newStr = null;
        }
        return newStr;
    }

    public static String checkNullStringR(String argString, String argReturn) {
        String strTmp = "";
        try {
            if (argString == null || argString.length() == 0) {
                strTmp = argReturn;
            } else {
                strTmp = argString.trim();
            }
        } catch (Exception e) {
            strTmp = "";
        }
        return strTmp.trim();
    }

    /**
     * LocalDate 타입을 String 형태로 변환
     * @param date
     * @return
     */
    public static String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }

    /**
     * String 타입을 LocalDate 형태로 반환
     * @param date
     * @return
     */
    public static LocalDate formatString(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date,formatter);
    }


}
