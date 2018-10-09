package zs.org.dao;


import org.apache.ibatis.annotations.Param;
import zs.org.enity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by ZhangShan on 2018/10/2.
 */
public interface SeckillDao {

    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(@Param("offset") long offset, @Param("limit") int limit);

}
