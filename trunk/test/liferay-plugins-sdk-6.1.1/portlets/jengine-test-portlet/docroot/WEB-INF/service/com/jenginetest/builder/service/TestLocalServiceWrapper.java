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

package com.jenginetest.builder.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TestLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TestLocalService
 * @generated
 */
public class TestLocalServiceWrapper implements TestLocalService,
	ServiceWrapper<TestLocalService> {
	public TestLocalServiceWrapper(TestLocalService testLocalService) {
		_testLocalService = testLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _testLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_testLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _testLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public void clearData()
		throws com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.clearData();
	}

	public void loadData()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.loadData();
	}

	/**
	* Clearing data test
	*/
	public void test1()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.test1();
	}

	/**
	* Object creation test
	*/
	public void test2()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.test2();
	}

	/**
	* Selection test
	*/
	public void test3()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.test3();
	}

	/**
	* Sub query test
	*/
	public void test4()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.test4();
	}

	/**
	* Inserting and Updating test
	*/
	public void test5()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.test5();
	}

	/**
	* Aggregation testing
	*/
	public void test6()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_testLocalService.test6();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TestLocalService getWrappedTestLocalService() {
		return _testLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTestLocalService(TestLocalService testLocalService) {
		_testLocalService = testLocalService;
	}

	public TestLocalService getWrappedService() {
		return _testLocalService;
	}

	public void setWrappedService(TestLocalService testLocalService) {
		_testLocalService = testLocalService;
	}

	private TestLocalService _testLocalService;
}