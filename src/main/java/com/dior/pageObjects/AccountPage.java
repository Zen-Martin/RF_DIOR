package com.dior.pageObjects;

import org.openqa.selenium.WebElement;
import com.dior.config.Configuration;
import com.dior.config.Properties;
import org.openqa.selenium.support.FindBy;


public class AccountPage extends Page{

    private final static Configuration PROP  = Properties.Config;

    private final static String accountURI = PROP.getEnvironment()+"couture/fr_fr/account/login";

    private final static String inscriptionURI = PROP.getEnvironment()+"couture/account/inscription";

    private String accountPasswordSize = "";

    private String inscriptionPasswordSize = "";

    // >-------------------- /account/login ----------------<
    @FindBy(id = "password")
    private WebElement passwordAccountField;

    // >-------------------- /account/inscription ----------------<
    @FindBy(id = "password")
    private WebElement passwordInscriptionField;


    public AccountPage() { }

    public void navigateToAccountLogin(){
        get(accountURI);
        manageAccess();
    }

    public void navigateToAccountInscription(){
        get(inscriptionURI);
        manageAccess();
    }

    public void getLoginPasswordFieldSize(){
        accountPasswordSize=passwordAccountField.getAttribute("data-min-length");
        passwordAccountField.sendKeys(PROP.getPwd());
    }

    public void getInscriptionPasswordFieldSize(){
        inscriptionPasswordSize=passwordInscriptionField.getAttribute("data-min-length");
        passwordInscriptionField.sendKeys(PROP.getPwd());
    }

    public boolean verifyPasswordFieldSize(){
        System.out.println("\naccount passwordfield size : "+accountPasswordSize
                    + "\ninscription passwordfield size : "+inscriptionPasswordSize);

            if(!accountPasswordSize.equals(inscriptionPasswordSize)) {

                System.out.println("\n la longueur du champs mot de passse requis varie : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n la longueur du champs mot de passse requis ne varie pas : "
                        + "\n\n\tBug Corrigé !!!");
                return true;
            }


    }


}
