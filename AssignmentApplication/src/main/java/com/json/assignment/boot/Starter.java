package com.json.assignment.boot;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.json.assignment.webservice.JsonWebservice;

@ApplicationPath("/assignment")
public class Starter extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public Starter() {
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		singletons.add(new JsonWebservice());

		return singletons;
	}
}
