package ir.dotin.core.model.service;

import ir.dotin.core.model.dao.PipeLineDAOImpl;
import ir.dotin.core.model.entity.PipeLine;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Stateless
@LocalBean
public class PipeLineServiceImpl<T extends PipeLine> {

    @EJB
    private PipeLineDAOImpl pipeLineDAO;

    public T findById(String id) {
        try {
            return (T) pipeLineDAO.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAllPipeLine() {
        try {
            return (List<T>) pipeLineDAO.findAll("PipeLine.list", true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deletePipeLine(T entity) {
        try {
            return pipeLineDAO.delete(entity);
        } catch (Exception e) {
            return false;
        }
    }


    public T createPipeLine(T entity) {
        try {
            return (T) pipeLineDAO.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public T editPipeLine(T entity) {
        try {
            return (T) pipeLineDAO.update(entity);
        } catch (Exception e) {
            return null;
        }
    }

}
