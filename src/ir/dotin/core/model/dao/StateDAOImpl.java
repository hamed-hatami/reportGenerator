package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.State;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 9/3/2016.
 */

@Stateless
@LocalBean
public class StateDAOImpl extends BaseDAOImpl<State> {

    public State findById(String id) {
        try {
            return (State) em.createNamedQuery("State.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String name) {
        try {
            List<State> stateList = (List<State>) em.createNamedQuery("State.exist")
                    .setParameter("name", name)
                    .getResultList();
            if (stateList.size()>0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }
}
