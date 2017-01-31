package fit5042.tutex.repository;

import fit5042.tutex.repository.entities.Member;
import fit5042.tutex.repository.entities.Service;
import fit5042.tutex.repository.entities.ServiceUse;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author pratikgarala
 */
@Remote
public interface ServiceRepository {
    
    public static final String NO_IMAGE_ICON = "http://nemanjakovacevic.net/wp-content/uploads/2013/07/placeholder.png";

    public void addService(Service service) throws Exception;

    public void addServiceUse (ServiceUse serviceUse) throws Exception;
    
    public void addMember (Member member) throws Exception;
    
    public Service searchServiceByServiceNo(int serviceNo) throws Exception;
    
    public ServiceUse searchServiceUseByServiceUseId(int serviceUseID) throws Exception;
    
    public Member searchMemberByMemberId(int memberId) throws Exception;
    
    public List<Member> searchMemberByFName(String fName) throws Exception;
     
    public List<Member> searchMemberByLName(String lName) throws Exception;
    
    public List<Member> searchMemberByMemberType(char memberType) throws Exception;
    
    public List<Member> searchMemberByEmail(String email) throws Exception;
    
    public List<Member> searchMember(String fName, String lName, String memberType, String email) throws Exception;

    public List<Service> searchService (int serviceNo, String ServiceName, String serviceType) throws Exception;
    
    public List<Service> searchServiceByServiceName(String serviceName) throws Exception;
    
    public List<Service> searchServiceByServiceType(String serviceType) throws Exception;
    
    public List<Service> getAllServices() throws Exception;
    
    public List<ServiceUse> getAllServiceUses() throws Exception;
    
    public List<Member> getAllMembers() throws Exception;
    
    public void removeService(int serviceNo) throws Exception;
    
    public void removeServiceUse(int serviceUseId) throws Exception;
    
    public void removeMember(int memberId) throws Exception;
    
    public void editService(Service service) throws Exception;
    
    public void editServiceUse(ServiceUse serviceUse) throws Exception;
    
    public void editMember(Member member) throws Exception;
    
    public List<ServiceUse> searchServiceUseByMember(int memberId) throws Exception;
    
   
}
