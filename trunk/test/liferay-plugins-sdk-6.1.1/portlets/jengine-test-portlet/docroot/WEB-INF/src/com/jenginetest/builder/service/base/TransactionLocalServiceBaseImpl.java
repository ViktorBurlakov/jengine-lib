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

import com.jenginetest.builder.model.Transaction;
import com.jenginetest.builder.service.AuthorLocalService;
import com.jenginetest.builder.service.AuthorService;
import com.jenginetest.builder.service.BookLocalService;
import com.jenginetest.builder.service.BookService;
import com.jenginetest.builder.service.LibraryLocalService;
import com.jenginetest.builder.service.LibraryService;
import com.jenginetest.builder.service.MemberLocalService;
import com.jenginetest.builder.service.MemberService;
import com.jenginetest.builder.service.TestLocalService;
import com.jenginetest.builder.service.TestService;
import com.jenginetest.builder.service.TransactionLocalService;
import com.jenginetest.builder.service.TransactionService;
import com.jenginetest.builder.service.persistence.AuthorPersistence;
import com.jenginetest.builder.service.persistence.BookPersistence;
import com.jenginetest.builder.service.persistence.LibraryPersistence;
import com.jenginetest.builder.service.persistence.MemberPersistence;
import com.jenginetest.builder.service.persistence.TransactionPersistence;

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
 * The base implementation of the transaction local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.jenginetest.builder.service.impl.TransactionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.jenginetest.builder.service.impl.TransactionLocalServiceImpl
 * @see com.jenginetest.builder.service.TransactionLocalServiceUtil
 * @generated
 */
