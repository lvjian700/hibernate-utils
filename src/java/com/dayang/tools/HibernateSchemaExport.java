package com.dayang.tools;

import java.net.URL;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

public class HibernateSchemaExport {
	
	static final String A_CREATE = "create";
	static final String A_UPDATE = "update";
	
	static Configuration config = null;
	
	static Options opts = new Options();
	
	static {
		
	}
	/**
	 * 根据cfg文件,生成数据库
	 * @param args
	 * cfg.xml file path
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException { 
		
		opts.addOption("h", "help", false, "the command help");
		opts.addOption("a", "action", true, "create | update database tables.");
		opts.addOption("f", "cfgfile", true, 
				"the hibernate.cfg.xml file name.");
		
		CommandLineParser parser = new PosixParser();
		CommandLine cl = parser.parse(opts, args);
		
		
		if(cl.hasOption("h")) {
			 
			printHelp(opts);
			 
			return;
		}
		
		String action = cl.getOptionValue("action");
		String cfgFilePath = cl.getOptionValue("cfgfile");
		
		System.out.println("Execute ...");
		System.out.print("do action:");
		System.out.println(action);
		System.out.print("Hibernate configuration file:");
		System.out.println(cfgFilePath);
		
		URL path = ClassLoader.getSystemResource(cfgFilePath);
		System.out.println("getResource, the hibernate config file path");
		System.out.println(path);
		
		config = new Configuration().configure(path);
		
		if(config == null) {
			System.err.println("Can not found hibernate config file. \n Please check your path");
			System.err.println(cfgFilePath);
			
			return;
		}
		
		if(action.equalsIgnoreCase(A_CREATE)) {
			System.out.println("Do create database tables process...");
			doCreate(config);
			
		} else if(action.equalsIgnoreCase(A_UPDATE)) {
			System.out.println("Do update database tables proces...");
			doUpdate(config);
		}		

	}
	
	static void doUpdate (Configuration config) {
		SchemaUpdate schemaUpdate = new SchemaUpdate(config);
		schemaUpdate.execute(true, true);
	}
	
	static void doCreate(Configuration config) {
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}
	
	static void printHelp(Options opts) {
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("Using Hibernate Schema Tools, to create or update database schema.", opts);
	}
}