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
@Table(name = "process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Process.findAll", query = "SELECT p FROM Process p WHERE p.isActive = 1"),
    @NamedQuery(name = "Process.findByProcId", query = "SELECT p FROM Process p WHERE p.procId = :procId"),
    @NamedQuery(name = "Process.findByProcName", query = "SELECT p FROM Process p WHERE p.procName = :procName"),
    @NamedQuery(name = "Process.findByProcUrl", query = "SELECT p FROM Process p WHERE p.procUrl = :procUrl"),
    @NamedQuery(name = "Process.findByProcDisplayName", query = "SELECT p FROM Process p WHERE p.procDisplayName = :procDisplayName"),
    @NamedQuery(name = "Process.findByIsmenu", query = "SELECT p FROM Process p WHERE p.ismenu = :ismenu")})
public class Process implements Serializable {
    @Column(name = "isActive")
    private Boolean isActive;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proc_id")
    private Integer procId;
    @Basic(optional = false)
    @Column(name = "proc_name")
    private String procName;
    @Column(name = "proc_url")
    private String procUrl;
    @Basic(optional = false)
    @Column(name = "proc_display_name")
    private String procDisplayName;
    @Column(name = "ismenu")
    private Boolean ismenu;

    public Process() {
    }

    public Process(Integer procId) {
        this.procId = procId;
    }

    public Process(Integer procId, String procName, String procDisplayName) {
        this.procId = procId;
        this.procName = procName;
        this.procDisplayName = procDisplayName;
    }

    public Integer getProcId() {
        return procId;
    }

    public void setProcId(Integer procId) {
        this.procId = procId;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public String getProcUrl() {
        return procUrl;
    }

    public void setProcUrl(String procUrl) {
        this.procUrl = procUrl;
    }

    public String getProcDisplayName() {
        return procDisplayName;
    }

    public void setProcDisplayName(String procDisplayName) {
        this.procDisplayName = procDisplayName;
    }

    public Boolean getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(Boolean ismenu) {
        this.ismenu = ismenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procId != null ? procId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Process)) {
            return false;
        }
        Process other = (Process) object;
        if ((this.procId == null && other.procId != null) || (this.procId != null && !this.procId.equals(other.procId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fdvsys.ases.bo.Process[ procId=" + procId + " ]";
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
}
