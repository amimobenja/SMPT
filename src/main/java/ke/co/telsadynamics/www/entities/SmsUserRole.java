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
@Table(name = "sms_user_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsUserRole.findAll", query = "SELECT s FROM SmsUserRole s")
    , @NamedQuery(name = "SmsUserRole.findByUserRoleId", query = "SELECT s FROM SmsUserRole s WHERE s.userRoleId = :userRoleId")})
public class SmsUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_role_id")
    private Integer userRoleId;
    
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsRoles roleId;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SmsUser userId;

    public SmsUserRole() {
    }

    public SmsUserRole(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public SmsRoles getRoleId() {
        return roleId;
    }

    public void setRoleId(SmsRoles roleId) {
        this.roleId = roleId;
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
        hash += (userRoleId != null ? userRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsUserRole)) {
            return false;
        }
        SmsUserRole other = (SmsUserRole) object;
        if ((this.userRoleId == null && other.userRoleId != null) || (this.userRoleId != null && !this.userRoleId.equals(other.userRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsUserRole[ userRoleId=" + userRoleId + " ]";
    }
    
}
