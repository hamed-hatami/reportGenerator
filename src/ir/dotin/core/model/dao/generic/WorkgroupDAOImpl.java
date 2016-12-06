package ir.dotin.core.model.dao.generic;


import ir.dotin.core.model.entity.generic.Workgroup;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@LocalBean
public class WorkgroupDAOImpl extends BaseDAOImpl<Workgroup> {


    public Workgroup findById(String id) {
        try {
            return (Workgroup) em.createNamedQuery("Workgroup.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Workgroup> findWorkGroupByOrgId(String orgId) {
        try {
            return (List<Workgroup>) em.createNamedQuery("WorkGroup.findWorkGroupByOrgId")
                    .setParameter("orgId", orgId)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Workgroup> findByRoleId(String roleId) {
        try {
            return (List<Workgroup>) em.createNamedQuery("Workgroup.findByRoleId")
                    .setParameter("roleId", Long.valueOf(roleId))
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }


}