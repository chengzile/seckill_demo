package com.xiaocheng.seckill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xiaocheng.seckill.dto.Exposer;
import com.xiaocheng.seckill.dto.SeckillExecution;
import com.xiaocheng.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaocheng.seckill.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SeckillServiceImplTest {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() {
		List<Seckill> seckills =seckillService.getSeckillList();
		logger.info("list={}",seckills);
	}

	@Test
	public void testGetSeckillById() {
		 Seckill seckill=seckillService.getSeckillById(1000);
		 logger.info("seckill={}",seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long id=1000;
		Exposer exposer=seckillService.exportSeckillUrl(id);
		logger.info("exposer={}",exposer);
	}

	@Test
	public void testExecuteSeckill() {
		String md5="652785cd5da374c9a9f48b5bf57285bb";
		long id=1000;
		long phone=13333333233L;
		SeckillExecution seckillExecution=seckillService.executeSeckill(id, phone, md5);
		logger.info("seckillExecution={}",seckillExecution);
	}

}
