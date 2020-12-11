CREATE DATABASE seckill;
use seckill;
CREATE TABLE seckill(
seckill_id BIGINT NOT NULL auto_increment COMMENT '秒杀库存id',
name VARCHAR(20) COMMENT '秒杀商品名',
number INT COMMENT '秒杀库存数量',
strart_time TIMESTAMP COMMENT '秒杀开始时间',
end_time TIMESTAMP COMMENT '秒杀结束时间',
create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀创建时间',
PRIMARY KEY(seckill_id)
)COMMENT='秒杀库存表';
ALTER TABLE seckill AUTO_INCREMENT = 1000;

CREATE TABLE success_killed(
seckill_id BIGINT COMMENT '秒杀库存id',
user_phone BIGINT COMMENT '秒杀人的电话',
state INT DEFAULT -1 COMMENT '订单状态 -1为无效 0 为成功 1为已支付 2 为已发货 3 为已完成',
create_time TIMESTAMP COMMENT '秒杀订单创建时间',
PRIMARY KEY(seckill_id,user_phone)
)COMMENT '秒杀成功订单表';




INSERT INTO `seckill`.`seckill` ( `name`, `number`, `strart_time`, `end_time`, `create_time`) VALUES ( '100秒杀iphone6', '100', '2020-04-01 22:52:35', '2020-04-11 22:52:41', '2020-04-08 22:52:47');
INSERT INTO `seckill`.`seckill` ( `name`, `number`, `strart_time`, `end_time`, `create_time`) VALUES ( '200秒杀ipad2', '200', '2020-04-01 22:52:35', '2020-04-11 22:52:41', '2020-04-08 22:52:47');
INSERT INTO `seckill`.`seckill` ( `name`, `number`, `strart_time`, `end_time`, `create_time`) VALUES ( '300秒杀iphone6s', '300', '2020-04-01 22:52:35', '2020-04-11 22:52:41', '2020-04-08 22:52:47');
INSERT INTO `seckill`.`seckill` ( `name`, `number`, `strart_time`, `end_time`, `create_time`) VALUES ( '400秒杀红米3', '400', '2020-04-01 22:52:35', '2020-04-11 22:52:41', '2020-04-08 22:52:47');
INSERT INTO `seckill`.`seckill` ( `name`, `number`, `strart_time`, `end_time`, `create_time`) VALUES ( '500秒杀小米3', '500', '2020-04-01 22:52:35', '2020-04-11 22:52:41', '2020-04-08 22:52:47');
INSERT INTO `seckill`.`seckill` ( `name`, `number`, `strart_time`, `end_time`, `create_time`) VALUES ( '600秒杀ipadmini', '600', '2020-04-01 22:52:35', '2020-04-11 22:52:41', '2020-04-08 22:52:47');
