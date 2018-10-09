
create database seckill;

use seckill;

CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品id',
  NAME VARCHAR(100) NOT NULL COMMENT '商品名称',
  NUMBER INT NOT NULL COMMENT '商品数量',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY(seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = UTF8 COMMENT = '秒杀库存'


insert into
  seckill (name,numbers,start_time,end_time)
values
  ('100秒杀iphoneXR',100,'2018-10-03 00:00:00','2018-11-02 00:00:00'),
  ('200秒杀iphoneXR',200,'2018-10-03 00:00:00','2018-11-02 00:00:00'),
  ('300秒杀iphoneXR',300,'2018-10-03 00:00:00','2018-11-02 00:00:00'),
  ('400秒杀iphoneXR',400,'2018-10-03 00:00:00','2018-11-02 00:00:00'),
  ('500秒杀iphoneXR',500,'2018-10-03 00:00:00','2018-11-02 00:00:00');


-- 秒杀成功明细表
-- 用户基本登陆信息

CREATE TABLE success_killed(
  seckill_id BIGINT NOT NULL COMMENT '商品id',
  user_phone BIGINT NOT NULL COMMENT '用户手机号',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY(seckill_id, user_phone),
KEY idx_create_time(create_time)
) ENGINE = InnoDb DEFAULT CHARSET = UTF8 COMMENT = '秒杀成功明细表'
