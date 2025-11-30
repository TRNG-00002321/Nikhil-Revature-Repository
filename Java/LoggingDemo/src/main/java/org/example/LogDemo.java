package org.example;

import java.util.logging.Logger;

public class LogDemo {
    private static final Logger logger = Logger.getLogger(LogDemo.class.getName());

    public static void main(String[] args)
    {
        logger.info("This is info");
        logger.warning("This is warning");
    }
}
