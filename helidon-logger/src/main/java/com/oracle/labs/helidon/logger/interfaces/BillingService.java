/*Copyright (c) 2021 Oracle and/or its affiliates.

The Universal Permissive License (UPL), Version 1.0

Subject to the condition set forth below, permission is hereby granted to any
person obtaining a copy of this software, associated documentation and/or data
(collectively the "Software"), free of charge and under any and all copyright
rights in the Software, and any and all patent rights owned or freely
licensable by each licensor hereunder covering either (i) the unmodified
Software as contributed to or provided by such licensor, or (ii) the Larger
Works (as defined below), to deal in both

(a) the Software, and
(b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
one is included with the Software (each a "Larger Work" to which the Software
is contributed by such licensors),

without restriction, including without limitation the rights to copy, create
derivative works of, display, perform, and distribute the Software and make,
use, sell, offer for sale, import, export, have made, and have sold the
Software and the Larger Work(s), and to sublicense the foregoing rights on
either these or other terms.

This license is subject to the following condition:
The above copyright notice and either this complete permission notice or at
a minimum a reference to the UPL must be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.oracle.labs.helidon.logger.interfaces;

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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
