package com.dior.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.dior.config.Configuration;
import com.dior.config.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class JobPage extends Page{

    private String url_1="";

    private String url_2="";

    private final static Configuration PROP  = Properties.Config;

    private final static String URI = PROP.getEnvironment()+"couture/fr_fr/la-maison-dior/les-metiers";

    @FindBy(xpath = "//p[contains(text(),\"L'Horlogerie\")]")
    private WebElement horlogerieOption;

    @FindBy(className = "ecrin-light")
    private WebElement title;

    public JobPage() {
    }


    public void navigateToJobPage() {
        get(URI);
        manageAccess();
    }

    public void getHorlogerielink() {
        url_1 = driver.getCurrentUrl();
        clickOn(horlogerieOption);
        shortUntil(visibilityOf(title));
        url_2 = driver.getCurrentUrl();
    }

    public boolean VerifyhorlogerieSameLink(){
        System.out.println("\nlink on the first page : "+url_1
                    + "\nlink after click : "+url_2);

            if(url_1.equals(url_2)) {

                System.out.println("\n l'horlogerie renvoit vers le lien par defaut qui est celui des metiers : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n l'horlogerie ne renvoit pas vers le lien par defaut qui est celui des metiers : "
                        + "\n\n\tBug Corrigé !!!");
                return true;
            }

    }


}
