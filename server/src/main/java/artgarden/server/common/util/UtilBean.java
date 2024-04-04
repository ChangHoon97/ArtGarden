package artgarden.server.common.util;

public class UtilBean {

    /**
     * String 입력시 Null일 경우 빈 문자열 반환
     * @param words
     * @return
     */
    public String checkNullString(String words){
        String result = "";
        if(words != null){
            result = words;
        }
        return result;
    }
}
