package MyHExample.oneToMany;

import MyHExample.manytomany.Cart;
import MyHExample.manytomany.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class oneToManyApp
{
    public static void main( String[] args )
    {
        Configuration configuration=new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Post post=new Post();
        post.setTitle("News");
        
        Post post1=new Post();
        post1.setTitle("Cats");

        Comment comment=new Comment("Ivan");
        Comment comment2=new Comment("Petro");
        Comment comment3=new Comment("Oksana");
        Comment comment4=new Comment("Olena");
        comment.setPost(post);
        comment2.setPost(post);
        comment3.setPost(post1);
        comment4.setPost(post1);

        Set<Comment>comments=new HashSet<>(Arrays.asList(comment,comment2));
        Set<Comment>comments2=new HashSet<>(Arrays.asList(comment3,comment4));
        post.setComments(comments);
        post1.setComments(comments2);

        session.persist(post);
        session.persist(post1);
        session.persist(comment);
        session.persist(comment2);
        session.persist(comment3);
        session.persist(comment4);

        Comment commentFromDb = session.find(Comment.class,1);
        System.out.println(commentFromDb.getPost().getTitle());

        Post postFromDb = session.find(Post.class, 2);

        System.out.println(postFromDb.getComments().contains(comment3));

        transaction.commit();
//        session.close();






    }
}
