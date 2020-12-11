package com.xiaocheng.seckill.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaocheng.seckill.entity.Seckill;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SeckillDaoTest {

	@Autowired
	private SeckillDao seckillDao;
	
	@Test
	public void testreduceNumber(){
		System.out.println(seckillDao.reduceNumber(1000, new Date()));
	}

	@Test
	public void testQueryByid() {
		System.out.println(seckillDao.queryByid(1000));
		/*Seckill [seckillId=1000, name=100秒杀iphone6, number=100, strartTime=Sat Apr 01 22:52:35 CST 2017, endTime=Tue Apr 11 22:52:41 CST 2017, createTime=Sat Apr 08 22:52:47 CST 2017]*/
	}

	/*
	 * Caused by: org.apache.ibatis.binding.BindingException: Parameter 'offset' not found. Available parameters are [arg1, arg0, param1, param2]
	 * */
	@Test
	public void testQueryAll() {
		for (Seckill temp : seckillDao.queryAll(0, 100)) {
			System.out.println(temp);
			/**
			 * Seckill [seckillId=1000, name=100秒杀iphone6, number=100, strartTime=Sat Apr 01 22:52:35 CST 2017, endTime=Tue Apr 11 22:52:41 CST 2017, createTime=Sat Apr 08 22:52:47 CST 2017]
				Seckill [seckillId=1001, name=200秒杀ipad2, number=200, strartTime=Sat Apr 01 22:52:35 CST 2017, endTime=Tue Apr 11 22:52:41 CST 2017, createTime=Sat Apr 08 22:52:47 CST 2017]
				Seckill [seckillId=1002, name=300秒杀iphone6s, number=300, strartTime=Sat Apr 01 22:52:35 CST 2017, endTime=Tue Apr 11 22:52:41 CST 2017, createTime=Sat Apr 08 22:52:47 CST 2017]
				Seckill [seckillId=1003, name=400秒杀红米3, number=400, strartTime=Sat Apr 01 22:52:35 CST 2017, endTime=Tue Apr 11 22:52:41 CST 2017, createTime=Sat Apr 08 22:52:47 CST 2017]
				Seckill [seckillId=1004, name=500秒杀小米3, number=500, strartTime=Sat Apr 01 22:52:35 CST 2017, endTime=Tue Apr 11 22:52:41 CST 2017, createTime=Sat Apr 08 22:52:47 CST 2017]
				Seckill [seckillId=1005, name=600秒杀ipadmini, number=600, strartTime=Sat Apr 01 22:52:35 CST 2017, endTime=Tue Apr 11 22:52:41 CST 2017, createTime=Sat Apr 08 22:52:47 CST 2017]
			 * 
			 * */
		}
	}

}
