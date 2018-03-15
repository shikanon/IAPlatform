/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package com.ue.util;


import com.ue.util.typeparser.StringToTypeParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * Read the service configuration from
 * server-config.xml resource file。
 */
public  class Constants {

	public static String OOZIE_CLIENT="http://hadoop-master:11000/oozie";
	public static String NAME_NODE="hdfs://hadoop-master:9000";
	public static String JOB_TRACKER="hadoop-master:8032";
	public static String QUEUE_NAME="default";
	public static String APP_WORKSPACE="/EML/oozie";
	public static String DRAFT_PATH="/EML/draft";
	public static String DATASET_PATH="/EML/Data";
	public static String MODULE_PATH="/EML/Modules";
	public static String DB_URL;
	public static String DB_USER;
	public static String DB_PASSWORD;
	public static Integer DB_TIMEOUT;
	/**********************************************
	 *********** MAIL *****************
	 **********************************************/
	public static String MAIL_HOST="mail.software.ict.ac.cn";
	public static String MAIL_USERNAME="bda@software.ict.ac.cn";
	public static String MAIL_PASSWORD="bda@ict";
	public static String HOSTNAME_CON="true";
	//static Logger logger = Logger.getLogger(Constants.class.getName());

	public  static  String SAMPLE_PATH="/EML/SAMPLE";

//	static {
//		Document dom = null;
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		try {
//			//Get the configuration file and build the xml dom object.
//
//			//InputStream in=classLoader.getResourceAsStream("server-config.xml");
//			InputStream in = new FileInputStream(new File("D:\\优异科技\\EMLnew\\ueSparkMLSys\\ueSparkMLSys\\src\\main\\resources\\server-config.xml"));
//			//classLoader.getResourceAsStream("server-config.xml")
//			dom = XMLUtil.read(in);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		/**
//		 * Use the java reflection mechanism to set the corresponding properties
//		 */
//		Class<Constants> clazz = Constants.class;
//		StringToTypeParser parser = StringToTypeParser.newBuilder().build();
//
//		Element root = dom.getDocumentElement();
//		NodeList nlist = root.getChildNodes();
//		int len = nlist.getLength();
//		for (int i = 0; i < len; ++i) {
//			Node tagNode = nlist.item(i);
//			Node valNode = tagNode.getFirstChild();
//			if (valNode == null)
//				continue;
//			String name = tagNode.getNodeName();
//			try {
//				Field f = clazz.getDeclaredField(name);
//				String val = valNode.getNodeValue();
//				// Use parser to automatically identify the type, and for type conversion, parse (String, Class)
//				f.set(null, parser.parse(val, f.getType()));
//				logger.info(name + " " + val);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//	}

}
