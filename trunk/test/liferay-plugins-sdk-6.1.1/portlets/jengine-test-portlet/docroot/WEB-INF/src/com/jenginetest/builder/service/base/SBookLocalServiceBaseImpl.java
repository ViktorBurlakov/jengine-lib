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

import com.jenginetest.builder.model.SBook;
import com.jenginetest.builder.service.SAuthorLocalService;
import com.jenginetest.builder.service.SAuthorService;
import com.jenginetest.builder.service.SBookLocalService;
import com.jenginetest.builder.service.SBookService;
import com.jenginetest.builder.service.SLibraryLocalService;
import com.jenginetest.builder.service.SLibraryService;
import com.jenginetest.builder.service.SMemberLocalService;
import com.jenginetest.builder.service.SMemberService;
import com.jenginetest.builder.service.STransactionLocalService;
import com.jenginetest.builder.service.STransactionService;
import com.jenginetest.builder.service.TestLocalService;
import com.jenginetest.builder.service.TestService;
import com.jenginetest.builder.service.persistence.SAuthorPersistence;
import com.jenginetest.builder.service.persistence.SBookPersistence;
import com.jenginetest.builder.service.persistence.SLibraryPersistence;
import com.jenginetest.builder.service.persistence.SMemberPersistence;
import com.jenginetest.builder.service.persistence.STransactionPersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the s book local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.jenginetest.builder.service.impl.SBookLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.jenginetest.builder.service.impl.SBookLocalServiceImpl
 * @see com.jenginetest.builder.service.SBookLocalServiceUtil
 * @generated
 */
