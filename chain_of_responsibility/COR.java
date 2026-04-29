package chain_of_responsibility;

import chain_of_responsibility.handlers.*;

public class COR {
    public static void main(String[] args) {
        IMoneyHandler thousandHandler = new ThousandHandler(3);
        IMoneyHandler fiveHundredHandler = new FiveHundredHandler(5);
        IMoneyHandler twoHundredHandler = new TwoHundredHandler(10);
        IMoneyHandler hundredHandler = new HundredHandler(20);

        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(twoHundredHandler);
        twoHundredHandler.setNextHandler(hundredHandler);

        int amountToWithdraw = 5701;

        System.out.println("\nDispensing amount: ₹" + amountToWithdraw);
        thousandHandler.dispense(amountToWithdraw);
    }
}