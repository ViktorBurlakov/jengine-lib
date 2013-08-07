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

package com.jenginetest.custom;


import com.jengine.db.Manager;
import com.jengine.db.ModelManager;
import com.jengine.db.field.*;
import com.jenginetest.builder.model.SAuthor;
import com.jenginetest.builder.model.impl.SAuthorImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.io.Serializable;
import java.util.Map;
import static com.jengine.utils.CollectionUtil.map;


public class Author extends CModel<SAuthor> {
    public static ModelField authorId = new PrimaryKey("authorId");
    public static ModelField firstName = new StringField("firstName", map("verbose", "First Name"));
    public static ModelField lastName = new StringField("lastName", map("verbose", "Last Name"));

    @Manager
    public static ModelManager manager = new ModelManager(Author.class, "Author", SAuthor.class, SAuthorImpl.class,
            SAuthorImpl.TABLE_NAME, SAuthorImpl.TABLE_COLUMNS, SAuthorImpl.ENTITY_CACHE_ENABLED);
    static public ClassUtil<Author> cls = new ClassUtil<Author>(Author.class);


    public Author(Serializable id, Map<String, Map> serviceContext) throws SystemException, PortalException {
        super(id, serviceContext);
    }

    public Author(SAuthor author, Map<String, Map> serviceContext) {
        super(author, serviceContext);
    }

    public String getVerbose() throws SystemException, PortalException {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    /* getters and setters block  */

    public String getFirstName() throws SystemException, PortalException {
        return (String) getValue(firstName);
    }

    public String getLastName() throws SystemException, PortalException {
        return (String) getValue(lastName);
    }
}