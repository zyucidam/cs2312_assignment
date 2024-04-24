public class ExProjectAlreadyExist extends Exception{
    public ExProjectAlreadyExist() {super("Project already exists!");}
    public ExProjectAlreadyExist(String message) {super(message);}
}
