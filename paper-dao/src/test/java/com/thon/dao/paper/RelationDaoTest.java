package com.thon.dao.paper;

import com.thon.dao.system.UserDao;
import com.thon.entity.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * Created by Chen on 2016/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testContent-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
public class RelationDaoTest extends AbstractTransactionalJUnit4SpringContextTests {


}
