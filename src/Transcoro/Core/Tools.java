/**
 *   _____
 * () | ,_   _,         ,   _   _   ,_   _
 *    |/  | / |  /|/|  / \_/   / \_/  | / \_
 *  (/    |/\/|_/ | |_/ \/ \__/\_/    |/\_/
 *
 *  Transcoro Software 2018 || The power of proyects
 **/
package Transcoro.Core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Tools {
    public static void setPane(BorderPane z, Node t, Node l, Node r, Node c){
        z.setTop(t);
        z.setLeft(l);
        z.setRight(r);
        z.setCenter(c);
    }

    public static void setPane(BorderPane z, Node t, Node l, Node r, Node b, Node c){
        z.setTop(t);
        z.setLeft(l);
        z.setRight(r);
        z.setBottom(b);
        z.setCenter(c);
    }

    public Label createLabel(String t, String i){
        Label l = new Label(t);
        l.setId(i);
        return l;
    }

    /*
     * Retrieves image from resources/images folder.
     * @param n The image in string.
     * @return The String n into ImageView v.
     */
    public ImageView getImagebyName(String n){
        Image m = new Image("resources/images/" + n);
        ImageView v = new ImageView(m);
        return v;
    }

    /*
    * Checks whether string inDate is a valid Date format.
    * @param inDate The string to validate.
    * @return true is if it is a valid Date Format, otherwise returns false.
    */
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
