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
package com.oracle.labs.helidon.logger.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import com.oracle.labs.helidon.logger.resources.BillingResourceImpl;

/**
 * Readiness is different form Liveness. Readiness ensures that we actually are
 * actually ready to process transactions and are fully configured, liveliness
 * is more of a Hello World situation
 * 
 * @author tg13456
 *
 */
@ApplicationScoped
@Liveness
public class LivenessChecker implements HealthCheck {

	private static long startTime = System.currentTimeMillis();
	private String billingFileLocation;

	@Inject
	public LivenessChecker(
			@ConfigProperty(name = "app.billingfilelocation", defaultValue = BillingResourceImpl.NO_LOCATION_SET) String billingFileLocation) {
		this.billingFileLocation = billingFileLocation;
	}

	@Override
	public HealthCheckResponse call() {
		// don't test anything here, we're just reporting that we are running, not that
		// any of the underlying connections to thinks like the database are active
		return HealthCheckResponse.named("logger-live").up().withData("uptime", System.currentTimeMillis() - startTime)
				.withData("billingFileLocation", billingFileLocation).build();
	}

}
