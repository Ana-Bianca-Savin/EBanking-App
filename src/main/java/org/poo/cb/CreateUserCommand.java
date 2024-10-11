package org.poo.cb;

public class CreateUserCommand extends Command {
    final private String email;
    final private String firstName;
    final private String lastName;
    final private String address;

    public CreateUserCommand(String email, String firstName, String lastName, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address.substring(1);
    }

    public void execute(Bank bank) {
        User user = new User(this.email, this.firstName, this.lastName, this.address);
        if (bank.getUsers().containsKey(this.email))
            System.out.println("User with " + this.email + " already exists");
        else
            bank.addUser(user);
    }
}
