package zs.org.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zs.org.dto.Exposer;
import zs.org.dto.SeckillExecution;
import zs.org.exception.RepeatKillException;
import zs.org.exception.SeckillCloseException;
import zs.org.exception.SeckillException;
import zs.org.service.SeckillService;

/**
 * Created by ZhangShan on 2018/10/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                    "classpath:spring/spring-service.xml"})

public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        seckillService.getSeckillList();
    }


    @Test
    public void getById() throws Exception {
        seckillService.getById(1000L);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = seckillService.exportSeckillUrl(1004L);
        logger.info(String.valueOf(exposer));
    }

    @Test
    public void executeSeckill() throws Exception {

        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(1004L, 13114800086L, "6df46d9f5aa906319824b6d1d9f9442e");
            logger.info("list={}",seckillExecution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillException e1){
            logger.error(e1.getMessage());
        }

    }

}