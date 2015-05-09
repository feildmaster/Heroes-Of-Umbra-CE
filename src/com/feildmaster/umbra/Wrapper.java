package com.feildmaster.umbra;

import net.alcuria.online.Main;

public class Wrapper {
    public static void main(String[] args) {
        try {
            Class.forName("net.alcuria.online.Main");
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find game file, now exiting");
            System.exit(1);
        }

        // Run the main program
        Main.main(args);
    }

}
