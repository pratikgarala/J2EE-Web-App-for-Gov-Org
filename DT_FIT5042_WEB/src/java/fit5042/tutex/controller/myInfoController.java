/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.controller;

import fit5042.tutex.repository.entities.Address;
import fit5042.tutex.repository.entities.Member;
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
@Named(value = "myInfoController")
@SessionScoped
public class myInfoController implements Serializable{

    private String pageTitle = "My Profile";
    private int memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private char type;
    private String phoneNumber;
    private String streetNumber;
    private String streetAddress;
    private String suburb;
    private String postcode;
    private String state;
    
    ELContext context;
    GovtApplication app;
    /**
     * Creates a new instance of myInfoController
     */
    public myInfoController() {
        
        context = FacesContext.getCurrentInstance().getELContext();
        app = (GovtApplication) FacesContext.getCurrentInstance().getApplication()
                       .getELResolver().getValue(context, null, "govtApplication");
        this.memberId = app.getCurrentMember().getMemberId();
        if (this.memberId != 0) {
            
            try {
                
                Member member = app.getServiceRepository().searchMemberByMemberId(memberId);
                this.memberId = member.getMemberId();
                this.lastName = member.getlName();
                this.firstName = member.getfName();
                this.type = member.getType();
                this.password = member.getPassword();
                this.phoneNumber = member.getPhoneNumber();
                this.streetNumber = member.getAddress().getStreetNumber();
                this.streetAddress = member.getAddress().getStreetAddress();
                this.suburb = member.getAddress().getSuburb();
                this.postcode = member.getAddress().getPostcode();
                this.state = member.getAddress().getState();
                this.email = member.getEmail();
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
    
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String updateMember(){
        if("".equals(lastName) || lastName == null || firstName == null || "".equals(firstName)){
            return "";
        } else {
            app.updateMember(new Member(memberId,firstName,lastName,email,password,type,
                            new Address(streetNumber,streetAddress,suburb,postcode,state),phoneNumber));
            this.resetValues();
            return "";
        }
    }
    
    public void resetValues(){
        this.memberId = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.type = 'G';
        this.streetNumber = "";
        this.streetAddress = "";
        this.suburb = "";
        this.postcode = "";
        this.state = "";
        this.phoneNumber = "";
    }
}
