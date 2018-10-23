package com.jeesite.modules.love.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z' };

    public static String getMD5(String comeString) {

        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };// 用来将字节转换成16进制表示的字符
        byte sources[] = comeString.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sources);
            byte tmp[] = md.digest();// MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2];// 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16
            // 进制需要 32 个字符
            int k = 0;// 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {// 从第一个字节开始，对 MD5 的每一个字节// 转换成 16
                // 进制字符的转换
                byte byte0 = tmp[i];// 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4 位的数字转换,// >>>
                // 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf];// 取字节中低 4 位的数字转换
            }

            s = new String(str);// 换后的结果转换为字符串

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * @param str
     * @return true:为空 false:非空
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static String sourceTypeToType(String in) {
        if (in.equals("0")) {// 语音
            return "tele";
        }
        if (in.equals("1")) {// 短信
            return "sms";
        }
        if (in.equals("2")) {// 流量
            return "gprs";
        }
        if (in.equals("tele")) {// 语音
            return "0";
        }
        if (in.equals("sms")) {// 短信
            return "1";
        }
        if (in.equals("gprs")) {// 流量
            return "2";
        }
        return in;
    }

    public static String filtChinese(String source) {
        String abc = source.replaceAll("[\\u4e00-\\u9fa5]", "");

        return abc.replaceAll("\\p{Punct}", "");
    }

    public static boolean hasChineseCharacter(String source) {
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(source);
        while (m.find()) {
            return true;
        }
        return false;

    }

    public static String escapeChar4Solr(String source) {
        String abc = "\\+|\\-|\\&|\\||\\!|\\(|\\)|\\{|\\}|\\[|\\]|\\^|\"|\\\\|\\~|\\*|\\?|\\:";
        // Pattern patter =
        // Pattern.compile("\\+|\\-|\\&|\\||\\!|\\(|\\)|\\{|\\}|\\[|\\]^|\"|\\|\\~|\\*|\\?|\\:");
        return source.replaceAll(abc, "");
    }
    
    public static void main(String[] args) {

        // System.out.println(hasChineseCharacter(escapeChar4Solr("gm")));

        System.out.println(geneInviteCodeById(212, 200));
        System.out.println(geneInviteCodeById(212, -200));
        System.out.println(geneInviteCodeById(-212, -200));
        /*
         * String tt = "公司(集团)"; System.out.println(escapeChar4Solr(tt));
         */
        // System.out.println(geneInviteCodeById(10000*10000,100));
        // System.out.println(geneInviteCodeById(10000*10000-1,100));
        // System.out.println(geneInviteCodeById(999,8000000));

    }

    public static String transUniCode2Chinese(String source) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = source.indexOf("\\u", pos)) != -1) {
            sb.append(source.substring(pos, i));
            if (i + 5 < source.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(
                        source.substring(i + 2, i + 6), 16));
            }
        }
        sb.append(source.substring(pos));
        return sb.toString();
    }

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String geneInviteCodeById(int x, int y) {
        long mm = x * 999983;
        if (mm > 1000000000) {
            mm = mm / 10;
        }

        long m = mm + y;
        // System.out.println(m);
        String ab = numericToString(m, 32);
        if (ab.length() < 6) {
            for (int i = 6 - ab.length(); i > 0; i--) {
                ab = 0 + ab;
            }
        }
        return ab;

    }

    public static String generateInviteCodeByPhone(String phone) {

        if (phone != null) {
            try {
                if (phone.length() > 10) {
                    phone = phone.substring(phone.length() - 10);
                }
                long num = Long.parseLong(phone);
                return numericToString(num, 36);
            } catch (Exception e) {
            }
        }
        return phone;
    }

    public static String numericToString(long i, int system) {
        long num = 0;
        if (i < 0) {
            num = ((long) 2 * 0x7fffffff) + i + 2;
        } else {
            num = i;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((num / system) > 0) {
            buf[--charPos] = digits[(int) (num % system)];
            num /= system;
        }
        buf[--charPos] = digits[(int) (num % system)];
        return new String(buf, charPos, (32 - charPos));
    }

    /**
     * 将其它进制的数字（字符串形式）转换为十进制的数字。
     * 
     * @param s
     *            其它进制的数字（字符串形式）
     * @param system
     *            指定的进制，常见的2/8/16。
     * @return 转换后的数字。
     */
    public static long stringToNumeric(String s, int system) {
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        long num = 0;
        for (int i = 0; i < buf.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == buf[i]) {
                    num += j * Math.pow(system, buf.length - i - 1);
                    break;
                }
            }
        }
        return (int) num;
    }
}
