/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import one.flexo.nibbler.proxy.CommonProxy;

/**
 * Nibbler! This cute little mod is a shared code library mod thing for the rest of my mods.  I'll fire up the grill!
 *
 * @author "oneandonlyflexo"
 */

@Mod(modid = NibblerInfo.modid,
name = NibblerInfo.name,
version = NibblerInfo.version,
useMetadata = true,
acceptedMinecraftVersions = "[1.12,1.12.2]",
acceptableRemoteVersions = "[0.1]")
public class Nibbler
{

	//Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="one.flexo.nibbler.proxy.ClientProxy", serverSide="one.flexo.nibbler.proxy.ServerProxy")
	public static CommonProxy proxy;

	@Mod.Instance
	public static Nibbler instance;

	public static Logger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
