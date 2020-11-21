package com.example.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class UsersManager {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

        SessionFactory sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);

        // obtains the session
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Group groupAdmin = new Group("Grupa Administratora");
        Group groupGuest = new Group("Gości Grupa");

        User user1 = new User("Karol", "karol123", "karol@gmail.net");
        User user2 = new User("Monika", "mary", "Monika123@gmail.net");

        groupAdmin.addUser(user1);
        groupAdmin.addUser(user2);

        groupGuest.addUser(user1);

        user1.addGroup(groupAdmin);
        user2.addGroup(groupAdmin);
        user1.addGroup(groupGuest);

        session.save(groupAdmin);
        session.save(groupGuest);

        session.getTransaction().commit();
        session.close();
    }

}
