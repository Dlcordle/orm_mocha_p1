package com.revature.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.util.MetaModel;

public class Parser 
{
	private static Logger logger = Logger.getLogger(Parser.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MetaModel<?>> inspectClass(LinkedList<Class<?>> clazzes)
	{
		LinkedList<MetaModel<?>> classList = new LinkedList<>();
		
		for(Class<?> classToInspect : clazzes)
		{
			MetaModel<?> buildObject = new MetaModel(classToInspect);
			buildObject.setPrimaryKeyField(buildObject.getPrimaryKey());
			
			try
			{
				buildObject.setForeignKeyFields(buildObject.getForeignKeys());
			}
			catch (Exception e)
			{
				logger.info(e.getMessage());
			}
			
			try
			{
				buildObject.setColumnFields(buildObject.getColumns());
			}
			catch (Exception e)
			{
				logger.info(e.getMessage());
			}
			
			classList.add(buildObject);
		}
		
		return classList;
	}
}
