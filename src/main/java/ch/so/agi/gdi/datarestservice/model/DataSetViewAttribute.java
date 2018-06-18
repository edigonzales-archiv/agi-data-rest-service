package ch.so.agi.gdi.datarestservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "data_set_view_attributes")
@Table(schema = "gdi_knoten")
public class DataSetViewAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gdi_oid")
	private Long gdiOid;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "alias")
	private String alias;
	
	@Column(name = "attribute_order")
	private int attributeOrder;

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getAttributeOrder() {
		return attributeOrder;
	}

	public void setAttributeOrder(int attributeOrder) {
		this.attributeOrder = attributeOrder;
	}

	@ManyToOne
	@JoinColumn(name = "gdi_oid_data_set_view")
	private DataSetView dataSetView;
	
	public DataSetView getDataSetView() {
		return dataSetView;
	}

}
