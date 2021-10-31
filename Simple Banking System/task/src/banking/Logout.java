package banking;

public class Logout implements Command {
    private final DataBase dataBase;
    long pan;
    int pin;

    public Logout(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void execute() {
        dataBase.logout();
    }

    @Override
    public Response getResult() {
        return dataBase.getOut();
    }
}
