# TP7 : JMS avec ActiveMQ

## Description

Projet d'implémentation d'un système de messagerie asynchrone avec JMS et ActiveMQ. Comprend un broker embarqué, un producteur et un consommateur de messages.

**Auteur** : Khecharem Hsan  
**Cours** : Développement d'applications réparties - LSI3  
**Date** : Décembre 2025


## Structure du projet

```
JMS/
├── src/main/java/
│   ├── EmbededActiveMQ.java
│   ├── JMSProducer.java
│   └── JMSConsumer.java
├── pom.xml
└── README.md
```

## Utilisation

### Ordre d'exécution

1. **Démarrer le Broker**
```bash
java EmbededActiveMQ
```

2. **Démarrer le Consumer**
```bash
java JMSConsumer
```

3. **Exécuter le Producer**
```bash
java JMSProducer
```

### Résultat attendu

**Console Broker :**
```
Broker ActiveMQ démarré sur tcp://localhost:61616
Appuyez sur Entrée pour arrêter...
```

**Console Consumer :**
```
Consumer démarré. En attente de messages...
Message reçu: Hello World! From: Hsan
```

**Console Producer :**
```
Connexion établie avec le broker.
Message envoyé: Hello World! From: Hsan
Connexion fermée.
```

## Concepts JMS

### Pattern Publish-Subscribe (Topic)
- Un message envoyé à un Topic est reçu par tous les consommateurs abonnés
- Plusieurs consommateurs peuvent écouter le même Topic
- Les messages ne sont pas persistés par défaut

### Différence Topic vs Queue

| Topic | Queue |
|-------|-------|
| Multiple consommateurs | Un seul consommateur |
| Broadcast | Point-to-point |
| Non persistant | Persistant |

### Sessions transactionnelles

- **Producer** : Session transactionnelle (true) → nécessite commit()
- **Consumer** : Session non-transactionnelle (false)
- **AUTO_ACKNOWLEDGE** : Accusé de réception automatique
