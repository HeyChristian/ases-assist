/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DecimalFormat;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Collection;

/**
 *
 * @author Christian
 */
public class JFilesManager {

    public class FileInfo{
        
        private String name;
        private String date;
        private String time;
        private String size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
        
        
        
    }
    
    
    public Collection<FileInfo> getLoadDailyFiles() {
        
        Collection<FileInfo> filesInfo = new ArrayList<FileInfo>();
        FileInfo fileInfo=null;
        try {
            java.util.Date now = new java.util.Date();
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            String today = format.format(now);
            String update = today;

            //ud = request.getParameter("q");
            String pfx = AppProperties.getPreprocessDirectory();
            System.out.println(pfx);

            StringBuilder buf = new StringBuilder();
            StringBuilder fl = new StringBuilder();
            File[] listOfFiles = null;
            SimpleDateFormat d = new SimpleDateFormat("MM-dd-yyyy");
            SimpleDateFormat h = new SimpleDateFormat("hh:mm:ss a");
            DecimalFormat df = new DecimalFormat("#,###,###,##0");
            Boolean items = false;

            String fileName = "";
            String b = "            ";
            int fn = 0;

            File folder = new File(pfx);
            listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                int l = listOfFiles.length;
                for (int i = 0; i < l; i++) {
                    
                    fileInfo = new FileInfo();
                    
                    if (listOfFiles[i].isFile()) {
                        fileName = listOfFiles[i].getName();
                        fn = fileName.length();
                        if ((fn == 12 && fileName.endsWith(".enr"))
                                || ((fileName.startsWith("v") || fileName.startsWith("V")) && fileName.endsWith(".dat"))) {
                            //Create an instance of file object.
                            File file = new File(pfx + fileName);
                            //Get the last modification information.
                            Long lastModified = file.lastModified();
                            //Create a new date object and pass last modified information to the date object.
                            Date date = new Date(lastModified);
                            Long n = file.length() / 1024;
                            //Long n = file.length();
                            if (n > 9999999999L) {
                                n = 9999999999L;
                            }
                            if (n < 1L) {
                                n = 1L;
                            }
                            String s = b + df.format(n);
                            s = s.substring(s.length() - 12) + " kb";
                            if (items) {
                                buf.append("^");
                            }

                            buf.append(fileName + "^" + d.format(date) + "^" + h.format(date) + "^" + s);
                            fl.append(fileName);
                            
                            fileInfo.setName(fileName);
                            fileInfo.setDate(d.format(date));
                            fileInfo.setTime(h.format(date));
                            fileInfo.setSize(s);
                            
                            filesInfo.add(fileInfo);
                            
                            items = true;
                            if (i + 1 < l) {
                                fl.append(" ");
                            }
                        }
                    }
                }
            }

            System.out.print(buf);
            //request.getSession().setAttribute("fl", fl.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return filesInfo;
    }
}
