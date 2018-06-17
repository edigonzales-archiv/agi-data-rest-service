package ch.so.agi.gdi.datarestservice.dbutil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String dbUrl;
	private String dbUsr;
	private String dbPwd;
	
	private static HashMap<String, String> jdbcDriverClasses = null;

	static {
		jdbcDriverClasses = new HashMap<String, String>();
		jdbcDriverClasses.put("postgresql", "org.postgresql.Driver");
		jdbcDriverClasses.put("sqlite", "org.sqlite.JDBC");
		jdbcDriverClasses.put("derby", "org.apache.derby.jdbc.EmbeddedDriver");
		jdbcDriverClasses.put("oracle", "oracle.jdbc.driver.OracleDriver");
	}

	private static ConnectionFactory instance = new ConnectionFactory();

	private ConnectionFactory() {} 
	
	private Connection createConnection(String dbUrl, String dbUsr, String dbPwd) {
		try {
            String[] splits = dbUrl.split(":");
            if (splits.length < 3)
                throw new IllegalArgumentException("Connection string is malformed: " + dbUrl);

            String driverType = splits[1];
            String driverClassName = jdbcDriverClasses.get(driverType);
            if(driverClassName == null)
                throw new IllegalArgumentException(
                        "Configuration error. ConnectionUrl contains unsupported driver type: " + driverType + "(" + dbUrl + ")");
 
            Driver driver = null;
            
            try {
                driver = (Driver)Class.forName(driverClassName).newInstance();
            }
            catch(Exception e) {
                throw new Exception("Could not find and load jdbc Driver Class " + driverClassName, e);
            }

            DriverManager.registerDriver(driver);
            
		} catch (Exception e) {
			log.error("Connection URL is undefined: " + dbUrl, e);
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error("Unable to Connect to Database.");
		}
		return connection;
	}
	
	public static Connection getConnection(String dbUrl, String dbUsr, String dbPwd) {
		return instance.createConnection(dbUrl, dbUsr, dbPwd);
	}
}
