package com.xiaocheng.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.xiaocheng.seckill.entity.Seckill;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	
	private final JedisPool jedisPool;

	private String password;

	private int timeout;

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (null!=password||password.trim().length()==0){
			this.password=null;
		}
		this.password=password;
	}


	
	private RuntimeSchema<Seckill> schema=RuntimeSchema.createFrom(Seckill.class);
	
	public RedisDao(String ip,int port){
		//建议使用JedisPoolConfig代替并添加属性设置，我这是图方便
		jedisPool=new JedisPool(new GenericObjectPoolConfig(),ip,port,timeout,password);

	}
	
	public Seckill getSeckill(long seckillId){
		//readis 操作逻辑
		try {
			Jedis jedis=jedisPool.getResource();
			try {
				String key="seckill:"+seckillId;
				byte[] bytes=jedis.get(key.getBytes());
				if (bytes!=null) {//反序列化
					Seckill seckill=schema.newMessage();
					ProtobufIOUtil.mergeFrom(bytes, seckill, schema);
					return seckill;
				}
			}finally{
				if (jedis!=null) {
					jedis.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public String  putSeckill(Seckill seckill){
		try {
			Jedis jedis=jedisPool.getResource();
			try {
				String key="seckill:"+seckill.getSeckillId();
				int timeout=60*60;
				byte[] bytes=ProtobufIOUtil.toByteArray(seckill, schema,
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));//最后这个参数是缓存器
				
				String result=jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			}finally{
				if (jedis!=null) {
					jedis.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
