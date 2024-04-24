public class ExLeaveTakingRestiction extends Exception{
    public ExLeaveTakingRestiction() {super("The leave is invalid");}   
    public ExLeaveTakingRestiction(String message) {super(message);}
}

