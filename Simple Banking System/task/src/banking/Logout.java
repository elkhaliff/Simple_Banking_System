package banking;

public class Logout implements Command {
    private final DataBase dataBase;
    private final Response response;

    private static final String SUCCESS_LOGOUT = "You have successfully logged out!";

    public Logout(DataBase dataBase) {
        this.dataBase = dataBase;
        response = new Response();
    }

    @Override
    public void execute() {
        response.setResponse(SUCCESS_LOGOUT);
        response.setNoError(true);
    }

    @Override
    public Response getResult() {
        return response;
    }
}
