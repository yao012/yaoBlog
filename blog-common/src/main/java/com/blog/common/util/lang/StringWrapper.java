package com.blog.common.util.lang;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author zijiang.wu
 * 类似于String,
 *
 */
public class StringWrapper implements Comparable<StringWrapper>, CharSequence {
    private final static Logger logger = LogManager.getLogger(StringWrapper.class);

    final String value;
    int hash;

    public StringWrapper(String value, int hashCode){
        this.value = value;
        this.hash = hashCode;
    }

    public StringWrapper(String value){
        this.value = value;
    }

    @Override
    public int length() {
        return value.length();
    }

    @Override
    public char charAt(int index) {
        return value.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return value.subSequence(start, end);
    }

    @Override
    public String toString(){
        return value;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        Class anCls = anObject.getClass();
        if(anCls == StringWrapper.class) {
            StringWrapper another = (StringWrapper)anObject;
            return value.equals(another.value);
        }else if (anObject instanceof String) {
            String another = (String)anObject;
            return value.equals(another);
        }else if(anObject instanceof AsciiString){
            AsciiString another = (AsciiString)anObject;
            int n = another.value.length;
            byte[] avalue = another.value;
            if(n == value.length()){
                for(int i=0; i<n; i++){
                    if(avalue[i] != value.charAt(i)){
                        return false;
                    }
                }
                return true;
            }
        }else if(anObject instanceof CharSequence){
            CharSequence another = (CharSequence)anObject;
            int n = value.length();
            if(n == another.length()){
                for(int i=0; i<n; i++){
                    if(value.charAt(i) != another.charAt(i)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        int h = hash;
        if (h == 0 && value.length()>0) {
            h = value.hashCode();
        }
        return h;
    }


    @Override
    public int compareTo(StringWrapper o) {
        return value.compareTo(o.value);
    }
}
