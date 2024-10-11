package org.poo.cb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }

        //  Initialize bank's instance
        Bank bank = Bank.Instance();
        bank.InitializeExchangeRates("src/main/resources/" + args[0]);
        bank.InitializeStocks("src/main/resources/" + args[1]);

        //  Open the file containing commands
        File f = new File("src/main/resources/" + args[2]);
        if (!f.exists())
            System.out.println("File does not exist");

        try {
            //  Read the commands
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            //  Use bank's method to execute the corresponding command
            while ((line = br.readLine()) != null) {
                bank.callCommand(line);
            }

            fr.close();
            br.close();
        } catch (IOException ie) {
            throw new RuntimeException(ie);
        }

        //  Clear data
        bank.getUsers().clear();
        bank.getStocks().clear();
    }
}