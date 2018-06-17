package ch.so.agi.gdi.datarestservice.service;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONObject;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ch.so.agi.gdi.datarestservice.dbutil.ConnectionFactory;

@Service
public class GetDataImpl implements GetData {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public JSONObject getDataSetViewByName(String dataSetName, String dbUrl, String dbUsr, String dbPwd) {

		String sql = "SELECT t_id, art_txt, bfs_nr, ST_AsEWKB(geometrie) AS geometry FROM agi_mopublic_pub.mopublic_bodenbedeckung_proj LIMIT 1";
		try (Connection conn = ConnectionFactory.getConnection(dbUrl, dbUsr, dbPwd); 
				PreparedStatement ps = conn.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					log.info(rs.getObject(1).toString());			
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} 
		/*
		catch (org.locationtech.jts.io.ParseException e) {
			e.printStackTrace();
		}
		*/
		
		return null;
	}

}
