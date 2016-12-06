package ir.dotin.core.model.service;

import ir.dotin.core.model.dao.DemographyDAOImpl;
import ir.dotin.core.model.entity.Demography;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Stateless
@LocalBean
public class DemographyServiceImpl<T extends Demography> {

    @EJB
    private DemographyDAOImpl demographyDAO;

    public T findById(String id) {
        try {
            return (T) demographyDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllDemography() {
        try {
            return (List<T>) demographyDAO.findAll("Demography.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteDemography(T entity) {
        try {
            return demographyDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createDemography(T entity) {
        try {
            return (T) demographyDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editDemography(T entity) {
        try {
            return (T) demographyDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

}
