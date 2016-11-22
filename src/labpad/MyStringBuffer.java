/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labpad;

import java.util.Arrays;

/**
 *
 * @author epicur
 */
public class MyStringBuffer {
    char[] str;
    int limit;
    final int CHUNK = 10;
    
    MyStringBuffer(){
        str = new char[CHUNK];
        //limit = =;
    }
    
    MyStringBuffer insert(int offset, char ch){
        if (offset > limit || offset < 0)
            throw new IndexOutOfBoundsException();
        if (limit == str.length){
            char[] tmp = str;
            //str = new char[str.length * 2];
            //System.arraycopy(tmp, 0 , str, 0, tmp.length);
            str = Arrays.copyOf(tmp, str.length * 2);
        }
        for (int i  = limit; i > offset; i--)
            str[i] = str[i - 1];
        str[offset] = ch;
        limit++;
        return this;
    }
    
    MyStringBuffer deleteCharAt (int offset){
        for (int i = offset; i < limit -1; i++)
            str[i] = str[i+1];
        limit--;
        return this;
    }
    
    void setChar(int offset, char ch){
        if (offset > limit || offset < 0)
            throw new IndexOutOfBoundsException();
        if (limit == str.length){
            char[] tmp = str;
            //str = new char[str.length * 2];
            //System.arraycopy(tmp, 0 , str, 0, tmp.length);
            str = Arrays.copyOf(tmp, str.length * 2);
        }
        str[offset] = ch;
        limit++;
    }
    
    public String toString(){
        return new String(str, 0, limit);
    }
}
