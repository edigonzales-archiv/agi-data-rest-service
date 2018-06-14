package ch.so.agi.gdi.datarestservice.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.http.MediaType

import static org.springframework.web.bind.annotation.RequestMethod.*

import org.springframework.http.ResponseEntity

import geoscript.layer.io.GeoJSONWriter
import geoscript.geom.Point
import geoscript.workspace.Workspace
import geoscript.workspace.PostGIS


@Controller
class MainController {
	private final Logger log = LoggerFactory.getLogger(this.getClass())
	
	//@GetMapping("/fubar")
	@RequestMapping(value="/fubar", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<?> fubar() {
		println("Hallo Welt.")
		
		def point = new Point(0,0)
		def poly = point.buffer(10)
		//println(poly.wkt)
		log.info(poly.wkt)
		
		def params = [
			dbtype: 'postgis',
			database: 'pub',
			host: '192.168.50.6',
			port: 5432,
			user: 'ddluser',
			passwd: 'ddluser'
		]
		//def workspace = Workspace.getWorkspace(params)
		/*
		List<Map> parameters = Workspace.getWorkspaceParameters("Postgis")
		parameters.each { Map param ->
			println "Parameter = ${param.key} Type = ${param.type} Required? ${param.required}"
		}
		*/
		
		
		PostGIS workspace = new PostGIS("pub", "192.168.50.6", "5432", "agi_mopublic_pub", "ddluser", "ddluser", false, false, 'Expose primary keys:true')
		//println workspace.getLayers()
		//println workspace.getLayers()
		
		List<Map> parameters = Workspace.getWorkspaceParameters("Postgis")
		parameters.each { Map param ->
			println "Parameter = ${param.key} Type = ${param.type} Required? ${param.required}"
		}

		
		println workspace.get("mopublic_bodenbedeckung_proj").getSchema()
		
		GeoJSONWriter writer = new GeoJSONWriter()
		String json = writer.write(workspace.get("mopublic_bodenbedeckung_proj"))
		//println json
		
		//return ResponseEntity.ok().body("Hallo Welt.");
		workspace.close()
		return ResponseEntity.ok().body(json);
	}
}
