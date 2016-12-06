package ir.dotin.core.model.service;



import ir.dotin.core.model.dao.ActualCustomerDAOImpl;
import ir.dotin.core.model.entity.ActualCustomer;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Stateless
@LocalBean
public class ActualCustomerServiceImpl<T extends ActualCustomer> {

    @EJB
    private ActualCustomerDAOImpl actualCustomerDAO;

    public T findById(String id) {
        try {
            return (T) actualCustomerDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public T findByNationalCode(String id) {
        try {
            return (T) actualCustomerDAO.findByNationalCode(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllActualCustomer() {
        try {
            return (List<T>) actualCustomerDAO.findAll("ActualCustomer.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteActualCustomer(T entity) {
        try {
            return actualCustomerDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createActualCustomer(T entity) {
        try {
            return (T) actualCustomerDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editActualCustomer(T entity) {
        try {

            return (T) actualCustomerDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            return actualCustomerDAO.exist(name);
        } catch (Exception e) {
            return false;
        }
    }

    public List<T> search(List<String> fields) {
        try {
            StringBuffer customeQuery = new StringBuffer("select * from tb_ActualCustomer where 1=1");
            for (String field : fields) {
                if (!field.split(",", -1)[1].isEmpty()) {
                    String[] record = field.split(",");
                    customeQuery.append(" and " + record[0] + "='" + record[1] + "'");
                }
            }
            return (List<T>) actualCustomerDAO.search(customeQuery.toString(), ActualCustomer.class);
        } catch (Exception e) {
            return null;
        }
    }
}
