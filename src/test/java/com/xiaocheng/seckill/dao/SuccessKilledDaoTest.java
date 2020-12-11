package com.xiaocheng.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SuccessKilledDaoTest {

	@Autowired
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled() {
		System.out.println(successKilledDao.insertSuccessKilled(1000, 13352461531L));
	}

	@Test
	public void testQueryByid() {
		System.out.println(successKilledDao.queryByid(1000, 13352461531L));
	}

}
