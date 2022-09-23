package order.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;
import org.eclipse.microprofile.lra.annotation.Compensate;
import org.eclipse.microprofile.lra.annotation.Complete;
import org.eclipse.microprofile.rest.client.inject.RestClient;
// import the definition of the LRA context header
// import some JAX-RS types
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/order")
@Produces(APPLICATION_JSON)
public class OrderResource {

    @Inject
    @RestClient
    private PaymentClient paymentClient;

    @GET
    @Path("/book")
    @LRA(value = LRA.Type.REQUIRED, end = false) // an LRA will be started before method execution if none exists and will not be ended after method execution
    public Response bookTicket() {
         System.out.println("book order ticket");
         String ticket = "1234";
         paymentClient.bookPayment();
         return Response.ok(ticket).build();
    }

    @GET
    @Path("/bookFailure")
    @LRA(value = LRA.Type.REQUIRED, end = false) // an LRA will be started before method execution if none exists and will not be ended after method execution
    public Response bookTicketFailure() {
        System.out.println("book order ticket");
        String ticket = "1234";
        try {
            // run out of money
            paymentClient.bookPaymentFailure();
        } catch (Exception e) {

        }
        return Response.serverError().build();
    }

    // ask to be notified if the LRA clos
    @PUT // must be PUT
    @Path("/complete")
    @Complete
    public Response completeWork() {
         System.out.printf("complete work");
         return Response.ok().build();
     }

    // ask to be notified if the LRA cance
    @PUT // must be PUT
    @Path("/compensate")
    @Compensate
    public Response compensateWork() {
         System.out.println("cancel booking order");
         return Response.ok().build();
    }
}