/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.mbeans;

import com.fdvsys.ases.datalayer.EntityManagerHelper;
import com.fdvsys.ases.entities.Roles;
import com.fdvsys.ases.entities.Users;
import com.fdvsys.ases.utils.DbProperties;

import com.fdvsys.ases.utils.JCrypto;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.apache.catalina.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Christian
 */
@ManagedBean(name = "UserBean")
@ApplicationScoped
public final class UserBean implements Serializable{

    private Collection<Users> activeUsers = new ArrayList<Users>();
    private static Collection<UserRole> permisions = new ArrayList<UserRole>();
    private UserRole selectedPermision = new UserRole();
    private Users selectedUser = new Users();
    private Users currentUser = new Users();
    private boolean forcePassword;
    private String rawPassword;
    private String rerawPassword;
    private Log log = LogFactory.getLog(UserBean.class);
    
    private boolean createmode=false;
    private int selectedProcessID;
    private int selectedPermisionKey;
    
    public static boolean isPostback() {
        FacesContext context = FacesContext.getCurrentInstance();
        return !context.getExternalContext().getRequestParameterMap().isEmpty();
    }
    public UserBean(){}
    
    
//    
//    public UserBean() {
//        try {
//
//            this.currentUser = new Users();
//            //this.currentUser.setUserId(0);
//            this.selectedUser = new Users();
//           //this.selectedUser.setUserId(0);
//            //Users ur =  EntityManagerHelper.getEntityManager().find(Users.class, 1);
//
//log.info("UserBean Constructor");
//            // this.setFname(ur.getFullname() );
//
//
//            //+ "---ENC>" + new JCrypto().encrypt("qwerty") + "----DEC> " + new JCrypto().decrypt(new JCrypto().encrypt("qwerty")) );
//            //this.setClearTextPassword( JCrypto.encrypt("qwerty") + ">>>" + JCrypto.decrypt("cXdlcnR5"));
//
//            //   userlist =  EntityManagerHelper.getEntityManager().
//
//        } catch (Exception ex) {
//        }
//
//
//    }

    
    private Collection<UserRole> GetUserRoleList(int userid){
        
        Collection<UserRole> list = new ArrayList<UserRole>();
        UserRole ur=null;
        Collection<com.fdvsys.ases.entities.Process> proclist = EntityManagerHelper.getEntityManager().createNamedQuery("Process.findAll").getResultList();
        
        
     String dbname = DbProperties.getDatabase();
        String qry =    "   SELECT \n" +
                        "		p.proc_id,\n" +
                        "		p.proc_display_name,\n" +
                        "		(SELECT CASE WHEN count(*) > 0 THEN r.role_id  ELSE 0 END  FROM " + dbname + ".roles r WHERE r.user_id = " + userid + " AND r.proc_id = p.proc_id) roleid,\n" +
                        "		(SELECT CASE WHEN count(*) > 0 THEN r.isactive ELSE 0 END  FROM " + dbname + ".roles r WHERE r.user_id = " + userid + " AND r.proc_id = p.proc_id) isactive\n" +
                        "   FROM \n" +
                        "		"+ dbname +".process p ";
        
        
        Collection perm = EntityManagerHelper.getEntityManager().createNativeQuery(qry).getResultList();
        
        
        Object[] obj;
        for(Object o: perm){
           
            obj = (Object[])o;
            
            ur=new UserRole();
            ur.setUserid(userid);
            ur.setProcid(Integer.parseInt(obj[0].toString()));
            ur.setProcessName(obj[1].toString());
            ur.setRoleid(Integer.parseInt(obj[2].toString()));
            ur.setHavePermision(Integer.parseInt(obj[3].toString()) == 0 ? "0":"1");
            
            list.add(ur);
            
            
        }
        return list;
  
        
    }
     public void onCellEdit(CellEditEvent event) {  
        Object oldValue = event.getOldValue();  
        Object newValue = event.getNewValue();  
          
        if(newValue != null && !newValue.equals(oldValue)) {  
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }  
    }  
    
