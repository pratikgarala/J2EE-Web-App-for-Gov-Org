/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.controller;

import fit5042.tutex.repository.ServiceRepository;
import fit5042.tutex.repository.entities.Service;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;

/**
 *
 * @author pratikgarala
 */
@Named(value = "indexController")
@RequestScoped
public class IndexController implements Serializable{
    private String pageTitle = "Viewing Services";
    private int serviceNo;
    private String serviceName;
    private String serviceType;
    private String thumbnail;
    private String description;

    ELContext context;
    GovtApplication app;
    
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
    
    
    
//    public void addService(){
//        //check if null?
//        if("".equals(serviceName) || serviceName == null || serviceType == null || "".equals(serviceType)){
//        }else{
//            context = FacesContext.getCurrentInstance().getELContext();
//            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
//                .getELResolver().getValue(context, null, "govtApplication");
//            app.addService(new Service(serviceNo,serviceName, serviceType, thumbnail, description));
//            this.resetValues();
////            return "";
//
//        }
//    }
    
//    public void updateService(){
//        if(serviceNo == 0 || "".equals(serviceName) || serviceName == null){
//        } else {
//            context = FacesContext.getCurrentInstance().getELContext();
//            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
//                .getELResolver().getValue(context, null, "govtApplication");
//                app.updateService(new Service(serviceNo,serviceName, serviceType, thumbnail, description));
//                this.resetValues();
//        }
//    }
//    
//    public void deleteService(){
//        if(serviceNo == 0 || "".equals(serviceName) || serviceName == null){
//        } else {
//            context = FacesContext.getCurrentInstance().getELContext();
//            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
//                .getELResolver().getValue(context, null, "govtApplication");
//                app.deleteService(serviceNo);
//                this.resetValues();
//        }
//    }
    
    public void searchService(){
        if (serviceNo != 0) {
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
                app.searchServiceByServiceNo(serviceNo);
                this.resetValues();
//        } else if (serviceName != null && !serviceName.equals("")){
//            context = FacesContext.getCurrentInstance().getELContext();
//            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
//                .getELResolver().getValue(context, null, "govtApplication");
//                app.searchServiceByServiceName(serviceName);
//                this.resetValues();
//        } else if (serviceType != null && !serviceType.equals("")){
//            context = FacesContext.getCurrentInstance().getELContext();
//            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
//                .getELResolver().getValue(context, null, "govtApplication");
//                app.searchServiceByServiceType(serviceType);
//                this.resetValues();
//        }
        }else if ((serviceName != null && !serviceName.equals("")) ||
             (serviceType != null && !serviceType.equals("")) ) {

                context = FacesContext.getCurrentInstance().getELContext();
                app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(context, null, "govtApplication");
                app.searchService(serviceNo, serviceName, serviceType);
        }
    }
    
    public void viewService(){
        context = FacesContext.getCurrentInstance().getELContext();
        app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
            .getELResolver().getValue(context, null, "govtApplication");
            app.viewService();
            this.resetValues();
    }
    
    public void resetValues(){
        this.serviceNo = 0;
        this.serviceName = "";
        this.serviceType = "";
        this.thumbnail = "";
        this.description = "";
        //return "";
    
    }
    
} 
