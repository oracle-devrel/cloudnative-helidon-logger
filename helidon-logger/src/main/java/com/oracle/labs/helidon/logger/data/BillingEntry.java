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
package com.oracle.labs.helidon.logger.data;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.helidon.common.Reflected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Reflected // this tells the Helidon native-image support to include this as a class that
			// can be accessed via reflection
@Data // Tells Lombok to create getters and setters, equals and hashcode
@ToString
@NoArgsConstructor // Tells Lombok to create a constructor with no args (needed for the JSCON
					// unmarshalling process to work)
@AllArgsConstructor // Tells Lombok to create a constructor with all the args (makes life easier
					// creating instances)
//Tells Helidon the details of this object for the OpenAPI delf documenting class
@Schema(name = "BillingEntry", description = "Details of the billing record", example = "{\"callerName\": \"Jack\", \"itemCount\": 10, \"itemName\": \"Pencil\"}")
public class BillingEntry {
	// Tells helidon OpenAPI support what the field is
	@Schema(required = true, description = "The name of the caller", example = "Jack")
	private String callerName;
	// Tells helidon OpenAPI support what the field is
	@Schema(required = true, description = "The name of the item", example = "Pencil")
	private String itemName;
	// Tells helidon OpenAPI support what the field is
	@Schema(required = true, description = "The number of items taken by the request", example = "10")
	private int itemCount;
}