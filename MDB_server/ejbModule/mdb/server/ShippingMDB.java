package mdb.server;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: ShippingMDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(
						propertyName = "destination", 
						propertyValue = "queue/ShippingQueue"),
				@ActivationConfigProperty(
						propertyName = "destinationType",
						propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(
						propertyName = "subscriptionDurability",
						propertyValue = "Durable")
							}, 
		mappedName = "ShippingQueue")
public class ShippingMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public ShippingMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub        
        try {
        	if (message instanceof TextMessage) {
        		System.out.println("Queue: I received a TextMessage at " + new Date());
        		TextMessage msg = (TextMessage)message;
        		System.out.println("Message is: " + msg.getText());
        	} else if (message instanceof ObjectMessage) {
        		System.out.println("Queue: I received an ObjectMessage at " + new Date());
        		ObjectMessage msg = (ObjectMessage) message;
        		Libro libro = (Libro)msg.getObject();
        		System.out.println("Dettagli libro:");
        		System.out.println(libro.getId());
        		System.out.println(libro.getTitolo());
        		System.out.println(libro.getAutore());
        	} else {
        		System.out.println("Not valid message for this Queue MDB");
        	}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
