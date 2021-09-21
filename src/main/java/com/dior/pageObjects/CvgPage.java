package com.dior.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CvgPage extends Page{

    @FindBy(id = "christian-dior-couture")
    private WebElement element1;

    @FindBy(id = "parfums-christian-dior")
    private WebElement element2;

    private int frame1;

    private int frame2;

    private int compareSize = 0;

    public CvgPage(){}

    public void getElementCGVSize() {
        manageAccess();
        tryCompareSize();
        refresh_page();
        tryCompareSize();
    }

    public void tryCompareSize(){
        frame1 = element1.getSize().getHeight();
        frame2 = element2.getSize().getHeight();
        verifyElementSize(frame1,frame2);
        if (compareSize==1){
            frame1 = element1.getSize().getWidth();
            frame2 = element2.getSize().getWidth();
            verifyElementSize(frame1,frame2);
        }

    }

    public void verifyElementSize(int elt1,int elt2){
        if (elt1==elt2){
            compareSize++;
        }

    }

    public boolean verifyCGVElementSize(){
        if (compareSize!=2) {
                System.out.println("\n les elements n'ont pas la meme taille apres actualisation de la page : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;

            } else {

                System.out.println("\n les elements ont la meme taille apres actualisation de la page : "
                        + "\n\n\tBug Corrigé !!!");
                return true;
            }

        }


}
