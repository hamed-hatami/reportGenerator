package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.BaseInformationParent;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 10/3/2016.
 */

@Stateless
@LocalBean
public class BaseInformationParentDAOImpl extends BaseDAOImpl<BaseInformationParent> {

    public BaseInformationParent findById(String id) {
        try {
            return (BaseInformationParent) em.createNamedQuery("BaseInformationParent.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String englishName) {
        try {
            List<BaseInformationParent> baseInformationParentList = (List<BaseInformationParent>) em.createNamedQuery("BaseInformationParent.exist")
                    .setParameter("englishName", englishName)
                    .getResultList();
            if (baseInformationParentList.size()>0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }
}
