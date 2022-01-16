
package carsexample;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
public class CarFrame extends JFrame{
    
  private JButton addButton,exportButton,importButton;  
  private JTextPane showArea;
  private JPanel buttonPanel;
  private Connection conn =null;
  public void createDb() throws ClassNotFoundException
  {
      try {
          Class.forName("org.sqlite.JDBC");
          conn = DriverManager.getConnection("jdbc:sqlite:cars.db");
            String sql = "CREATE TABLE IF NOT EXISTS car (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	model text NOT NULL,\n"
                + "	brand text NOT NULL,\n"
                + "	price real\n"
                + ");";
            Statement st=conn.createStatement();
             st.execute(sql);
             
      } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Sql exception "+ex.getMessage());
      }
  }
  
  public void fillTextArea()
  {
      showArea.setText("");
      try {
          String  sql = "SELECT * FROM car";
          Statement stmt  = conn.createStatement();
          ResultSet rs    = stmt.executeQuery(sql);
          while(rs.next())
          {
              String t=rs.getString("brand")+","+rs.getString("model")+","+rs.getDouble("price");
              showArea.setText(showArea.getText()+t+"\n");
          }
      } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Sql exception "+ex.getMessage());
      }
  }
  public boolean carExists(Car c)
  {
      
      try {
          String  sql = "SELECT * FROM car where brand='"+
                  c.getbrand()+"' and model='"+c.getmodel()+"' and price="+c.getprice();
          Statement stmt  = conn.createStatement();
          ResultSet rs    = stmt.executeQuery(sql);
          int icount=0;
          while(rs.next()) icount++;
          if(icount >0) return true;
      } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Sql exception "+ex.getMessage());
      }
      return false;
  }
  
  public void addCar(Car c)
  {
      try {
          String sql = "INSERT INTO car(model,brand,price) VALUES(?,?,?)";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, c.getmodel());
          pstmt.setString(2, c.getbrand());
          pstmt.setDouble(3, c.getprice());
          pstmt.executeUpdate();
      } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Sql exception "+ex.getMessage());
      }
  }
  
  
  public void exportDb()
  {
       try {
           JFileChooser chooser=new JFileChooser();
           int ret=chooser.showSaveDialog(this);
           if(ret == JFileChooser.APPROVE_OPTION)
           {
               String filename=chooser.getSelectedFile().getAbsolutePath();
               FileWriter fw=new FileWriter(filename);
               PrintWriter pw=new PrintWriter(fw);
               String  sql = "SELECT * FROM car";
               Statement stmt  = conn.createStatement();
               ResultSet rs    = stmt.executeQuery(sql);
               while(rs.next())
               {
                  String t=rs.getString("brand")+","+rs.getString("model")+","+rs.getDouble("price");
                  pw.println(t);
               }
             fw.close();       
           }
        
      } catch (Exception ex) {
         JOptionPane.showMessageDialog(null,"Sql exception "+ex.getMessage());
      }
  }
  
  public void importDb()
  {
      try
      {
         JFileChooser chooser=new JFileChooser();
           int ret=chooser.showOpenDialog(this);
           if(ret == JFileChooser.APPROVE_OPTION)
           {
              String filename=chooser.getSelectedFile().getAbsolutePath();
              FileReader fr=new FileReader(filename);
              Scanner in = new Scanner(fr);
              while(in.hasNextLine())
              {
                  String line = in.nextLine();
                  String x[]=line.split(",");
                  if(x.length!=3) continue;
                  try{
                    Car g=new Car(x[0],x[1],Double.parseDouble(x[2]));
                    if(!carExists(g))
                        addCar(g);
                  }catch(Exception e)
                  {
                      
                  }
              }
              fr.close();
           }
      }
      catch(Exception e)
      {
       JOptionPane.showMessageDialog(null,"Sql exception "+e.getMessage());
      }
      fillTextArea();
  }
  
  public void makeButtons()
  {
      buttonPanel=new JPanel();
      add(buttonPanel);
      buttonPanel.setLayout(new GridLayout(1,3));
      addButton=new JButton("ADD CAR");
      buttonPanel.add(addButton);
      exportButton=new JButton("EXPORT DB");
      buttonPanel.add(exportButton);
      importButton=new JButton("IMPORT DB");
      buttonPanel.add(importButton);
      
       addButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              CarDialog dialog=new CarDialog(CarFrame.this,"Input car");
              Car c=dialog.getCar();
              if(c!=null)
              {
                  boolean f=carExists(c);
                  if(f)
                      JOptionPane.showMessageDialog(null, "Car exists");
                  else
                  {
                      addCar(c);
                      fillTextArea();
                  }
              }
          }
      });
       
      exportButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              exportDb();
          }
      });
      
     importButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              importDb();
          }
      });
  }

  public void makeTextArea()
  {
        showArea=new JTextPane();
        showArea.setPreferredSize( new Dimension( 500, 300 ) );
        showArea.setEditable(false);
        JScrollPane sp=new JScrollPane(showArea);
        add(sp);
  }
  
  public CarFrame(String title) throws ClassNotFoundException
  {
      super(title);
      setSize(500,300);
      setResizable(false);
      setLayout(new FlowLayout());
    
      makeButtons();
      makeTextArea();
      
        createDb();
        
      fillTextArea();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
  }
  
  
}
