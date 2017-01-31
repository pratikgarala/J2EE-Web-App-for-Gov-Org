/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.repository.entities;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author pratikgarala
 */
@Named
@Entity

@NamedQueries({@NamedQuery(name = Service.GET_ALL_QUERY_NAME, query = "SELECT s FROM Service s")})
public class Service implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "Service.getAll";
    
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seqService", sequenceName = "SERVICE_SEQ", initialValue=50001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqService")
    private int serviceNo;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "service_type", nullable = false )
    private String serviceType;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by")
    private int createdBy;

    public Service() {
    }

    
//    public Service(String serviceName, String serviceType, String thumbnail, String description) {
////        this.serviceNo = serviceNo;
//        this.serviceName = serviceName;
//        this.serviceType = serviceType;
//        this.thumbnail = thumbnail;
//        this.description = description;
//    }

    public Service(String serviceName, String serviceType, String thumbnail, String description, int createdBy) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.thumbnail = thumbnail;
        this.description = description;
        this.createdBy = createdBy;
    }

    
    
//    public Service(int serviceNo, String serviceName, String serviceType, String thumbnail, String description) {
//        this.serviceNo = serviceNo;
//        this.serviceName = serviceName;
//        this.serviceType = serviceType;
//        this.thumbnail = thumbnail;
//        this.description = description;
//    }
    
    

    public Service(int serviceNo, String serviceName, String serviceType, String thumbnail, String description, int createdBy) {
        this.serviceNo = serviceNo;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.thumbnail = thumbnail;
        this.description = description;
        this.createdBy = createdBy;
    }

    public int getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(int serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) serviceNo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if (this.serviceNo != other.serviceNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fit5042.tutex.repository.entities.Service[ id=" + serviceNo + " ]";
    }
    
}
