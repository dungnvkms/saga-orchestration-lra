package order.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(baseUri ="http://localhost:8082")
@Path("/payment")
public interface PaymentClient {
    @GET
    @Path("/book")
    void bookPayment();

    @GET
    @Path("/bookFailure")
    void bookPaymentFailure();
}
