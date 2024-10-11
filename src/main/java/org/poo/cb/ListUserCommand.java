package org.poo.cb;

public class ListUserCommand extends Command {
    final private String email;

    public ListUserCommand(String email) {
        this.email = email;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);

        //  Check if the user exists
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }

        //  Create friends string
        String friends = "";
        for (int i = 0; i < user.getFriends().size(); i++) {
            friends += "\"" + user.getFriends().get(i).getEmail() + "\"";
            if (i != user.getFriends().size() - 1)
                friends += ",";
        }

        //  Display result
        System.out.println("{\"email\":\"" + this.email + "\",\"firstname\":\"" + user.getFirstName()
                + "\",\"lastname\":\"" + user.getLastName() + "\",\"address\":\"" + user.getAddress()
                + "\",\"friends\":[" + friends + "]}");
    }
}
