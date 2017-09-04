/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_system_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsSystemUsers.findAll", query = "SELECT s FROM SmsSystemUsers s")
    , @NamedQuery(name = "SmsSystemUsers.findBySmsSystemUserId", query = "SELECT s FROM SmsSystemUsers s WHERE s.smsSystemUserId = :smsSystemUserId")
    , @NamedQuery(name = "SmsSystemUsers.findByUsername", query = "SELECT s FROM SmsSystemUsers s WHERE s.username = :username")
    , @NamedQuery(name = "SmsSystemUsers.findByPassword", query = "SELECT s FROM SmsSystemUsers s WHERE s.password = :password")
    , @NamedQuery(name = "SmsSystemUsers.findByPasswordStatus", query = "SELECT s FROM SmsSystemUsers s WHERE s.passwordStatus = :passwordStatus")
    , @NamedQuery(name = "SmsSystemUsers.findBySmsSystemUserLastLogin", query = "SELECT s FROM SmsSystemUsers s WHERE s.smsSystemUserLastLogin = :smsSystemUserLastLogin")
    , @NamedQuery(name = "SmsSystemUsers.findByIntrash", query = "SELECT s FROM SmsSystemUsers s WHERE s.intrash = :intrash")})
public class SmsSystemUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sms_system_user_id")
    private Integer smsSystemUserId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "password_status")
    private String passwordStatus;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "sms_system_user_last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date smsSystemUserLastLogin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "intrash")
    private boolean intrash;
    
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsUser userId;

    public SmsSystemUsers() {
    }

    public SmsSystemUsers(Integer smsSystemUserId) {
        this.smsSystemUserId = smsSystemUserId;
    }

    public SmsSystemUsers(Integer smsSystemUserId, String username, String password, String passwordStatus, Date smsSystemUserLastLogin, boolean intrash) {
        this.smsSystemUserId = smsSystemUserId;
        this.username = username;
        this.password = password;
        this.passwordStatus = passwordStatus;
        this.smsSystemUserLastLogin = smsSystemUserLastLogin;
        this.intrash = intrash;
    }

    public Integer getSmsSystemUserId() {
        return smsSystemUserId;
    }

    public void setSmsSystemUserId(Integer smsSystemUserId) {
        this.smsSystemUserId = smsSystemUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(String passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public Date getSmsSystemUserLastLogin() {
        return smsSystemUserLastLogin;
    }

    public void setSmsSystemUserLastLogin(Date smsSystemUserLastLogin) {
        this.smsSystemUserLastLogin = smsSystemUserLastLogin;
    }

    public boolean getIntrash() {
        return intrash;
    }

    public void setIntrash(boolean intrash) {
        this.intrash = intrash;
    }

    public SmsUser getUserId() {
        return userId;
    }

    public void setUserId(SmsUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smsSystemUserId != null ? smsSystemUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsSystemUsers)) {
            return false;
        }
        SmsSystemUsers other = (SmsSystemUsers) object;
        if ((this.smsSystemUserId == null && other.smsSystemUserId != null) || (this.smsSystemUserId != null && !this.smsSystemUserId.equals(other.smsSystemUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsSystemUsers[ smsSystemUserId=" + smsSystemUserId + " ]";
    }
    
}
