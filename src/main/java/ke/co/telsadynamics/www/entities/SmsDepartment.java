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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tracom
 */
@Entity
@Table(name = "sms_department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsDepartment.findAll", query = "SELECT s FROM SmsDepartment s")
    , @NamedQuery(name = "SmsDepartment.findByDepartmentId", query = "SELECT s FROM SmsDepartment s WHERE s.departmentId = :departmentId")
    , @NamedQuery(name = "SmsDepartment.findByDepartmentName", query = "SELECT s FROM SmsDepartment s WHERE s.departmentName = :departmentName")
    , @NamedQuery(name = "SmsDepartment.findByDescription", query = "SELECT s FROM SmsDepartment s WHERE s.description = :description")
    /*, @NamedQuery(name = "SmsDepartment.findByCreatedAt", query = "SELECT s FROM SmsDepartment s WHERE s.createdAt = :createdAt")*/})
public class SmsDepartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "department_id")
    private Integer departmentId;
    
    @Size(max = 50)
    @Column(name = "department_name")
    private String departmentName;
    
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)    
    @OneToMany(mappedBy = "departmentId", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="documentId") 
    private Collection<SmsUser> smsUserCollection;

    public SmsDepartment() {
    }

    public SmsDepartment(Integer departmentId) {
        this.departmentId = departmentId;
    }

//    public SmsDepartment(Integer departmentId, Date createdAt) {
//        this.departmentId = departmentId;
//        this.createdAt = createdAt;
//    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }

    @XmlTransient
    public Collection<SmsUser> getSmsUserCollection() {
        return smsUserCollection;
    }

    public void setSmsUserCollection(Collection<SmsUser> smsUserCollection) {
        this.smsUserCollection = smsUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departmentId != null ? departmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsDepartment)) {
            return false;
        }
        SmsDepartment other = (SmsDepartment) object;
        if ((this.departmentId == null && other.departmentId != null) || (this.departmentId != null && !this.departmentId.equals(other.departmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.telsadynamics.www.entities.SmsDepartment[ departmentId=" + departmentId + " ]";
    }
    
}
