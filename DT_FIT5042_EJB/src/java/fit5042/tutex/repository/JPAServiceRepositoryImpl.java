package fit5042.tutex.repository;

import fit5042.tutex.repository.entities.Member;
import fit5042.tutex.repository.entities.Service;
import fit5042.tutex.repository.entities.ServiceUse;
//import fit5042.tutex.repository.entities.ServiceUse;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author pratikgarala
 */
@Stateless
public class JPAServiceRepositoryImpl implements ServiceRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
   

    @Override
    public List<Member> getAllMembers() throws Exception {
        return entityManager.createNamedQuery(Member.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public List<Service> getAllServices() throws Exception {
        return entityManager.createNamedQuery(Service.GET_ALL_QUERY_NAME).getResultList();
    }
    
    @Override
    public List<ServiceUse> getAllServiceUses() throws Exception {
        return entityManager.createNamedQuery(ServiceUse.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void addService(Service service) throws Exception {
        entityManager.persist(service);
    }

    @Override
    public void addServiceUse(ServiceUse serviceUse) throws Exception {
        entityManager.persist(serviceUse);
    }

    @Override
    public void addMember(Member member) throws Exception {
        entityManager.persist(member);
    }

    @Override
    public Service searchServiceByServiceNo(int serviceNo) throws Exception {
        Service service = entityManager.find(Service.class, serviceNo);
        return service;
    }
    
    @Override
    public ServiceUse searchServiceUseByServiceUseId(int serviceUseID) throws Exception {
        ServiceUse serviceUse = entityManager.find(ServiceUse.class, serviceUseID);
        return serviceUse;
    }

    @Override
    public Member searchMemberByMemberId(int memberId) throws Exception {
       Member member = entityManager.find(Member.class, memberId);
        return member;
    }
    
    @Override
    public List<Service> searchServiceByServiceName(String serviceName) throws Exception {
     
        String q = "SELECT s FROM Service s where s.serviceName like ?1";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, "%" + serviceName + "%");
        return query.getResultList();
    }

    @Override
    public List<Service> searchServiceByServiceType(String serviceType) throws Exception {
        String q = "SELECT s FROM Service s where s.serviceType like ?1";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, "%" + serviceType + "%");
        return query.getResultList();
        
    }



    @Override
    public void removeService(int serviceNo) throws Exception {
        Service service = this.searchServiceByServiceNo(serviceNo);
      
        if (service != null) {
            entityManager.remove(service);
        }
    }

    @Override
    public void removeServiceUse(int serviceUseId) throws Exception {
        ServiceUse serviceUse = this.searchServiceUseByServiceUseId(serviceUseId);
      
        if (serviceUse != null) {
            entityManager.remove(serviceUse);
        }
    }

    @Override
    public void removeMember(int memberId) throws Exception {
        Member member = this.searchMemberByMemberId(memberId);
      
        if (member != null) {
            entityManager.remove(member);
        }
    }

    @Override
    public void editService(Service service) throws Exception {
        entityManager.merge(service);
    }

    @Override
    public void editServiceUse(ServiceUse serviceUse) throws Exception {
        entityManager.merge(serviceUse);
    }

    @Override
    public void editMember(Member member) throws Exception {
        entityManager.merge(member);
    }   

    @Override
    public List<ServiceUse> searchServiceUseByMember(int memberId) throws Exception {
        String q = "SELECT s FROM ServiceUse s where s.pubMember.memberId = ?1";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, memberId );
        return  query.getResultList();
    }

    @Override
    public List<Member> searchMemberByFName(String fName) throws Exception {
        String q = "SELECT m FROM Member m where m.fName like ?1";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, "%" + fName + "%");
        return query.getResultList();
    }

    @Override
    public List<Member> searchMemberByLName(String lName) throws Exception {
        String q = "SELECT m FROM Member m where m.lName like ?1";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, "%" + lName + "%");
        return query.getResultList();
    }

    @Override
    public List<Member> searchMemberByMemberType(char memberType) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Member.class);
        Root<Member> s = query.from(Member.class);
        query.select(s).where(builder.equal(s.get("memberType"), memberType));
        
        //Service service = entityManager.find(Service.class, serviceName);
        return  entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Member> searchMemberByEmail(String email) throws Exception {
        String q = "SELECT m FROM Member m where m.email like ?1";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, "%" + email + "%");
        return query.getResultList();
    }

    @Override
    public List<Member> searchMember(String fName, String lName, String memberType, String email) throws Exception {
        String q = "SELECT m FROM Member m WHERE "
                + "m.fName like ?1 AND "
                + "m.lName like ?2 AND " 
//                + "m.type = ?3 AND " 
                + "m.email like ?3";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, "%" + fName + "%");
        query.setParameter( 2, "%" + lName + "%");
//        query.setParameter( 3,  memberType);
        query.setParameter( 3, "%" + email + "%");
        return query.getResultList();
    }

    @Override
    public List<Service> searchService(int serviceNo, String ServiceName, String serviceType) throws Exception {
         String q = "SELECT s FROM Service s WHERE "
                + "s.serviceName like ?1 AND " 
                + "s.serviceType like ?2";
        Query query = entityManager.createQuery(q);
        query.setParameter( 1, "%" + ServiceName + "%");
        query.setParameter( 2, "%" + serviceType + "%");
        return query.getResultList();
    }
}