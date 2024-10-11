package org.poo.cb;

import java.util.ArrayList;
import java.util.HashMap;

public class RecommendStocksCommand extends Command {
    public RecommendStocksCommand() {}

    public void execute(Bank bank) {
        //  Get the stock hashmap
        HashMap<String, Stock> stocks = bank.getStocks();
        ArrayList<String> stocksToBuy = getStringsForStocks(stocks);

        //  Build string with stocksToBuy
        String strStocks = "";
        for (int i = 0; i < stocksToBuy.size(); i++) {
            strStocks += "\"" + stocksToBuy.get(i) + "\"";

            if (i != stocksToBuy.size() - 1)
                strStocks += ",";
        }

        //  Display result
        System.out.println("{\"stocksToBuy\":[" + strStocks + "]}");
    }

    public static ArrayList<String> getStringsForStocks(HashMap<String, Stock> stocks) {
        ArrayList<String> stocksToBuy = new ArrayList<>();

        for (Stock st: stocks.values()) {
            //  Calculate short term SMA
            double SMA5 = 0;
            for (int i = 5; i < 10; i++)
                SMA5 += st.getLast10Values()[i];
            SMA5 /= 5d;

            //  Calculate the long term SMA
            double SMA10 = 0;
            for (int i = 0; i < 10; i++)
                SMA10 += st.getLast10Values()[i];
            SMA10 /= 10d;

            //  Check if the stock is recommended
            if (SMA5 > SMA10)
                stocksToBuy.add(st.getName());
        }
        return stocksToBuy;
    }
}
