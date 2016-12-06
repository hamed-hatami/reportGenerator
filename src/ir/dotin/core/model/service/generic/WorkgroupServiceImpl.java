package ir.dotin.core.model.service.generic;


import ir.dotin.core.model.dao.generic.WorkgroupDAOImpl;
import ir.dotin.core.model.entity.generic.Workgroup;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@LocalBean
public class WorkgroupServiceImpl<T extends Workgroup> {

    @EJB
    private WorkgroupDAOImpl workgroupDAO;
    @EJB
    private UserServiceImpl userService;


    public T findById(String id) {
        try {
            return (T) workgroupDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findByRoleId(String roleId) {
        try {
            return (List<T>) workgroupDAO.findByRoleId(roleId);
        } catch (Exception e) {
            return null;
        }
    }


    public List<T> getAllWorkGroup() {
        try {
            return (List<T>) workgroupDAO.findAll("Workgroup.list", true);
        } catch (Exception e) {
            return null;
        }
    }


    public boolean deleteWorkGroup(T entity) {
        try {
            return workgroupDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createWorkGroup(T entity) {
        try {
            return (T) workgroupDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editWorkGroup(T entity) {
        try {
            return (T) workgroupDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findWorkGroupByOrgId(String orgId) {
        try {
            return (List<T>) workgroupDAO.findWorkGroupByOrgId(orgId);
        } catch (Exception e) {
            return null;
        }
    }


}