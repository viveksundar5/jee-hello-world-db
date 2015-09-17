package net.nortlam.study;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class Service {
    
    private static final Logger LOG = Logger.getLogger(Service.class.getName());
    
    @PersistenceContext
    private EntityManager em;
    
    public Person findByID(long ID) throws NotFoundException, ServerErrorException {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        
        query.select(root).where(builder.equal(root.get(("ID")), ID));
        
        Person found = null;
        try {
            found = em.createQuery(query).getSingleResult();
        } catch(NoResultException ex) {
            //NoResultException - if there is no result
            LOG.log(Level.WARNING, "### findByID() NO RESULT EXCEPTION:{0}", ID);
            throw new NotFoundException(ex.getCause());
        } catch(NonUniqueResultException | IllegalStateException | QueryTimeoutException |
                TransactionRequiredException | PessimisticLockException | LockTimeoutException ex) {
            //NonUniqueResultException - if more than one result
            //IllegalStateException - if called for a Java Persistence query language UPDATE or DELETE statement
            //QueryTimeoutException - if the query execution exceeds the query timeout value set and only the statement is rolled back
            //TransactionRequiredException - if a lock mode other than NONE has been set and there is no transaction or the persistence context has not been joined to the transaction
            //PessimisticLockException - if pessimistic locking fails and the transaction is rolled back
            //LockTimeoutException - if pessimistic locking fails and only the statement is rolled back
            LOG.log(Level.SEVERE, "### findByID() EXCEPTION:{0}", ID);
            throw new ServerErrorException(ex.getCause());
        }  catch(PersistenceException ex) {
            //PersistenceException - if the query execution exceeds the query timeout value set and the transaction is rolled back                    LOG.log(Level.SEVERE, "### findByID() PERSISTENCE EXCEPTION:{0}", ID);
            throw new ServerErrorException(ex.getCause());
        }

        return found;
    }
}
