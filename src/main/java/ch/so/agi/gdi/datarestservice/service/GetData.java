package ch.so.agi.gdi.datarestservice.service;

import org.json.JSONObject;

public interface GetData {
	JSONObject getDataSetViewByName(String dataSetViewName, String dbUrl, String dbUsr, String dbPwd);
}
