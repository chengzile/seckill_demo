package com.xiaocheng.seckill.web;

import java.util.Date;
import java.util.List;

import com.xiaocheng.seckill.dto.Exposer;
import com.xiaocheng.seckill.dto.SeckillExecution;
import com.xiaocheng.seckill.dto.SeckillResult;
import com.xiaocheng.seckill.exception.RepeatKillException;
import com.xiaocheng.seckill.exception.SeckillCloseException;
import com.xiaocheng.seckill.exception.SeckillException;
import com.xiaocheng.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaocheng.seckill.entity.Seckill;
import com.xiaocheng.seckill.enums.SeckillStatEnum;

/**
 * Controller层
 * url:/模块/资源/{id}/细分
 * */

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model,Long seckillId){
		
		System.out.println(seckillId);
		
		List<Seckill> seckills =seckillService.getSeckillList();
		model.addAttribute("list", seckills);
		
		return "list";
	}
	
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId")Long seckillId,Model model){
		if (seckillId==null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill=seckillService.getSeckillById(seckillId);
		if (seckill==null) {
			return "redirect:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public @ResponseBody SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId){//TODO
		SeckillResult<Exposer> result;
		try {
			Exposer exposer=seckillService.exportSeckillUrl(seckillId);
			result=new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result=new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
	} 
	
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public @ResponseBody SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId,
                                                                 @PathVariable("md5")	 String md5,
                                                                 @CookieValue(value="killPhone",required=false)Long userPhone){//required=false如果不存在不报错
		
		try {
			if (userPhone==null) {
				throw new SeckillException("未注册,请输入手机号");
			}
			SeckillExecution seckillExecution=seckillService.executeSeckill(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		}catch (SeckillCloseException e) {
			logger.error(e.getMessage());
			SeckillExecution seckillExecution=new SeckillExecution(seckillId,SeckillStatEnum.END);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		}catch (RepeatKillException e) {
			logger.error(e.getMessage());
			SeckillExecution seckillExecution=new SeckillExecution(seckillId,SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		} catch (Exception e) {
			logger.error(e.getMessage());
			SeckillExecution seckillExecution=new SeckillExecution(seckillId,SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		}
	} 
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	public @ResponseBody SeckillResult<Long> time(){
		Long time=new Date().getTime();
		return new  SeckillResult<Long>(true,time);
	}
}
