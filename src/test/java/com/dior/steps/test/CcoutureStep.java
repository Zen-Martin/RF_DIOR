package com.dior.steps.test;

import com.dior.context.ScenarioContext;
import com.dior.pageObjects.CcouturePage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class CcoutureStep implements En {

    public CcoutureStep(
            CcouturePage ccouturePage,
            ScenarioContext scenario
    ){

        Given("User is on website french version on rubrique **contact-couture**", () -> {
            ccouturePage.navigateToContact_Couture();
        });

        When("User scroll-down to rubrique ask question", () -> {
            ccouturePage.getAskQuestionEnability();
        });

        When("User write inside zone area", () -> {
            ccouturePage.getTextAreaSpace();
        });

        Then("User should be able to click on **ask question**", () -> {
            ccouturePage.verifyAskQuestionEnability();
            ccouturePage.saveScreenShotPNG();
            Assert.assertEquals(ccouturePage.verifyAskQuestionEnability(),true);
        });

        Then("User should see space between zone and writting", () -> {
            ccouturePage.verifyTextAreaSpace();
            ccouturePage.saveScreenShotPNG();
            Assert.assertEquals(ccouturePage.verifyTextAreaSpace(),true);
        });


    }

}
