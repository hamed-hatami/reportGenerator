package ir.dotin.core.model.service;

import ir.dotin.core.model.dao.AddressDAOImpl;
import ir.dotin.core.model.entity.Address;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */

@Stateless
@LocalBean
public class AddressServiceImpl<T extends Address> {

    @EJB
    private AddressDAOImpl addressDAO;

    public T findById(String id) {
        try {
            return (T) addressDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findByCustomerId(Long id) {
        try {
            return (List<T>) addressDAO.findByCustomerId(id);
        } catch (Exception e) {
            return null;
        }
    }


    public List<T> getAllAddress() {
        try {
            return (List<T>) addressDAO.findAll("Address.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteAddress(T entity) {
        try {
            return addressDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createAddress(T entity) {
        try {
            return (T) addressDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editAddress(T entity) {
        try {
            return (T) addressDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String postalCode,Long customerId) {
        try {
            return addressDAO.exist(postalCode,customerId);
        } catch (Exception e) {
            return false;
        }
    }
}
