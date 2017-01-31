/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.controller;

import fit5042.tutex.repository.ServiceRepository;
import fit5042.tutex.repository.entities.Member;
import fit5042.tutex.repository.entities.Service;
import fit5042.tutex.repository.entities.ServiceUse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author pratikgarala
 */

@Named(value = "govtApplication")
@ApplicationScoped
public class GovtApplication{
    
    @EJB
    private ServiceRepository serviceRepository;
    private List<Service> services;
    private List<Member> members;
    private Member currentMember;
    
    public GovtApplication(){
//        try {        
//            services = serviceRepository.getAllServices();
//        } catch (Exception ex) {
//            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    @PostConstruct
    public void init(){
        try {        
            services = serviceRepository.getAllServices();
            members = serviceRepository.getAllMembers();
            System.out.println(services.get(0).getServiceNo());
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }
    
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
 
    public ServiceRepository getServiceRepository() {
        return serviceRepository;
    }

    public void setServiceRepository(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
  
    
    
    public void addService(Service service){
        try {
            if (service != null) {
                serviceRepository.addService(service);
                services.removeAll(services);
                services = serviceRepository.getAllServices();
//                services.add(service);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateService(Service service){
        try {
            if (service != null) {
                serviceRepository.editService(service);
                services.removeAll(services);
                services = serviceRepository.getAllServices();
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteService(int serviceNo){
        try {
            if (serviceNo != 0) {
                serviceRepository.removeService(serviceNo);
                services.removeAll(services);
                services = serviceRepository.getAllServices();
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchServiceByServiceNo(int serviceNo){
        try {
            if (serviceNo != 0) {
                Service s = serviceRepository.searchServiceByServiceNo(serviceNo);
                services.removeAll(services);
                services.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchServiceByServiceName(String serviceName){
        try {
            if (serviceName != null) {
                services.removeAll(services);
                services = serviceRepository.searchServiceByServiceName(serviceName);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchServiceByServiceType(String serviceType){
        try {
            if (serviceType != null) {
                services.removeAll(services);
                services = serviceRepository.searchServiceByServiceType(serviceType);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewService(){
        try {
                services.removeAll(services);
                services = serviceRepository.getAllServices();
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewMembers(){
        try {
                members.removeAll(members);
                members = serviceRepository.getAllMembers();
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateMember(Member member){
        try {
            if (member != null) {
                serviceRepository.editMember(member);
                members.removeAll(members);
                members = serviceRepository.getAllMembers();
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateServiceUse(ServiceUse serviceUse){
        try {
            if (serviceUse != null) {
                serviceRepository.editServiceUse(serviceUse);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteMember(int memberId){
        try {
            if (memberId != 0) {
                serviceRepository.removeMember(memberId);
                members.removeAll(members);
                members = serviceRepository.getAllMembers();
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMember(Member member){
        try {
            if (member != null) {
                serviceRepository.addMember(member);
                members.removeAll(members);
                members = serviceRepository.getAllMembers();
//                services.add(service);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addServiceUse(ServiceUse serviceUse){
        try {
            if (serviceUse != null) {
                serviceRepository.addServiceUse(serviceUse);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Member searchMemberByMemberId(int memberId){
        Member m = null;
        try {
            if (memberId != 0) {
                m = serviceRepository.searchMemberByMemberId(memberId);
                members.removeAll(members);
                members.add(m);
            }
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public ServiceUse searchServiceUseByServiceUseID(int serviceUseId){
        ServiceUse su = null;
        try {
            su = serviceRepository.searchServiceUseByServiceUseId(serviceUseId);
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return su;
    }
    
    public void searchMember(String fName, String lName, char memberType, String email){
        try {
            members.remove(members);
            members = serviceRepository.searchMember(fName, lName, email, email);
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchService (int serviceNo, String serviceName, String serviceType){
        try {
            services.removeAll(services);
                services = serviceRepository.searchService(serviceNo, serviceName, serviceType);
        } catch (Exception ex) {
            Logger.getLogger(GovtApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 