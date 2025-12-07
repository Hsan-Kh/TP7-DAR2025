import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class JMSProducer {
    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            System.out.println("Connexion établie avec le broker.");

            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("MonTopic");
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String nomExpediteur = "Hsan";
            TextMessage message = session.createTextMessage("Hello World! From: " + nomExpediteur);

            producer.send(message);
            System.out.println("Message envoyé: " + message.getText());
            session.commit();
            producer.close();

        } catch (JMSException e) {
            System.err.println("Erreur JMS: " + e.getMessage());
            e.printStackTrace();

            if (session != null) {
                try {
                    session.rollback();
                } catch (JMSException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connexion fermée.");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}