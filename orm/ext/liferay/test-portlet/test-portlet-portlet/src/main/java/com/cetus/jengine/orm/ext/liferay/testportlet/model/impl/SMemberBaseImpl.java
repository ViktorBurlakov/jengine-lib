package com.cetus.jengine.orm.ext.liferay.testportlet.model.impl;

import com.cetus.jengine.orm.ext.liferay.testportlet.model.SMember;
import com.cetus.jengine.orm.ext.liferay.testportlet.service.SMemberLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the SMember service. Represents a row in the &quot;SB_SMember&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SMemberImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SMemberImpl
 * @see com.cetus.jengine.orm.ext.liferay.testportlet.model.SMember
 * @generated
 */
public abstract class SMemberBaseImpl extends SMemberModelImpl
    implements SMember {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a s member model instance should use the {@link SMember} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            SMemberLocalServiceUtil.addSMember(this);
        } else {
            SMemberLocalServiceUtil.updateSMember(this);
        }
    }
}