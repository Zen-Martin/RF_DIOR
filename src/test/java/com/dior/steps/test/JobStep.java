package com.dior.steps.test;

import com.dior.context.ScenarioContext;
import com.dior.pageObjects.JobPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class JobStep implements En {

    public JobStep(
            JobPage jobPage,
            ScenarioContext scenario
    ){

        Given("User is on **job page**", () -> {
            jobPage.navigateToJobPage();
        });

        When("User click on **horlogerie**", () -> {
            jobPage.getHorlogerielink();
        });

        Then("User should be redirect on another page", () -> {
            jobPage.VerifyhorlogerieSameLink();
            jobPage.saveScreenShotPNG();
            Assert.assertEquals(jobPage.VerifyhorlogerieSameLink(),true);
        });

    }
}
