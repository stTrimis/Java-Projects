/*This project is reserved by Stelios Trimis. Copyright © 2021*/

package data5_hasmap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class StudentFrame extends JFrame
{
  private int idcount=0;
  private JTextField nameText,lastnameText,idText;
  private JButton addButton,showButton,deleteButton,updateButton;
  private JPanel textPanel,buttonPanel;
  HashMap<Integer,Student> map=new HashMap<Integer,Student>();
  void makeTextPanel()
  {
     textPanel=new JPanel();
     textPanel.setLayout(new GridLayout(1,6));
     textPanel.add(new JLabel("Name: "));
     nameText=new JTextField("",15);
     textPanel.add(nameText);
     textPanel.add(new JLabel("Lastname: "));
     lastnameText=new JTextField("",15);
     textPanel.add(lastnameText);
     textPanel.add(new JLabel("Id: "));
     idText=new JTextField("",10);
     textPanel.add(idText);
     add(textPanel);
  }
  
  void makeButtonPanel()
  {
      buttonPanel=new JPanel();
      buttonPanel.setLayout(new GridLayout(1,4));
      addButton=new JButton("Add");
      addButton.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent ae) 
          {
              if(nameText.getText().length()==0 || lastnameText.getText().length()==0)
                  JOptionPane.showMessageDialog(null,"Empty fields");
              else
              {
                  Integer v=new Integer(idcount++);
                  Student s=new Student(v.intValue(),nameText.getText(),lastnameText.getText());
                  map.put(v,s);
              }
          }
          
      });
      buttonPanel.add(addButton);
      showButton=new JButton("Show");
      showButton.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent ae) 
          {
              String htmlText="<html><table border=1>";
              int i=0;
              for(Integer key : map.keySet())
              {
                  Student s=map.get(key);
                  htmlText+="<tr>";
                  htmlText+="<td>"+s.getid()+"</td>";
                  htmlText+="<td>"+s.getname()+"</td>";
                  htmlText+="<td>"+s.getlastname()+"</td>";
                  htmlText+="</tr>";
              }
              htmlText+="</table></border>";
              JOptionPane.showMessageDialog(null,htmlText);
          }
          
      });
      buttonPanel.add(showButton);
      deleteButton=new JButton("Delete");
      deleteButton.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent ae) 
          {
                if(idText.getText().length()==0)
                    JOptionPane.showMessageDialog(null,"Empty id text");
                else
                {
                    Integer key=new Integer(Integer.parseInt(idText.getText()));
                    if(map.containsKey(key))
                        map.remove(key);
                    else
                        JOptionPane.showMessageDialog(null,"id not found");
                }
          }
          
      }
      );
      buttonPanel.add(deleteButton);
      updateButton=new JButton("Update");
      updateButton.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent ae) 
          {
                if(idText.getText().length()==0)
                    JOptionPane.showMessageDialog(null,"Empty id text");
                else
                {
                     Integer key=new Integer(Integer.parseInt(idText.getText()));
                    if(map.containsKey(key))
                    {
                        Student s=new Student(key.intValue(),nameText.getText(),lastnameText.getText());
                        map.replace(key, s);
                    }
                    else
                        JOptionPane.showMessageDialog(null,"id not found");
                }
          }
          
      });
      buttonPanel.add(updateButton);
      add(buttonPanel);
  }
  
  public StudentFrame(String title)
  {
      super(title);
      setSize(500,150);
      setLayout(new GridLayout(2,1));
      makeTextPanel();
      makeButtonPanel();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
  }
}
