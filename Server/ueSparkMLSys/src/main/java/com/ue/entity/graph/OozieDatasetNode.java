/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package com.ue.entity.graph;




/**
 * Dataset node in Oozie workflow graph
 */
public class OozieDatasetNode extends OozieNode  {

	/** File id */
	private String file = "";

	public OozieDatasetNode() {
		super();
	}

	public void init(String widgetId, String datasetId, String filePath,
					 String x, String y) {
		init(widgetId, datasetId, x, y);
		file = filePath;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	/** Generate a XML String for the node */
	@Override
	public String toXML() {
		StringBuffer sb = new StringBuffer(100);
		sb.append("<widget type='dataset'>\n");
		sb.append("  <id>" + id + "</id>\n");
		sb.append("  <moduleId>" + moduleId + "</moduleId>\n");
		sb.append("  <x>" + x + "</x>\n");
		sb.append("  <y>" + y + "</y>\n");
		sb.append("  <file>" + file + "</file>\n");
		sb.append("</widget>\n");
		return sb.toString();
	}
}
