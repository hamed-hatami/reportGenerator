package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.Address;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Stateless
@LocalBean
public class AddressDAOImpl extends BaseDAOImpl<Address> {

    public Address findById(String id) {
        try {
            return (Address) em.createNamedQuery("Address.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Address> findByCustomerId(Long id) {
        try {
            return (List<Address>) em.createNamedQuery("Address.findByCustomerId")
                    .setParameter("customerId", id)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String postalCode,Long customerId) {
        try {
            List<Address> baseInformationChildList = (List<Address>) em.createNamedQuery("Address.exist")
                    .setParameter("postalCode", postalCode)
                    .setParameter("customerId",customerId)
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
}
