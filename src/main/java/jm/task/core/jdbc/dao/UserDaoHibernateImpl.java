package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        String sql;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            sql = "CREATE TABLE IF NOT EXISTS `kata2`.`users` (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` INT(3) NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка в методе createUsersTable");
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                System.out.println("Ошибка при откате транзакции");
                e1.printStackTrace();
            }
        } finally {
            try {
                assert session != null;
                session.close();
            } catch (Exception e2) {
                System.out.println("Ошибка при закрытии сессии");
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        String sql;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            sql = "DROP TABLE IF EXISTS users";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка в методе dropUsersTable");
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                System.out.println("Ошибка при откате транзакции");
                e1.printStackTrace();
            }
        } finally {
            try {
                assert session != null;
                session.close();
            } catch (Exception e2) {
                System.out.println("Ошибка при закрытии сессии");
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка в методе saveUser");
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                System.out.println("Ошибка при откате транзакции");
                e1.printStackTrace();
            }
        } finally {
            try {
                assert session != null;
                session.close();
            } catch (Exception e2) {
                System.out.println("Ошибка при закрытии сессии");
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE User where id = :id").setParameter("id", id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка в методе removeUserById");
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                System.out.println("Ошибка при откате транзакции");
                e1.printStackTrace();
            }
        } finally {
            try {
                assert session != null;
                session.close();
            } catch (Exception e2) {
                System.out.println("Ошибка при закрытии сессии");
                e2.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = null;
        try {
            session = Util.getSessionFactory().openSession();
            users = session.createQuery("FROM User").list();
        } catch (Exception e) {
            System.out.println("Ошибка в методе getAllUsers");
            e.printStackTrace();
        } finally {
            try {
                assert session != null;
                session.close();
            } catch (Exception e2) {
                System.out.println("Ошибка при закрытии сессии");
                e2.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка в методе cleanUsersTable");
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                System.out.println("Ошибка при откате транзакции");
                e1.printStackTrace();
            }
        } finally {
            try {
                assert session != null;
                session.close();
            } catch (Exception e2) {
                System.out.println("Ошибка при закрытии сессии");
                e2.printStackTrace();
            }
        }


    }
}
