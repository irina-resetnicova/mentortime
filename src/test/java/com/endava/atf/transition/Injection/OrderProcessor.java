package com.endava.atf.transition.Injection;


public class OrderProcessor {
    private final Order order;
    private final Customer customer;

    public OrderProcessor(Order order, Customer customer) {
        this.order = order;
        this.customer = customer;
    }

        public void processOrder() {
            System.out.println("Processing order for Customer: " + customer.getCustomerName());
            System.out.println("Order Details: " + order.getOrderDetails());
        }
    }

//Внутри класса OrderProcessor, эти поля order и customer используются для хранения экземпляров Order и Customer,
// которые будут обработаны в методе processOrder. Когда метод processOrder вызывается, класс OrderProcessor может получить доступ к данным заказа
// и клиента, выполнить какую-либо логику обработки заказа и выводить информацию о заказе на экран или выполнять другие действия.