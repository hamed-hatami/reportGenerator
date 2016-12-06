package ir.dotin.core.model.service.generic;

import ir.dotin.core.model.dao.generic.RoleDAOImpl;
import ir.dotin.core.model.entity.generic.Role;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@LocalBean
public class RoleServiceImpl<T extends Role> {

    @EJB
    private RoleDAOImpl roleDAO;
    @EJB
    private WorkgroupServiceImpl workgroupService;


    public T findById(String id) {
        try {
            return (T) roleDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

   /* public T findByName(String name) {
        try {
            return (T) roleDAO.findByName(name);
        } catch (Exception e) {
            return null;
        }
    }*/


    public List<T> getAllRole() {
        try {

            return (List<T>) roleDAO.findAll("Role.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteRole(T entity) {
        try {
            roleDAO.delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public T createRole(T entity) {
        try {
            return (T) roleDAO.create(entity);
        } catch (Exception e) {
            return null;
        }
    }


    public T editRole(T entity) {
        try {
            return (T) roleDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

}
