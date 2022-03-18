package com.revature.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import com.revature.util.MetaModel;

public class Config 
{
	private String username;
	private String password;
	private String url;
	
	private LinkedList<Class<?>> classesToRead;
	private LinkedList<MetaModel<?>> models;
	
	private Parser parse;
	
	public Config() {
		super();
		parse = new Parser();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Config(String username, String password, String url) {
		super();
		this.username = username;
		this.password = password;
		this.url = url;
		classesToRead = new LinkedList<Class<?>>();
		
		models = new LinkedList<MetaModel<?>>();
		
		parse = new Parser();
		
		models = (LinkedList<MetaModel<?>>) parse.inspectClass(classesToRead);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<MetaModel<?>> getModels()
	{
		return models;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addToClassQueue(Class<?> newClass)
	{
		classesToRead.add(newClass);
		models.add(new MetaModel(newClass));	
	}
	public Class<?> dequeueClassQueue()
	{
		return classesToRead.poll();
	}
	
	public void setClassesToRead(LinkedList<Class<?>> newList)
	{
		classesToRead = newList;
		models = (LinkedList<MetaModel<?>>) parse.inspectClass(classesToRead);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(password, url, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Config other = (Config) obj;
		return Objects.equals(password, other.password) && Objects.equals(url, other.url)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "Config [username=" + username + ", password=" + password + ", url=" + url + "]";
	}
}
