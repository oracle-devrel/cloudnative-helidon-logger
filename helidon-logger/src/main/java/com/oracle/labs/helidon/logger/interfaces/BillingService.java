package com.oracle.labs.helidon.logger.interfaces;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.oracle.labs.helidon.logger.data.BillingEntry;
import com.oracle.labs.helidon.logger.data.BillingEntryResponse;
import com.oracle.labs.helidon.logger.data.BillingInfo;


@Path("/billing")
@ApplicationScoped
public interface BillingService {
	@Path("/saveentry")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Operation(summary = "Write a billing record", description = "Writes a billing record")
	@APIResponse(description = "If the record has been written", responseCode = "200", content = @Content(schema = @Schema(name = "BillingEntryResponse", implementation = BillingEntryResponse.class, type = SchemaType.OBJECT), example = "{\"written\": true}"))
	// create a counter for this method
	// specify a name, otherwise it will default to the method name
	// The ConcurrentGage is different form the @Counter and means that this will
	// increment on entry to the method,and decrement on exit from the method, in
	// other words we can see how many calls are actually active in the method at
	// any point in time (useful for method that may run for a long time)
	@Counted(name = "billingEntryCounter")
	public BillingEntryResponse postBillingEntry(
			@RequestBody(description = "The details of the billing entry", required = true, content = @Content(schema = @Schema(name = "BillingEntry", implementation = BillingEntry.class), example = "{\"callerName\": \"Jack\", \"itemCount\": 10, \"itemName\": \"Pencil\"}")) BillingEntry billingEntry);

	@Path("/billinginfo")
	@Counted(name = "billingInfoCounter")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Operation(summary = "Get stats on the billing", description = "Returns information on how many billing records have been written and the size of the file")
	@APIResponse(description = "Billing info stats", responseCode = "200", content = @Content(schema = @Schema(name = "BillingInfo", implementation = BillingInfo.class, type = SchemaType.OBJECT), example = "{\"billingActive\": true, \"recordsWrittenSinceStartup\": 10, \"billingFileSize\": 1024}"))
	public BillingInfo getBillingInfo();
}
