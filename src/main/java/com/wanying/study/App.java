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
    public static void main( String[] args )
    {
        System.out.println(new Date());
        String stringDate = "Fri Oct 30 17:27:20 CST 2015";
        SimpleDateFormat sim = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'CST' yyyy", Locale.US);
        try {
            Date date = sim.parse(stringDate);
            sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sim.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List list = new ArrayList();
        list.add("Blobbo");
        list.add("Cracked");
        list.add("Dumbo");
        // list.add(new Date()); // Don't mix and match!

        // Convert a collection to Object[], which can store objects
        // of any type.
        Object[] ol = list.toArray();
        System.out.println("Array of Object has length " + ol.length);

        // This would throw an ArrayStoreException if the line
        // "list.add(new Date())" above were uncommented.
        String[] sl = (String[]) list.toArray(new String[0]);
        System.out.println("Array of String has length " + sl.length);
        System.out.println("Array of String has length " + sl);

    }
}
