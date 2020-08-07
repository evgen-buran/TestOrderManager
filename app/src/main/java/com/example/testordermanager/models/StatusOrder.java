package com.example.testordermanager.models;

public class StatusOrder {

	private final String NOT_READY = "not ready";
	private final String READY = "ready";
	private int idStatus;
	private String nameStatus;

	public StatusOrder() {
		this.nameStatus = NOT_READY;
	}

	public StatusOrder(int idStatus) {
		this.idStatus = idStatus;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}
}
