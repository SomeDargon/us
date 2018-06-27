package com.thon.service.post;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.post.AdvertiseDao;
import com.thon.entity.post.Advertise;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 57 on 2016/1/27.
 */
@Service
@Transactional
public class AdvertiseService {

    @Autowired
    private AdvertiseDao advertiseDao;


    public Advertise getAdvertise(int id){
        return advertiseDao.get(id);
    }

    //save
    public void saveAdvertise(Advertise advertise){


        advertiseDao.save(advertise);
    }

    //delete
    public void deleteAdvertise(Advertise advertise){
        advertiseDao.delete(advertise);
    }

    //find
    public Page<Advertise> findAdvertises(Page<Advertise> page, Advertise advertise){
        DetachedCriteria dc = advertiseDao.createDetachedCriteria();

        if(StringUtils.isNotBlank(advertise.getTitle())){
            dc.add(Restrictions.like("title", "%"+advertise.getTitle()+"%"));
        }
//        if(advertise.getType() != null){
//            dc.add(Restrictions.eq("type", advertise.getType()));
//        }
//        if (StringUtils.isNotEmpty(advertise.getType())){
//            String[] params = advertise.getType().split(",");
//            dc.add(
//                    Restrictions.or(Restrictions.in("type", params),
//                            Restrictions.or(Restrictions.eq("type",advertise.getType())))
//            );
//
//        }

        if(StringUtils.isNotBlank(advertise.getDescription())){
            dc.add(Restrictions.like("description", "%"+advertise.getDescription()+"%"));
        }
        if(advertise.getStatus() != null){
            dc.add(Restrictions.eq("status", advertise.getStatus()));
        }
        if(advertise.getRecommend() != null){
            dc.add(Restrictions.eq("recommend", advertise.getRecommend()));
        }
        if(StringUtils.isNotBlank(advertise.getSource())){
            dc.add(Restrictions.eq("source", advertise.getSource()));
        }
        if(advertise.getId() != null){
            dc.add(Restrictions.eq("id", advertise.getId()));
        }
        dc.addOrder(Order.asc("id"));
        return advertiseDao.find(page, dc);
    }

}
