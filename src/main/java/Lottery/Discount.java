package Lottery;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Setter
@Getter
public class Discount extends LotteryAbstractService{

    private String purpose;
    private Double percentage;


    public Discount(String purpose, Double percentage) {
        this.purpose = purpose;
        this.percentage = percentage;
    }


    @Override
    public boolean purchaseTicket(User user, long countOfTickets) {
        return false;
    }

    List<Map<User, Discount>> discountHistory = new ArrayList<>();



}




