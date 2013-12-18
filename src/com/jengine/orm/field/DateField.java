/*
 * This file is part of JEngine.
 *
 * Copyright (C) 2013 Victor Burlakov
 *
 * JEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JEngine.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jengine.orm.field;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.jengine.utils.CollectionUtil.map;


public class DateField extends Field {
    private DateFormat dateFormatter = null;
    private String dateFormat = "yyyy-MM-dd"; // ISO date format

    public DateField() {
        this(new HashMap<String, Object>());
    }

    public DateField(Object... options) {
        this(map(options));
    }

    public DateField(Map<String, Object> options) {
        super(Date.class, options);
        if (options.containsKey("dateFormat")) {
            this.dateFormat = (String) options.get("dateFormat");
        }
        this.dateFormatter = new SimpleDateFormat(dateFormat);
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Date cast(Object value){
        if (Long.class.isInstance(value)) {
            return new Date((Long)value);
        } else if (String.class.isInstance(value)){
            try {

                return dateFormatter.parse((String) value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return (Date) value;
    }

    public String format(Object value) {
        return new SimpleDateFormat(dateFormat).format(value);
    }
}