package ir.dotin.core.model.dao;

import ir.dotin.core.model.dao.generic.BaseDAOImpl;
import ir.dotin.core.model.entity.BaseInformationChild;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by h.safyari on 10/3/2016.
 */
@Stateless
@LocalBean
public class BaseInformationChildDAOImpl extends BaseDAOImpl<BaseInformationChild> {

    public BaseInformationChild findById(String id) {
        try {
            return (BaseInformationChild) em.createNamedQuery("BaseInformationChild.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean exist(String englishName,Long parentId) {
        try {
            List<BaseInformationChild> baseInformationChildList = (List<BaseInformationChild>) em.createNamedQuery("BaseInformationChild.exist")
                    .setParameter("englishName", englishName)
                    .setParameter("parentId",parentId)
                    .getResultList();
            if (baseInformationChildList.size()>0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public boolean existwitoutParent(String englishName) {
        try {
            List<BaseInformationChild> baseInformationChildList = (List<BaseInformationChild>) em.createNamedQuery("BaseInformationChild.existwitoutParent")
                    .setParameter("englishName", englishName)
                    .getResultList();
            if (baseInformationChildList.size()>0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public List<BaseInformationChild> findByParentName(String parentName) {
        try {
            return (List<BaseInformationChild>) em.createNamedQuery("BaseInformationChild.findByParentName")
                    .setParameter("englishName", parentName)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
