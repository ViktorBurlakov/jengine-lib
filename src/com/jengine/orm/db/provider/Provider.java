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

package com.jengine.orm.db.provider;


import com.jengine.orm.*;
import com.jengine.orm.db.DBConnection;
import com.jengine.orm.db.DBException;
import com.jengine.orm.db.adapter.Adapter;
import com.jengine.orm.db.expression.Expression;
import com.jengine.orm.db.expression.ExpressionImpl;
import com.jengine.orm.db.query.SQLQuery;
import com.jengine.orm.field.Field;
import com.jengine.orm.field.ForeignField;
import com.jengine.orm.field.FunctionField;
import com.jengine.orm.field.ReferenceField;
import com.jengine.utils.CollectionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jengine.utils.CollectionUtil.*;


public class Provider {
    private ModelClassBase modelClass;
    private Adapter adapter;

    public Provider(Adapter adapter) {
        this.adapter = adapter;
    }

    public DBConnection getConnection() throws DBException {
        return adapter.getConnection();
    }

    /* cache methods */

    public void cache(Model obj) {
    }

    public Model getCache(Class cls, Object id) {
        return null;
    }

    public void clearCache(Model obj) {
    }

    public void clearCache(Class cls) {
    }


    /* general methods */

    public Object insert(String table, Map<String, Object> attributes) throws DBException {
        List columns = new ArrayList();
        columns.addAll(attributes.keySet());

        return insert(table, columns, CollectionUtil.values(columns, attributes));
    }

    public Object insert(String table, List<String> columns, List values) throws DBException {
        StringBuffer sql = new StringBuffer();
        List valuesMarks = newList(values.size(), "?");

        sql.append("INSERT INTO ")
                .append(table)
                .append(" (").append(concat(columns, ", ")).append(") ")
                .append(" VALUES ").append(" (").append(concat(valuesMarks, ", ")).append(") ");

        DBConnection connection = this.adapter.getConnection();
        this.adapter.executeUpdate(connection, sql.toString(), values);

        return connection.getGeneratedKeys().size() == 1 ? connection.getGeneratedKeys().get(0) : null;
    }

    public void update(String table, Map<String, Object> attributes) throws DBException {
        StringBuffer sql = new StringBuffer();
        List pairs = new ArrayList();
        List params = new ArrayList();

        for (String columnName : attributes.keySet()) {
            StringBuffer pair = new StringBuffer();
            pair.append(columnName).append("=").append("?");
            pairs.add(pair.toString());
            params.add(attributes.get(columnName));
        }
        sql.append("UPDATE ").append(table).append(" SET ").append(concat(pairs, ", "));

        DBConnection connection = this.adapter.getConnection();
        this.adapter.executeUpdate(connection, sql.toString(), params);
    }

    public void remove(String table, String primaryKey, Serializable id) throws DBException {
        StringBuffer sql = new StringBuffer();
        List params = new ArrayList();
        params.add(id);

        sql.append("DELETE FROM ")
                .append(table)
                .append(" WHERE ")
                .append(primaryKey).append("=").append("?");

        DBConnection connection = this.adapter.getConnection();
        this.adapter.executeUpdate(connection, sql.toString(), params);
    }

    public Object insert(ModelQuery modelQuery) throws DBException {
        SQLQuery query = new SQLQuery();
        setModels(query, modelQuery);
        setValues(query, modelQuery);

        String sql = buildInsertSQL(query);

        DBConnection connection = this.adapter.getConnection();
        this.adapter.executeUpdate(connection, sql, query.getParams());

        return connection.getGeneratedKeys();
    }

    public void remove(ModelQuery modelQuery) throws DBException {
        SQLQuery query = new SQLQuery();
        setModels(query, modelQuery);
        setFilters(query, modelQuery);
        setStringQuery(query, modelQuery);

        // todo: design new classes for sql query to do better
        if (query.getRelations().size() == 0) {
            query.setTableAlias(null);
        }

        String sql = buildRemoveSQL(query);

        DBConnection connection = this.adapter.getConnection();
        this.adapter.executeUpdate(connection, sql, query.getParams());
    }

    public void update(ModelQuery modelQuery) throws DBException{
        SQLQuery query = new SQLQuery();
        setModels(query, modelQuery);
        setValues(query, modelQuery);
        setFilters(query, modelQuery);
        setStringQuery(query, modelQuery);

        String sql = buildUpdateSQL(query);

        DBConnection connection = this.adapter.getConnection();
        this.adapter.executeUpdate(connection, sql, query.getParams());
    }

    public List select(ModelQuery modelQuery) throws DBException {
        SQLQuery sqlQuery = buildSelect(modelQuery);
        String sql = buildSelectSQL(sqlQuery);

        DBConnection connection = this.adapter.getConnection();
        return this.adapter.executeQuery(connection, sql, sqlQuery.getParams());
    }


