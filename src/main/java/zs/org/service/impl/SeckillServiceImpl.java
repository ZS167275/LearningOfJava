package zs.org.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import zs.org.dao.SeckillDao;
import zs.org.dao.SuccessKilledDao;
import zs.org.dto.Exposer;
import zs.org.dto.SeckillExecution;
import zs.org.emums.SeckillStatEnum;
import zs.org.enity.Seckill;
import zs.org.enity.SuccessKilled;
import zs.org.exception.RepeatKillException;
import zs.org.exception.SeckillCloseException;
import zs.org.exception.SeckillException;
import zs.org.service.SeckillService;

import java.util.Date;
import java.util.List;


/**
 * Created by ZhangShan on 2018/10/9.
 */

@Service
public class SeckillServiceImpl implements SeckillService {

    private final String salt = "zszafafasdgadsfs/fasfafaf";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 注入serviec以来
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Override
    public List<Seckill> getSeckillList() {

        return seckillDao.queryAll(0,100);
    }

    @Override
    public Seckill getById(long seckillId) {

        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null){
            return new Exposer(false,seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endtTime = seckill.getEndTime();
        Date nowTime = new Date();

        if(nowTime.getTime()<startTime.getTime() || nowTime.getTime() > endtTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endtTime.getTime());
        }

        String md5 = getMD5(seckillId);
        return new Exposer(true,md5,seckillId);

    }


    private String getMD5(long seckillId){
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if(md5 ==null||!md5.equals(getMD5(seckillId))){
            throw new SeckillException("SECKILL DATA REWATE");
        }

        try {
            Date nowTime = new Date();
            // 减库存表
            int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
            if(updateCount<=0){
                throw new SeckillCloseException("seckill close");
            }else {
                // 更新秒杀成功表
                int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
                if(insertCount<=0){
                    throw new RepeatKillException("repeat");
                }else{
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    // 数据字典枚举表示
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS,successKilled);
                }
            }
        } catch(RepeatKillException | SeckillCloseException e1){
            throw e1;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            // 运行期异常转换
            throw new SeckillException("seckill error" + e.getMessage());
        }
    }

}
