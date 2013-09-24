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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"), //WHERE u.isActive = 1"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByLastloginDate", query = "SELECT u FROM Users u WHERE u.lastloginDate = :lastloginDate"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByLastname", query = "SELECT u FROM Users u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Users.findByFullname", query = "SELECT u FROM Users u WHERE u.fullname = :fullname"),
    @NamedQuery(name = "Users.findByCreationDate", query = "SELECT u FROM Users u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "Users.findByCreationUser", query = "SELECT u FROM Users u WHERE u.creationUser = :creationUser"),
    @NamedQuery(name = "Users.findByLastupdateDate", query = "SELECT u FROM Users u WHERE u.lastupdateDate = :lastupdateDate"),
    @NamedQuery(name = "Users.findByLastupdateUser", query = "SELECT u FROM Users u WHERE u.lastupdateUser = :lastupdateUser"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByIslocked", query = "SELECT u FROM Users u WHERE u.islocked = :islocked")})
public class Users implements Serializable {
    @Column(name = "needChangePassword")
    private Boolean needChangePassword;
    
    @Column(name = "lastChangePasswordDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChangePasswordDate;
    @Basic(optional = false)
    @Column(name = "isActive")
    private boolean isActive;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "lastlogin_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastloginDate;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "fullname")
    private String fullname;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "creation_user")
    private int creationUser;
    @Basic(optional = false)
    @Column(name = "lastupdate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdateDate;
    @Basic(optional = false)
    @Column(name = "lastupdate_user")
    private int lastupdateUser;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "islocked")
    private Boolean islocked;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String username, String name, Date creationDate, int creationUser, Date lastupdateDate, int lastupdateUser, String password) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.creationDate = creationDate;
        this.creationUser = creationUser;
        this.lastupdateDate = lastupdateDate;
        this.lastupdateUser = lastupdateUser;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastloginDate() {
        return lastloginDate;
    }

    public void setLastloginDate(Date lastloginDate) {
        this.lastloginDate = lastloginDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(int creationUser) {
        this.creationUser = creationUser;
    }

    public Date getLastupdateDate() {
        return lastupdateDate;
    }

    public void setLastupdateDate(Date lastupdateDate) {
        this.lastupdateDate = lastupdateDate;
    }

    public int getLastupdateUser() {
        return lastupdateUser;
    }

    public void setLastupdateUser(int lastupdateUser) {
        this.lastupdateUser = lastupdateUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIslocked() {
        return islocked;
    }

    public void setIslocked(Boolean islocked) {
        this.islocked = islocked;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fdvsys.ases.bo.Users[ userId=" + userId + " ]";
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

  

    public Date getLastChangePasswordDate() {
        return lastChangePasswordDate;
    }

    public void setLastChangePasswordDate(Date lastChangePasswordDate) {
        this.lastChangePasswordDate = lastChangePasswordDate;
    }

    public Boolean getNeedChangePassword() {
        return needChangePassword;
    }

    public void setNeedChangePassword(Boolean needChangePassword) {
        this.needChangePassword = needChangePassword;
    }
    
}
