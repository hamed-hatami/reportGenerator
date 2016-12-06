package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.City;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 9/3/2016.
 */

@Stateless
@LocalBean
public class CityDAOImpl extends BaseDAOImpl<City> {

    public City findById(String id) {
        try {
            return (City) em.createNamedQuery("City.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<City> findByStateId(String id) {
        try {
            return (List<City>) em.createNamedQuery("City.findByStateId")
                    .setParameter("stateId", Long.valueOf(id))
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            List<City> cityList = (List<City>) em.createNamedQuery("City.exist")
                    .setParameter("name",name )
                    .getResultList();
            if (cityList.size()>0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }
}
