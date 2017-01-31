/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.repository.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author pratikgarala
 */
@Entity
@Table(name = "MEMBER")
@NamedQueries({@NamedQuery(name = Member.GET_ALL_QUERY_NAME, query = "SELECT m FROM Member m")})
public class Member implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "Member.getAll";
    
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seqMember", sequenceName = "MEMBER_SEQ", initialValue=10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMember")
    @Column(name = "member_Id")
    private int memberId;
    @Column(name = "fName")
    private String fName;
    @Column(name = "lName")
    private String lName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "member_type")
    private char type;
    @Embedded
    @Column(name = "address")
    private Address address;
    @Column(name = "phone_number")
    private String phoneNumber;
    
    
    @OneToMany(mappedBy = "pubMember")
    private Set<ServiceUse> serviceUses_p;
    @OneToMany(mappedBy = "govMember")
    private Set<ServiceUse> serviceUses_g;

    public Member() {
    }

    public Member(int memberId, String fName, String lName, String email, String password, char type, Address address, String phoneNumber) {
        this.memberId = memberId;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
        
        this.serviceUses_p = new HashSet<>();
        this.serviceUses_g = new HashSet<>();
    }

    public Member(String fName, String lName, String email, String password, char type, Address address, String phoneNumber) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
        
        this.serviceUses_p = new HashSet<>();
        this.serviceUses_g = new HashSet<>();
    }
    
    

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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

    public Set<ServiceUse> getServiceUses_p() {
        return serviceUses_p;
    }

    public void setServiceUses_p(Set<ServiceUse> serviceUses_p) {
        this.serviceUses_p = serviceUses_p;
    }

    public Set<ServiceUse> getServiceUses_g() {
        return serviceUses_g;
    }

    public void setServiceUses_g(Set<ServiceUse> serviceUses_g) {
        this.serviceUses_g = serviceUses_g;
    }




    @Override
    public boolean equals(Object obj) {
       if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        if (this.memberId != other.memberId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.memberId;
        return hash;
    }

    @Override
    public String toString() {
        return "fit5042.tutex.repository.entities.Member1[ id=" + memberId + " ]";
    }
    
}
