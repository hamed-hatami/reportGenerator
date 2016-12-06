package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.ActualCustomer;
import ir.dotin.core.model.entity.Interests;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Stateless
@LocalBean
public class InterestsDAOImpl extends BaseDAOImpl<Interests> {

    public Interests findById(String id) {
        try {
            return (Interests) em.createNamedQuery("Interests.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Interests> findParent() {
        try {
            return (List<Interests>) em.createNamedQuery("Interests.findParent")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Interests> actualCustomer(Long id) {
        try {
            return (List<Interests>) em.createNamedQuery("Interests.actualCustomer")
                    .setParameter("actualCustomerId", id)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
