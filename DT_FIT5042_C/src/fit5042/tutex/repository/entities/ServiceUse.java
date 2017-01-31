/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.repository.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author pratikgarala
 */
@Entity
@NamedQueries({@NamedQuery(name = ServiceUse.GET_ALL_QUERY_NAME, query = "SELECT s FROM ServiceUse s")})
public class ServiceUse implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "ServiceUse.getAll";
    public static final String SEARCH_BY_PUB_MEMBER = "ServiceUse.searchByPubMember";
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "serviceUseID")
    @SequenceGenerator(name="seqServiceUse", sequenceName = "SERVICE_USE_SEQ", initialValue=30001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqServiceUse")
    private int serviceUseID;
    @Column(name = "date")
    private Date date;
    
    @Column(name = "services")
    private String services;
    @ManyToOne
    @JoinColumn(name = "Pub_Member")
    private Member pubMember;
    @ManyToOne
    @JoinColumn(name = "Gov_Member")
    private Member govMember;
    @Column(name = "status")
    private char status;

    public ServiceUse() {
//        this.services = new HashSet<>();
    }

//    public ServiceUse(Date date, Set<String> services, Member pubMember, Member govMember, char status) {
//        this.date = date;
//        this.services = services;
//        this.pubMember = pubMember;
//        this.govMember = govMember;
//        this.status = status;
//    }
//
//    public ServiceUse(int serviceUseID, Date date, Set<String> services, Member pubMember, Member govMember, char status) {
//        this.serviceUseID = serviceUseID;
//        this.date = date;
//        this.services = services;
//        this.pubMember = pubMember;
//        this.govMember = govMember;
//        this.status = status;
//    }

    public ServiceUse(int serviceUseID, Date date, String services, Member pubMember, Member govMember, char status) {
        this.serviceUseID = serviceUseID;
        this.date = date;
        this.services = services;
        this.pubMember = pubMember;
        this.govMember = govMember;
        this.status = status;
    }

    public ServiceUse(Date date, String services, Member pubMember, Member govMember, char status) {
        this.date = date;
        this.services = services;
        this.pubMember = pubMember;
        this.govMember = govMember;
        this.status = status;
    }
    
    

    public int getServiceUseID() {
        return serviceUseID;
    }

    public void setServiceUseID(int serviceUseID) {
        this.serviceUseID = serviceUseID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    
//    @CollectionTable(name = "Services")
//    public Set<String> getServices() {
//        return services;
//    }
//
//    public void setServices(Set<String> services) {
//        this.services = services;
//    }

    public Member getPubMember() {
        return pubMember;
    }

    public void setPubMember(Member pubMember) {
        this.pubMember = pubMember;
    }

    public Member getGovMember() {
        return govMember;
    }

    public void setGovMember(Member govMember) {
        this.govMember = govMember;
    }
    
    
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) serviceUseID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceUse)) {
            return false;
        }
        ServiceUse other = (ServiceUse) object;
        if (this.serviceUseID != other.serviceUseID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fit5042.tutex.repository.entities.ServiceUse[ id=" + serviceUseID + " ]";
    }
    
}
