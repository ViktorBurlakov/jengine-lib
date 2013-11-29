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

package com.jengine.db.field;

import com.jengine.db.ModelManager;

import java.util.Map;

public class IntegerField extends Field {

    public IntegerField() {
        super(Integer.class);
    }

    public IntegerField(Object... options) {
        super(Integer.class, options);
    }

    public IntegerField(Map<String, Object> options) {
        super(Integer.class, options);
    }

    public IntegerField(String name, Map<String, Object> options) {
        super(name, Integer.class, options);
    }

    public IntegerField(ModelManager manager, String name, Map<String, Object> options) {
        super(manager, name, Integer.class, options);
    }
}