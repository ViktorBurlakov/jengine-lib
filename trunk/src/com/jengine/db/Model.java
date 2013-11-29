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

package com.jengine.db;

import com.jengine.db.exception.DBException;
import com.jengine.db.field.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;


public class Model {
    protected ModelClass cls;
    protected Map<String, Object> values = new LinkedHashMap<String, Object>();

    public Model() throws SystemException, PortalException {
        cls = (ModelClass) ModelClass.getClassObject(getClass());
    }

    public Model(Map values) throws SystemException, PortalException {
        this();
        setValues(values);
    }

    public String getVerbose() throws SystemException, PortalException, DBException {
        return String.valueOf(this.getPrimaryKey());
    }

    public String format(String field) throws SystemException, PortalException, DBException {
        return cls.getManager().getField(field).format(this.getValue(field));
    }

    /* get & set field values */

    public boolean isNew() throws SystemException, PortalException, DBException {
        return getValue(cls.getManager().getPrimaryKey()) == null;
    }

    public Serializable getPrimaryKey() throws SystemException, PortalException, DBException {
        return (Serializable) getValue(cls.getManager().getPrimaryKey());
    }

    public void setValue(String fieldName, Object value) throws SystemException, PortalException {
        this.setValue(cls.getManager().getField(fieldName), value);
    }

    public Object getValue(String fieldName) throws SystemException, PortalException, DBException {
        return this.getValue(cls.getManager().getField(fieldName));
    }

    public void setValue(Field field, Object value) throws SystemException, PortalException {
        if (field.isForeign() || field.isFunction() || field.isProperty()) {
            return;
        }
        values.put(field.getName(), field.castType(value));
    }

    public Object getValue(Field field) throws SystemException, PortalException, DBException {
        if (field.isForeign()) {
            ForeignField foreignField = (ForeignField) field;
            Object referenceId = values.get(foreignField.getReference().getName());
            Model reference = cls.getDb().get(field.getFieldClass(), referenceId);
            return reference.getValue(foreignField.getField());
        } else if (field.isReference()) {
            return cls.getDb().get(field.getFieldClass(), values.get(field.getName()));
        } else if (field.isMultiReference()) {
            MultiReferenceField multiField = (MultiReferenceField) field;
            return cls.getDb().filter(field.getFieldClass(), "? = ?", multiField.getReferenceModelField(), getPrimaryKey());
        } else if (field.isProperty()) {
            try {
                Method method = getClass().getMethod(((ModelProperty) field).getMethodName());
                return method.invoke(this);
            } catch (Exception e) {
                throw new SystemException(e);
            }
        } else {
            return values.get(field.getServiceName());
        }
    }

    public void setValues(Map<String, Object> valueMap) throws SystemException, PortalException {
        for (String fieldName : valueMap.keySet()) {
            setValue(fieldName, valueMap.get(fieldName));
        }
    }

    public Map<String, Object> getValues(List<String> fields) throws SystemException, PortalException, DBException {
        Map<String, Object> values = new LinkedHashMap<String, Object>();

        for (String fieldName : fields) {
            values.put(fieldName, getValue(fieldName));
        }

        return values;
    }

    /* modify methods */

    public Model save() throws SystemException, PortalException, DBException {
        return cls.getDb().save(this);
    }

    public void remove() throws SystemException, PortalException, DBException {
        cls.getDb().remove(this);
    }

    public void cache() throws SystemException, PortalException {
        cls.getDb().cache(this);
    }

    public boolean equals(Object obj) {
        try {
            return getPrimaryKey().equals(((Model)obj).getPrimaryKey());
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }
}
