package com.utils;

public class TimeCompare {

    public static boolean compareTime(String nowtime, String time) {
        String now_year_str = nowtime.substring(0, 4);
        String now_month_str = nowtime.substring(4, 6);
        String now_day_str = nowtime.substring(6, 8);
        String now_hour_str = nowtime.substring(8, 10);
        String now_minute_str = nowtime.substring(10, 12);
        String now_second_str = nowtime.substring(12, 14);
        System.out.println(nowtime);
        long now_year = Long.parseLong(now_year_str);
        int now_month = Integer.parseInt(now_month_str);
        int now_day = Integer.parseInt(now_day_str);
        int now_hour = Integer.parseInt(now_hour_str);
        int now_minute = Integer.parseInt(now_minute_str);
        int now_second = Integer.parseInt(now_second_str);

        String year_str = time.substring(0, 4);
        String month_str = time.substring(4, 6);
        String day_str = time.substring(6, 8);
        String hour_str = time.substring(8, 10);
        String minute_str = time.substring(10, 12);
        String second_str = time.substring(12, 14);
        long year = Long.parseLong(year_str);
        int month = Integer.parseInt(month_str);
        int day = Integer.parseInt(day_str);
        int hour = Integer.parseInt(hour_str);
        int minute = Integer.parseInt(minute_str);
        int second = Integer.parseInt(second_str);

//		System.out.println("nowtime:"+Long.toString(now_year)+Integer.toString(now_month)+Integer.toString(now_day)+Integer.toString(now_hour)+Integer.toString(now_minute)+Integer.toString(now_second));
//		System.out.println("time:"+Long.toString(year)+Integer.toString(month)+Integer.toString(day)+Integer.toString(hour)+Integer.toString(minute)+Integer.toString(second));
//		开始比较
        if (now_year == year && now_month == month && now_day == day) {
            //是同一天，比较时分秒
            if (now_hour > hour) {
                return true;
            } else if (now_hour == hour && now_minute > minute) {
//				同一个小时，比较分
                return true;
            } else if (now_hour == hour && now_minute == minute && now_second > second) {
//				同一个分钟，比较秒
                return true;
            } else {
                return false;
            }
        } else {
            //不是同一天，比较年月日
            if (now_year > year) {
                return true;
            } else if (now_year == year && now_month > month) {
//				同一年，比较月
                return true;
            } else if (now_year == year && now_month == month && now_day > day) {
//				同一月，比较日
                return true;
            } else {
                return false;
            }
        }
    }
}
