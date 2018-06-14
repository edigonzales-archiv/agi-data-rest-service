package ch.so.agi.gdi.datarestservice.controller;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geojson.feature.*;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@Controller
public class MainController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
			
	@RequestMapping(value="/fubar", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> fubar() throws IOException  {
        Map<String, Object> params = new HashMap<>();
        params.put("dbtype", "postgis");
        params.put("host", "192.168.50.6");
        params.put("port", 5432);
        params.put("database", "pub");
        params.put("schema", "agi_mopublic_pub");        
        params.put("user", "ddluser");
        params.put("passwd", "ddluser");
        params.put("Expose primary keys", true);
        
        DataStore dataStore = DataStoreFinder.getDataStore(params);

        SimpleFeatureSource source = dataStore.getFeatureSource("mopublic_bodenbedeckung_proj");
        SimpleFeatureCollection fc = source.getFeatures();
        
        FeatureJSON fjson = new FeatureJSON();
        StringWriter writer = new StringWriter();
        
        
//        SimpleFeatureIterator iterator = fc.features();
//        try {
//            while (iterator.hasNext()) {
//                SimpleFeature feature = iterator.next();
//                fjson.writeFeature(feature, writer);
//            }
//        } finally {
//            iterator.close();
//        }
        
        fjson.writeFeatureCollection(fc, writer);
        String json = writer.toString();

        
        System.out.println(dataStore.getNames());
		
		
		return ResponseEntity.ok().body(json);
	}
}
