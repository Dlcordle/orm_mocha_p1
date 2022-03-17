package com.revature.service;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Config 
{
	private String username;
	private String password;
	private String url;
	
	private LinkedList<Class<?>> classesToRead;
	
	public Config() {
		super();
	}
	public Config(String username, String password, String url) {
		super();
		this.username = username;
		this.password = password;
		this.url = url;
		classesToRead = new LinkedList<Class<?>>();
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
	
	
	public void addToClassQueue(Class<?> newClass)
	{
		classesToRead.add(newClass);
	}
	public Class<?> dequeueClassQueue()
	{
		return classesToRead.poll();
	}
	
	public void setClassesToRead(LinkedList<Class<?>> newList)
	{
		classesToRead = newList;
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
