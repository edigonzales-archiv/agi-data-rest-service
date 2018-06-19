package ch.so.agi.gdi.datarestservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "data_source")
@Table(schema = "gdi_knoten")
public class DataSource implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum ConnectionType {
        database, directory;
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gdi_oid")
	private Long gdiOid;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "connection")
	private String connection;

	@Column(name = "connection_type")
    @Enumerated(EnumType.STRING)
	private ConnectionType connection_type;

	public Long getGdiOid() {
		return gdiOid;
	}

	public void setGdiOid(Long gdiOid) {
		this.gdiOid = gdiOid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public ConnectionType getConnection_type() {
		return connection_type;
	}

	public void setConnection_type(ConnectionType connection_type) {
		this.connection_type = connection_type;
	}
	
	@OneToMany(mappedBy = "dataSource")
	private List<DataSet> dataSets = new ArrayList<DataSet>();

	public List<DataSet> getDataSets() {
		return dataSets;
	}
}
