package ir.dotin.core.model.service;


import ir.dotin.core.model.dao.CountryDAOImpl;
import ir.dotin.core.model.entity.Country;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 9/3/2016.
 */

@Stateless
@LocalBean
public class CountryServiceImpl<T extends Country> {

    @EJB
    private CountryDAOImpl countryDAO;

    public T findById(String id) {
        try {
            return (T) countryDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllCountry() {
        try {
            return (List<T>) countryDAO.findAll("Country.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteCountry(T entity) {
        try {
            return countryDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createCountry(T entity) {
        try {
            return (T) countryDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editCountry(T entity) {
        try {
            return (T) countryDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            return countryDAO.exist(name);
        } catch (Exception e) {
            return false;
        }
    }

}
