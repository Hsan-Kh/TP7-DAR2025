import org.apache.activemq.broker.BrokerService;

public class EmbededActiveMQ {
    public static void main(String[] args) {
        try {
            BrokerService broker = new BrokerService();
            broker.setBrokerName("MonBrokerActiveMQ");
            broker.addConnector("tcp://0.0.0.0:61616");
            broker.setPersistent(false);

            broker.start();
            System.out.println("Broker ActiveMQ démarré sur tcp://localhost:61616");
            System.out.println("Appuyez sur Entrée pour arrêter le broker...");
            System.in.read();
            broker.stop();
            System.out.println("Broker arrêté.");

        } catch (Exception e) {
            System.err.println("Erreur lors du démarrage du broker: " + e.getMessage());
            e.printStackTrace();
        }
    }
}