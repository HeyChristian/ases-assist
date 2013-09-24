/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByRoleId", query = "SELECT r FROM Roles r WHERE r.roleId = :roleId"),
    @NamedQuery(name = "Roles.findByUserId", query = "SELECT r FROM Roles r WHERE r.userId = :userId"),
    @NamedQuery(name = "Roles.findByProcId", query = "SELECT r FROM Roles r WHERE r.procId = :procId"),
    @NamedQuery(name = "Roles.findByIsactive", query = "SELECT r FROM Roles r WHERE r.isactive = :isactive")})
public class Roles implements Serializable {
    @Column(name = "lastupdate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdateDate;
    @Column(name = "isactive")
    private Integer isactive;
    
    @Column(name = "lastupdate_user")
    private Integer lastupdateUser;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "proc_id")
    private int procId;

    public Roles() {
    }

    public Roles(Integer roleId) {
        this.roleId = roleId;
    }

    public Roles(Integer roleId, int userId, int procId) {
        this.roleId = roleId;
        this.userId = userId;
        this.procId = procId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProcId() {
        return procId;
    }

    public void setProcId(int procId) {
        this.procId = procId;
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
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fdvsys.ases.bo.Roles[ roleId=" + roleId + " ]";
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

 

    public Integer getLastupdateUser() {
        return lastupdateUser;
    }

    public void setLastupdateUser(Integer lastupdateUser) {
        this.lastupdateUser = lastupdateUser;
    }

    public Date getLastupdateDate() {
        return lastupdateDate;
    }

    public void setLastupdateDate(Date lastupdateDate) {
        this.lastupdateDate = lastupdateDate;
    }
    
}
