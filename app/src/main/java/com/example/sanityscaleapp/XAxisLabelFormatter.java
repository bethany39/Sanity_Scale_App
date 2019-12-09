package com.example.sanityscaleapp;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class XAxisLabelFormatter extends ValueFormatter {

    private long referenceTimestamp; // minimum timestamp in your data set
    private long latestTimestamp;
    private SimpleDateFormat mDataFormat;
    private Date mDate;


    public XAxisLabelFormatter(String dateString) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        long milliseconds = 0;
        try {
            Date d = f.parse(dateString);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.referenceTimestamp = milliseconds;
        System.out.println(referenceTimestamp);
        this.mDataFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.mDate = new Date();
        this.latestTimestamp=0;
    }

    public String getFormattedValue(String string_date) {
        // convertedTimestamp = originalTimestamp - referenceTimestamp
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        long milliseconds = 0;
//        try {
//            Date d = f.parse(string_date);
//            milliseconds = d.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long convertedTimestamp = (long) milliseconds  - referenceTimestamp;
////        if(milliseconds>latestTimestamp){
////            milliseconds=latestTimestamp;
////        }
//        // Retrieve original timestamp
//        long originalTimestamp = referenceTimestamp + convertedTimestamp;
//        // Convert timestamp to hour:minute
//        return getDateString(originalTimestamp);
        return string_date;
    }

    private String getDateString(long timestamp) {
        try {
            mDate.setTime(timestamp);
            return mDataFormat.format(mDate);
        } catch(Exception ex) {
            return "xx";
        }
    }

}
