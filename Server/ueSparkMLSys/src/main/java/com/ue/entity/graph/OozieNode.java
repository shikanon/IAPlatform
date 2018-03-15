/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package com.ue.entity.graph;



/**
 * Base node in Oozie workflow graph
 */
public abstract class OozieNode  {
	protected String id = "";

	/** moduleId id */
	protected String moduleId = "";

	/** (x, y) is the position in the panel */
	protected String x ;
	protected String y ;

	public OozieNode() {
	}

	public void init(String id, String moduleId,
					 String x, String y) {
		this.id = id;
		this.moduleId = moduleId;
		this.x = x;
		this.y = y;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public abstract String toXML();

	@Override
	public String toString() {
		return toXML();
	}
}
