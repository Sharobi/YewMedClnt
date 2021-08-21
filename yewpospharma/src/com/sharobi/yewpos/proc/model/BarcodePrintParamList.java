/**
 *
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manodip Jana
 *
 * May 11, 2018
 */
public class BarcodePrintParamList implements Serializable{
private static final long serialVersionUID = 1L;

	/**
	 * The list of BarcodePrintParam
	 */
	private List<BarcodePrintParam> paramList;

	/**
	 * Default constructor.
	 */
	public BarcodePrintParamList() {
		this.paramList = new ArrayList<BarcodePrintParam>();
	}

	/**
	 * Constructor with the list of BarcodePrintParam <br/>
	 *
	 * @param barcodePrintParamList	The list of BarcodePrintParam
	 */
	public BarcodePrintParamList(List<BarcodePrintParam> barcodePrintParamList) {
		this.paramList = barcodePrintParamList;
	}

	/**
	 * @return the barcodePrintParamList
	 */
	public List<BarcodePrintParam> getParamList() {
		return paramList;
	}

	/**
	 * @param barcodePrintParamList the barcodePrintParamList to set
	 */
	public void setParamList(List<BarcodePrintParam> barcodePrintParamList) {
		this.paramList = barcodePrintParamList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BarcodePrintParamList [barcodePrintParamList=" + paramList + "]";
	}

}
