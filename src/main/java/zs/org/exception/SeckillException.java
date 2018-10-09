package zs.org.exception;

/**
 * Created by ZhangShan on 2018/10/9.
 */
public class SeckillException extends RuntimeException {


    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
