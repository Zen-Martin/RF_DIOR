package com.dior.steps.test;

import com.dior.context.ScenarioContext;
import com.dior.pageObjects.HomePage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class HomeStep implements En {

    public HomeStep(
            HomePage homePage,
            ScenarioContext scenario
    ){


        Given("User is on international website homepage",()->{
            homePage.navigateToHomePage_Int();
        });

        Given("User is on website french version",()->{
            homePage.navigateToHomePage_Fr();
        });

        When("User look all headers elements",()->{
            homePage.getHeaderElementMaison();
        });

        When("User click on conditions vente general",()->{
            homePage.navigateToCVG();
        });

        And("User should be able to connect", () -> {
            homePage.getToFindConnexion();
        });

        When("User move to international website", () -> {
            homePage.navigateToHomePage_Int();
        });

        // language
        When("User plays with language version", () -> {
            homePage.getLangageService();

        });

        // --- The user must also be able to log in
        Then("User should be also able to connect", () -> {
            homePage.getToFindConnexion();
            homePage.verifyGetConnexionToInt_Website();
            homePage.saveScreenShotPNG();
            Assert.assertEquals(homePage.verifyGetConnexionToInt_Website(),true);
        });

        Then("User should see service header appear", () -> {
            homePage.verifyServiceLangageAppear();
            homePage.saveScreenShotPNG();
            Assert.assertEquals(homePage.verifyServiceLangageAppear(),true);

        });

        Then("User should see element *Maison* in english",()->{
            homePage.verifyHeaderElementTranslate();
            homePage.saveScreenShotPNG();
            Assert.assertEquals(homePage.verifyHeaderElementTranslate(),true);
        });




    }

}
