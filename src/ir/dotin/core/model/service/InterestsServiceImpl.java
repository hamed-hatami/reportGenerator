package ir.dotin.core.model.service;

import ir.dotin.core.model.dao.InterestsDAOImpl;
import ir.dotin.core.model.entity.ActualCustomer;
import ir.dotin.core.model.entity.Interests;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */

@Stateless
@LocalBean
public class InterestsServiceImpl<T extends Interests> {

    @EJB
    private InterestsDAOImpl interestsDAO;

    public T findById(String id) {
        try {
            return (T) interestsDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findParent() {
        try {
            return (List<T>) interestsDAO.findParent();
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllInterests() {
        try {
            return (List<T>) interestsDAO.findAll("Interests.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteInterests(T entity) {
        try {
            return interestsDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createInterests(T entity) {
        try {
            return (T) interestsDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editInterests(T entity) {
        try {
            return (T) interestsDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> actualCustomer(Long id) {
        try {
            return (List<T>) interestsDAO.actualCustomer(id);
        } catch (Exception e) {
            return null;
        }
    }

}
