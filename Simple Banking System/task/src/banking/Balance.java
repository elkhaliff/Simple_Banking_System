package banking;

public class Balance implements Command {
    private final DataBase dataBase;
    int id;
    private final Response response;

    public Balance(DataBase dataBase, int id) {
        this.dataBase = dataBase;
        this.id = id;
        response = new Response();
    }

    @Override
    public void execute() {
        int balance = dataBase.getBalance(id);
        response.setResponse(String.valueOf(balance));
    }

    @Override
    public Response getResult() {
        return response;
    }
}
