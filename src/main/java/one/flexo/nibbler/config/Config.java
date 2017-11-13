/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.config;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.Configuration;
import one.flexo.nibbler.Nibbler;
import one.flexo.nibbler.proxy.CommonProxy;

public class Config {

	static final String CATEGORY_GENERAL = "general";
	static final String CATEGORY_DIMENSION = "dimensions";

	public static void readConfig() {
		Configuration cfg = CommonProxy.config;
		try {

			//Add in configs here when we got 'em!

		} catch (Exception e1) {
			Nibbler.logger.log(Level.ERROR, "Problem loading config file!", e1);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	static void initGeneralConfig(Configuration cfg) {

	}

}
