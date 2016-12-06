package ir.dotin.core.model.service;



import ir.dotin.core.model.dao.CityDAOImpl;
import ir.dotin.core.model.entity.City;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 9/3/2016.
 */

@Stateless
@LocalBean
public class CityServiceImpl<T extends City> {

    @EJB
    private CityDAOImpl cityDAO;

    public T findById(String id) {
        try {
            return (T) cityDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findByStateId(String stateId) {
        try {
            return (List<T>) cityDAO.findByStateId(stateId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllCity() {
        try {
            return (List<T>) cityDAO.findAll("City.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteCity(T entity) {
        try {
            return cityDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createCity(T entity) {
        try {
            return (T) cityDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editCity(T entity) {
        try {
            return (T) cityDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            return cityDAO.exist(name);
        } catch (Exception e) {
            return false;
        }
    }

}
