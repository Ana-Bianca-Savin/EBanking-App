package org.poo.cb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Bank {
    //  Unique instance
    private static Bank b;
    private Bank() {}

    //  Hashmap for having easy access to users
    private final HashMap<String, User> users = new HashMap<>();

    //  Linked hashmap with two dimensions for retaining exchange rates
    private final LinkedHashMap<Currency, LinkedHashMap<Currency, Double>> exchangeRates = new LinkedHashMap<Currency, LinkedHashMap<Currency, Double>>();

    //  Hashmap of all available stocks
    private final HashMap<String, Stock> stocks = new HashMap<String, Stock>();

    //  Unique instance initialization
    public static Bank Instance() {
        if (b == null)
            b = new Bank();
        return b;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public HashMap<String, Stock> getStocks() {
        return stocks;
    }

    public LinkedHashMap<Currency, LinkedHashMap<Currency, Double>> getExchangeRates() {
        return exchangeRates;
    }

    public void addUser(User user) {
        b.users.put(user.getEmail(), user);
    }

    public void callCommand(String line) {
        //  If a null line was passed
        if (line.isEmpty())
            return;

        //  Split the command and its parameters
        String[] split = line.split(" ");

        //  Get the command's name
        String commandName = split[0] + " " + split[1];

        switch (commandName) {
            case "CREATE USER":
                String address = "";
                for (int i = 5; i < split.length; i++)
                    address += " " + split[i];
                CreateUserCommand createUserCommand = new CreateUserCommand(split[2], split[3], split[4], address);
                createUserCommand.execute(b);
                break;
            case "ADD FRIEND":
                AddFriendCommand addFriendCommand = new AddFriendCommand(split[2], split[3]);
                addFriendCommand.execute(b);
                break;
            case "ADD ACCOUNT":
                AddAccountCommand addAccountCommand = new AddAccountCommand(split[2], Currency.valueOf(split[3]));
                addAccountCommand.execute(b);
                break;
            case "ADD MONEY":
                AddMoneyCommand addMoneyCommand = new AddMoneyCommand(split[2], Currency.valueOf(split[3]), Double.parseDouble(split[4]));
                addMoneyCommand.execute(b);
                break;
            case "EXCHANGE MONEY":
                ExchangeMoneyCommand exchangeMoneyCommand = new ExchangeMoneyCommand(split[2], Currency.valueOf(split[3]), Currency.valueOf(split[4]), Double.parseDouble(split[5]));
                exchangeMoneyCommand.execute(b);
                break;
            case "TRANSFER MONEY":
                TransferMoneyCommand transferMoneyCommand = new TransferMoneyCommand(split[2], split[3], Currency.valueOf(split[4]), Double.parseDouble(split[5]));
                transferMoneyCommand.execute(b);
                break;
            case "BUY STOCKS":
                BuyStocksCommand buyStocksCommand = new BuyStocksCommand(split[2], split[3], Integer.parseInt(split[4]));
                buyStocksCommand.execute(b);
                break;
            case "RECOMMEND STOCKS":
                RecommendStocksCommand recommendStocksCommand = new RecommendStocksCommand();
                recommendStocksCommand.execute(b);
                break;
            case "LIST USER":
                ListUserCommand listUserCommand = new ListUserCommand(split[2]);
                listUserCommand.execute(b);
                break;
            case "LIST PORTFOLIO":
                ListPortfolioCommand listPortfolioCommand = new ListPortfolioCommand(split[2]);
                listPortfolioCommand.execute(b);
                break;
            case "BUY PREMIUM":
                BuyPremiumCommand buyPremiumCommand = new BuyPremiumCommand(split[2]);
                buyPremiumCommand.execute(b);
                break;
        }
    }

    public void InitializeExchangeRates(String fileName) {
        //  Check if file exists
        File f = new File(fileName);
        if (!f.exists())
            System.out.println("File does not exist");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            boolean header = true;
            Currency[] currencies = new Currency[5];

            while ((line = br.readLine()) != null) {
                //  Split the comma separated values
                String[] split = line.split(",");

                if (header) {
                    //  Initialize the vector containing the current currencies
                    for (int i = 0; i < currencies.length; i ++)
                        currencies[i] = Currency.valueOf(split[i + 1]);
                    header = false;
                } else {
                    //  Hashmap containing all exchange rates for current currency
                    LinkedHashMap<Currency, Double> currentExchangeRate = new LinkedHashMap<>();
                    for (int i = 0; i < currencies.length; i++)
                        currentExchangeRate.put(currencies[i], Double.parseDouble(split[i + 1]));

                    //  Add the pair in the exchange rates hashmap
                    exchangeRates.put(Currency.valueOf(split[0]), currentExchangeRate);
                }
            }
        } catch (IOException ie) {
            throw new RuntimeException(ie);
        }
    }

    public void InitializeStocks(String fileName) {
        //  Check if file exists
        File f = new File(fileName);
        if (!f.exists())
            System.out.println("File does not exist");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                //  Split the comma separated values
                String[] split = line.split(",");

                if (header) {
                    header = false;
                    continue;
                }

                //  Add the stock
                double[] values = new double[10];
                for (int i = 0; i < 10; i++)
                    values[i] = Double.parseDouble(split[i + 1]);
                stocks.put(split[0], new Stock(split[0], values));
            }

        } catch (IOException ie) {
            throw new RuntimeException(ie);
        }
    }
}
