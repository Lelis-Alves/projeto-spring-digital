package com.alves.services;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alves.controllers.ControllerVersion;

@Component
public class Version {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerVersion.class);

	public Properties versionEntity() {

		Properties prop = new Properties();
		prop.setProperty("version.check.time", new SimpleDateFormat().format(new Date().getTime()));

		try {
			Enumeration<URL> resources = getClass().getClassLoader().getResources("version.properties");
			while (resources.hasMoreElements()) {
				try {
					prop.load(resources.nextElement().openStream());
				} catch (IOException e) {
					LOG.error("version ERROR", e);
					prop.setProperty("Exception", e.getMessage());
				}
			}
		} catch (IOException ex) {
			LOG.error("version ERROR", ex);
			prop.setProperty("Exception", ex.getMessage());
		}
		return prop;
	}
}
