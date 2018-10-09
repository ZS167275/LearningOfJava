package zs.org.exception;

/**
 * Created by ZhangShan on 2018/10/9.
 */
public class RepeatKillException extends SeckillException {


    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
