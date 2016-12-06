package ir.dotin.core.model.service;

import ir.dotin.core.model.dao.BaseInformationParentDAOImpl;
import ir.dotin.core.model.entity.BaseInformationParent;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Mr_safiary on 10/12/2016.
 */
@Stateless
@LocalBean
public class BaseInformationParentServiceImpl<T extends BaseInformationParent> {

    @EJB
    private BaseInformationParentDAOImpl baseInformationParentDAO;

    public T findById(String id) {
        try {
            return (T) baseInformationParentDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllBaseInformationParent() {
        try {
            return (List<T>) baseInformationParentDAO.findAll("BaseInformationParent.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteBaseInformationParent(T entity) {
        try {
            return baseInformationParentDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createBaseInformationParent(T entity) {
        try {
            return (T) baseInformationParentDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editBaseInformationParent(T entity) {
        try {
            return (T) baseInformationParentDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            return baseInformationParentDAO.exist(name);
        } catch (Exception e) {
            return false;
        }
    }
}
