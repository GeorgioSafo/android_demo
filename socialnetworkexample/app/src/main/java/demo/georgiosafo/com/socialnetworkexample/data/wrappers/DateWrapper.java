/**
 * Copyright (c) 2017 Gevork Safaryan
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package demo.georgiosafo.com.socialnetworkexample.data.wrappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import demo.georgiosafo.com.socialnetworkexample.R;
import demo.georgiosafo.com.socialnetworkexample.SocialNetworkExampleApp;
import demo.georgiosafo.com.socialnetworkexample.data.utils.CalendarUtils;
import demo.georgiosafo.com.socialnetworkexample.data.utils.TimeZoneUtil;

/**
 * Created by gevorksafaryan on 29.05.17.
 */

public class DateWrapper {
    /**
     * Parse date from string to long
     *
     * @param stringDate string if date from server
     * @return if success long date else if {@link ParseException} null
     */
    public static Long stringToLong(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SocialNetworkExampleApp.getSocialNetworkExampleApp().getString(R.string.date_format_mask), Locale.getDefault());
        try {
            return simpleDateFormat.parse(stringDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse date from long to String
     *
     * @param longDate date as long
     * @return if success String date else if {@link ParseException} null
     */
    public static String longToString(Long longDate) {
        Date date = new Date(longDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(TimeZoneUtil.applyTimeZoneToDate(date));
        CalendarUtils.CalendarType type = CalendarUtils.compareWithTodayDate(calendar);
        String text;
        switch (type) {
            case TODAY:
                text = "СЕГОДНЯ";
                break;
            case YESTERDAY:
                text = "ВЧЕРА";
                break;
            default:
                text = calendar.get(Calendar.DAY_OF_MONTH) + " " + SocialNetworkExampleApp.getSocialNetworkExampleApp().getResources().getStringArray(R.array.months)[calendar.get(Calendar.MONTH)].toUpperCase() + " " + calendar.get(Calendar.YEAR);
                break;
        }
        return text;
    }
}
