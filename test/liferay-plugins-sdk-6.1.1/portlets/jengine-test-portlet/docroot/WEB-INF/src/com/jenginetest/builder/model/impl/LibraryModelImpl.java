/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.jenginetest.builder.model.impl;

import com.jenginetest.builder.model.Library;
import com.jenginetest.builder.model.LibraryModel;
import com.jenginetest.builder.model.LibrarySoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Library service. Represents a row in the &quot;SB_Library&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.jenginetest.builder.model.LibraryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LibraryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LibraryImpl
 * @see com.jenginetest.builder.model.Library
 * @see com.jenginetest.builder.model.LibraryModel
 * @generated
 */
@JSON(strict = true)
public class LibraryModelImpl extends BaseModelImpl<Library>
	implements LibraryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a library model instance should use the {@link com.jenginetest.builder.model.Library} interface instead.
	 */
	public static final String TABLE_NAME = "SB_Library";
	public static final Object[][] TABLE_COLUMNS = {
			{ "libraryId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "address", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table SB_Library (libraryId LONG not null primary key,name VARCHAR(75) null,address VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table SB_Library";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.jenginetest.builder.model.Library"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.jenginetest.builder.model.Library"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Library toModel(LibrarySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Library model = new LibraryImpl();

		model.setLibraryId(soapModel.getLibraryId());
		model.setName(soapModel.getName());
		model.setAddress(soapModel.getAddress());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Library> toModels(LibrarySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Library> models = new ArrayList<Library>(soapModels.length);

		for (LibrarySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.jenginetest.builder.model.Library"));

	public LibraryModelImpl() {
	}

	public long getPrimaryKey() {
		return _libraryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setLibraryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_libraryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Library.class;
	}

	public String getModelClassName() {
		return Library.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("libraryId", getLibraryId());
		attributes.put("name", getName());
		attributes.put("address", getAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long libraryId = (Long)attributes.get("libraryId");

		if (libraryId != null) {
			setLibraryId(libraryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}
	}

	@JSON
	public long getLibraryId() {
		return _libraryId;
	}

	public void setLibraryId(long libraryId) {
		_libraryId = libraryId;
	}

	@JSON
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_name = name;
	}

	@JSON
	public String getAddress() {
		if (_address == null) {
			return StringPool.BLANK;
		}
		else {
			return _address;
		}
	}

	public void setAddress(String address) {
		_address = address;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Library.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Library toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Library)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		LibraryImpl libraryImpl = new LibraryImpl();

		libraryImpl.setLibraryId(getLibraryId());
		libraryImpl.setName(getName());
		libraryImpl.setAddress(getAddress());

		libraryImpl.resetOriginalValues();

		return libraryImpl;
	}

	public int compareTo(Library library) {
		long primaryKey = library.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Library library = null;

		try {
			library = (Library)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = library.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<Library> toCacheModel() {
		LibraryCacheModel libraryCacheModel = new LibraryCacheModel();

		libraryCacheModel.libraryId = getLibraryId();

		libraryCacheModel.name = getName();

		String name = libraryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			libraryCacheModel.name = null;
		}

		libraryCacheModel.address = getAddress();

		String address = libraryCacheModel.address;

		if ((address != null) && (address.length() == 0)) {
			libraryCacheModel.address = null;
		}

		return libraryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{libraryId=");
		sb.append(getLibraryId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.jenginetest.builder.model.Library");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>libraryId</column-name><column-value><![CDATA[");
		sb.append(getLibraryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Library.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Library.class
		};
	private long _libraryId;
	private String _name;
	private String _address;
	private Library _escapedModelProxy;
}