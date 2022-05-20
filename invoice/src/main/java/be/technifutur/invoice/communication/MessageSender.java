//package be.technifutur.invoice.communication;
//
//import be.technifutur.invoice.model.Invoice;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageBuilder;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.util.UUID;
//
//@Component
//public class MessageSender implements InitializingBean {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final ObjectMapper mapper;
//    private final Logger logger = LoggerFactory.getLogger(MessageSender.class);
//
//    public MessageSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.mapper = mapper;
//    }
//
//    public void sendInvoiceToBooking(Invoice i) throws JsonProcessingException {
//        String iJson = mapper.writeValueAsString(i);
//        Message m = MessageBuilder.withBody(iJson.getBytes(StandardCharsets.UTF_8))
//                        .setContentType("application.json")
//                        .build();
//        logger.info("SENDING invoice TO booking: " + i);
//        rabbitTemplate.send("direct.exchange", "invoice_key", m);
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        sendInvoiceToBooking(new Invoice(UUID.randomUUID(), 10));
//    }
//}
