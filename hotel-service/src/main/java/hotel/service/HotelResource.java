package hotel.service;

import org.eclipse.microprofile.lra.annotation.Compensate;
import org.eclipse.microprofile.lra.annotation.Complete;
import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hotel")
public class HotelResource {
    @GET
    @Path("/book")
    @LRA
    public Response book() {
        System.out.println("book hotel ticket");
        String ticket = "1234";
//        return Response.status(500).build();
        return Response.ok(ticket).build();
    }

    @GET
    @Path("/bookFailure")
    @LRA
    public Response bookFailure() {
        System.out.println("book hotel failed");
        String ticket = "1234";
        return Response.status(500).build();
    }

    @PUT // must be PUT
    @Path("/complete")
    @Complete
    public Response complete() {
        System.out.printf("complete work");
        return Response.ok().build();
    }

    @PUT // must be PUT
    @Path("/compensate")
    @Compensate
    public Response compensate() {
        System.out.println("cancel booking hotel");
        return Response.ok().build();
    }
}
