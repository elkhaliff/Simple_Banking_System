package banking;

public class Balance implements Command {
    private final DataBase dataBase;

    public Balance(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void execute() {
        dataBase.balance();
    }

    @Override
    public Response getResult() {
        return dataBase.getOut();
    }
}
