/**
 *   _____
 * () | ,_   _,         ,   _   _   ,_   _
 *    |/  | / |  /|/|  / \_/   / \_/  | / \_
 *  (/    |/\/|_/ | |_/ \/ \__/\_/    |/\_/
 *
 *  Transcoro Software 2018 || The power of proyects
 **/
package Transcoro.Classes.tabs;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;

public class Tab {

    /**
     * The Tab Title
     */
    private String title;

    /**
     * The Tab CSS ID
     */
    private String cssId;

    /**
     * The Tab Button
     */
    private Button button;

    /**
     * Checks if the tab is active at the moment.
     */
    private boolean isActive = false;

    /**
     * Creates a Tab
     */
    public Tab(int id, String title, String cssId, Button button){
        this.title = title;
        this.cssId = cssId;
        this.button = button;
    }

    public Tab(String title){
        this.title = title;
        this.cssId = generateId();
        this.button = new Button(this.title);
        this.button.setId(this.cssId);
        this.button.setContentDisplay(ContentDisplay.LEFT);
    }

    /**
     * Creates an automatic CSS id according to the title if not specified.
     * @return Generated CSS ID
     */
    public String generateId(){

        boolean titleWhitespace = this.getTitle().matches("^\\s*$");

        if(this.getCssId() != null){
            boolean idWhiteSpace = this.getCssId().matches("^\\s*$");

            if(!idWhiteSpace)
                return this.getCssId();
        }
        else
            this.setCssId((!titleWhitespace) ? this.getTitle() : this.getCssId().replaceAll("\\s+",""));

        return this.getCssId();
    }

    /**
     * @return the Tab title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the Tab title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the Tab CSS ID
     */
    public String getCssId() {
        return cssId;
    }

    /**
     * @param cssId the Tab CSS ID to set
     */
    public void setCssId(String cssId) {
        this.cssId = cssId;
    }

    /**
     * @return the Tab Button
     */
    public Button getButton() {
        return button;
    }

    /**
     * @param button the Tab button to set
     */
    public void setButton(Button button) {
        this.button = button;
    }

    /**
     * @return a bool whether the tab is active or not
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param active boolean activates/deactivates tab.
     */
    public void setActive(boolean active) {
        isActive = active;
    }
}
