package banking;

public class CloseAccount  implements Command {
    private final DataBase dataBase;
    int id;
    private final Response response;

    private static final String SUCCESS_CLOSE = "The account has been closed!";

    public CloseAccount(DataBase dataBase, int id) {
        this.dataBase = dataBase;
        this.id = id;
        response = new Response();
    }

    @Override
    public void execute() {
        dataBase.closeAccount(id);
        response.setResponse(SUCCESS_CLOSE);
        response.setNoError(true);
    }

    @Override
    public Response getResult() {
        return response;
    }
}
