/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_audit_trail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsAuditTrail.findAll", query = "SELECT s FROM SmsAuditTrail s")
    , @NamedQuery(name = "SmsAuditTrail.findByLogId", query = "SELECT s FROM SmsAuditTrail s WHERE s.logId = :logId")
    /*, @NamedQuery(name = "SmsAuditTrail.findByOccurrenceTime", query = "SELECT s FROM SmsAuditTrail s WHERE s.occurrenceTime = :occurrenceTime")*/
    , @NamedQuery(name = "SmsAuditTrail.findByActivityType", query = "SELECT s FROM SmsAuditTrail s WHERE s.activityType = :activityType")
    , @NamedQuery(name = "SmsAuditTrail.findByEntityName", query = "SELECT s FROM SmsAuditTrail s WHERE s.entityName = :entityName")
    , @NamedQuery(name = "SmsAuditTrail.findByEntityId", query = "SELECT s FROM SmsAuditTrail s WHERE s.entityId = :entityId")
    , @NamedQuery(name = "SmsAuditTrail.findByDescription", query = "SELECT s FROM SmsAuditTrail s WHERE s.description = :description")
    , @NamedQuery(name = "SmsAuditTrail.findByStatus", query = "SELECT s FROM SmsAuditTrail s WHERE s.status = :status")
    , @NamedQuery(name = "SmsAuditTrail.findBySource", query = "SELECT s FROM SmsAuditTrail s WHERE s.source = :source")
    , @NamedQuery(name = "SmsAuditTrail.findByIpAddress", query = "SELECT s FROM SmsAuditTrail s WHERE s.ipAddress = :ipAddress")})
public class SmsAuditTrail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_id")
    private Integer logId;
    
//    @Column(name = "occurrence_time")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date occurrenceTime;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "activity_type")
    private String activityType;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "entity_name")
    private String entityName;
    
    @Column(name = "entity_id")
    private Integer entityId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "source")
    private String source;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ip_address")
    private String ipAddress;
    
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsUser userId;

    public SmsAuditTrail() {
    }

    public SmsAuditTrail(Integer logId) {
        this.logId = logId;
    }

    public SmsAuditTrail(Integer logId, String activityType, String entityName, String description, String status, String source, String ipAddress) {
        this.logId = logId;
        this.activityType = activityType;
        this.entityName = entityName;
        this.description = description;
        this.status = status;
        this.source = source;
        this.ipAddress = ipAddress;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

//    public Date getOccurrenceTime() {
//        return occurrenceTime;
//    }
//
//    public void setOccurrenceTime(Date occurrenceTime) {
//        this.occurrenceTime = occurrenceTime;
//    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsAuditTrail)) {
            return false;
        }
        SmsAuditTrail other = (SmsAuditTrail) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsAuditTrail[ logId=" + logId + " ]";
    }
    
}
