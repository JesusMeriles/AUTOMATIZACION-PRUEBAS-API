package com.todoly.stepdefinitions;

import com.todoly.client.UserClient;
import com.todoly.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private final TestContext context;
    private final UserClient userClient;

    public Hooks(TestContext context, UserClient userClient) {
        this.context = context;
        this.userClient = userClient;
    }

    @Before
    public void setUp(Scenario scenario) {
        context.reset();
        System.out.println("==================================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("==================================================");
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("==================================================");
        System.out.println("Finishing Scenario: " + scenario.getName() + " - Status: " + scenario.getStatus());

        // Cleanup: If a user was created and exists, delete the user account
        // Note: In Todo.ly, deleting the user also cascades and removes user's projects.
        if (context.getUserResponse() != null && context.getUserResponse().getId() != null && context.getUserRequest() != null) {
            try {
                if (context.getToken() != null) {
                    System.out.println("Cleaning up user using Token...");
                    userClient.deleteUserWithToken(context.getToken());
                } else {
                    System.out.println("Cleaning up user using Basic Auth...");
                    userClient.deleteUserWithBasicAuth(
                            context.getUserRequest().getEmail(),
                            context.getUserRequest().getPassword()
                    );
                }
            } catch (Exception e) {
                System.out.println("Teardown notice: User might already be deleted or cleanup skipped: " + e.getMessage());
            }
        }
        System.out.println("==================================================");
    }
}
