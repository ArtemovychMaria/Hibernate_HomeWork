package MyHExample.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HibernateApp
{
    public static void main( String[] args )
    {
        Configuration configuration=new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Item item=new Item(1);
        Item item2=new Item(2);
        Item item3=new Item(3);
        Item item4=new Item(4);

        Set<Item>items=new HashSet<>(Arrays.asList(item,item2,item3,item4));

        Cart cart=new Cart(2,"Cart1",items);

        session.persist(cart);

        transaction.commit();
        session.close();






    }
}
