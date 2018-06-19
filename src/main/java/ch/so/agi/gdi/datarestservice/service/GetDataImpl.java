package ch.so.agi.gdi.datarestservice.service;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.json.JSONObject;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ch.so.agi.gdi.datarestservice.dbutil.ConnectionFactory;
import ch.so.agi.gdi.datarestservice.model.DataSetView;
import ch.so.agi.gdi.datarestservice.repository.DataSetViewRepository;

@Service
public class GetDataImpl implements GetData {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSetViewRepository dataSetViewRepository;

	@Override
	public JSONObject getDataSetViewByName(String dataSetViewName, String dbUrl, String dbUsr, String dbPwd) {

		// 1049: ch.so.agi.grundbuchplan_f.bodenbedeckung_projektiert (keine Attribute)
		// 2481: ch.so.alw.bienenstandorte_und_sperrgebiete.bienenstandorte
		Optional<DataSetView> dataSetView = dataSetViewRepository.findById(2481L); 
		log.info("*********");
		log.info(dataSetView.get().getName().toString());
		log.info(dataSetView.get().getDataSet().getDataSource().getName());
		log.info(dataSetView.get().getDataSet().getDataSetName());
//		log.info(dataSetView.get().getDataSetViewAttributes().get(0).getName().toString());
//		log.info(String.valueOf(dataSetView.get().getDataSetViewAttributes().size()));
		dataSetView.get().getDataSetViewAttributes().forEach(attr -> log.info("attrName: " + attr.getName()));
		
		
		log.info("*********");


		
		// we need to obtain pk and geometry_column
		
		
		
//		String sql = "SELECT t_id, art_txt, bfs_nr, ST_AsEWKB(geometrie) AS geometry FROM agi_mopublic_pub.mopublic_bodenbedeckung_proj LIMIT 1";
//		try (Connection conn = ConnectionFactory.getConnection(dbUrl, dbUsr, dbPwd); 
//				PreparedStatement ps = conn.prepareStatement(sql);) {
//			try (ResultSet rs = ps.executeQuery();) {
//				while (rs.next()) {
//					log.info(rs.getObject(1).toString());			
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			log.error(e.getMessage());
//		} 
		/*
		catch (org.locationtech.jts.io.ParseException e) {
			e.printStackTrace();
		}
		*/
		
		return null;
	}
	
//	private DataSource getDataSource(String user, String password) {
//	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//	    dataSourceBuilder.url("jdbc:postgresql://192.168.50.6:5432/pub");
//	    dataSourceBuilder.username("ddluser");
//	    dataSourceBuilder.password("ddluser");
//	    return dataSourceBuilder.build();   
//	}

	
//	private void getDataSetMeta(String dataSetName, ) {
//		
//	}

}
