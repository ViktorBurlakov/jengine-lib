/*
 *  This file is part of JEngine.
 *  *
 *  * Copyright (C) 2013-2014 Cetus (http://cs1.com.ua/). All rights reserved.
 *  *
 *  * JEngine is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * JEngine is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with JEngine.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jengine.orm.db.adapter.jdbc.connection;


import com.jengine.orm.db.DBConnection;
import com.jengine.orm.db.DBException;
import com.jengine.orm.db.adapter.ConnectionManager;
import com.jengine.orm.db.adapter.jdbc.JDBCConnection;

import javax.sql.DataSource;

public class DBCPConnectionPool extends ConnectionManager {
    protected DataSource dataSource;

    public DBCPConnectionPool(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DBConnection getConnection() throws DBException {
        try {
            return new JDBCConnection(this.dataSource.getConnection());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
