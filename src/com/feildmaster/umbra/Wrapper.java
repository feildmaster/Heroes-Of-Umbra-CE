package com.feildmaster.umbra;

import net.alcuria.online.Main;

public class Wrapper {
    public static void main(String[] args) {
        try {
            Class.forName("net.alcuria.online.Main");

            // Run the main program
            Main.main(args);
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find game file, now exiting");
            System.exit(0);
        } catch (Exception pokemon) {
            System.out.println("Exception occured: " + pokemon.getLocalizedMessage());
            pokemon.printStackTrace();
            System.exit(1);
        }
    }
}
