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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_entity_permission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsEntityPermission.findAll", query = "SELECT s FROM SmsEntityPermission s")
    , @NamedQuery(name = "SmsEntityPermission.findByPermissionId", query = "SELECT s FROM SmsEntityPermission s WHERE s.permissionId = :permissionId")
    , @NamedQuery(name = "SmsEntityPermission.findByPermission", query = "SELECT s FROM SmsEntityPermission s WHERE s.permission = :permission")
    , @NamedQuery(name = "SmsEntityPermission.findByCaption", query = "SELECT s FROM SmsEntityPermission s WHERE s.caption = :caption")})
public class SmsEntityPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "permission_id")
    private Integer permissionId;
    
    @Size(max = 50)
    @Column(name = "permission")
    private String permission;
    
    @Size(max = 50)
    @Column(name = "caption")
    private String caption;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @OneToMany(mappedBy = "permissionId", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="permissionId")     
    private Collection<SmsRolesPermissionMap> smsRolesPermissionMapCollection;
    
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsEntity entityId;

    public SmsEntityPermission() {
    }

    public SmsEntityPermission(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @XmlTransient
    public Collection<SmsRolesPermissionMap> getSmsRolesPermissionMapCollection() {
        return smsRolesPermissionMapCollection;
    }

    public void setSmsRolesPermissionMapCollection(Collection<SmsRolesPermissionMap> smsRolesPermissionMapCollection) {
        this.smsRolesPermissionMapCollection = smsRolesPermissionMapCollection;
    }

    public SmsEntity getEntityId() {
        return entityId;
    }

    public void setEntityId(SmsEntity entityId) {
        this.entityId = entityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionId != null ? permissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsEntityPermission)) {
            return false;
        }
        SmsEntityPermission other = (SmsEntityPermission) object;
        if ((this.permissionId == null && other.permissionId != null) || (this.permissionId != null && !this.permissionId.equals(other.permissionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsEntityPermission[ permissionId=" + permissionId + " ]";
    }
    
}
