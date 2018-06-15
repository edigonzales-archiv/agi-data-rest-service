package ch.so.agi.gdi.datarestservice.controller

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.http.MediaType

import static org.springframework.web.bind.annotation.RequestMethod.*

import org.springframework.http.ResponseEntity

import groovy.sql.Sql
import groovy.json.JsonOutput

@Controller
class MainController {
	//private final Logger log = LoggerFactory.getLogger(this.getClass())
	Logger log = LoggerFactory.getLogger( this.getClass() );
	
	
	//@GetMapping("/fubar")
	@RequestMapping(value="/fubar", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> fubar() {
		log.info "Hallo Stefan"
				
		def dbUrl = "jdbc:postgresql://192.168.50.6/pub"
		def dbUsr = "ddluser"
		def dbPwd = "ddluser"
		def dbDriver = "org.postgresql.Driver" 
		def sql = Sql.newInstance(dbUrl, dbUsr, dbPwd, dbDriver)
		sql.eachRow("SELECT t_id, art_txt, bfs_nr, ST_AsGeoJSON(geometrie) FROM agi_mopublic_pub.mopublic_bodenbedeckung_proj LIMIT 1") { row ->
			log.info row.toString()
			//log.info JsonOutput.toJson(row.toString())
		}
		
		sql.close()
		
		
		return ResponseEntity.ok().body("Fubar");
	}
}
