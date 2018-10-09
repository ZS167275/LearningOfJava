package zs.org.dao;

import org.apache.ibatis.annotations.Param;
import zs.org.enity.SuccessKilled;

/**
 * Created by ZhangShan on 2018/10/2.
 */

public interface SuccessKilledDao {

    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

}
