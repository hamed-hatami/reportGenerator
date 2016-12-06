package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.Country;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 9/3/2016.
 */

@Stateless
@LocalBean
public class CountryDAOImpl extends BaseDAOImpl<Country> {

    public Country findById(String id) {
        try {
            return (Country) em.createNamedQuery("Country.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            List<Country> countryList = (List<Country>) em.createNamedQuery("Country.exist")
                    .setParameter("name", name)
                    .getResultList();
            if (countryList.size()>0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }
}
