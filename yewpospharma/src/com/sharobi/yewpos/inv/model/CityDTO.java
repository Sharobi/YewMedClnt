/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int stateId;
	private String stateName;
	private int countryId;
	private String countryName;

 

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}



	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}



	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}



	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}



	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}



	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}



	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}



	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityDTO [id=" + id + ", name=" + name + ", stateId=" + stateId + ", stateName=" + stateName
				+ ", countryId=" + countryId + ", countryName=" + countryName + "]";
	}

	
	
}
