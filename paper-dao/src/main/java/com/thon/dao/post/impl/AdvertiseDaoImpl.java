package com.thon.dao.post.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.post.AdvertiseDao;
import com.thon.entity.post.Advertise;
import org.springframework.stereotype.Repository;

/**
 * Created by win on 2016/1/27.
 */
@Repository
public class AdvertiseDaoImpl extends BaseDaoImpl<Advertise> implements AdvertiseDao {

    public AdvertiseDaoImpl() {
        super(Advertise.class);
    }

}
