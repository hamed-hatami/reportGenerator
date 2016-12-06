package ir.dotin.core.model.service;

import ir.dotin.core.model.dao.BaseInformationChildDAOImpl;
import ir.dotin.core.model.entity.BaseInformationChild;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Mr_safiary on 10/5/2016.
 */
@Stateless
@LocalBean
public class BaseInformationChildServiceImpl<T extends BaseInformationChild> {

    @EJB
    private BaseInformationChildDAOImpl baseInformationChildDAO;

    public T findById(String id) {
        try {
            return (T) baseInformationChildDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllBaseInformationChild() {
        try {
            return (List<T>) baseInformationChildDAO.findAll("BaseInformationChild.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteBaseInformationChild(T entity) {
        try {
            return baseInformationChildDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createBaseInformationChilds(T entity) {
        try {
            return (T) baseInformationChildDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editBaseInformationChild(T entity) {
        try {
            return (T) baseInformationChildDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name,Long parentId) {
        try {
            return baseInformationChildDAO.exist(name,parentId);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean existwitoutParent(String name) {
        try {
            return baseInformationChildDAO.existwitoutParent(name);
        } catch (Exception e) {
            return false;
        }
    }

    public List<T> findByParentName(String parentName) {
        try {
            return (List<T>) baseInformationChildDAO.findByParentName(parentName);
        } catch (Exception e) {
            return null;
        }
    }

}
