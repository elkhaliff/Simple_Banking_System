package banking;

public class AddIncome implements Command {
    private final DataBase dataBase;
    int id;
    int balance;
    private final Response response;

    public AddIncome(DataBase dataBase, int id, int balance) {
        this.dataBase = dataBase;
        this.id = id;
        this.balance = balance;
        response = new Response();
    }

    @Override
    public void execute() {
        dataBase.addIncome(id, balance);
        response.setResponse("Income was added!");
    }

    @Override
    public Response getResult() {
        return response;
    }
}