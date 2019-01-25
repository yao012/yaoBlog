package com.blog.common.util;

import com.blog.common.util.lang.AsciiString;
import com.blog.common.util.lang.StringWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Objects;

/**
 * @author wuzijiang
 * 主要用于比较字符串(继承CharSequence).
 * 为了降内存的消耗, 去除不必要的hashcode计算, 本系统实现了AsciiString, 使用byte代替char;
 * 又实现了StringWrapper, 如果hashcode已经通过某种方式计算出来了, 则不再计算.
 *
 */
public class ObjectsUtil {

    private final static Logger logger = LogManager.getLogger(ObjectsUtil.class);
    /**
     * MYSQL中的datetime只是精确到秒.
     * java.sql.Date与java.util.Date比较, 只要getTime()相同, 就会相同.
     * @param a
     * @param b
     * @return
     */
    public static boolean secondsEquals(Date a, Date b){
        if(a==b){
            return true;
        }
        if(a != null && b != null && (
                Math.abs(a.getTime()/1000 - b.getTime()/1000) <= 1 )
                ){
            return true;
        }
        return false;
    }

    /**
     * 针对字符串,作了特殊处理
     * @param o1
     * @param o2
     * @return
     */
    public static boolean equals(Object o1, Object o2){
        if(o1 == o2){
            return true;
        }
        if(o1 != null && o2 != null){
            Class c1 = o1.getClass();
            Class c2 = o2.getClass();
            if(c1 == c2){
                //同一类, 比如都是String
                return o1.equals(o2);
            }else if(o1 instanceof CharSequence && o2 instanceof CharSequence){
                if(c1 == AsciiString.class){
                    return ((AsciiString)o1).equals(o2);
                }else if(c2 == AsciiString.class){
                    return ((AsciiString)o2).equals(o1);
                }else if(c1 == StringWrapper.class){
                    return ((StringWrapper)o1).equals(o2);
                }else if(c2 == StringWrapper.class){
                    return ((StringWrapper)o2).equals(o1);
                }else{
                    int s1Len = ((CharSequence)o1).length();
                    int s2Len = ((CharSequence)o2).length();
                    if(s1Len != s2Len){
                        return false;
                    }
                    for(int i=0; i<s1Len; i++){
                        if(((CharSequence)o1).charAt(i) != ((CharSequence)o2).charAt(i)){
                            return false;
                        }
                    }
                    return true;
                }
            }else{
                return o1.equals(o2);
            }
        }else{
            return false;
        }
    }

    public static boolean equals(CharSequence s1, CharSequence s2){
        if(s1 == s2){
            return true;
        }
        if(s1 != null && s2 != null){
            Class c1 = s1.getClass();
            Class c2 = s2.getClass();
            if(c1 == c2){
                return s1.equals(s2);
            }

            if(c1 == AsciiString.class){
                return ((AsciiString)s1).equals(s2);
            }else if(c2 == AsciiString.class){
                return ((AsciiString)s2).equals(s1);
            }else if(c1 == StringWrapper.class){
                return ((StringWrapper)s1).equals(s2);
            }else if(c2 == StringWrapper.class){
                return ((StringWrapper)s2).equals(s1);
            }else{
                int s1Len = s1.length();
                int s2Len = s2.length();
                if(s1Len != s2Len){
                    return false;
                }
                for(int i=0; i<s1Len; i++){
                    if(s1.charAt(i) != s2.charAt(i)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


    public static int compareTo(CharSequence s1, CharSequence s2){
        if(s1 == s2){
            return 0;
        }
        if(s1 != null && s2 != null){
            if(s1 instanceof AsciiString){
                return ((AsciiString)s1).compareTo(s2);
            }else  if(s2 instanceof AsciiString){
                return ((AsciiString)s2).compareTo(s1);
            }else if(s1 instanceof String && s2 instanceof String){
                return ((String)s1).compareTo((String)s2);
            }else{
                int len1 = s1.length();
                int len2 = s2.length();
                int lim = Math.min(len1, len2);

                int k = 0;
                while (k < lim) {
                    char c1 = s1.charAt(k);
                    char c2 = s2.charAt(k);
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    k++;
                }
                return len1 - len2;
            }
        }
        return 0;
    }

    public static int compareTo(Object o1, Object o2){
        if(o1 == o2){
            return 0;
        }
        if(o1 != null && o2 != null){

            if(o1 instanceof CharSequence && o2 instanceof CharSequence){
                return compareTo((CharSequence)o1, (CharSequence)o2);
            }else{
                return ((Comparable)o1).compareTo((Comparable)o2);
            }
        }
        return 0;
    }

    public static void main(String[] args){
        long time = System.currentTimeMillis();
        java.sql.Date date1 = new java.sql.Date(time);
        Date date2 = new Date(time);

        //结果为true
        System.out.println("" + Objects.equals(date1, date2));
    }

}
