package com.project.transactions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HealthIT extends TransactionsApplicationIT {
    @Test
    public void healthCheck() {
        IntegrationRequests.get("/actuator/health")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
