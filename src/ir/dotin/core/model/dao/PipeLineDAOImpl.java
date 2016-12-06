package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.PipeLine;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Stateless
@LocalBean
public class PipeLineDAOImpl extends BaseDAOImpl<PipeLine> {

    public PipeLine findById(String id) {
        try {
            return (PipeLine) em.createNamedQuery("PipeLine.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
