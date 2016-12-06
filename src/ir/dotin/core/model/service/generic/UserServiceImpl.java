package ir.dotin.core.model.service.generic;


import ir.dotin.core.model.dao.generic.UserDAOImpl;
import ir.dotin.core.model.entity.generic.User;

import javax.ejb.*;
import java.util.List;


@Stateless
@LocalBean
public class UserServiceImpl<T extends User> {

    @EJB
    private UserDAOImpl userDAO;
    @EJB
    private RoleServiceImpl roleService;
    @EJB
    private WorkgroupServiceImpl workgroupService;

    public boolean exist(String username) {
        try {
            return userDAO.exist(username);
        } catch (Exception e) {
            return false;
        }
    }


    public T authenticate(String username, String password) {
        try {
            return (T) userDAO.authenticate(username, password);
        } catch (Exception e) {
            return null;
        }
    }


    public T findById(String id) {
        try {
            return (T) userDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public T findByUsername(String user) {
        try {
            return (T) userDAO.findByUsername(user);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findAllByUsername(User user) {
        try {
            return (List<T>) userDAO.findAllByUsername(user.getUsername());
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findByPerson(Long personId) {
        try {
            return (List<T>) userDAO.findByPerson(personId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findByWorkGroup(Long workGroupId) {
        try {
            return (List<T>) userDAO.findByWorkGroup(workGroupId);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean modifyPassword(T entity) {
        try {
            userDAO.update(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<T> getAllUser() {
        try {
            return (List<T>) userDAO.findAll("User.list", true);
        } catch (Exception e) {
            return null;
        }
    }



    public List<T> getAllPendingUser() {
        try {
            return (List<T>) userDAO.findAll("User.pendingList", true);
        } catch (Exception e) {
            return null;
        }
    }

    public T createUser(T entity) {
        try {
            return (T) userDAO.create(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public User create(T user) {
        try {
             return createUser(user);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteUser(T entity) {
        try {
           return userDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public User editUser(T entity) {
        try {
            return userDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

}