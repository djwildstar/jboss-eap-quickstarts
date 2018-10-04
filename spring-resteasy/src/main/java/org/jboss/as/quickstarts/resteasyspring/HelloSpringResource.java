/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.resteasyspring;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Joshua Wilson
 *
 */
@Path("/")
public class HelloSpringResource {
	private static int submissions = 0;
	private static int accepted = 0;
	private static int rejected = 0;

    @Autowired
    GreetingBean greetingBean;

    /**
     * Create a default REST endpoint that directs the user to use the demonstration endpoints.
     *
     * @return html
     */
    @GET
    @Produces("text/html")
    public Response getDefault() {
        String msg = "Hello. Supported RESTful methods include:<br>"
        	+ "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/status'>jboss-spring-resteasy/status</a> - Show data submission status<br>"
        	+ "PUT <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/deathrecord'>jboss-spring-resteasy/deathrecord</a> - Submit mortality data to BioSense<br>"
        	+ "<br>"
        	+ "The following methods were inherited from the JBoss EAP Quickstart:<br>"
        	+ "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/hello?name=yourname'>jboss-spring-resteasy/hello?name=yourname</a> - 'Hello World' test method.<br>"
            + "GET or PUT <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/basic'>jboss-spring-resteasy/basic</a><br>"
            + "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/queryParam?param=query'>jboss-spring-resteasy/queryParam?param=query</a><br>"
            + "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/matrixParam;param=matrix'>jboss-spring-resteasy/matrixParam;param=matrix</a><br>"
            + "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/uriParam/789'>jboss-spring-resteasy/uriParam/789</a><br>"
            + "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/locating/hello?name=yourname'>jboss-spring-resteasy/locating/hello?name=yourname</a><br>"
            + "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/locating/basic'>jboss-spring-resteasy/locating/basic</a><br>"
            + "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/locating/queryParam?param=query'>jboss-spring-resteasy/locating/queryParam?param=query</a><br>"
            + "GET <a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/locating/matrixParam;param=matrix'>jboss-spring-resteasy/locating/matrixParam;param=matrix</a><br>"
            + "GET<a href='http://biosense-restful-ha-team6.services-dev.cdc.gov/jboss-spring-resteasy/locating/uriParam/789'>jboss-spring-resteasy/locating/uriParam/789</a><br>";
        System.out.println("getDefault()");
        return Response.ok(msg).build();
    }
    
    @GET
    @Path("status")
    @Produces("text/plain")
    public Response showStatus() {
    	String reportPage = "Death record submission status:\n"
    		+ "  " + sprintf(submissions) + " records submitted\n"
    		+ "    " + sprintf(accepted) + " records accepted, and\n"
    		+ "    " + sprintf(rejected) + " records rejected\n";
    	rejected++;
    	return Response.ok(reportPage).build();
    }
    
    @PUT
    @Path("deathrecord")
    @Consumes("application/json")
    public void putDeathRecord(String body) {
    	submissions++;
    	accepted++;
    	System.out.println(body);
    }

    @GET
    @Path("hello")
    @Produces("text/plain")
    public Response sayHello(@QueryParam("name") String name) {
        String greetingMsg = greetingBean.greet(name);
        System.out.println("Sending greeing: " + greetingMsg);
        return Response.ok(greetingMsg).build();
    }

    @GET
    @Path("basic")
    @Produces("text/plain")
    public String getBasic() {
        System.out.println("getBasic()");
        return "basic";
    }

    @PUT
    @Path("basic")
    @Consumes("text/plain")
    public void putBasic(String body) {
        System.out.println(body);
    }

    @GET
    @Path("queryParam")
    @Produces("text/plain")
    public String getQueryParam(@QueryParam("param") String param) {
        return param;
    }

    @GET
    @Path("matrixParam")
    @Produces("text/plain")
    public String getMatrixParam(@MatrixParam("param") String param) {
        return param;
    }

    @GET
    @Path("uriParam/{param}")
    @Produces("text/plain")
    public int getUriParam(@PathParam("param") int param) {
        return param;
    }

}
