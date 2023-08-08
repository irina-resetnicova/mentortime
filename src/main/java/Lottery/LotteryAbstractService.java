package Lottery;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class LotteryAbstractService {

    protected static double debitCap;
    protected static long tickets;
    protected static double ticketPrice;

    private Map<User, Long> procurementHistory;

    private DiscountService discountService;

    protected List<Map<User, Discount>> discountHistory = new ArrayList<>();

    void newDiscountHistory(){

//        discountHistory.addAll(procurementHistory);


    }



    static {
        debitCap = 10000;
        tickets = 5000;
        ticketPrice = 5;
    }

    public LotteryAbstractService() {
        procurementHistory = new HashMap<>();
        discountService = new DiscountService();
        discountHistory = new ArrayList<>();

//        Function<String, Long> a = new Function<String, Long>() {
//            @Override
//            public Long apply(String s) {
//                return Long.getLong(s);
//            }
//        };
//
//        System.out.println(a.apply("34"));
//
//        Function<String, Long> stringLongFunction = (String s) -> Long.valueOf(s);
//
//        System.out.println(stringLongFunction.apply("34"));
//
//        } 
    }

    public abstract boolean purchaseTicket(User user, long countOfTickets);

}
