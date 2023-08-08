package Lottery;



public class Main {
    public static void main(String[] args) {

         User user1 = new User("Serg", "10-20-69");
         User user2 = new User("Serg", "10-20-69");

         LotteryService lotteryService = new LotteryService();


         lotteryService.purchaseTicket(user1,10);

        lotteryService.purchaseTicket(user2, 100);



    }
}