public abstract class SBookLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements SBookLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.jenginetest.builder.service.SBookLocalServiceUtil} to access the s book local service.
	 */

	/**
	 * Adds the s book to the database. Also notifies the appropriate model listeners.
	 *
	 * @param sBook the s book
	 * @return the s book that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public SBook addSBook(SBook sBook) throws SystemException {
		sBook.setNew(true);

		return sBookPersistence.update(sBook, false);
	}

	/**
	 * Creates a new s book with the primary key. Does not add the s book to the database.
	 *
	 * @param bookId the primary key for the new s book
	 * @return the new s book
	 */
	public SBook createSBook(long bookId) {
		return sBookPersistence.create(bookId);
	}

	/**
	 * Deletes the s book with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bookId the primary key of the s book
	 * @return the s book that was removed
	 * @throws PortalException if a s book with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public SBook deleteSBook(long bookId)
		throws PortalException, SystemException {
		return sBookPersistence.remove(bookId);
	}

	/**
	 * Deletes the s book from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sBook the s book
	 * @return the s book that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public SBook deleteSBook(SBook sBook) throws SystemException {
		return sBookPersistence.remove(sBook);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(SBook.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return sBookPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return sBookPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return sBookPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return sBookPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public SBook fetchSBook(long bookId) throws SystemException {
		return sBookPersistence.fetchByPrimaryKey(bookId);
	}

	/**
	 * Returns the s book with the primary key.
	 *
	 * @param bookId the primary key of the s book
	 * @return the s book
	 * @throws PortalException if a s book with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SBook getSBook(long bookId) throws PortalException, SystemException {
		return sBookPersistence.findByPrimaryKey(bookId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return sBookPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the s books.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of s books
	 * @param end the upper bound of the range of s books (not inclusive)
	 * @return the range of s books
	 * @throws SystemException if a system exception occurred
	 */
	public List<SBook> getSBooks(int start, int end) throws SystemException {
		return sBookPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of s books.
	 *
	 * @return the number of s books
	 * @throws SystemException if a system exception occurred
	 */
	public int getSBooksCount() throws SystemException {
		return sBookPersistence.countAll();
	}

	/**
	 * Updates the s book in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param sBook the s book
	 * @return the s book that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public SBook updateSBook(SBook sBook) throws SystemException {
		return updateSBook(sBook, true);
	}

	/**
	 * Updates the s book in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param sBook the s book
	 * @param merge whether to merge the s book with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the s book that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public SBook updateSBook(SBook sBook, boolean merge)
		throws SystemException {
		sBook.setNew(false);

		return sBookPersistence.update(sBook, merge);
	}

	/**
	 * Returns the s author local service.
	 *
	 * @return the s author local service
	 */
	public SAuthorLocalService getSAuthorLocalService() {
		return sAuthorLocalService;
	}

	/**
	 * Sets the s author local service.
	 *
	 * @param sAuthorLocalService the s author local service
	 */
	public void setSAuthorLocalService(SAuthorLocalService sAuthorLocalService) {
		this.sAuthorLocalService = sAuthorLocalService;
	}

	/**
	 * Returns the s author remote service.
	 *
	 * @return the s author remote service
	 */
	public SAuthorService getSAuthorService() {
		return sAuthorService;
	}

	/**
	 * Sets the s author remote service.
	 *
	 * @param sAuthorService the s author remote service
	 */
	public void setSAuthorService(SAuthorService sAuthorService) {
		this.sAuthorService = sAuthorService;
	}

	/**
	 * Returns the s author persistence.
	 *
	 * @return the s author persistence
	 */
	public SAuthorPersistence getSAuthorPersistence() {
		return sAuthorPersistence;
	}

	/**
	 * Sets the s author persistence.
	 *
	 * @param sAuthorPersistence the s author persistence
	 */
	public void setSAuthorPersistence(SAuthorPersistence sAuthorPersistence) {
		this.sAuthorPersistence = sAuthorPersistence;
	}

	/**
	 * Returns the s book local service.
	 *
	 * @return the s book local service
	 */
	public SBookLocalService getSBookLocalService() {
		return sBookLocalService;
	}

	/**
	 * Sets the s book local service.
	 *
	 * @param sBookLocalService the s book local service
	 */
	public void setSBookLocalService(SBookLocalService sBookLocalService) {
		this.sBookLocalService = sBookLocalService;
	}

	/**
	 * Returns the s book remote service.
	 *
	 * @return the s book remote service
	 */
	public SBookService getSBookService() {
		return sBookService;
	}

	/**
	 * Sets the s book remote service.
	 *
	 * @param sBookService the s book remote service
	 */
	public void setSBookService(SBookService sBookService) {
		this.sBookService = sBookService;
	}

	/**
	 * Returns the s book persistence.
	 *
	 * @return the s book persistence
	 */
	public SBookPersistence getSBookPersistence() {
		return sBookPersistence;
	}

	/**
	 * Sets the s book persistence.
	 *
	 * @param sBookPersistence the s book persistence
	 */
	public void setSBookPersistence(SBookPersistence sBookPersistence) {
		this.sBookPersistence = sBookPersistence;
	}

	/**
	 * Returns the s library local service.
	 *
	 * @return the s library local service
	 */
	public SLibraryLocalService getSLibraryLocalService() {
		return sLibraryLocalService;
	}

	/**
	 * Sets the s library local service.
	 *
	 * @param sLibraryLocalService the s library local service
	 */
	public void setSLibraryLocalService(
		SLibraryLocalService sLibraryLocalService) {
		this.sLibraryLocalService = sLibraryLocalService;
	}

	/**
	 * Returns the s library remote service.
	 *
	 * @return the s library remote service
	 */
	public SLibraryService getSLibraryService() {
		return sLibraryService;
	}

	/**
	 * Sets the s library remote service.
	 *
	 * @param sLibraryService the s library remote service
	 */
	public void setSLibraryService(SLibraryService sLibraryService) {
		this.sLibraryService = sLibraryService;
	}

	/**
	 * Returns the s library persistence.
	 *
	 * @return the s library persistence
	 */
	public SLibraryPersistence getSLibraryPersistence() {
		return sLibraryPersistence;
	}

	/**
	 * Sets the s library persistence.
	 *
	 * @param sLibraryPersistence the s library persistence
	 */
	public void setSLibraryPersistence(SLibraryPersistence sLibraryPersistence) {
		this.sLibraryPersistence = sLibraryPersistence;
	}

	/**
	 * Returns the s member local service.
	 *
	 * @return the s member local service
	 */
	public SMemberLocalService getSMemberLocalService() {
		return sMemberLocalService;
	}

	/**
	 * Sets the s member local service.
	 *
	 * @param sMemberLocalService the s member local service
	 */
	public void setSMemberLocalService(SMemberLocalService sMemberLocalService) {
		this.sMemberLocalService = sMemberLocalService;
	}

	/**
	 * Returns the s member remote service.
	 *
	 * @return the s member remote service
	 */
	public SMemberService getSMemberService() {
		return sMemberService;
	}

	/**
	 * Sets the s member remote service.
	 *
	 * @param sMemberService the s member remote service
	 */
	public void setSMemberService(SMemberService sMemberService) {
		this.sMemberService = sMemberService;
	}

	/**
	 * Returns the s member persistence.
	 *
	 * @return the s member persistence
	 */
	public SMemberPersistence getSMemberPersistence() {
		return sMemberPersistence;
	}

	/**
	 * Sets the s member persistence.
	 *
	 * @param sMemberPersistence the s member persistence
	 */
	public void setSMemberPersistence(SMemberPersistence sMemberPersistence) {
		this.sMemberPersistence = sMemberPersistence;
	}

	/**
	 * Returns the s transaction local service.
	 *
	 * @return the s transaction local service
	 */
	public STransactionLocalService getSTransactionLocalService() {
		return sTransactionLocalService;
	}

	/**
	 * Sets the s transaction local service.
	 *
	 * @param sTransactionLocalService the s transaction local service
	 */
	public void setSTransactionLocalService(
		STransactionLocalService sTransactionLocalService) {
		this.sTransactionLocalService = sTransactionLocalService;
	}

	/**
	 * Returns the s transaction remote service.
	 *
	 * @return the s transaction remote service
	 */
	public STransactionService getSTransactionService() {
		return sTransactionService;
	}

	/**
	 * Sets the s transaction remote service.
	 *
	 * @param sTransactionService the s transaction remote service
	 */
	public void setSTransactionService(STransactionService sTransactionService) {
		this.sTransactionService = sTransactionService;
	}

	/**
	 * Returns the s transaction persistence.
	 *
	 * @return the s transaction persistence
	 */
	public STransactionPersistence getSTransactionPersistence() {
		return sTransactionPersistence;
	}

	/**
	 * Sets the s transaction persistence.
	 *
	 * @param sTransactionPersistence the s transaction persistence
	 */
	public void setSTransactionPersistence(
		STransactionPersistence sTransactionPersistence) {
		this.sTransactionPersistence = sTransactionPersistence;
	}

	/**
	 * Returns the test local service.
	 *
	 * @return the test local service
	 */
	public TestLocalService getTestLocalService() {
		return testLocalService;
	}

	/**
	 * Sets the test local service.
	 *
	 * @param testLocalService the test local service
	 */
	public void setTestLocalService(TestLocalService testLocalService) {
		this.testLocalService = testLocalService;
	}

	/**
	 * Returns the test remote service.
	 *
	 * @return the test remote service
	 */
	public TestService getTestService() {
		return testService;
	}

	/**
	 * Sets the test remote service.
	 *
	 * @param testService the test remote service
	 */
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		PersistedModelLocalServiceRegistryUtil.register("com.jenginetest.builder.model.SBook",
			sBookLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.jenginetest.builder.model.SBook");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
	}

	protected Class<?> getModelClass() {
		return SBook.class;
	}

	protected String getModelClassName() {
		return SBook.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = sBookPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = SAuthorLocalService.class)
	protected SAuthorLocalService sAuthorLocalService;
	@BeanReference(type = SAuthorService.class)
	protected SAuthorService sAuthorService;
	@BeanReference(type = SAuthorPersistence.class)
	protected SAuthorPersistence sAuthorPersistence;
	@BeanReference(type = SBookLocalService.class)
	protected SBookLocalService sBookLocalService;
	@BeanReference(type = SBookService.class)
	protected SBookService sBookService;
	@BeanReference(type = SBookPersistence.class)
	protected SBookPersistence sBookPersistence;
	@BeanReference(type = SLibraryLocalService.class)
	protected SLibraryLocalService sLibraryLocalService;
	@BeanReference(type = SLibraryService.class)
	protected SLibraryService sLibraryService;
	@BeanReference(type = SLibraryPersistence.class)
	protected SLibraryPersistence sLibraryPersistence;
	@BeanReference(type = SMemberLocalService.class)
	protected SMemberLocalService sMemberLocalService;
	@BeanReference(type = SMemberService.class)
	protected SMemberService sMemberService;
	@BeanReference(type = SMemberPersistence.class)
	protected SMemberPersistence sMemberPersistence;
	@BeanReference(type = STransactionLocalService.class)
	protected STransactionLocalService sTransactionLocalService;
	@BeanReference(type = STransactionService.class)
	protected STransactionService sTransactionService;
	@BeanReference(type = STransactionPersistence.class)
	protected STransactionPersistence sTransactionPersistence;
	@BeanReference(type = TestLocalService.class)
	protected TestLocalService testLocalService;
	@BeanReference(type = TestService.class)
	protected TestService testService;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private SBookLocalServiceClpInvoker _clpInvoker = new SBookLocalServiceClpInvoker();
}