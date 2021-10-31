package banking;

public class CreatePAN implements Command {
    private final DataBase dataBase;

    public CreatePAN(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void execute() {
        dataBase.createPAN();
    }

    @Override
    public Response getResult() {
        return dataBase.getOut();
    }
}