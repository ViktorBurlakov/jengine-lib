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

import java.util.HashMap;

public class MultiReferenceField extends Field {
    private String referenceModelName;
    private String referenceModelFieldName;

    public MultiReferenceField(Class fieldClass, String referenceModelFieldName) {
        this(fieldClass.getSimpleName(), fieldClass, referenceModelFieldName);
    }

    public MultiReferenceField(String referenceModelName, Class fieldClass, String referenceModelFieldName) {
        super(fieldClass, new HashMap<String, Object>());
        this.referenceModelFieldName = referenceModelFieldName;
        this.referenceModelName = referenceModelName;
        this.type = Type.MULTI_REFERENCE;
    }

    public String getReferenceModelFieldName() {
        return referenceModelFieldName;
    }

    public void setReferenceModelFieldName(String referenceModelFieldName) {
        this.referenceModelFieldName = referenceModelFieldName;
    }

    public void setReferenceModelName(String referenceModelName) {
        this.referenceModelName = referenceModelName;
    }

    public String getReferenceModelName() {
        return referenceModelName;
    }
}