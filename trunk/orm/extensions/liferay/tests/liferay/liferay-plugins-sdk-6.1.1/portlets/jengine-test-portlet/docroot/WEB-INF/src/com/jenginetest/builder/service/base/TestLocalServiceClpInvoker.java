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

package com.jenginetest.builder.service.base;

import com.jenginetest.builder.service.TestLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TestLocalServiceClpInvoker {
	public TestLocalServiceClpInvoker() {
		_methodName52 = "getBeanIdentifier";

		_methodParameterTypes52 = new String[] {  };

		_methodName53 = "setBeanIdentifier";

		_methodParameterTypes53 = new String[] { "java.lang.String" };

		_methodName56 = "test";

		_methodParameterTypes56 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return TestLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			TestLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			TestLocalServiceUtil.test();
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName56;
	private String[] _methodParameterTypes56;
}