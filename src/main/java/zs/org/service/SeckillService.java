package zs.org.service;

import zs.org.dto.Exposer;
import zs.org.dto.SeckillExecution;
import zs.org.enity.Seckill;
import zs.org.exception.RepeatKillException;
import zs.org.exception.SeckillCloseException;
import zs.org.exception.SeckillException;

import java.util.List;

/**
 * 方法定粒度,参数,返回类型（return 类型/异常）
 * Created by ZhangShan on 2018/10/9.
 */
public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    /* 秒杀开始秒杀接口的地址,否则输出系统时间 */
    Exposer exportSeckillUrl(long seckillId);

    /*执行秒杀操作*/
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

}
