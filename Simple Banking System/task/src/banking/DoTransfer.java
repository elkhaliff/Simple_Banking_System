package banking;

public class DoTransfer implements Command {
    private final DataBase dataBase;
    int id;
    int id_tr;
    int balance;
    int transfer;
    private final Response response;

    public DoTransfer(DataBase dataBase, int id) {
        this.dataBase = dataBase;
        this.id = id;
        response = new Response();
    }

    @Override
    public void execute() {
        String pan_tr = Utils.getString("Transfer\n" +
                                "Enter card number:");
        if (!Utils.isLuhn(pan_tr))
            response.setResponse("Probably you made a mistake in the card number. Please try again!");
        else {
            id_tr = dataBase.isExists(pan_tr);
            if (id_tr == 0)
                response.setResponse("Such a card does not exist.");
            else {
                balance = dataBase.getBalance(id);
                transfer = Utils.getInt("Enter how much money you want to transfer:");
                if (balance < transfer)
                    response.setResponse("Not enough money!");
                else {
                    dataBase.addIncome(id, (-1) * transfer);
                    dataBase.addIncome(id_tr, transfer);

                    response.setResponse("Success!");
                }
            }
        }
    }

    @Override
    public Response getResult() {
        return response;
    }
}