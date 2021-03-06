/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import one.flexo.nibbler.item.NibblerItem;
import one.flexo.nibbler.proxy.CommonProxy;
import one.flexo.nibbler.registry.NibblerRegisteredObject;
import one.flexo.nibbler.registry.NibblerRegistry;

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
acceptableRemoteVersions = "[0.3,)")
public class Nibbler
{
	@Mod.Instance
	public static Nibbler instance;

	@SidedProxy(clientSide="one.flexo.nibbler.proxy.ClientProxy", serverSide="one.flexo.nibbler.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static final NibblerRegistry registry = new NibblerRegistry();

	private static class LogoItem extends NibblerItem implements NibblerRegisteredObject {
		public LogoItem(String modid, String name, CreativeTabs tab) {
			super(modid, name, tab);
		}

		@Override
		public NibblerRegistry getRegistry() {
			return registry;
		}
	};


	public static NibblerItem logo;

	public static final Nibble unknown = new Nibble(NibblerInfo.modid, "unknown");

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		logo = new LogoItem(NibblerInfo.modid, "nibbler", null);
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
