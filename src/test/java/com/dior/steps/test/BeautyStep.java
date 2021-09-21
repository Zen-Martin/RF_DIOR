package com.dior.steps.test;

import com.dior.context.ScenarioContext;
import com.dior.pageObjects.BeautyPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class BeautyStep implements En {

    public BeautyStep(
            BeautyPage beautyPage,
            ScenarioContext scenario
    ){

        Given("User is on **beauty page**", () -> {
            beautyPage.navigateToBeautyPage();
        });

        When("User plays with slider", () -> {
            beautyPage.startPlayWithSlider();
        });

        Then("User should see slider effects", () -> {
            beautyPage.verifySliderFeature();
            beautyPage.saveScreenShotPNG();
            Assert.assertEquals(beautyPage.verifySliderFeature(),true);
        });


    }

}
