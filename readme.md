Hibernate-Utils is my personal tools. Using to generate database schema according to hibernate configuration.

##Requirements
---
* JDK 1.6 or later
* Ant 1.8 & Ivy 2.3. or later


##Usage
---

###1.Install Dependencies & compile

    git clone https://github.com/lvjian700/hibernate-utils.git
    cd hibernate-utils
    ant compile

Dependences:    

* Hibernate-tools 3.6 or later
* apache commons-cli 1.2

    
###2.Test project
    
    ant dbhelp

It output some help document:
    
    dbhelp:
     [java] usage: Using Hibernate Schema Tools, to create or update database schema.
     [java]  -a,--action <arg>    create | update database tables.
     [java]  -f,--cfgfile <arg>   the hibernate.cfg.xml file name.
     [java]  -h,--help            the command help

###3.Package Jar
    
    ant jar

You will find jar file in bin/jar. Copy it to your project.


###4.Custom execution script
hibernate-utils support 3 ant task:    

* dbhelp: help document
* dbcreate: create database schema
* dbupdate: update database schema

You can find these in build.xml. Add them to your project.

    <target name="dbhelp"  description="help [hibernate export tools]">
		<java fork="true" classname="com.dayang.tools.HibernateSchemaExport"  
				classpathref="classpath">
			<classpath path="${classes.dir}" />
			<arg value="-h" />
		</java>
	</target>

	<target name="dbcreate" 
		description="create database [hibernate export tools]">

		<java fork="true" classname="com.dayang.tools.HibernateSchemaExport" 
			 	classpathref="classpath">
			<classpath path="${classes.dir}" />
			<arg value="-a" />
			<arg value="create" />
			<arg value="-f" />
			<arg value="com/dayang/tools/sample/hibernate.cfg.xml" />

		</java>
	</target>

	<target name="dbupdate" 
		description="update database [hibernate export tools]">

		<java fork="true" classname="com.dayang.tools.HibernateSchemaExport"  
				classpathref="classpath">
			<classpath path="${classes.dir}" />
			<arg value="-a" />
			<arg value="update" />
			<arg value="-f" />
			<arg value="com/dayang/tools/sample/hibernate.cfg.xml" />

		</java>
	</target>




