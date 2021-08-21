/**
 * 
 */
package com.sharobi.yewpos.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author habib
 *
 */
public class Initializer implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger(Initializer.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Context destroying[.....] : " + arg0.getServletContext().getContextPath());
		logger.info("Context destroyed[-----] : " + arg0.getServletContext().getContextPath());
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Context initializing[.....] : " + arg0.getServletContext().getContextPath());
		logger.info("Context initialized[+++++] : " + arg0.getServletContext().getContextPath());
	}
}
