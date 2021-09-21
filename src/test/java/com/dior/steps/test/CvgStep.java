package com.dior.steps.test;

import com.dior.context.ScenarioContext;
import com.dior.pageObjects.CvgPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class CvgStep implements En {

    public CvgStep(
            CvgPage cvgPage,
            ScenarioContext scenario
    ) {
        Then("User should see same frame size",()->{
            cvgPage.getElementCGVSize();
            cvgPage.saveScreenShotPNG();
        });

        And("User refresh the page",()->{
            cvgPage.getElementCGVSize();
        });

        Then("User should see same frame size too",()->{
            cvgPage.verifyCGVElementSize();
            cvgPage.saveScreenShotPNG();
            Assert.assertEquals(cvgPage.verifyCGVElementSize(),true);
        });



    }
}
