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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsRoles.findAll", query = "SELECT s FROM SmsRoles s")
    , @NamedQuery(name = "SmsRoles.findByRoleId", query = "SELECT s FROM SmsRoles s WHERE s.roleId = :roleId")
    , @NamedQuery(name = "SmsRoles.findByRoleName", query = "SELECT s FROM SmsRoles s WHERE s.roleName = :roleName")
    , @NamedQuery(name = "SmsRoles.findByDescription", query = "SELECT s FROM SmsRoles s WHERE s.description = :description")
   /*, @NamedQuery(name = "SmsRoles.findByCreationDate", query = "SELECT s FROM SmsRoles s WHERE s.creationDate = :creationDate")*/
    , @NamedQuery(name = "SmsRoles.findByIntrash", query = "SELECT s FROM SmsRoles s WHERE s.intrash = :intrash")})
public class SmsRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;
    
    @Size(max = 100)
    @Column(name = "role_name")
    private String roleName;
    
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Basic(optional = false)
    @Column(name = "intrash")
    private boolean intrash;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @OneToMany(mappedBy = "roleId",  orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="roleId")     
    private Collection<SmsUserRole> smsUserRoleCollection;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  
    @OneToMany(mappedBy = "roleId", orphanRemoval = true, fetch = FetchType.EAGER) 
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="roleId") 
    private Collection<SmsRolesPermissionMap> smsRolesPermissionMapCollection;

    public SmsRoles() {
    }

    public SmsRoles(Integer roleId) {
        this.roleId = roleId;
    }

    public SmsRoles(Integer roleId, boolean intrash) {
        this.roleId = roleId;
        this.intrash = intrash;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getIntrash() {
        return intrash;
    }

    public void setIntrash(boolean intrash) {
        this.intrash = intrash;
    }

    @XmlTransient
    public Collection<SmsUserRole> getSmsUserRoleCollection() {
        return smsUserRoleCollection;
    }

    public void setSmsUserRoleCollection(Collection<SmsUserRole> smsUserRoleCollection) {
        this.smsUserRoleCollection = smsUserRoleCollection;
    }

    @XmlTransient
    public Collection<SmsRolesPermissionMap> getSmsRolesPermissionMapCollection() {
        return smsRolesPermissionMapCollection;
    }

    public void setSmsRolesPermissionMapCollection(Collection<SmsRolesPermissionMap> smsRolesPermissionMapCollection) {
        this.smsRolesPermissionMapCollection = smsRolesPermissionMapCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsRoles)) {
            return false;
        }
        SmsRoles other = (SmsRoles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsRoles[ roleId=" + roleId + " ]";
    }
    
}
