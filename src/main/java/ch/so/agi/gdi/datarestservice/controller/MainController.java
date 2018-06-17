package ch.so.agi.gdi.datarestservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.mapbox.geojson.*;

import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.json.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class MainController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/fubar", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> fubar() {
		log.info("Hallo Stefan.");
				
		String dbUrl = "jdbc:postgresql://192.168.50.6/pub";
		String dbUsr = "ddluser";
		String dbPwd = "ddluser";
		String dbDriver = "org.postgresql.Driver";
		
		String sql = "SELECT t_id, art_txt, bfs_nr, ST_AsGeoJSON(geometrie)::text, ST_AsEWKB(geometrie) AS geometry FROM agi_mopublic_pub.mopublic_bodenbedeckung_proj LIMIT 1";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd); PreparedStatement ps = conn.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					//list.add(rs.getInt("id"));
					//log.info(rs.getString("art_txt"));
					
					//log.info(rs.getString("geometry"));
					
					//Feature feat = Feature.fromJson(rs.getString("geometry"));
					
					//log.info(feat.toString());
					
					
					WKBReader wkbReader = new WKBReader();
					org.locationtech.jts.geom.Geometry geom = wkbReader.read(rs.getBytes("geometry"));
					//log.info(geom.toText());
					
					GeoJsonWriter geoJsonWriter = new GeoJsonWriter();
					//log.info(geoJsonWriter.write(geom));
		
					JSONObject jo = new JSONObject();
					jo.put("name", "jon doe");
					jo.put("age", "22");
					jo.put("city", "chicago");
					
					jo.put("art_txt", rs.getObject("art_txt"));
					jo.put("bfs_nr", rs.getObject("bfs_nr"));

					JSONObject jsonGeom = new JSONObject(geoJsonWriter.write(geom));
					jo.put("geometry", jsonGeom);

					//log.info(jo.toString());
					
					//return ResponseEntity.ok().body(feat.toJson());	
					
					JSONObject obj = new JSONObject(jo.toString());
					log.info(obj.toString());

					
					
					
					
					
					return ResponseEntity.ok().body(jo.toString(4));					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (org.locationtech.jts.io.ParseException e) {
			e.printStackTrace();
		}
		/*
        Connection conn = null;
        try {
			conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			// throw exception
		} finally {
			if (conn != null) {
				try { 
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}
		}
        */
        

		/*
		def sql = Sql.newInstance(dbUrl, dbUsr, dbPwd, dbDriver)
		sql.eachRow("SELECT t_id, art_txt, bfs_nr, ST_AsGeoJSON(geometrie) FROM agi_mopublic_pub.mopublic_bodenbedeckung_proj LIMIT 1") { row ->
			log.info row.toString()
			//log.info JsonOutput.toJson(row.toString())
		}
		
		sql.close()
		*/
		
		return ResponseEntity.ok().body("Fubar");
	}

}
