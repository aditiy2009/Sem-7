/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication19;


import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author root
 */
public class Main_list_panel extends javax.swing.JPanel {
    List<issue> issuelist;
     DefaultListModel listModel = new DefaultListModel();
    JPanel cards;
    static issue selected;
    ArrayList<issue> displaylist=new ArrayList<issue>();
    Socket soc;
   principal p;
   admin a;
   User u;
   String username;
   
    public issue get_selectedissue(){
    
    return selected;
    }
  
   
    public Main_list_panel()  {
                initComponents();
                
                
        
    
        jList1.setModel(listModel);
       // System.out.println(jList1.getSelectedIndex());
        //if(jList1.getSelectedIndex()==-1){ 
             
        //}
      
        //populate(); 
       //particulars("ALL","Sort by Rating(High to Low)","ALL");
       //selected=issuelist.get(0);
    }
   public void getobjs(principal p,admin a,User u)
    {
                 this.p=p;
                this.a=a;
                this.u=u;
    }
    void setsocket(Socket soc) throws IOException, ClassNotFoundException
    {
    this.soc = soc;
    
    
        try {
        try {
            retrieve();
        } catch (SQLException ex) {
            Logger.getLogger(Main_list_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger(Main_list_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void setuname(String uname)
     {
       username = uname;    
     }
    
    
    
    /*public Main_list_panel(String status,String sort,String subject) {
        initComponents();
        logic2(status,sort,subject);
    }*/
    
  /*  public void logic2(String status,String sort,String subject){
     try {
            retrieve2(status,sort,subject);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_list_panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main_list_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        populate();  

        
        jList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()){
               //ENTER ON CLICK CODE 
                  
                 }
                
            }
        });
    }*/
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setBackground(new java.awt.Color(1, 29, 58));
        setFont(new java.awt.Font("Waree", 2, 36)); // NOI18N

        jList1.setBackground(new java.awt.Color(1, 29, 58));
        jList1.setFont(new java.awt.Font("DejaVu Serif", 0, 24)); // NOI18N
        jList1.setForeground(new java.awt.Color(254, 254, 254));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
             
             if(jList1.getSelectedIndex()!=-1){  
             
             selected=displaylist.get((int)jList1.getSelectedIndex());
              System.out.println(selected.get_title()+"  "+selected.get_date()+" Index"+jList1.getSelectedIndex());
             }
             a.create.setVisible(true);
              a.view.setVisible(true);
              a.edit.setVisible(true);
              a.deleteb.setVisible(true);
              
              u.create.setVisible(true);
              u.view.setVisible(true);
              
             
              
              if(username.equals(selected.UID))
              {
              u.deleteb.setVisible(true);
              u.edit.setVisible(true);
              }
              else
              {
               u.deleteb.setVisible(false);
              u.edit.setVisible(false);
              }
              
             
             if("Awaiting approval".equals(selected.Status))
             {
             p.solved.setVisible(false);
             p.approved.setVisible(true);
             }
             if("Approved".equals(selected.Status))
             {
             p.solved.setVisible(true);
             p.approved.setVisible(false);
             }
             if("Solved".equals(selected.Status))
             {
             p.solved.setVisible(false);
             p.approved.setVisible(true);
             }
             
             return;
    }//GEN-LAST:event_jList1ValueChanged

    void populate(){
    //issue i1=new issue("U1510121","Title"," Description","Students",123998,98);
    //issue i2=new issue("U1121021","Ti2323"," dsfsdfription","HAHA",15498,78);
    //issuelist.add(i1);
    //issuelist.add(i2);
    //jList1.setListData(issue.toArray());
   // for(issue i:issuelist){
        //jList1.add(i.get_title());
   // }
   
     for(issue i:issuelist){
         String rowOne = "<html> "
                 + "<h1 style=\"color:#2da87f;\">Title:"+i.get_title()+"</h1><br> Subject:"+i.get_subject()+"<br> Status:"+i.get_status()+"<br> Rating:"+i.get_rating()+"<br> Date:"+i.get_date()+"<br><br><br></html>";
       // listModel.addElement(i.get_title()+" \t"+i.get_subject()); 
        listModel.addElement(rowOne);
      //  System.out.println(i.get_title()); 
     }


    }
    
    void retrieve() throws ClassNotFoundException, SQLException, IOException{
   // Connection conn = null;
   //Statement stmt = null;
      //STEP 2: Register JDBC driver
      //Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      //System.out.println("Connecting to a selected database...");
     // conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      //System.out.println("Creating statement...");
     // stmt = conn.createStatement();
     
//     
//     jdbc j = new jdbc();
//      String sql = "SELECT * FROM issue";
//      ResultSet rs = j.getaccess(sql);
//      //STEP 5: Extract data from result set
//      while(rs.next()){
//         //Retrieve by column name
//        
//        //  System.out.println(rs.getString(1));
//         issue i1=new issue(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(8),rs.getString(7));
//         issuelist.add(i1);
//      }
//      selected=issuelist.get(0);

    // System.out.println("mainlist panel :"+soc.getPort()+" gis: "+soc.getInputStream()+" gos "+soc.getOutputStream());
            DataInputStream din=new DataInputStream(soc.getInputStream());
            DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
            
            Gson g = new Gson();
            String ar;
            dos.writeUTF("1");
            dos.flush();
            
           // dos.writeUTF("hi");
//           int n=0;
//           while(n!=100)
//           {
//            System.out.println(n+" : "+din.available());
//        try {
//            Thread.sleep(400);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Main_list_panel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            n++;
//             ar = din.readUTF();
//           }
//
//           while(din.available()!=9)
//            {
//                System.out.println(din.available());
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Main_list_panel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            }
           
            ar = din.readUTF();
            System.out.println(ar);
            
            
            
            
           issuelist = g.fromJson(ar , new TypeToken<List<issue>>(){}.getType());
           System.out.println(issuelist.get(0).IID);
           // jList1.setModel(listModel);
            particulars("ALL","Sort by Rating(High to Low)","ALL");
            //selected=issuelist.get(0);
 
    }
   /*  void retrieve2(String status,String sort,String subject) throws ClassNotFoundException, SQLException{
   // Connection conn = null;
   //Statement stmt = null;
      //STEP 2: Register JDBC driver
      //Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      //System.out.println("Connecting to a selected database...");
     // conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      //System.out.println("Creating statement...");
     // stmt = conn.createStatement();
     jdbc j = new jdbc();
      String sql = "SELECT * FROM issue where Subject="+subject;
      ResultSet rs = j.getaccess(sql);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
        
          System.out.println(rs.getString(1));
         issue i1=new issue(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
         issuelist.add(i1);
      }
    }*/
     
     void refresh_list() throws ClassNotFoundException, SQLException, IOException{
     //issuelist.clear();
     retrieve();
     particulars("ALL","Sort by Rating(High to Low)","ALL");
     
     a.view.setVisible(false);
     a.deleteb.setVisible(false);
     a.edit.setVisible(false);
     
       u.view.setVisible(false);
     u.deleteb.setVisible(false);
     u.edit.setVisible(false);
     
     p.approved.setVisible(false);
     p.solved.setVisible(false);
     }
     
     void particulars(String status,String sort,String subject){
     
         
         
         listModel.clear();
       displaylist.clear();
       if(sort.equals("Sort by Rating(Low to High)"))
       Collections.sort(issuelist, issue.ratingComparatoraesc);
       else if(sort.equals("Sort by Rating (High to Low)"))
       Collections.sort(issuelist, issue.ratingComparatordesc);
//       else if(sort.equals("Sort by Date (High to Low)"))
//              Collections.sort(issuelist, issue.dateComparatordesc);
//       else if(sort.equals("Sort by Date (Low to High)"))
//           Collections.sort(issuelist, issue.dateComparatoraesc);
//       
     for(issue i:issuelist){
         if((i.get_subject().equals(subject)||subject=="ALL")&&(i.get_status().equals(status)||status=="ALL")){
         displaylist.add(i);
         //String rowOne = "<html><body width='390px'><hr width='1000px'><div align='right'>"+i.get_rating()+"</div><h1>Title: "+i.get_title()+"</h1><br></div><h2>Subject: "+i.get_subject()+"<br> Status: "+i.get_status()+"<div align='right'>"+i.get_date()+"</div></h2></body></html>";
         
         String rowOne = "<html><body width='390px'><hr width='1000px'><pa>Title: "+i.get_title()+"<div align='right'>"+i.get_rating()+"</div></pa><br><h2>Subject: "+i.get_subject()+"<br> Status: "+i.get_status()+"<div align='right'>"+i.get_date()+"</div></h2></body></html>";

//         String rowOne = "<html> "
//                 + "<h1 style=\"color:#2da87f;\">Title:"+i.get_title()+"</h1><br> Subject:"+i.get_subject()+"<br> Status:"+i.get_status()+"<br> Rating:"+i.get_rating()+"<br><br></html>";
       // listModel.addElement(i.get_title()+" \t"+i.get_subject()); 
        listModel.addElement(rowOne);
       // System.out.println(i.get_title());
         }
     }
     }
    
     
 
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}