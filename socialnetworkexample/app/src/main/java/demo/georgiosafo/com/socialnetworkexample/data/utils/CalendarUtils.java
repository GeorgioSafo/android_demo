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
package demo.georgiosafo.com.socialnetworkexample.data.utils;

import java.util.Calendar;

/**
 * Created by gevorksafaryan on 29.05.17.
 */

public class CalendarUtils {

    /**
     * Return type of date is it today yesterday of before
     *
     * @param calendar {@link Calendar}
     * @return {@link CalendarType}
     */
    public static CalendarType compareWithTodayDate(Calendar calendar) {
        Calendar c1 = Calendar.getInstance(); //today
        if (checkDayAndYearEquals(calendar, c1)) {
            return CalendarType.TODAY;
        } else {
            c1.add(Calendar.DAY_OF_YEAR, -1); // yesterday
            if (checkDayAndYearEquals(calendar, c1)) {
                return CalendarType.YESTERDAY;
            } else {
                return CalendarType.BEFORE;
            }
        }
    }

    public static boolean checkDayAndYearEquals(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    public enum CalendarType {
        TODAY, YESTERDAY, BEFORE
    }
}
