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
 * This class is a wrapper for {@link MemberLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MemberLocalService
 * @generated
 */
public class MemberLocalServiceWrapper implements MemberLocalService,
	ServiceWrapper<MemberLocalService> {
	public MemberLocalServiceWrapper(MemberLocalService memberLocalService) {
		_memberLocalService = memberLocalService;
	}

	/**
	* Adds the member to the database. Also notifies the appropriate model listeners.
	*
	* @param member the member
	* @return the member that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.jenginetest.builder.model.Member addMember(
		com.jenginetest.builder.model.Member member)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.addMember(member);
	}

	/**
	* Creates a new member with the primary key. Does not add the member to the database.
	*
	* @param memberId the primary key for the new member
	* @return the new member
	*/
	public com.jenginetest.builder.model.Member createMember(long memberId) {
		return _memberLocalService.createMember(memberId);
	}

	/**
	* Deletes the member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberId the primary key of the member
	* @return the member that was removed
	* @throws PortalException if a member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.jenginetest.builder.model.Member deleteMember(long memberId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.deleteMember(memberId);
	}

	/**
	* Deletes the member from the database. Also notifies the appropriate model listeners.
	*
	* @param member the member
	* @return the member that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.jenginetest.builder.model.Member deleteMember(
		com.jenginetest.builder.model.Member member)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.deleteMember(member);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _memberLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.jenginetest.builder.model.Member fetchMember(long memberId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.fetchMember(memberId);
	}

	/**
	* Returns the member with the primary key.
	*
	* @param memberId the primary key of the member
	* @return the member
	* @throws PortalException if a member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.jenginetest.builder.model.Member getMember(long memberId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.getMember(memberId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of members
	* @param end the upper bound of the range of members (not inclusive)
	* @return the range of members
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.jenginetest.builder.model.Member> getMembers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.getMembers(start, end);
	}

	/**
	* Returns the number of members.
	*
	* @return the number of members
	* @throws SystemException if a system exception occurred
	*/
	public int getMembersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.getMembersCount();
	}

	/**
	* Updates the member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param member the member
	* @return the member that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.jenginetest.builder.model.Member updateMember(
		com.jenginetest.builder.model.Member member)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.updateMember(member);
	}

	/**
	* Updates the member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param member the member
	* @param merge whether to merge the member with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the member that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.jenginetest.builder.model.Member updateMember(
		com.jenginetest.builder.model.Member member, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberLocalService.updateMember(member, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _memberLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_memberLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _memberLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MemberLocalService getWrappedMemberLocalService() {
		return _memberLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMemberLocalService(
		MemberLocalService memberLocalService) {
		_memberLocalService = memberLocalService;
	}

	public MemberLocalService getWrappedService() {
		return _memberLocalService;
	}

	public void setWrappedService(MemberLocalService memberLocalService) {
		_memberLocalService = memberLocalService;
	}

	private MemberLocalService _memberLocalService;
}