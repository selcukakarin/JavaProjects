
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Selcuk
 */
public class CalisanIslemleri {
    
    private Connection con=null;
    
    private Statement statement=null;
    
    private PreparedStatement  preparedStatement=null;  
    
    public ArrayList<Calisan> calisanlariGetir(){
        ArrayList<Calisan> cikti=new ArrayList<Calisan>();
        
        try {
            statement=con.createStatement();
            
            String sorgu="Select * from calisanlar";
            
            ResultSet rs=statement.executeQuery(sorgu);
            
            while(rs.next()){
                
                int id=rs.getInt("id");
                String ad=rs.getString("ad");
                String soyad=rs.getString("soyad");
                String dept=rs.getString("departman");
                String maas=rs.getString("maas");
                
                cikti.add(new Calisan(id, ad, soyad, dept, maas));
                
            }
            
            return cikti;
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
        
        
        
    }
    
    public boolean girisYap(String kullaniciAdi,String parola){
        
        String sorgu="Select * from adminler where username=? and password=?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, parola);
            
            ResultSet rs=preparedStatement.executeQuery();
            
            /*
            if(rs.next()==false){
                
                return false;
                
            }else{
                
                return true;
                
            }
*/
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
    public void calisanSil(int id){
        
        String sorgu="Delete from calisanlar where id=?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void calisanGuncelle(int id,String yeniAd,String yeniSoyad,String yeniDepartman,String yeniMaas){
        
        String sorgu="Update calisanlar set ad=? , soyad=? , departman=? , maas=? where id=?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, yeniAd);
            preparedStatement.setString(2, yeniSoyad);
            preparedStatement.setString(3, yeniDepartman);
            preparedStatement.setString(4, yeniMaas);
            preparedStatement.setInt(5, id);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void calisanEkle(String ad,String soyad,String departman,String maas){
        
        String sorgu="insert into calisanlar(ad,soyad,departman,maas) values (?,?,?,?)";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, departman);
            preparedStatement.setString(4, maas);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public CalisanIslemleri(){
        
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.dbIsmi+ "?useUnicode=true&characterEncoding=utf8";
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
        
        
        try {
            con = DriverManager.getConnection(url, Database.kullaniciAdi, Database.parola);
            System.out.println("Bağlantı Başarılı...");
            
            
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            //ex.printStackTrace();
        }
        
    }
    
    
}