public abstract class TransactionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements TransactionLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.jenginetest.builder.service.TransactionLocalServiceUtil} to access the transaction local service.
	 */

	/**
	 * Adds the transaction to the database. Also notifies the appropriate model listeners.
	 *
	 * @param transaction the transaction
	 * @return the transaction that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Transaction addTransaction(Transaction transaction)
		throws SystemException {
		transaction.setNew(true);

		return transactionPersistence.update(transaction, false);
	}

	/**
	 * Creates a new transaction with the primary key. Does not add the transaction to the database.
	 *
	 * @param transactionId the primary key for the new transaction
	 * @return the new transaction
	 */
	public Transaction createTransaction(long transactionId) {
		return transactionPersistence.create(transactionId);
	}

	/**
	 * Deletes the transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionId the primary key of the transaction
	 * @return the transaction that was removed
	 * @throws PortalException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public Transaction deleteTransaction(long transactionId)
		throws PortalException, SystemException {
		return transactionPersistence.remove(transactionId);
	}

	/**
	 * Deletes the transaction from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transaction the transaction
	 * @return the transaction that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public Transaction deleteTransaction(Transaction transaction)
		throws SystemException {
		return transactionPersistence.remove(transaction);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Transaction.class,
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
		return transactionPersistence.findWithDynamicQuery(dynamicQuery);
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
		return transactionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
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
		return transactionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return transactionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Transaction fetchTransaction(long transactionId)
		throws SystemException {
		return transactionPersistence.fetchByPrimaryKey(transactionId);
	}

	/**
	 * Returns the transaction with the primary key.
	 *
	 * @param transactionId the primary key of the transaction
	 * @return the transaction
	 * @throws PortalException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction getTransaction(long transactionId)
		throws PortalException, SystemException {
		return transactionPersistence.findByPrimaryKey(transactionId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return transactionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> getTransactions(int start, int end)
		throws SystemException {
		return transactionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of transactions.
	 *
	 * @return the number of transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int getTransactionsCount() throws SystemException {
		return transactionPersistence.countAll();
	}

	/**
	 * Updates the transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param transaction the transaction
	 * @return the transaction that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Transaction updateTransaction(Transaction transaction)
		throws SystemException {
		return updateTransaction(transaction, true);
	}

	/**
	 * Updates the transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param transaction the transaction
	 * @param merge whether to merge the transaction with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the transaction that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Transaction updateTransaction(Transaction transaction, boolean merge)
		throws SystemException {
		transaction.setNew(false);

		return transactionPersistence.update(transaction, merge);
	}

	/**
	 * Returns the author local service.
	 *
	 * @return the author local service
	 */
	public AuthorLocalService getAuthorLocalService() {
		return authorLocalService;
	}

	/**
	 * Sets the author local service.
	 *
	 * @param authorLocalService the author local service
	 */
	public void setAuthorLocalService(AuthorLocalService authorLocalService) {
		this.authorLocalService = authorLocalService;
	}

	/**
	 * Returns the author remote service.
	 *
	 * @return the author remote service
	 */
	public AuthorService getAuthorService() {
		return authorService;
	}

	/**
	 * Sets the author remote service.
	 *
	 * @param authorService the author remote service
	 */
	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	/**
	 * Returns the author persistence.
	 *
	 * @return the author persistence
	 */
	public AuthorPersistence getAuthorPersistence() {
		return authorPersistence;
	}

	/**
	 * Sets the author persistence.
	 *
	 * @param authorPersistence the author persistence
	 */
	public void setAuthorPersistence(AuthorPersistence authorPersistence) {
		this.authorPersistence = authorPersistence;
	}

	/**
	 * Returns the book local service.
	 *
	 * @return the book local service
	 */
	public BookLocalService getBookLocalService() {
		return bookLocalService;
	}

	/**
	 * Sets the book local service.
	 *
	 * @param bookLocalService the book local service
	 */
	public void setBookLocalService(BookLocalService bookLocalService) {
		this.bookLocalService = bookLocalService;
	}

	/**
	 * Returns the book remote service.
	 *
	 * @return the book remote service
	 */
	public BookService getBookService() {
		return bookService;
	}

	/**
	 * Sets the book remote service.
	 *
	 * @param bookService the book remote service
	 */
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * Returns the book persistence.
	 *
	 * @return the book persistence
	 */
	public BookPersistence getBookPersistence() {
		return bookPersistence;
	}

	/**
	 * Sets the book persistence.
	 *
	 * @param bookPersistence the book persistence
	 */
	public void setBookPersistence(BookPersistence bookPersistence) {
		this.bookPersistence = bookPersistence;
	}

	/**
	 * Returns the library local service.
	 *
	 * @return the library local service
	 */
	public LibraryLocalService getLibraryLocalService() {
		return libraryLocalService;
	}

	/**
	 * Sets the library local service.
	 *
	 * @param libraryLocalService the library local service
	 */
	public void setLibraryLocalService(LibraryLocalService libraryLocalService) {
		this.libraryLocalService = libraryLocalService;
	}

	/**
	 * Returns the library remote service.
	 *
	 * @return the library remote service
	 */
	public LibraryService getLibraryService() {
		return libraryService;
	}

	/**
	 * Sets the library remote service.
	 *
	 * @param libraryService the library remote service
	 */
	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	/**
	 * Returns the library persistence.
	 *
	 * @return the library persistence
	 */
	public LibraryPersistence getLibraryPersistence() {
		return libraryPersistence;
	}

	/**
	 * Sets the library persistence.
	 *
	 * @param libraryPersistence the library persistence
	 */
	public void setLibraryPersistence(LibraryPersistence libraryPersistence) {
		this.libraryPersistence = libraryPersistence;
	}

	/**
	 * Returns the member local service.
	 *
	 * @return the member local service
	 */
	public MemberLocalService getMemberLocalService() {
		return memberLocalService;
	}

	/**
	 * Sets the member local service.
	 *
	 * @param memberLocalService the member local service
	 */
	public void setMemberLocalService(MemberLocalService memberLocalService) {
		this.memberLocalService = memberLocalService;
	}

	/**
	 * Returns the member remote service.
	 *
	 * @return the member remote service
	 */
	public MemberService getMemberService() {
		return memberService;
	}

	/**
	 * Sets the member remote service.
	 *
	 * @param memberService the member remote service
	 */
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * Returns the member persistence.
	 *
	 * @return the member persistence
	 */
	public MemberPersistence getMemberPersistence() {
		return memberPersistence;
	}

	/**
	 * Sets the member persistence.
	 *
	 * @param memberPersistence the member persistence
	 */
	public void setMemberPersistence(MemberPersistence memberPersistence) {
		this.memberPersistence = memberPersistence;
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
	 * Returns the transaction local service.
	 *
	 * @return the transaction local service
	 */
	public TransactionLocalService getTransactionLocalService() {
		return transactionLocalService;
	}

	/**
	 * Sets the transaction local service.
	 *
	 * @param transactionLocalService the transaction local service
	 */
	public void setTransactionLocalService(
		TransactionLocalService transactionLocalService) {
		this.transactionLocalService = transactionLocalService;
	}

	/**
	 * Returns the transaction remote service.
	 *
	 * @return the transaction remote service
	 */
	public TransactionService getTransactionService() {
		return transactionService;
	}

	/**
	 * Sets the transaction remote service.
	 *
	 * @param transactionService the transaction remote service
	 */
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * Returns the transaction persistence.
	 *
	 * @return the transaction persistence
	 */
	public TransactionPersistence getTransactionPersistence() {
		return transactionPersistence;
	}

	/**
	 * Sets the transaction persistence.
	 *
	 * @param transactionPersistence the transaction persistence
	 */
	public void setTransactionPersistence(
		TransactionPersistence transactionPersistence) {
		this.transactionPersistence = transactionPersistence;
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
		PersistedModelLocalServiceRegistryUtil.register("com.jenginetest.builder.model.Transaction",
			transactionLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.jenginetest.builder.model.Transaction");
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
		return Transaction.class;
	}

	protected String getModelClassName() {
		return Transaction.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = transactionPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AuthorLocalService.class)
	protected AuthorLocalService authorLocalService;
	@BeanReference(type = AuthorService.class)
	protected AuthorService authorService;
	@BeanReference(type = AuthorPersistence.class)
	protected AuthorPersistence authorPersistence;
	@BeanReference(type = BookLocalService.class)
	protected BookLocalService bookLocalService;
	@BeanReference(type = BookService.class)
	protected BookService bookService;
	@BeanReference(type = BookPersistence.class)
	protected BookPersistence bookPersistence;
	@BeanReference(type = LibraryLocalService.class)
	protected LibraryLocalService libraryLocalService;
	@BeanReference(type = LibraryService.class)
	protected LibraryService libraryService;
	@BeanReference(type = LibraryPersistence.class)
	protected LibraryPersistence libraryPersistence;
	@BeanReference(type = MemberLocalService.class)
	protected MemberLocalService memberLocalService;
	@BeanReference(type = MemberService.class)
	protected MemberService memberService;
	@BeanReference(type = MemberPersistence.class)
	protected MemberPersistence memberPersistence;
	@BeanReference(type = TestLocalService.class)
	protected TestLocalService testLocalService;
	@BeanReference(type = TestService.class)
	protected TestService testService;
	@BeanReference(type = TransactionLocalService.class)
	protected TransactionLocalService transactionLocalService;
	@BeanReference(type = TransactionService.class)
	protected TransactionService transactionService;
	@BeanReference(type = TransactionPersistence.class)
	protected TransactionPersistence transactionPersistence;
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
	private TransactionLocalServiceClpInvoker _clpInvoker = new TransactionLocalServiceClpInvoker();
}