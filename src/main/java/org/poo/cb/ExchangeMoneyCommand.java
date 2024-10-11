package org.poo.cb;

public class ExchangeMoneyCommand extends Command {
    final private String email;
    final private Currency sourceCurrency;
    final private Currency destinationCurrency;
    final private double amount;

    public ExchangeMoneyCommand(String email, Currency sourceCurrency, Currency destinationCurrency, double amount) {
        this.email = email;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.amount = amount;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);

        //  Check if the user exists
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }

        //  Get the source account
        Account accSource = user.getPortfolio().getAccounts().get(sourceCurrency);

        //  Get the current exchange rate
        double currentExchangeRate = bank.getExchangeRates().get(destinationCurrency).get(sourceCurrency);

        //  Check if 1% tax needs to be applied and decide what strategy will be used
        StatusStrategy statusStrategy;
        if (user.getHasPremium()) {
            statusStrategy = new PremiumStrategy();
        } else {
            if (amount * currentExchangeRate > accSource.getAmount() / 2d) {
                statusStrategy = new NonPremiumStrategy();
            } else {
                statusStrategy = new PremiumStrategy();
            }
        }

        //  Extract the money from the source account if there are enough funds
        boolean exchangedSuccessfully = statusStrategy.exchange(accSource, amount * currentExchangeRate);
        if (!exchangedSuccessfully) {
            System.out.println("Insufficient amount in account " + this.sourceCurrency + " for exchange");
            return;
        }

        //  Add the amount of money multiplied by exchange rate to destination account
        Account accDest = user.getPortfolio().getAccounts().get(destinationCurrency);

        boolean addedMoneySuccessfully = accDest.add(amount);
        if (!addedMoneySuccessfully)
            System.out.println("You can't add a negative amount of money");
    }
}
