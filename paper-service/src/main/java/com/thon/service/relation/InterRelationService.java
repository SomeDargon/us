package com.thon.service.relation;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.relation.InterRelationDao;
import com.thon.entity.relation.InterRelation;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 57 on 2017/3/8.
 */
@Service
@Transactional
public class InterRelationService {
    @Autowired
    private InterRelationDao interRelationDao;
    public Page<InterRelation> find(Page<InterRelation> page, InterRelation relation) {
        DetachedCriteria dc = interRelationDao.createDetachedCriteria();
        if (StringUtils.isNotEmpty(relation.getName())){
            dc.add(Restrictions.like("name", "%"+relation.getName()+"%"));
        }
        if (relation.getuId()!=null){
            dc.add(Restrictions.eq("id", "%"+relation.getName()+"%"));
        }
        dc.add(Restrictions.eq(relation.DEL_FLAG, relation.DEL_FLAG_NORMAL));
        dc.addOrder(Order.desc("insertTime"));
        return interRelationDao.find(page, dc);
    }

    public InterRelation findByIdRelation(int id){
        return interRelationDao.get(id);
    }

    public void saveRelation(InterRelation relation){
            interRelationDao.save(relation);
    }

    public void delRelation(InterRelation relation){
        interRelationDao.delete(relation);
    }
}
