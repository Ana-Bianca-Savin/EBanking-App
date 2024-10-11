package org.poo.cb;

public class AddFriendCommand extends Command {
    final private String emailUser;
    final private String emailFriend;

    public AddFriendCommand(String emailUser, String emailFriend) {
        this.emailUser = emailUser;
        this.emailFriend = emailFriend;
    }

    public void execute(Bank bank) {
        //  Get users for the emails
        User user = bank.getUsers().get(emailUser);
        User friend = bank.getUsers().get(emailFriend);

        //  Check if both the account exist
        if (user == null) {
            System.out.println("User with " + this.emailUser + " doesn't exist");
            return;
        }
        if (friend == null) {
            System.out.println("User with " + this.emailFriend + " doesn't exist");
            return;
        }

        //  Check if the users are already friends
        for (User u: user.getFriends())
            if (u.getEmail().equals(this.emailFriend)) {
                System.out.println("User with " + this.emailFriend + " is already a friend");
                return;
            }

        //  Add the friend
        user.getFriends().add(friend);
        friend.getFriends().add(user);
    }
}
