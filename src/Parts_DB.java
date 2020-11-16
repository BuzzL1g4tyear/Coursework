import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parts_DB{
    
    private static String server = "localhost";
    private static String login = "root";
    private static String password = "";
    private static String DB_Name = "parts_db";
    private static Integer portnumber = 3306;
    
    private int ID;
    private int ID_box;
    private int ID_bp;
    private int ID_cpu;
    private int ID_mem;
    private int ID_mb;
    private int ID_ssd;
    private int ID_vc;
    private int ID_user;
    private String Name;
    private String Maker;
    private String Cores;
    private String Vmem;
    private String Value;
    private String Volt;
    private String Form;
    private String Socket;
    private String Set;
    private String Rate;
    private String Chipset;
    private String Type_G_Mem;
    private String Type;
    private String Type_BOX;
    private String Timings;
    private String RAM;
    private String Cooling;
    private String Type_RAM;
    private String TDP;
    private int Power;
    private int Price;
    
    public Parts_DB(int pID, int pID_box, int pID_bp, int pID_cpu, int pID_mem, int pID_mb ,int pID_ssd , int pID_vc, int pID_user){
    
	this.ID = pID;
	this.ID_box = pID_box;
	this.ID_cpu = pID_cpu;
	this.ID_bp = pID_bp;
	this.ID_mem = pID_mem;
	this.ID_mb = pID_mb;
	this.ID_ssd = pID_ssd;
	this.ID_vc = pID_vc;
	this.ID_user = pID_user;
    
    }
    
    public Parts_DB(int pid,String pMaker,String pForm,String pSocket,String pChipset,String pRAM,String pType_RAM,int pPrice){
    
	this.ID = pid;
	this.Maker = pMaker;
	this.Form = pForm;
	this.Socket = pSocket;
	this.Chipset = pChipset;
	this.RAM = pRAM;
	this.Type_RAM = pType_RAM;
	this.Price = pPrice;
	
    }//8
    
    public Parts_DB(int pid,String pName,String pMaker,String pCores,String pRate,String pSocket,String pType,String pTDP,int pPrice){
    
	this.ID = pid;
	this.Name = pName;
	this.Maker = pMaker;
	this.Cores = pCores;
	this.Rate = pRate;
	this.Socket = pSocket;
	this.Type = pType;
	this.TDP = pTDP;
	this.Price = pPrice;
	
    }//9
    
    public Parts_DB(int pid,String pMaker,String pName,String pVmem,String pType_G_Mem,String pCooling,int pPrice){
    
	this.ID = pid;
	this.Maker = pMaker;
	this.Name = pName;
	this.Vmem = pVmem;
	this.Type_G_Mem = pType_G_Mem;
	this.Cooling = pCooling;
	this.Price = pPrice;
	
    }//7
    
    public Parts_DB(int pid,String pMaker,String pType_BOX,String pForm,String pCooling, int pPrice){
    
	this.ID=pid;
	this.Maker=pMaker;
	this.Type_BOX=pType_BOX;
	this.Form=pForm;
	this.Cooling=pCooling;
	this.Price=pPrice;
	
    }//6
    
    public Parts_DB(int pid,String pMaker,String pName,String pType_RAM,String pTimings,String pRate,String pValue,String pSet,String pVolt,int pPrice){
	
	this.ID=pid;
	this.Maker=pMaker;
	this.Name=pName;
	this.Type_RAM=pType_RAM;
	this.Timings=pTimings;
	this.Rate=pRate;
	this.Value=pValue;
	this.Set=pSet;
	this.Volt=pVolt;
	this.Price=pPrice;
	
    }//10
    
    public Parts_DB(int pid,String pMaker,int pPower,int pPrice){
	
	this.ID=pid;
	this.Maker=pMaker;
	this.Power=pPower;
	this.Price=pPrice;
	
    }//4
    
    public Parts_DB(int pid,String pMaker,String pName,String pValue,int pPrice){
	
	this.ID=pid;
	this.Maker=pMaker;
	this.Name=pName;
	this.Value=pValue;
	this.Price=pPrice;
	
    }//5
    
    public int getID_box(){
	return ID_box;
    }
    
    public int getID_bp(){
	return ID_bp;
    }
    
    public int getID_cpu(){
	return ID_cpu;
    }
    
    public int getID_mem(){
	return ID_mem;
    }
    
    public int getID_mb(){
	return ID_mb;
    }
    
    public int getID_ssd(){
	return ID_ssd;
    }
    
    public int getID_vc(){
	return ID_vc;
    }
    
    public int getID_user(){
	return ID_user;
    }
    
    public int getID(){
	return ID;
    }
    
    public String getName(){
	return Name;
    }
    
    public String getMaker(){
	return Maker;
    }
    
    public String getCores(){
	return Cores;
    }
    
    public String getForm(){
	return Form;
    }
    
    public String getRate(){
	return Rate;
    }
    
    public String getType(){
	return Type;
    }
    
    public String getSet(){
	return Set;
    }
    
    public String getValue(){
	return Value;
    }
    
    public String getVolt(){
	return Volt;
    }
    
    public String getType_BOX(){
	return Type_BOX;
    }
    
    public String getSocket(){
	return Socket;
    }
    
    public String getChipset(){
	return Chipset;
    }
    
    public String getRAM(){
	return RAM;
    }
    
    public String getVmem(){
	return Vmem;
    }
    
    public String getType_RAM(){
	return Type_RAM;
    }
    
    public String getType_G_Mem(){
	return Type_G_Mem;
    }
    
    public String getTDP(){
	return TDP;
    }

    public String getCooling(){
	return Cooling;
    }
    
    public String getTimings(){
	return Timings;
    }
    
    public int getPrice(){
	return Price;
    }
    
    public int getPower(){
	return Power;
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
	    Logger.getLogger("Получите соединение ->"+Parts_DB.class.getName()).log(Level.SEVERE, null, ex);
	}
	return cnx;
    }
}