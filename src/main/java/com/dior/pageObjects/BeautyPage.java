package com.dior.pageObjects;

import com.dior.config.Configuration;
import com.dior.config.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class BeautyPage extends Page{


    @FindBy(className = "dots")
    private WebElement verticalSlider;

    @FindBy(className = "dots")
    private List<WebElement> SliderItem;

    private int scrollNumber = 0;

    private String initPosition = "";

    private String previousSliderScroll = "";

    private String nextSliderScroll = "";

    private final static Configuration PROP  = Properties.Config;

    private final static String URI    = PROP.getEnvironment()+"couture/fr_fr/la-maison-dior/les-metiers/maquillage";

    public BeautyPage(){
    }

    public void navigateToBeautyPage() {
        get(URI);
        manageAccess();
    }

    public void startPlayWithSlider(){
        previousSliderScroll = getScrollPosition();
        initPosition = previousSliderScroll;
        clickOn(verticalSlider);

        for(WebElement e: SliderItem)
        {
            clickOn(e);
            nextSliderScroll=getScrollPosition();

            if(!nextSliderScroll.equals(previousSliderScroll)){

                scrollNumber++;
            }
            previousSliderScroll=nextSliderScroll;

        }

    }


    public boolean verifySliderFeature(){
        System.out.println("\neffective scrolling number when using slider : "+scrollNumber);

            if(scrollNumber==0) {
                System.out.println("\n Le slider ne fonctionne pas "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            }else {
                System.out.println("\n Le slider fonctionne bien "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            }

    }




}
