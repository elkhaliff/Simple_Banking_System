package banking;

interface Command {

    void execute();

    Response getResult();
}