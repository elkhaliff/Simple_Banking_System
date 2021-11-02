package banking;

public class Login implements Command {
    private final DataBase dataBase;
    String pan;
    String pin;
    int id;
    private final Response response;

    private static final String ERROR_PAN_PIN = "Wrong card number or PIN!";
    private static final String SUCCESS_LOGIN = "You have successfully logged in!";

    public Login(DataBase dataBase, String pan, String pin) {
        this.dataBase = dataBase;
        this.pan = pan;
        this.pin = pin;
        response = new Response();
    }

    @Override
    public void execute() {
        id = dataBase.testCard(pan, pin);
        if (id != 0) {
            response.setResponse(SUCCESS_LOGIN);
            response.setId(id);
            response.setNoError(true);
        } else {
            response.setResponse(ERROR_PAN_PIN);
            response.setNoError(false);
        }
    }

    @Override
    public Response getResult() {
        return response;
    }
}
