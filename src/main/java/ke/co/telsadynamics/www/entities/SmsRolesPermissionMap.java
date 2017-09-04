/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.entities;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_roles_permission_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsRolesPermissionMap.findAll", query = "SELECT s FROM SmsRolesPermissionMap s")
    , @NamedQuery(name = "SmsRolesPermissionMap.findByRolePermissionMapId", query = "SELECT s FROM SmsRolesPermissionMap s WHERE s.rolePermissionMapId = :rolePermissionMapId")})
public class SmsRolesPermissionMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_permission_map_id")
    private Integer rolePermissionMapId;
    
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsEntityPermission permissionId;
    
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsRoles roleId;

    public SmsRolesPermissionMap() {
    }

    public SmsRolesPermissionMap(Integer rolePermissionMapId) {
        this.rolePermissionMapId = rolePermissionMapId;
    }

    public Integer getRolePermissionMapId() {
        return rolePermissionMapId;
    }

    public void setRolePermissionMapId(Integer rolePermissionMapId) {
        this.rolePermissionMapId = rolePermissionMapId;
    }

    public SmsEntityPermission getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(SmsEntityPermission permissionId) {
        this.permissionId = permissionId;
    }

    public SmsRoles getRoleId() {
        return roleId;
    }

    public void setRoleId(SmsRoles roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolePermissionMapId != null ? rolePermissionMapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsRolesPermissionMap)) {
            return false;
        }
        SmsRolesPermissionMap other = (SmsRolesPermissionMap) object;
        if ((this.rolePermissionMapId == null && other.rolePermissionMapId != null) || (this.rolePermissionMapId != null && !this.rolePermissionMapId.equals(other.rolePermissionMapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsRolesPermissionMap[ rolePermissionMapId=" + rolePermissionMapId + " ]";
    }
    
}
