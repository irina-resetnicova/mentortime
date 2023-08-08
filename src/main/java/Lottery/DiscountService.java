package Lottery;

import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    private List<Discount> discounts;

    public DiscountService() {

        discounts = new ArrayList<>();

        Discount discount100 = new Discount("100 amount", 2.0);
        Discount discount500 = new Discount("500 amount", 10.0);
        Discount discount1000 = new Discount("1000 amount", 30.0);

        discounts.addAll(List.of(discount100, discount500, discount1000));
    }


    public Discount getDiscount(Double amount) {
        if (almostEqual(amount, 100.0, 10)) {
            return discounts.get(0);
        } else if (almostEqual(amount, 500.0, 50)) {
            return discounts.get(1);
        } else if (almostEqual(amount, 1000.0, 100)) {
            return discounts.get(2);
        }
        return null;
    }


    private static boolean almostEqual(double a, double b, double eps) {
        return Math.abs(a - b) < eps;
    }
}