package banking;

public class Login implements Command {
    private final DataBase dataBase;
    long pan;
    int pin;

    public Login(DataBase dataBase, long pan, int pin) {
        this.dataBase = dataBase;
        this.pan = pan;
        this.pin = pin;
    }

    @Override
    public void execute() {
        dataBase.login(pan, pin);
    }

    @Override
    public Response getResult() {
        return dataBase.getOut();
    }
}
