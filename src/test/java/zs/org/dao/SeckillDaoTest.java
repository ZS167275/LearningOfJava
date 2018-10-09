package zs.org.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zs.org.enity.Seckill;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ZhangShan on 2018/10/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {
        seckillDao.reduceNumber(1000L,new Date());
    }

    @Test
    public void queryById() throws Exception {
        Long id = 1000L;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {

        List<Seckill> l = seckillDao.queryAll(0,100);
        for (Seckill a:l){
            System.out.println(a);
        }
    }

}