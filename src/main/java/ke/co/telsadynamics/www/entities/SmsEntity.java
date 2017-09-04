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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_entity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsEntity.findAll", query = "SELECT s FROM SmsEntity s")
    , @NamedQuery(name = "SmsEntity.findByEntityId", query = "SELECT s FROM SmsEntity s WHERE s.entityId = :entityId")
    , @NamedQuery(name = "SmsEntity.findByEntityName", query = "SELECT s FROM SmsEntity s WHERE s.entityName = :entityName")
    , @NamedQuery(name = "SmsEntity.findByModule", query = "SELECT s FROM SmsEntity s WHERE s.module = :module")})
public class SmsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "entity_id")
    private Integer entityId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "entity_name")
    private String entityName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "module")
    private String module;
    
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "entityId", orphanRemoval = true, fetch = FetchType.EAGER)    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="entityId")
    private Collection<SmsEntityPermission> smsEntityPermissionCollection;

    public SmsEntity() {
    }

    public SmsEntity(Integer entityId) {
        this.entityId = entityId;
    }

    public SmsEntity(Integer entityId, String entityName, String module) {
        this.entityId = entityId;
        this.entityName = entityName;
        this.module = module;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @XmlTransient
    public Collection<SmsEntityPermission> getSmsEntityPermissionCollection() {
        return smsEntityPermissionCollection;
    }

    public void setSmsEntityPermissionCollection(Collection<SmsEntityPermission> smsEntityPermissionCollection) {
        this.smsEntityPermissionCollection = smsEntityPermissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entityId != null ? entityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsEntity)) {
            return false;
        }
        SmsEntity other = (SmsEntity) object;
        if ((this.entityId == null && other.entityId != null) || (this.entityId != null && !this.entityId.equals(other.entityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsEntity[ entityId=" + entityId + " ]";
    }
    
}
