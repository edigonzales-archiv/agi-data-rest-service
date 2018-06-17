package ch.so.agi.gdi.datarestservice.service;

import org.json.JSONObject;

public interface GetData {
	JSONObject getDataSetViewByName(String dataSetName, String dbUrl, String dbUsr, String dbPwd);
}
