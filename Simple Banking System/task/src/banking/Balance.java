package banking;

public class Balance implements Command {
    private final DataBase dataBase;
    String pan;
    String pin;
    private final Response response;

    public Balance(DataBase dataBase, String pan, String pin) {
        this.dataBase = dataBase;
        this.pan = pan;
        this.pin = pin;
        response = new Response();
    }

    @Override
    public void execute() {
        int balance = dataBase.getBalance(pan, pin);
        response.setResponse(String.valueOf(balance));
    }

    @Override
    public Response getResult() {
        return response;
    }
}
