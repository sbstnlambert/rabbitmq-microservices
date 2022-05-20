//package be.technifutur.invoice.communication;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfig {
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    // Création d'une Queue dans laquelle je renvoie une réservation qui ne peut pas se perdre
//    @Bean("booking_queue")
//    public Queue bookingQueue() {
//        return new Queue("booking_queue", true);
//    }
//
//    @Bean("invoice_queue")
//    public Queue invoiceQueue() {
//        return new Queue("invoice_queue", true);
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange("direct.exchange");
//    }
//
//    @Bean
//    public Binding binding(DirectExchange exchange, Queue invoice_queue) {
//        return BindingBuilder.bind(invoice_queue).to(exchange).with("invoice_key");
//    }
//
//}
