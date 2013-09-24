/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.mbeans;

import com.fdvsys.ases.utils.AppProperties;
import com.fdvsys.ases.utils.JFilesManager;
import com.fdvsys.ases.utils.JFilesManager.FileInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Christian
 */
@ManagedBean
@RequestScoped
public class DailyBean implements Serializable {

    /**
     * Creates a new instance of DailyBean
     */
    private Collection<FileInfo> files = new ArrayList();
    private Collection<FileInfo> selectedFiles = new ArrayList();
    private String updateDate;
    private String processTime;
    private String logText;

    public DailyBean() {
    }

    public void merge() {

        Boolean isSecond = false;
        String dir = AppProperties.getScriptsDirectory();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String script = "datmrge.sh ";
        
        try{
            Date  date =  new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(updateDate);
            this.setUpdateDate(date.toString());
        }catch(Exception ex){
            
        }
        /*
         String [] v =request.getParameter("q").split("\\^");
         String d = v[0];
         String fl = (String) session.getAttribute("fl");
         String [] f = fl.split(" ");
         */


        String cmd = null;// dir + "datmerge.sh " + d + " ";

        /*for (int i = 1; i < v.length ; i++) {
    
         int j = Integer.parseInt(v[i]) - 1;
         if ((f[j].startsWith("V") || f[j].startsWith("v")) && f[j].endsWith(".dat")) {
        
         if (isSecond) cmd = cmd + " -g ";
        
         cmd = cmd + f[j];
        
         isSecond = true;
         }
      
         }*/
        //df.format()

        for (FileInfo f : this.selectedFiles) {


            cmd = dir + script + "d " + this.getUpdateDate();
            if ((f.getName().startsWith("V") || f.getName().startsWith("v")) && f.getName().endsWith(".dat")) {

                if (isSecond) {
                    cmd = cmd + " -g ";
                }

                cmd = cmd + f.getName();
                isSecond = true;

            }




            try {
                System.out.println("cmd=" + cmd);
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = in.readLine();
                while (line != null) {
                    System.out.print(" " + line + "\n");
                    this.logText = this.logText + "\n" + line;
                    line = in.readLine();
                }
                in.close();
            } catch (IOException e) {
                System.out.println(e);
                    this.logText = this.logText + "\n ERROR: \n" + e.getMessage();
            }
            
            
        }

    }

    public Collection<FileInfo> getFiles() {
        return new JFilesManager().getLoadDailyFiles();
    }

    public void setFiles(Collection<FileInfo> files) {
        this.files = files;
    }

    public Collection<FileInfo> getSelectedFiles() {
        return selectedFiles;
    }

    public void setSelectedFiles(Collection<FileInfo> selectedFiles) {
        this.selectedFiles = selectedFiles;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }
}
