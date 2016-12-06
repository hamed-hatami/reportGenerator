package ir.dotin.core.model.dao.generic;


import ir.dotin.core.model.entity.generic.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@LocalBean
public class UserDAOImpl extends BaseDAOImpl<User> {

    public boolean exist(String username) {
        try {
            List<User> user = (List<User>) em.createNamedQuery("User.exist")
                    .setParameter("username", username)
                    .getResultList();
            if (user.size() != 0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public User authenticate(String username, String password) {
        try {
            return (User) em.createNamedQuery("User.findByUsernameAndPassword")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findById(String id) {
        try {
            return (User) em.createNamedQuery("User.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findByUsername(String username) {
        try {
            return (User) em.createNamedQuery("User.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findAllByUsername(String username) {
        try {
            return (List<User>) em.createNamedQuery("User.findByUsername")
                    .setParameter("username", username)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findByPerson(Long personId) {
        try {
            return (List<User>) em.createNamedQuery("User.findById")
                    .setParameter("id", personId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findByWorkGroup(Long workGroupId) {
        try {
            return (List<User>) em.createNamedQuery("User.findByWorkGroupId")
                    .setParameter("workGroupId", workGroupId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}