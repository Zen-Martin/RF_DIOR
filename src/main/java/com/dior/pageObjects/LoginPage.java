package com.dior.pageObjects;

import com.dior.config.Configuration;
import com.dior.config.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends Page{


    private String favorisAfterConnected = "";

    private String favorisAfterDisconnected = "";

    @FindBy(className = "side-panel-close-icon")
    private WebElement closeloginfield;

    @FindBy(className = "connected-account-logout")
    private WebElement disconnectfield;

    @FindBy(id = "favoris-tab-header")
    private WebElement favoristab;

    @FindBy(className = "my-account")
    private WebElement headerRectConnectElement;

    @FindBy(id = "compte-tab-header")
    private WebElement comptetab;

    @FindBy(id = "login")
    private WebElement emailfield;

    @FindBy(id = "password")
    private WebElement pwdfield;

    @FindBy(className = "button-link__title")
    private WebElement submitfield;

    private final static Configuration PROP  = Properties.Config;

    private final static String URI = PROP.getEnvironment()+"fr_fr";

    public LoginPage() {
    }

    public void connexion() {
        clickOn(headerRectConnectElement);
        emailfield.sendKeys(PROP.getEmail());
        pwdfield.sendKeys(PROP.getPwd());
        clickOn(submitfield);
        shortUntil(visibilityOf(disconnectfield));
        clickOn(closeloginfield);
    }

    public void getConnectedFavorisElement(){
        refresh_page();
        clickOn(headerRectConnectElement);
        clickOn(favoristab);
        clickOn(comptetab);
        clickOn(favoristab);
        favorisAfterConnected=favoristab.getText();
        clickOn(comptetab);
        clickOn(disconnectfield);
        clickOn(closeloginfield);

    }

    public void getDisconnectedFavorisElement(){
        refresh_page();
        clickOn(headerRectConnectElement);
        clickOn(favoristab);
        clickOn(comptetab);
        clickOn(favoristab);
        favorisAfterDisconnected=favoristab.getText();
        clickOn(comptetab);
        clickOn(closeloginfield);

    }

    public void navigateToHomePage_fr(){
        get(URI);
        manageAccess();
    }

    public boolean verifyFavorisElementState(){

        System.out.println("\nfavoris after connexion : "+favorisAfterConnected
                    + "\nfavoris after disconnexion : "+favorisAfterDisconnected);


            if(favorisAfterConnected.equals(favorisAfterDisconnected)) {

                System.out.println("\n Les favoris sont maintenus après deconnexion "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n Les favoris ne sont pas maintenus après deconnexion "
                        + "\n\n\tBug Corrigé !!!");
                return true;
            }

}

}
