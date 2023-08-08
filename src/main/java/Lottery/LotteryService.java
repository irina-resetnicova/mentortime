package Lottery;

import java.util.HashMap;
import java.util.Map;

public class LotteryService extends LotteryAbstractService {

    public boolean purchaseTicket(User user, long countOfTickets) {
        if (countOfTickets > tickets) {
            System.out.println("Chose less count of tickets");
            return false;
        }


        double userHasToPay = countOfTickets * ticketPrice;
        Discount userDiscount = getDiscountService().getDiscount(userHasToPay);
        if (userDiscount != null) {
            double userPersentage = userDiscount.getPercentage();
            userHasToPay = userPersentage - userHasToPay * userPersentage;

            Map<User, Discount> userDiscountH = new HashMap<>();
            userDiscountH.put(user, userDiscount);
            discountHistory.add(userDiscountH);
        }
        tickets = tickets - countOfTickets;

        debitCap += userHasToPay;

        if (getProcurementHistory().containsKey(user)) {
            long userTickets = getProcurementHistory().get(user);
            getProcurementHistory().put(user, countOfTickets + userTickets);

        } else {
            getProcurementHistory().put(user, countOfTickets);
        }
        return true;

    }
}
