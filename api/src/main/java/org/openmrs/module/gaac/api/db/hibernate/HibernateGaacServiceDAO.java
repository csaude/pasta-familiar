/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.gaac.api.db.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.gaac.AffinityType;
import org.openmrs.module.gaac.Gaac;
import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.ReasonLeavingGaacType;
import org.openmrs.module.gaac.api.db.GaacServiceDAO;

/**
 * It is a default implementation of {@link GaacServiceDAO}.
 */
public class HibernateGaacServiceDAO implements GaacServiceDAO {
	protected final Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public AffinityType saveAffinityType(AffinityType affinityType) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(affinityType);
		return affinityType;
	}

	public void deleteAffinityType(AffinityType affinityType)
			throws DAOException {
		this.sessionFactory.getCurrentSession().delete(affinityType);
	}

	public AffinityType getAffinityType(Integer affinityId) throws DAOException {
		return (AffinityType) this.sessionFactory.getCurrentSession().get(
				AffinityType.class, affinityId);
	}

	public AffinityType getAffinityType(String name) throws DAOException {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(
				AffinityType.class);
		c.add(Expression.eq("retired", Boolean.valueOf(false)));
		c.add(Expression.eq("name", name));
		return (AffinityType) c.uniqueResult();
	}

	public AffinityType getAffinityByUUID(String uuid) throws DAOException {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(
				AffinityType.class);
		c.add(Expression.eq("retired", Boolean.valueOf(false)));
		c.add(Expression.eq("uuid", uuid));
		return (AffinityType) c.uniqueResult();
	}

	public List<AffinityType> getAllAffinityTypes(Boolean includeRetired)
			throws DAOException {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(
				AffinityType.class);
		c.addOrder(Order.asc("name"));
		if (!includeRetired.booleanValue())
			c.add(Expression.eq("retired", Boolean.valueOf(false)));
		return c.list();
	}

	public ReasonLeavingGaacType saveReasonLeavingGaacType(
			ReasonLeavingGaacType reasonLeavingGaacType) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(
				reasonLeavingGaacType);
		return reasonLeavingGaacType;
	}

	public void deleteReasonLeavingGaacType(
			ReasonLeavingGaacType reasonLeavingGaacType) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(reasonLeavingGaacType);
	}

	public ReasonLeavingGaacType getReasonLeavingGaacType(Integer reasonId)
			throws DAOException {
		return (ReasonLeavingGaacType) this.sessionFactory.getCurrentSession()
				.get(ReasonLeavingGaacType.class, reasonId);
	}

	public ReasonLeavingGaacType getReasonLeavingGaacType(String name)
			throws DAOException {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(
				ReasonLeavingGaacType.class);
		c.add(Expression.eq("retired", Boolean.valueOf(false)));
		c.add(Expression.eq("name", name));
		return (ReasonLeavingGaacType) c.uniqueResult();
	}

	public ReasonLeavingGaacType getReasonLeavingGaacTypeByUUID(String uuid)
			throws DAOException {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(
				ReasonLeavingGaacType.class);
		c.add(Expression.eq("retired", Boolean.valueOf(false)));
		c.add(Expression.eq("uuid", uuid));
		return (ReasonLeavingGaacType) c.uniqueResult();
	}

	public List<ReasonLeavingGaacType> getAllReasonLeavingGaacTypes(
			Boolean includeRetired) throws DAOException {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(
				ReasonLeavingGaacType.class);
		c.addOrder(Order.asc("name"));
		if (!includeRetired.booleanValue())
			c.add(Expression.eq("retired", Boolean.valueOf(false)));
		return c.list();
	}

	public Gaac saveGaac(Gaac gaac) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(gaac);
		return gaac;
	}

	public Gaac getGaac(Integer gaacId) throws DAOException {
		return (Gaac) this.sessionFactory.getCurrentSession().get(Gaac.class,
				gaacId);
	}

	public Gaac getGaacByName(String name) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		criteria.add(Expression.eq("name", name));
		return (Gaac) criteria.uniqueResult();
	}

	public Gaac getGaacByUUID(String uuid) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		criteria.add(Expression.eq("uuid", uuid));
		return (Gaac) criteria.uniqueResult();
	}

	public Gaac getGaacByIdentifier(String identifier) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		criteria.add(Expression.eq("gaacIdentifier", identifier));
		return (Gaac) criteria.uniqueResult();
	}

	public List<Gaac> getAllOfLocation(Location location) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		criteria.add(Expression.eq("location", location));
		return criteria.list();
	}

	public List<Gaac> getAllOfAffinity(AffinityType affinity)
			throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		criteria.add(Expression.eq("affinity", affinity));
		return criteria.list();
	}

	public List<Gaac> getAllGaac(Boolean includeVoided) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		if (!includeVoided.booleanValue())
			criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		return criteria.list();
	}

	public List<Gaac> getAllGaacEnrolled(Date startDate, Date endDate,
			Location location, AffinityType affinity) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));

		criteria.add(Expression.between("startDate", startDate, endDate));
		if (affinity != null)
			criteria.add(Expression.eq("affinity", affinity));
		if (location != null)
			criteria.add(Expression.eq("location", location));
		return criteria.list();
	}

	public void deleteGaac(Gaac gaac) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(gaac);
	}

	public GaacMember saveGaacMember(GaacMember member) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(member);
		return member;
	}

	public GaacMember getGaacMember(Integer gaacMemberId) throws DAOException {
		return (GaacMember) this.sessionFactory.getCurrentSession().get(
				GaacMember.class, gaacMemberId);
	}

	public GaacMember getGaacMemberByMember(Patient member) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(GaacMember.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		criteria.add(Expression.eq("member", member));
		return (GaacMember) criteria.uniqueResult();
	}
	
	//Damasceno
	public List<GaacMember> getAllGaacMemberHistory(Patient member) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(GaacMember.class);
		criteria.add(Expression.eq("member", member));
		return criteria.list();
	}
	public List<Gaac> getGaacByIdentifierAndLocation(String identifier,Location paramLocation) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Gaac.class);
		criteria.add(Expression.eq("voided", Boolean.valueOf(false)));
		criteria.add(Expression.eq("gaacIdentifier", identifier));
		criteria.add(Expression.eq("location", paramLocation));
		return criteria.list();
	}
	
}