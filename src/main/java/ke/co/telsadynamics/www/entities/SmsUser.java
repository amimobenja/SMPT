/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsUser.findAll", query = "SELECT s FROM SmsUser s")
    , @NamedQuery(name = "SmsUser.findByUserId", query = "SELECT s FROM SmsUser s WHERE s.userId = :userId")
    , @NamedQuery(name = "SmsUser.findByEmailAddress", query = "SELECT s FROM SmsUser s WHERE s.emailAddress = :emailAddress")
    , @NamedQuery(name = "SmsUser.findByMsisdn", query = "SELECT s FROM SmsUser s WHERE s.msisdn = :msisdn")
    , @NamedQuery(name = "SmsUser.findByStatus", query = "SELECT s FROM SmsUser s WHERE s.status = :status")
    , @NamedQuery(name = "SmsUser.findByFirstName", query = "SELECT s FROM SmsUser s WHERE s.firstName = :firstName")
    , @NamedQuery(name = "SmsUser.findByMiddleName", query = "SELECT s FROM SmsUser s WHERE s.middleName = :middleName")
    , @NamedQuery(name = "SmsUser.findByLastName", query = "SELECT s FROM SmsUser s WHERE s.lastName = :lastName")
    , @NamedQuery(name = "SmsUser.findByNickName", query = "SELECT s FROM SmsUser s WHERE s.nickName = :nickName")
    , @NamedQuery(name = "SmsUser.findByDescr", query = "SELECT s FROM SmsUser s WHERE s.descr = :descr")
    , @NamedQuery(name = "SmsUser.findBySmsSent", query = "SELECT s FROM SmsUser s WHERE s.smsSent = :smsSent")
    , @NamedQuery(name = "SmsUser.findByLastLogin", query = "SELECT s FROM SmsUser s WHERE s.lastLogin = :lastLogin")
    , @NamedQuery(name = "SmsUser.findByDocumentId", query = "SELECT s FROM SmsUser s WHERE s.documentId = :documentId")
    , @NamedQuery(name = "SmsUser.findByDocumentType", query = "SELECT s FROM SmsUser s WHERE s.documentType = :documentType")
    , @NamedQuery(name = "SmsUser.findByIntrash", query = "SELECT s FROM SmsUser s WHERE s.intrash = :intrash")
    , @NamedQuery(name = "SmsUser.findByLastSmsAt", query = "SELECT s FROM SmsUser s WHERE s.lastSmsAt = :lastSmsAt")})
public class SmsUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    
    @Size(max = 100)
    @Column(name = "email_address")
    private String emailAddress;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "msisdn")
    private String msisdn;
    
    @Size(max = 10)
    @Column(name = "status")
    private String status;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "middle_name")
    private String middleName;
    
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    
    @Size(max = 50)
    @Column(name = "nick_name")
    private String nickName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "descr")
    private String descr;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "sms_sent")
    private boolean smsSent;
    
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "document_id")
    private String documentId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "document_type")
    private String documentType;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "intrash")
    private boolean intrash;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_sms_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSmsAt;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userId", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Collection<SmsAuditTrail> smsAuditTrailCollection;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userId", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Collection<SmsSystemUsers> smsSystemUsersCollection;
    
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsDepartment departmentId;
    
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userId", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
    private Collection<SmsUserRole> smsUserRoleCollection;

    public SmsUser() {
    }

    public SmsUser(Integer userId) {
        this.userId = userId;
    }

    public SmsUser(Integer userId, String msisdn, String firstName, String middleName, String descr, boolean smsSent, String documentId, String documentType, boolean intrash, Date lastSmsAt) {
        this.userId = userId;
        this.msisdn = msisdn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.descr = descr;
        this.smsSent = smsSent;
        this.documentId = documentId;
        this.documentType = documentType;
        this.intrash = intrash;
        this.lastSmsAt = lastSmsAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public boolean getSmsSent() {
        return smsSent;
    }

    public void setSmsSent(boolean smsSent) {
        this.smsSent = smsSent;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public boolean getIntrash() {
        return intrash;
    }

    public void setIntrash(boolean intrash) {
        this.intrash = intrash;
    }

    public Date getLastSmsAt() {
        return lastSmsAt;
    }

    public void setLastSmsAt(Date lastSmsAt) {
        this.lastSmsAt = lastSmsAt;
    }

    @XmlTransient
    public Collection<SmsAuditTrail> getSmsAuditTrailCollection() {
        return smsAuditTrailCollection;
    }

    public void setSmsAuditTrailCollection(Collection<SmsAuditTrail> smsAuditTrailCollection) {
        this.smsAuditTrailCollection = smsAuditTrailCollection;
    }

    @XmlTransient
    public Collection<SmsSystemUsers> getSmsSystemUsersCollection() {
        return smsSystemUsersCollection;
    }

    public void setSmsSystemUsersCollection(Collection<SmsSystemUsers> smsSystemUsersCollection) {
        this.smsSystemUsersCollection = smsSystemUsersCollection;
    }

    public SmsDepartment getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(SmsDepartment departmentId) {
        this.departmentId = departmentId;
    }

    @XmlTransient
    public Collection<SmsUserRole> getSmsUserRoleCollection() {
        return smsUserRoleCollection;
    }

    public void setSmsUserRoleCollection(Collection<SmsUserRole> smsUserRoleCollection) {
        this.smsUserRoleCollection = smsUserRoleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsUser)) {
            return false;
        }
        SmsUser other = (SmsUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsUser[ userId=" + userId + " ]";
    }
    
}