     public void save(ActionEvent event) {
          
         
         
         
        
        
          System.out.println("Save Hire Event!!!");
       
          try{
        
            Users usr = this.currentUser;  
            if(usr.getUserId()!= 0)
                EntityManagerHelper.getEntityManager().find(Roles.class,usr.getUserId());
           
            
            usr.setUsername(currentUser.getUsername());
            usr.setName(currentUser.getName());
            usr.setLastname(currentUser.getLastname());
            usr.setFullname(currentUser.getName() + " " + currentUser.getLastname());
            usr.setIsActive(currentUser.getIsActive());
            usr.setIslocked(currentUser.getIsActive());
            usr.setLastChangePasswordDate(currentUser.getLastChangePasswordDate());
            usr.setNeedChangePassword(false);
            usr.setLastloginDate(currentUser.getLastloginDate());
              
              
            usr.setLastupdateDate(new Date());
            usr.setLastupdateUser(-1);
            if(usr.getUserId().equals(0)){
                usr.setCreationDate(new Date());
                usr.setCreationUser(-1);
                
            }
              
            if(!EntityManagerHelper.getEntityManager().getTransaction().isActive())
                EntityManagerHelper.getEntityManager().getTransaction().begin();
            
            if(this.selectedUser.getUserId().equals(0))
                EntityManagerHelper.getEntityManager().merge(this.selectedUser);

            EntityManagerHelper.getEntityManager().getTransaction().commit();
        
          
        this.currentUser = new Users();
        this.currentUser.setUserId(0);
        this.selectedUser = new Users();
        this.selectedUser.setUserId(0);
        this.createmode=false;
         this.selectedPermision = new UserRole();
        
        
        this.activeUsers = this.GetUserList();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Save Event!!!"));

        
          }catch(Exception ex){
              
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));

          }
        
        
    }
     
     public void updateSelectedRole(ValueChangeEvent event){
         this.setSelectedPermisionKey(Integer.parseInt(this.selectedPermision.getHavePermision()));
         System.out.println("updateSelectedRole");
     }
     
     public void saveRole(ActionEvent event){
         
          System.out.println("Save Hire Event!!!");
       
          try{
  
              
            UserRole ur = this.selectedPermision;
           // ur.setHavePermision(this.getSelectedPermisionKey());
            Roles role = new Roles();
            role.setRoleId(ur.getRoleid());
            
            if(ur.getRoleid()!= 0)
                EntityManagerHelper.getEntityManager().find(Roles.class,role.getRoleId());
            
            role.setProcId(ur.getProcid());
            role.setUserId(ur.getUserid());
            
            role.setIsactive(this.getSelectedPermisionKey());
            role.setLastupdateUser(-1);
            role.setLastupdateDate(new Date());

            if(!EntityManagerHelper.getEntityManager().getTransaction().isActive())
                EntityManagerHelper.getEntityManager().getTransaction().begin();
            
            if(role.getRoleId()==0)
                EntityManagerHelper.getEntityManager().merge(role);

            EntityManagerHelper.getEntityManager().getTransaction().commit();
        
            this.selectedPermision = new UserRole();
        
     
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Save Role " + ur.getProcessName() + "!!!"));

        
          }catch(Exception ex){
              
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));

          }
         
         
         
     }
     
     
     public void onEdit(RowEditEvent event) {  
       // FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getModel());  
  
      //  FacesContext.getCurrentInstance().addMessage(null, msg);  
        log.debug("onEdit Fire Event!!!!");
      System.out.println("onEdit Fire Event!!!!!");
        try{
      
         UserRole ur = ((UserRole)event.getObject());
         
        
         Roles role = new Roles();
         role.setRoleId(ur.getRoleid());
            
            if(ur.getRoleid()!= 0)
                EntityManagerHelper.getEntityManager().find(Roles.class,role.getRoleId());
            else{
                role.setUserId(this.selectedUser.getUserId());
                role.setProcId(ur.getProcid());
            }
            
            
            role.setIsactive(ur.getHavePermision().equals("1") ? 1:0);
            role.setLastupdateUser(-1);
            role.setLastupdateDate(new Date());

            if(!EntityManagerHelper.getEntityManager().getTransaction().isActive())
                EntityManagerHelper.getEntityManager().getTransaction().begin();
            
            if(role.getRoleId()==0)
                EntityManagerHelper.getEntityManager().merge(role);

            EntityManagerHelper.getEntityManager().getTransaction().commit();

           log.debug("Insert / Updated :" + ur.getProcessName());
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update Role :" + ur.getProcessName()));

        
        }catch(Exception ex){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));

        }
         
         
    }  
      
    public void onCancel(RowEditEvent event) {  
        //FacesMessage msg = new FacesMessage("Car Cancelled", ((Car) event.getObject()).getModel());  
  
        //FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
    
    public void createNewUser(ActionEvent event){
        
         System.out.println("createNewUser Hire Event!!!!");

        this.currentUser = new Users();
        this.currentUser.setUserId(-1);
        this.selectedUser = new Users();
        this.selectedUser.setUserId(0);
        this.forcePassword = true;
        this.currentUser.setIslocked(false);
        this.currentUser.setIsActive(true);
        this.createmode = true;
        log.debug("Create New User Hire Event!!!");
        
        
        
        
        
    }
    
    
    public void cancelEdit(ActionEvent event) {
            
        
        
        System.out.println("CancelEdit Hire Event!!!!");

        String lastuser = this.currentUser.getFullname();
        this.currentUser = new Users();
        this.currentUser.setUserId(0);
        this.selectedUser = new Users();
        this.selectedUser.setUserId(0);
        this.createmode=false;
        log.debug("CancelEdit Hire Event!!!");
        
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancel Edit User: " + lastuser));

    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("User Selected: ", ((Users) event.getObject()).getFullname());

        this.createmode=false;
        this.currentUser = ((Users) event.getObject());
        this.permisions = this.GetUserRoleList(this.currentUser.getUserId());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowSelectPerm(SelectEvent event) {
        
        this.selectedPermision =  (UserRole) event.getObject(); 
        FacesMessage msg = new FacesMessage("Role Selected: ", this.selectedPermision.getProcessName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    
    }
    
    
    public Collection<Users> GetUserList() {

        return EntityManagerHelper.getEntityManager().createNamedQuery("Users.findAll").getResultList(); //new ArrayList<Users>();
    }

    public Collection<Users> getActiveUsers() {
        return GetUserList();
    }

    public void setActiveUsers(Collection<Users> activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isForcePassword() {
        return forcePassword;
    }

    public void setForcePassword(boolean forcePassword) {
        this.forcePassword = forcePassword;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String getRerawPassword() {
        return rerawPassword;
    }

    public void setRerawPassword(String rerawPassword) {
        this.rerawPassword = rerawPassword;
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isCreatemode() {
        return createmode;
    }

    public void setCreatemode(boolean createmode) {
        this.createmode = createmode;
    }

    public Collection<UserRole> getPermisions() {
        
        int id = this.getCurrentUser().getUserId() != null ? this.getCurrentUser().getUserId() : 0;
        
        
        return this.GetUserRoleList(id);
    }

    public void setPermisions(Collection<UserRole> permisions) {
        this.permisions = permisions;
    }

    public int getSelectedProcessID() {
        return selectedProcessID;
    }

    public void setSelectedProcessID(int selectedProcessID) {
        this.selectedProcessID = selectedProcessID;
    }

    public int getSelectedPermisionKey() {
        return selectedPermisionKey;
    }

    public void setSelectedPermisionKey(int selectedPermisionKey) {
        this.selectedPermisionKey = selectedPermisionKey;
    }

    public UserRole getSelectedPermision() {
        return selectedPermision;
    }

    public void setSelectedPermision(UserRole selectedPermision) {
        this.selectedPermision = selectedPermision;
    }
    
    public class UserRole{
        
        private int procid;
        private int roleid;
        private String processName;
        private int userid;
        private String havePermision;

        
        
        
        public int getProcid() {
            return procid;
        }

        public void setProcid(int procid) {
            this.procid = procid;
        }

        public int getRoleid() {
            return roleid;
        }

        public void setRoleid(int roleid) {
            this.roleid = roleid;
        }

        public String getProcessName() {
            return processName;
        }

        public void setProcessName(String processName) {
            this.processName = processName;
        }

       

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getHavePermision() {
            return havePermision;
        }

        public void setHavePermision(String havePermision) {
            this.havePermision = havePermision;
        }
        
        
        
    }
}
