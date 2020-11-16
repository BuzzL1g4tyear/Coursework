import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Users_DB {
    
    private static String server = "localhost";
    private static String login = "root";
    private static String password = "";
    private static String DB_Name = "users_db";
    private static Integer portnumber = 3306;
    
    private String login_;
    private String NN;
    private String pas;
    private String fname;
    private String sname;
    private String mail;
    private int id;
    
    public Users_DB(int uid, String ulogin, String uNN, String upas, String ufname, String usname, String umail){
	
	this.id = uid;
	this.login_ = ulogin;
	this.NN = uNN;
	this.pas = upas;
	this.fname = ufname;
	this.sname = usname;
	this.mail = umail;
	
    }
    
    public String getLogin(){
	return login_;
    }
    
    public String getNN(){
	return NN;
    }
    
    public String getPas(){
	return pas;
    }
    
    public String getFName(){
	return fname;
    }
    
    public String getSName(){
	return sname;
    }
    
    public String getEmail(){
	return mail;
    }
    
    public int getID(){
	return id;
    }
    
    public static Connection getConection(){
	Connection cnx = null;
	
	MysqlDataSource DataSource = new MysqlDataSource();
	DataSource.setServerName(server);
	DataSource.setUser(login);
	DataSource.setPassword(password);
	DataSource.setDatabaseName(DB_Name);
	DataSource.setPortNumber(portnumber);
	
	try {
	    cnx = (Connection) DataSource.getConnection();
	} catch (SQLException ex) {
	    Logger.getLogger("Получите соединение ->"+Users_DB.class.getName()).log(Level.SEVERE, null, ex);
	}
	return cnx;
    }

}
