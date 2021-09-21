package com.dior.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.dior.config.Configuration;
import com.dior.config.Properties;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


public class HomePage extends Page {

    @FindBy(id = "prc-12-1")
    private WebElement headerElementMaison;

    @FindBy(id = "prc-2-1")
    private WebElement langageservice;

    @FindBy(className = "header-bottom")
    private WebElement headerElement;

    @FindBy(className = "header-center")
    private WebElement headerTitle;

    @FindBy(xpath = "//a[contains(text(),'Conditions Générales de Vente')]")
    private WebElement cvgLink;

    @FindBy(id = "conditions-generales-de-vente")
    private WebElement cvgLinkTitle;

    @FindBy(className = "my-account")
    private WebElement headerRectConnectElement;

    @FindBy(xpath = "//ul[@class='navigation-desktop-menu']")
    private WebElement headerMenu;


    private String elementTranslate = "";
    private int connectionPossibilityNumber = 0;
    private int languagePossibilityNumber = 0;
    private String language = "";
    private final static Configuration PROP  = Properties.Config;
    private final static String URI = PROP.getEnvironment()+"couture/fr_fr/la-maison-dior/les-metiers/maquillage";
    private final static String site_version[] = {PROP.getEnvironment()+"en_int",PROP.getEnvironment()+"fr_fr",PROP.getEnvironment()+"en_hk",PROP.getEnvironment()+"ja_jp",PROP.getEnvironment()+"zh_tw",PROP.getEnvironment()+"en_ro"};

    public HomePage() {
    }

    public void navigateToCVG() {
        goToLinkpage(cvgLink);
        shortUntil(visibilityOf(cvgLinkTitle));
    }

    public void navigateToHomePage_Int() {
        get(site_version[0]);
        refresh_page();
    }

    public void navigateToHomePage_Fr() {
        get(site_version[1]);
        waitForLoadingPage();
        manageAccess();
    }

    public void getHeaderElementMaison() {
        shortUntil(visibilityOf(headerMenu));
        shortUntil(visibilityOf(headerElementMaison));
        elementTranslate = headerElementMaison.getText();
    }

    public void getToFindConnexion() {
        shortUntil(visibilityOf(headerElement));
        try{
            if (headerRectConnectElement.isDisplayed()){
                connectionPossibilityNumber++;
                clickOn(headerRectConnectElement);
            }

        }catch (Exception e){
            System.out.println("élement de connexion absent !!");
        }
    }

    public void getLangageService() {
        for (int i = 2; i<site_version.length; i++){
            goTopage(site_version[i]);
                if(langageservice.isDisplayed()){
                    languagePossibilityNumber++;
                }

        }

    }

    public boolean verifyHeaderElementTranslate() {
            System.out.println("On peut observer le header " + elementTranslate + " sur le site en anglais");

            if (elementTranslate.equals("MAISON")) {

                System.out.println("\n l'element <<" + elementTranslate + ">> n'est pas traduit sur le site international"
                        + "\n\n\tBug Non Corrigé !");
                return false;

            } else {

                System.out.println("\n l'element <<" + elementTranslate + ">> est traduit sur le site international"
                        + "\n\n\tBug Corrigé !");
                return true;
            }

    }

    public boolean verifyGetConnexionToInt_Website() {
        System.out.println("\ncompteur pour element de connexion : " + connectionPossibilityNumber);

            if (connectionPossibilityNumber < 2) {

                System.out.println("\n Connexion depuis le site international impossible "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n Connexion depuis le site international possible "
                        + "\n\n\tBug Corrigé !!!");
                return true;
            }

    }

    public boolean verifyServiceLangageAppear() {
            System.out.println("\noccurence d'appurition de la barre de service : " + languagePossibilityNumber);

            if (languagePossibilityNumber != 4) {

                System.out.println("\n les occurences sont inferieurs au version testées : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n les occurences sont égales au version testées  : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            }

    }


}
