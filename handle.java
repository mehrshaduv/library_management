package com.senator.librarymanagement;
// Java Program to illustrate
// reading from Text File
// using Scanner Class
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class handle {
    private String BookName, AuthorName, code;
    
    
    private File file;
    private ArrayList<String> data = new ArrayList<String>();
    public handle(String FileName)
    {
        try {
            file = new File(FileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
              data.add(sc.nextLine());
            }
            sc.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
    }
    
    public void AddValue(String BookName, String AuthorName, String code) throws IOException
    {
        if(check_id(code) && !code.isEmpty() )
        {
            data.add(code + ":" + BookName + ":" + AuthorName);
            write_value();
            JOptionPane.showMessageDialog(null, "Added successfully.\nplease click update button to see resualt.");
        
        }
        
    }
    
    public boolean check_id(String code)
    {
        String[] st;
        for (int i = 0; i < data.size(); i++) {
            st = data.get(i).split(":",0);
            if(code.equals(st[0]))
            {
                JOptionPane.showMessageDialog(null, "Invalid code !! This code has already been used.");
                return false;
            }
        }
        return true;
        
                
    }
    
    public void searche_value(String x)
    {
        int t = 0;
        String[] st;
        String a;
        for (int i = 0; i < data.size(); i++) {
            st = data.get(i).split(":",0);
            for (int j = 0; j < st.length; j++) {
                a = st[j].toUpperCase();
                if(a.contains(x.toUpperCase()))
                {
                    JOptionPane.showMessageDialog(null, "founded !!\ncode:BookName:AuthoreName\n"+data.get(i).strip());
                    t++;
                }
            }
        }
        if(t == 0)
            JOptionPane.showMessageDialog(null, "not found !!");
        
    }
    
    public void delete_value(String x) throws IOException
    {
        int t = 0;
        String[] st;
        String a, inp;
        for (int i = 0; i < data.size(); i++) {
            st = data.get(i).split(":",0);
            for (int j = 0; j < st.length; j++) {
                a = st[j].toUpperCase();
                if(a.contains(x.toUpperCase()))
                {
                    JOptionPane.showMessageDialog(null, "founded !!\ncode:BookName:AuthoreName\n"+data.get(i).strip());
                    t++;
                    inp = JOptionPane.showInputDialog(null,"Are you sure you want to delete the value?","no");
                    if(inp.toUpperCase().contains("YES"))
                        data.remove(i);
                }
            }
        }
        if(t == 0)
            JOptionPane.showMessageDialog(null, "not found !!");
        else
        {
            write_value();
            JOptionPane.showMessageDialog(null, "Removed successfully.\nplease click update button to see resualt.");
        
        }
    }
    
    
    public void edit_value(String x) throws IOException
    {
        int t = 0,tc = 0;
        String[] st;
        String a, inp,inp2;
        for (int i = 0; i < data.size(); i++) {
            st = data.get(i).split(":",0);
            for (int j = 0; j < st.length; j++) {
                a = st[j].toUpperCase();
                if(a.contains(x.toUpperCase()))
                {
                    JOptionPane.showMessageDialog(null, "founded !!\ncode:BookName:AuthoreName\n"+data.get(i).strip());
                    
                    inp = JOptionPane.showInputDialog(null,"Are you sure you want to edit the value?","no");
                    if(inp.toUpperCase().contains("YES"))
                    {
                        this.code = JOptionPane.showInputDialog(null,"Enter the code : ","example : 1234");
                        this.BookName = JOptionPane.showInputDialog(null,"Enter the book name : ","example : java programing");
                        this.AuthorName = JOptionPane.showInputDialog(null,"Enter the authore name : ","example : SeN[a]ToR");
                        inp2 = JOptionPane.showInputDialog(null,"Are you sure you want to edit the value?\n"+data.get(i).strip()+"\nnew value : \n"+this.code+":"+this.BookName+":"+this.AuthorName,"no");
                        if(inp2.toUpperCase().contains("YES"))
                        {
                            data.set(i, this.code+":"+this.BookName+":"+this.AuthorName);
                            t++;
                        }
                        else
                            tc++;
                    }
                    else
                        tc++;
                }
            }
        }
        if(t == 0)
            JOptionPane.showMessageDialog(null, "not found !!");
        else
        {
            write_value();
            JOptionPane.showMessageDialog(null, "edit " + t + " process was successfuly.\nplease click update button to see resualt.");
        }
        if(tc > 0)
            JOptionPane.showMessageDialog(null, "You have canceled the "+tc+" edit process !!");
        
    }
    
    public void write_value() throws IOException
    {
        FileWriter fw = new FileWriter("Database.dat");
        BufferedWriter bw = new BufferedWriter(fw);    
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i) != "")
                bw.write(data.get(i) + "\n");
        }
        bw.close();
        fw.close();
    }
    
}
