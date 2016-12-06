package ir.dotin.core.model.dao.generic;


import ir.dotin.core.model.entity.generic.Role;
/*import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;*/

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;


@Stateless
@LocalBean
public class RoleDAOImpl extends BaseDAOImpl<Role> {

   /* @Resource
    private BundleContext bundleContext;*/

    public Role findById(String id) {
        try {
            return (Role) em.createNamedQuery("Role.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

   /* public Role findByName(String name) {
        try {
            ServiceReference<EntityManagerFactory> serviceReference = bundleContext.getServiceReference(EntityManagerFactory.class);
            EntityManagerFactory emf = bundleContext.getService(serviceReference);
            return (Role) em.createNamedQuery("Role.findByName")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }*/

}