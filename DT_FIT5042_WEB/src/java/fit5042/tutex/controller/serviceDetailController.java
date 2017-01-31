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
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pratikgarala
 */
@Named(value = "serviceDetailController")
@SessionScoped
public class serviceDetailController implements Serializable{

    private String pageTitle = "Service";
    private int serviceNo;
    private String serviceName;
    private String serviceType;
    private String thumbnail;
    private String description;
    private int createdBy;
    /**
     * Creates a new instance of serviceDetailController
     */
    ELContext context;
    GovtApplication app;

    public serviceDetailController() {
        if (FacesContext.getCurrentInstance().getExternalContext()
        .getRequestParameterMap().get("serviceNo") != null) {
            this.serviceNo = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap().get("serviceNo")); 
        }
        context = FacesContext.getCurrentInstance().getELContext();
        app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                       .getELResolver().getValue(context, null, "govtApplication");
        
        // Assign movie based on the id provided
        if (this.serviceNo != 0) {            
            try {                
                Service service = app.getServiceRepository().searchServiceByServiceNo(serviceNo);
                this.serviceNo = service.getServiceNo();
                this.serviceName = service.getServiceName();
                this.serviceType = service.getServiceType();
                this.thumbnail = service.getThumbnail();
                this.description = service.getDescription();
                this.createdBy = service.getCreatedBy();
            } catch (Exception ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    public String getPageTitle() {
        return pageTitle;
    }
    
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
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
        if (this.thumbnail == null || this.thumbnail.equals("")) {
            return ServiceRepository.NO_IMAGE_ICON;
        } else {
            return thumbnail;
        }
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
    
    
    
    public String updateService(){
        if("".equals(serviceName) || serviceName == null || serviceType == null || "".equals(serviceType)){
            return "";
        } else {
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
                app.updateService(new Service(serviceNo,serviceName, serviceType, thumbnail, description, createdBy));
                this.resetValues();
                return "/faces/admin/index";
        }
    }
    
    public String deleteService(){
        if(serviceNo == 0 || "".equals(serviceName) || serviceName == null){
            return "";
        } else {
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
                app.deleteService(serviceNo);
                this.resetValues();
                return "/faces/admin/index";
        }
    }
    
    public String addServiceUse(){
        if("".equals(serviceName) || serviceName == null || serviceType == null || "".equals(serviceType)){
            return "";
        }else{
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
//            Set<String> services = new HashSet<>();
            app.addServiceUse(new ServiceUse(new Date(), 
                               serviceName, 
                                app.getCurrentMember(), 
                                app.searchMemberByMemberId(createdBy),
                                'U'));
            this.resetValues();
            return "/faces/admin/showServiceUses";

        }

    }
    
    public String addServiceUse_m(){
        if("".equals(serviceName) || serviceName == null || serviceType == null || "".equals(serviceType)){
            return "";
        }else{
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
//            Set<String> services = new HashSet<>();
            app.addServiceUse(new ServiceUse(new Date(), 
                               serviceName, 
                                app.getCurrentMember(), 
                                app.searchMemberByMemberId(createdBy),
                                'U'));
            this.resetValues();
            return "/faces/member/showServiceUses";

        }

    }
    
    public void resetValues(){
        this.serviceNo = 0;
        this.serviceName = "";
        this.serviceType = "";
        this.thumbnail = "";
        this.description = "";    
    }
    
    
}