    /* getters and setters */

    public ModelClassBase getModelClass() {
        return modelClass;
    }

    public void setModelClass(ModelClassBase modelClass) {
        this.modelClass = modelClass;
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

/* protected methods */

    protected String buildInsertSQL(SQLQuery query) {
        StringBuffer sql = new StringBuffer();
        StringBuffer fieldClause = new StringBuffer();
        StringBuffer valueClause = new StringBuffer();

        sql.append(" INSERT INTO ").append(query.getTableName());
        for (String param :query.getValues().keySet()) {
            if (fieldClause.length() > 0) {
                fieldClause.append(", ");
            }
            fieldClause.append(param);
        }
        sql.append(" ").append("(").append(fieldClause).append(")");
        sql.append(" VALUES ");
        for (Object value :query.getValues().values()) {
            if (valueClause.length() > 0) {
                valueClause.append(", ");
            }
            valueClause.append("?");
        }
        sql.append(" ").append("(").append(valueClause).append(")");

        return sql.toString();
    }


    protected String buildRemoveSQL(SQLQuery query)  {
        StringBuffer sql = new StringBuffer();

        sql.append(" DELETE FROM ").append(buildTableClause(query)).append(" ").append(buildWhereClause(query));

        return sql.toString();
    }

    protected String buildUpdateSQL(SQLQuery query) {
        StringBuffer sql = new StringBuffer();
        StringBuffer setClause = new StringBuffer();
        StringBuffer whereClause = new StringBuffer();

        sql.append(" UPDATE ").append(buildTableClause(query)).append(" ");

        // set clause
        for (Object param :query.getValues().keySet()) {
            if (setClause.length() > 0) {
                setClause.append(", ");
            }
            setClause.append(param).append("=").append("?");
        }
        if (setClause.length() > 0) {
            sql.append(" SET ").append(setClause);
        }

        sql.append(buildWhereClause(query));

        return sql.toString();
    }

    protected SQLQuery buildSelect(ModelQuery modelQuery) throws DBException {
        SQLQuery query = new SQLQuery();

        setModels(query, modelQuery);
        setTargets(query, modelQuery);
        setFilters(query, modelQuery);
        setStringQuery(query, modelQuery);
        setOrder(query, modelQuery);
        setPage(query, modelQuery);

        return query;
    }

    protected String buildSelectSQL(SQLQuery query) {
        StringBuffer queryString = new StringBuffer();

        // select clause
        List<String> targets = new ArrayList<String>();
        for (Object[] target : query.getTargets()) {
            if (target.length > 1) {
                targets.add(String.format("%s AS %s", target));
            } else {
                targets.add((String) target[0]);
            }
        }
        queryString.append(" SELECT ").append(concat(targets, ", ")).append(" ");

        // from clause
        queryString.append(" FROM ").append(buildTableClause(query));

        // where clause
        queryString.append(buildWhereClause(query));

        // order clause
        if(!query.getOrder().isEmpty()) {
            queryString.append(" ORDER BY ").append(query.getOrder().get("orderByCol"));
            if (query.getOrder().containsKey("orderByType")) {
                queryString.append(" ").append(query.getOrder().get("orderByType"));
            }
            queryString.append("");
        }

        return queryString.toString();
    }

    protected void setValues(SQLQuery query, ModelQuery modelQuery) throws DBException {
        for (String fieldName : modelQuery.getValues().keySet()) {
            Field field = modelQuery.getFieldMap().get(fieldName);
            Object value = modelQuery.getValues().get(fieldName);
            query.addValue(getSQLName(query, field), value);
            query.addParam(value);
        }
    }

    protected StringBuffer buildWhereClause(SQLQuery query) {
        StringBuffer queryString = new StringBuffer();

        if (!query.getFitler().isEmpty() || !query.getStringQueries().isEmpty()) {
            queryString.append(" WHERE ");
            StringBuffer expressionClause = new StringBuffer();
            if (!query.getFitler().isEmpty()) {
                for (String field : query.getFitler().keySet()) {
                    Expression expression = query.getFitler().get(field);
                    if (expressionClause.length() > 0) {
                        expressionClause.append(" AND ");
                    }
                    expressionClause.append(makeSQLExpr(field, expression.getOperation(), expression.getValue()));
                }
            }
            if (!query.getStringQueries().isEmpty()) {
                for (String stringQuery : query.getStringQueries()) {
                    if (expressionClause.length() > 0) {
                        expressionClause.append(" AND ");
                    }
                    expressionClause.append(stringQuery);
                }
            }
            queryString.append(expressionClause).append(" ");
        }

        return queryString;
    }

    protected StringBuffer buildTableClause(SQLQuery query) {
        StringBuffer queryString = new StringBuffer();

        queryString.append(query.getTableName());
        if (query.getTableAlias() != null && query.getTableAlias().length() > 0) {
            queryString.append(" AS ").append(query.getTableAlias()).append(" ");
        }
        for (String alias : query.getRelations().keySet()) {
            String relation = (String) query.getRelations().get(alias).get(0);
            String expr = (String) query.getRelations().get(alias).get(1);
            queryString.append(" LEFT JOIN ").append(relation).append(" as ").append(alias)
                    .append(" ON ").append(expr).append(" ");
        }
        return queryString;
    }

    protected void setModels(SQLQuery query, ModelQuery modelQuery) {
        query.setTableName(modelQuery.getManager().getTableName());
        query.setTableAlias(modelQuery.getManager().getSelf().getFieldName());

        for (Field field : modelQuery.getFieldMap().values()) {
            if (field.getType() == Field.Type.FOREIGN) {
                addRelation(query, new ArrayList<String>(), query.getTableAlias(), (ForeignField) field);
//            }  else if (field.getType() == Field.Type.REFERENCE) {
//                addRelation(query, new ArrayList<String>(), query.getTableAlias(), (ReferenceField) field);
            }
        }
    }

    protected void addRelation(SQLQuery query, List<String> path, String alias, ForeignField foreignField) {
        ModelManager manager =  modelClass.getModelClass(foreignField.getCurrentField().getReferenceModelName()).getManager();
        Field referenceModelField = manager.getField(foreignField.getCurrentField().getReferenceModelFieldName());
        path.add(foreignField.getCurrentField().getFieldName());
        String foreignAlias = concat(path, "__").toString();
        if (foreignField.getCurrentField().getType() != Field.Type.SELF) {
            List value = list(manager.getTableName(),String.format("%s.%s = %s.%s",
                    foreignAlias, referenceModelField.getColumnName(),
                    alias, foreignField.getCurrentField().getColumnName()));
            query.getRelations().put(foreignAlias, value);
        }
        if (foreignField.getNextField().getType() == Field.Type.FOREIGN) {
            addRelation(query, path, foreignAlias, (ForeignField) foreignField.getNextField());
//        } else if (foreignField.getNextField().getType() == Field.Type.REFERENCE){
//            addRelation(query, path, foreignAlias, (ReferenceField) foreignField.getNextField());
        }
    }

//    protected void addRelation(SQLQuery query,  List<String> path, String alias, ReferenceField referenceField) {
//        ModelManager manager =  modelClass.getModelClass(referenceField.getReferenceModelName()).getManager();
//        Field referenceModelField = manager.getField(referenceField.getReferenceModelFieldName());
//        path.add(referenceField.getFieldName());
//        String foreignAlias = concat(path, "__").toString();
//        List value = list(manager.getTableName(),String.format("%s.%s = %s.%s",
//                foreignAlias, referenceModelField.getColumnName(),
//                alias, referenceField.getColumnName()));
//        query.getRelations().put(foreignAlias, value);
//    }

    protected void setTargets(SQLQuery query, ModelQuery modelQuery) {
        for (Field field : modelQuery.getFullFields()) {
            addTarget(field, query, modelQuery);
        }
    }

    protected void addTarget(Field field, SQLQuery query, ModelQuery modelQuery) {
        if (field.getType() == Field.Type.FUNCTION) {
            FunctionField functionField = (FunctionField) field;
            List<Field> attributes = functionField.getAttributes();
            List<String> dbAttributes = new ArrayList<String>();
            for (Field attr : attributes) {
                dbAttributes.add(getSQLName(query, attr));
            }
            query.addTarget(functionField.render(dbAttributes), functionField.getFieldName());
            query.putTargetType(functionField.getFieldName(), functionField.getColumnType());
        } else {
            String sqlName = getSQLName(query, field);
            query.addTarget(sqlName);
            query.putTargetType(sqlName, field.getColumnType());
        }
    }

    protected String buildReferenceTargets(SQLQuery query, String alias, ReferenceField referenceField) {
        StringBuffer target = new StringBuffer();
        ModelManager manager =  modelClass.getModelClass(referenceField.getReferenceModelName()).getManager();
        for (Field field : manager.getFields()) {
            if (field.getType() == Field.Type.PLAIN || field.getType() == Field.Type.REFERENCE) {
                if (target.length() > 0) {
                    target.append(", ");
                }
                String sqlName = getSQLName(query, field);
                if (field.getType() == Field.Type.REFERENCE) {
                    ReferenceField referenceField1 = (ReferenceField) field;
                    ModelManager manager1 =  modelClass.getModelClass(referenceField1.getReferenceModelName()).getManager();
                    query.putTargetType(sqlName, manager1.getField(referenceField1.getReferenceModelFieldName()).getColumnType());
                } else {
                    query.putTargetType(sqlName, field.getColumnType());
                }
                target.append(alias).append(".").append(sqlName);
            }
        }

        return target.toString();
    }

    protected void setFilters(SQLQuery query, ModelQuery modelQuery) throws DBException {
        if (modelQuery.getFilter().size() > 0) {
            for (int i=0; i < modelQuery.getFilter().size(); i++) {
                Expression expression = modelQuery.getFilter().get(i);
                Field field = modelQuery.getFilterFields().get(i);
                String sqlName = getSQLName(query, field);

                if (expression.getValue() != null && expression.getValue().getClass().equals(ModelQuery.class)) {
                    SQLQuery subSql = buildSelect((ModelQuery) expression.getValue());
                    query.getFitler().put(sqlName, new ExpressionImpl(sqlName, expression.getOperation(), subSql));
                    for(Object param : subSql.getParams()) {
                        query.getParams().add(param);
                    }
                } else {
                    Object value = field.cast(expression.getValue());
                    query.getFitler().put(sqlName, new ExpressionImpl(sqlName, expression.getOperation(), value));
                    query.getParams().add(value);
                }
            }
        }
    }

    protected void setStringQuery(SQLQuery query, ModelQuery modelQuery) throws DBException {
        if (modelQuery.getStringQueries().size() > 0) {
            for (int i=0; i < modelQuery.getStringQueries().size(); i++) {
                ModelQuery.StringQuery stringQuery = modelQuery.getStringQueries().get(i);
                List<Field> modelFields = stringQuery.getModelFields();
                List<String> sqlFields = new ArrayList<String>();

                for (Field field : modelFields) {
                    sqlFields.add(getSQLName(query, field));
                }
                for (int paramIndex = 0; paramIndex < stringQuery.getParams().size(); paramIndex++) {
                    Object param = stringQuery.getParams().get(paramIndex);

                    if (param != null && param.getClass().equals(ModelQuery.class)) {
                        SQLQuery subSql = buildSelect((ModelQuery) param);
                        for(Object subParam : subSql.getParams()) {
                            query.getParams().add(subParam);
                        }
                        stringQuery.setParamSQL(paramIndex, "(" + buildSelectSQL(subSql) + ")");
                    } else {
                        query.getParams().add(param);
                    }
                }
                query.addStringQuery(stringQuery.getSQL(sqlFields));
            }
        }
    }

    protected void setOrder(SQLQuery query, ModelQuery modelQuery) {
        if (modelQuery.getOrderField() != null) {
            Map<String, String> result = new HashMap<String, String>();
            result.put("orderByCol", getSQLName(query, modelQuery.getOrderField()));
            if (modelQuery.getOrderType() != null) {
                result.put("orderByType", modelQuery.getOrderType());
            }
            query.setOrder(result);
        }
    }

    protected void setPage(SQLQuery query, ModelQuery modelQuery) {
        query.setStart(modelQuery.getPage().get("start"));
        query.setEnd(modelQuery.getPage().get("end"));
    }

    protected String getSQLName(SQLQuery query, Field field) {
        if (field.getType() == Field.Type.FOREIGN) {
            String alias = concat(((ForeignField)field).getReferenceFields(), "__").toString();
            return String.format("%s.%s", alias, field.getColumnName());
        } else {
            return String.format("%s.%s", query.getTableAlias(), field.getColumnName());
        }
    }

    protected String makeSQLExpr(String field, String operation, Object value) {
        StringBuffer expr = new StringBuffer();
        Object param = value.getClass().equals(SQLQuery.class) ?
                String.format("(%s)", buildSelectSQL((SQLQuery) value)) : "?";

        if ("eq".equals(operation)) {
            expr.append(field).append("=").append(param);
        } else if ("ge".equals(operation)) {
            expr.append(field).append( ">=").append(param);
        } else  if ("gt".equals(operation)) {
            expr.append(field).append(">").append(param);
        } else if ("le".equals(operation)) {
            expr.append(field).append("<=").append(param);
        } else  if ("lt".equals(operation)) {
            expr.append(field).append("<").append(param);
        } else if ("like".equals(operation)) {
            expr.append(field).append(" ").append("like").append(" ").append(param);
        } else if ("ne".equals(operation)) {
            expr.append(field).append("<>").append(param);
        } else if ("isnull".equals(operation)) {
            if ((Boolean) value) {
                expr.append(field).append(" ").append("is null").append(" ");
            } else  {
                expr.append(field).append(" ").append("is not null").append(" ");
            }
        } else if ("isempty".equals(operation)) {
            if ((Boolean) value) {
                expr.append(field).append("=").append("''");
            } else  {
                expr.append(field).append("<>").append("''");
            }
        } else {
            expr.append(field).append(operation).append(param);
        }

        return expr.toString();
    }
}
