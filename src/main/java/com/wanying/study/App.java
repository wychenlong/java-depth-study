package com.wanying.study;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Hello world!
 *
 */
public class App
{

    ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
        protected  Integer initialValue(){
            return 1;
        }
    };
    public static void main( String[] args )
    {
        App app =new App();
        System.out.println(app.local.get());
    }
}
