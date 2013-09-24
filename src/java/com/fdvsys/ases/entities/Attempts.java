/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "attempts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attempts.findAll", query = "SELECT a FROM Attempts a"),
    @NamedQuery(name = "Attempts.findByAttemptId", query = "SELECT a FROM Attempts a WHERE a.attemptId = :attemptId"),
    @NamedQuery(name = "Attempts.findByUserId", query = "SELECT a FROM Attempts a WHERE a.userId = :userId"),
    @NamedQuery(name = "Attempts.findByCount", query = "SELECT a FROM Attempts a WHERE a.count = :count")})
public class Attempts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "attempt_id")
    private Integer attemptId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "count")
    private int count;

    public Attempts() {
    }

    public Attempts(Integer attemptId) {
        this.attemptId = attemptId;
    }

    public Attempts(Integer attemptId, int userId, int count) {
        this.attemptId = attemptId;
        this.userId = userId;
        this.count = count;
    }

    public Integer getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Integer attemptId) {
        this.attemptId = attemptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attemptId != null ? attemptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attempts)) {
            return false;
        }
        Attempts other = (Attempts) object;
        if ((this.attemptId == null && other.attemptId != null) || (this.attemptId != null && !this.attemptId.equals(other.attemptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fdvsys.ases.bo.Attempts[ attemptId=" + attemptId + " ]";
    }
    
}
