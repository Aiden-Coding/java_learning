
package com.itmayiedu.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserService {
	@GET
	@Path("/getUser/{id : \\d+}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public String getUser(@PathParam("id") Integer id);
}
