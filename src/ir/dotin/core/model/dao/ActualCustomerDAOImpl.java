package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.ActualCustomer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */

@Stateless
@LocalBean
public class ActualCustomerDAOImpl extends BaseDAOImpl<ActualCustomer> {

    public ActualCustomer findById(String id) {
        try {
            return (ActualCustomer) em.createNamedQuery("ActualCustomer.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String nationalCode) {
        try {
            List<ActualCustomer> baseInformationChildList = (List<ActualCustomer>) em.createNamedQuery("ActualCustomer.exist")
                    .setParameter("nationalCode", nationalCode)
                    .getResultList();
            if (baseInformationChildList.size()>0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public List<ActualCustomer> findByNationalCode(String nationalCode) {
        try {
            return (List<ActualCustomer>) em.createNamedQuery("ActualCustomer.findByNationalCode")
                    .setParameter("nationalCode", nationalCode)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
