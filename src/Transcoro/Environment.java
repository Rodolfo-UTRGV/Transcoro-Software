/**
*   _____
 * () | ,_   _,         ,   _   _   ,_   _
 *    |/  | / |  /|/|  / \_/   / \_/  | / \_
 *  (/    |/\/|_/ | |_/ \/ \__/\_/    |/\_/
 *
 *  Transcoro Software 2018 || The power of proyects
**/
package Transcoro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Transcoro.Core.ConfigurationManager;
import Transcoro.Core.Tools;
import Transcoro.Core.MainLayout;
import Transcoro.Database.Database;

public class Environment extends Application {

    Scene Mainscene;

    /**
     * Major version of the application.
     */
    public final static int MAJOR = 1;

    /**
     * Minor version of the application.
     */
    public final static int MINOR = 2;

    /**
     * Stable build version of the application.
     */
    public final static int BUILD = 3;

    /**
     * Version as string.
     */
    public static final String version = MAJOR + "." + MINOR + "." + BUILD;

    /**
     * CSS as string.
     */
    private String CSSLink = "Resources/css/OB" + version + ".css";

    private static ConfigurationManager     config;
    private static Database                 database;
    private static MainLayout               mainLayout;
    private static Tools                    tools;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Mainscene = new Scene(mainLayout);
        primaryStage.setScene(Mainscene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Transcoro S.A. de C.V. ~ v" + this.version);
        primaryStage.show();
    }


    public static void main(String[] args) {
        try
        {
            config = new ConfigurationManager("config.ini");
            database = new Database(getConfig());
            tools = new Tools();
            mainLayout = new MainLayout();
            System.out.println("Main Application -> START");
            launch(args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return The ConfigurationManager
     */
    public static ConfigurationManager getConfig()
    {
        return config;
    }

    /**
     * @return The Database
     */
    public static Database getDatabase()
    {
        return database;
    }

    /**
     * Shutdowns everything
     */
    public static void prepareShutdown()
    {
        System.exit(0);
    }

    /**
     * @return the tools
     */
    public static Tools getTools() {
        return tools;
    }

    /**
     * @param Tools the tools to set
     */
    public static void setTools(Tools Tools) {
        tools = Tools;
    }
}
