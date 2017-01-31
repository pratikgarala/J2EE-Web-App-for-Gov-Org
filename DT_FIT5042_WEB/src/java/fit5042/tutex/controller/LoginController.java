/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.controller;

import fit5042.tutex.repository.entities.Member;
import fit5042.tutex.repository.entities.Service;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pratikgarala
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable{

    private String pageTitle = "Login Page";
    private int memberId;
    private String password = "";
    private String msg = "";
    
    private Member currentMember;
    ELContext context;
    GovtApplication app;
    /**
     * Creates a new instance of loginController
     */
    public LoginController() {
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }
    
    
    
    public String login(){
        if (this.memberId != 0 || !this.password.equals("")) {
            
            try {
                
                context = FacesContext.getCurrentInstance().getELContext();
                app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                       .getELResolver().getValue(context, null, "govtApplication");
                
                Member member = app.getServiceRepository().searchMemberByMemberId(memberId);
                app.setCurrentMember(member);
                
                if(member != null && this.password.equals(member.getPassword())){
                    this.msg = "";
                    this.memberId = 0;
                    this.password = "";
                    if (member.getType() == 'P'){
                        return "member/index?faces-redirect=true";
                    }else{
                        return "admin/index?faces-redirect=true";
                    }
                }else{
                    this.msg = "Member ID or Password is worng..!!";
                    return "login?faces-redirect=true";
                }
            } catch (Exception ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
                this.msg = "Member ID or Password is worng..!!";
                return "login?faces-redirect=true";
            }
        }else{
            this.msg = "Member ID or Password is missing..!!";
            return "login?faces-redirect=true";
        }
    }
    
    public String getNewMember(){
        return "login";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/login";
    }
    
}
