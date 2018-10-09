package zs.org.enity;

import java.util.Date;

/**
 * Created by ZhangShan on 2018/10/2.
 */
public class SuccessKilled {

    private long seckillId;

    private long userPhone;

    private Date createTime;

    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "success_killed{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", createTime=" + createTime +
                '}';
    }
}
