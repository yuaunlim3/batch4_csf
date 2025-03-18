package vttp.batch4.csf.ecommerce.models.Exception;

public class FailedException extends RuntimeException {
    public FailedException(){
        super();
    }

    public FailedException(String msg){
        super(msg);
    }

    public FailedException(String msg,Throwable t){
        super(msg,t);
    }
}
