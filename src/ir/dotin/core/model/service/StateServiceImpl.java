package ir.dotin.core.model.service;



import ir.dotin.core.model.dao.StateDAOImpl;
import ir.dotin.core.model.entity.State;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 9/3/2016.
 */
@Stateless
@LocalBean
public class StateServiceImpl<T extends State> {

    @EJB
    private StateDAOImpl stateDAO;

    public T findById(String id) {
        try {
            return (T) stateDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllState() {
        try {
            return (List<T>) stateDAO.findAll("State.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteState(T entity) {
        try {
            return stateDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createState(T entity) {
        try {
            return (T) stateDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editState(T entity) {
        try {
            return (T) stateDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            return stateDAO.exist(name);
        } catch (Exception e) {
            return false;
        }
    }

}
