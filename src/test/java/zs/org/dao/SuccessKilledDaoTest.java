package zs.org.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zs.org.enity.SuccessKilled;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by ZhangShan on 2018/10/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        successKilledDao.insertSuccessKilled(1000L,13114800086L);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1000L,13114800086L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}