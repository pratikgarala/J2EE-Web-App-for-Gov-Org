/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.controller;

import fit5042.tutex.repository.ServiceRepository;
import fit5042.tutex.repository.entities.Service;
import java.io.Serializable;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pratikgarala
 */
@Named(value = "addServiceController")
@RequestScoped
public class addServiceController implements Serializable{

    private String pageTitle = "Add Service";
    private String serviceName;
    private String serviceType;
    private String thumbnail;
    private String description;
    
    ELContext context;
    GovtApplication app;
    
    /**
     * Creates a new instance of addServiceController
     */
    public addServiceController() {
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
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
    
    public String addService(){
        //check if null?
        if("".equals(serviceName) || serviceName == null || serviceType == null || "".equals(serviceType)){
            return "";
        }else{
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
            app.addService(new Service(serviceName, serviceType, thumbnail, description, app.getCurrentMember().getMemberId()));
            this.resetValues();
            return "/faces/admin/index";

        }
    }
    
    public void resetValues(){
        this.serviceName = "";
        this.serviceType = "";
        this.thumbnail = "";
        this.description = "";    
    }
    
    
}
