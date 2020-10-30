package com.nagneo.vo;

public class ChampionVersionVO {
	private String type;
	private String format;
	private String version;
	private String id;
	private ChampionDataVO data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ChampionDataVO getData() {
		return data;
	}

	public void setData(ChampionDataVO data) {
		this.data = data;
	}

}
