/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.controller;

import fit5042.tutex.repository.entities.Member;
import fit5042.tutex.repository.entities.Service;
import fit5042.tutex.repository.entities.ServiceUse;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pratikgarala
 */
@Named(value = "showServiceUsesController")
@RequestScoped
public class ShowServiceUsesController {
    private String pageTitle = "My Service Uses";
    private List<ServiceUse> serviceUses;
    private List<Member> member;
    ELContext context;
    GovtApplication app;
    /**
     * Creates a new instance of ShowServiceUsesController
     */
    public ShowServiceUsesController() {
        context = FacesContext.getCurrentInstance().getELContext();
        app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
        try {
            serviceUses = app.getServiceRepository().searchServiceUseByMember(app.getCurrentMember().getMemberId());
        } catch (Exception ex) {
            Logger.getLogger(ShowServiceUsesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }

    public List<ServiceUse> getServiceUses() {
        return serviceUses;
    }

    public void setServiceUses(List<ServiceUse> serviceUses) {
        this.serviceUses = serviceUses;
    }
 
    public String updateStatus(){
        int serviceUseId = 0;
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("serviceUseId") != null) {
           serviceUseId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext()
        .getRequestParameterMap().get("serviceUseId")); 
        }
        if(serviceUseId != 0){
            ServiceUse su = app.searchServiceUseByServiceUseID(serviceUseId);           
            su.setStatus('C');
            app.updateServiceUse(su);
//            this.serviceUses.remove(serviceUses);
//            try {
//                serviceUses = app.getServiceRepository().searchServiceUseByMember(app.getCurrentMember().getMemberId());
//            } catch (Exception ex) {
//                Logger.getLogger(ShowServiceUsesController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return "/faces/admin/showServiceUses";
    }
    
    public String updateStatus_m(){
        int serviceUseId = 0;
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("serviceUseId") != null) {
           serviceUseId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext()
        .getRequestParameterMap().get("serviceUseId")); 
        }
        if(serviceUseId != 0){
            ServiceUse su = app.searchServiceUseByServiceUseID(serviceUseId);           
            su.setStatus('C');
            app.updateServiceUse(su);
//            this.serviceUses.remove(serviceUses);
//            try {
//                serviceUses = app.getServiceRepository().searchServiceUseByMember(app.getCurrentMember().getMemberId());
//            } catch (Exception ex) {
//                Logger.getLogger(ShowServiceUsesController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return "/faces/member/showServiceUses";
    }
}
