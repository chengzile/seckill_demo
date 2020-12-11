package com.xiaocheng.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaocheng.seckill.entity.SuccessKilled;
import org.springframework.stereotype.Repository;


public interface SuccessKilledDao {
	public int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	public SuccessKilled queryByid(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
