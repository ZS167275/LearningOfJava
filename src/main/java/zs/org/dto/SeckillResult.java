package zs.org.dto;

/**
 * Created by ZhangShan on 2018/10/9.
 */
public class SeckillResult<T> {

    private boolean suuccess;

    private T data;

    private String error;


    public SeckillResult(boolean suuccess, T data) {
        this.suuccess = suuccess;
        this.data = data;
    }

    public SeckillResult(boolean suuccess, String error) {
        this.suuccess = suuccess;
        this.error = error;
    }


    public boolean isSuuccess() {
        return suuccess;
    }

    public void setSuuccess(boolean suuccess) {
        this.suuccess = suuccess;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
