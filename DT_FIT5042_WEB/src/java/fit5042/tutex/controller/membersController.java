/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.controller;

import fit5042.tutex.repository.entities.Address;
import fit5042.tutex.repository.entities.Member;
import java.io.Serializable;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author pratikgarala
 */
@Named(value = "membersController")
@RequestScoped
public class membersController implements Serializable{

    private String pageTitle = "Viewing Members";
    private int memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private char type;
    private Address address;
    private String phoneNumber;
    
    ELContext context;
    GovtApplication app;
    /**
     * Creates a new instance of membersController
     */
    public membersController() {
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

    public String getFristName() {
        return firstName;
    }

    public void setFristName(String fristName) {
        this.firstName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
 
    public String searchMember(){
        if (memberId != 0) {
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
                Member m = app.searchMemberByMemberId(memberId);
                this.resetValues();
        
        } else if ((firstName != null && !firstName.equals("")) ||
                   (lastName != null && !lastName.equals("")) ||
                   (email != null && !email.equals(""))
                ){
            context = FacesContext.getCurrentInstance().getELContext();
            app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(context, null, "govtApplication");
                app.searchMember(firstName, lastName, type, email);
                this.resetValues();
        } 
        return "/faces/admin/members";
    }
    
    public void viewMembers(){
       
        context = FacesContext.getCurrentInstance().getELContext();
        app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
            .getELResolver().getValue(context, null, "govtApplication");
            app.viewMembers();
            this.resetValues();
    }
    
    public void resetValues(){
        this.memberId = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.type = 'G';
        this.phoneNumber = "";
    }
}
