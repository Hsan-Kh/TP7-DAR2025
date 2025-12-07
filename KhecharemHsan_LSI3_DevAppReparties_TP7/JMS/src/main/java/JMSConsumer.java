import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class JMSConsumer {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("MonTopic");
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {

                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage textMessage = (TextMessage) message;
                            System.out.println("Message reçu: " + textMessage.getText());
                        } else {
                            System.out.println("Message reçu de type: " + message.getClass().getName());
                        }
                    } catch (JMSException e) {
                        System.err.println("Erreur lors de la réception du message: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });

            connection.start();
            System.out.println("Consumer démarré. En attente de messages...");
            System.out.println("Appuyez sur Entrée pour arrêter le consumer.");

            System.in.read();

            consumer.close();
            session.close();
            connection.close();
            System.out.println("Consumer arrêté.");

        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
