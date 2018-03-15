/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package com.ue.entity.workflow;


import com.ue.entity.Program;
import org.dom4j.Element;

public class DistributeActionNodeDef extends ActionNodeDef {

	public DistributeActionNodeDef(Program program, String nodeId, String cmdLine) {
		super(program, nodeId, cmdLine);
	}

	@Override
	protected void appendFileParameter(Element shell) {
	}

}
