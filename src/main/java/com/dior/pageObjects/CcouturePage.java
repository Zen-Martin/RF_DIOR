package com.dior.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.dior.config.Configuration;
import com.dior.config.Properties;

public class CcouturePage extends Page {

    @FindBy(id = "acceptInvite")
    private WebElement askQuestionButton;

    @FindBy(className = "contact-form__fieldset__large-cell")
    private WebElement textArea;

    private String textAreaSize = "";

    private String inputAreaSize = "";

    private String askQuestionsButtonStatus="";

    @FindBy(id = "message")
    private WebElement inputArea;

    private final static Configuration PROP  = Properties.Config;

    private final static String URI = PROP.getEnvironment()+"fr_fr/contact-couture";

    public CcouturePage(){}

    public void navigateToContact_Couture(){
        get(URI);
        manageAccess();
    }

    public void getAskQuestionEnability(){
        scroll(1050);
        try {

            askQuestionButton.click();

        } catch (Exception e){
            askQuestionsButtonStatus = e.toString();
            System.out.println(e);
        }

    }

    public void getTextAreaSpace() {
        textAreaSize = textArea.getLocation().getX()+"";
        inputArea.sendKeys(PROP.getEmail());
        inputAreaSize = inputArea.getLocation().getX()+"";

    }

    public boolean verifyAskQuestionEnability() {
        if (askQuestionsButtonStatus.contains("is not clickable")) {
                System.out.println("\n impossible de cliquer sur le button : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n possible de cliquer sur le bouton : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            }

    }

    public boolean verifyTextAreaSpace(){
        System.out.println("\ntext area position : "+textAreaSize
                    + "\nwriting space position : "+inputAreaSize);

            if (textAreaSize.equals(inputAreaSize)) {

                System.out.println("\n les elements se confondent : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n les elements se distinguent : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            }

    }


}
