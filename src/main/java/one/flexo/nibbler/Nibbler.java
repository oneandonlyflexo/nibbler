/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
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
acceptableRemoteVersions = "[0.2]")
public class Nibbler
{
	@Mod.Instance
	public static Nibbler instance;

	@SidedProxy(clientSide="one.flexo.nibbler.proxy.ClientProxy", serverSide="one.flexo.nibbler.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static Logger logger;

	public static final NibblerRegistry registry = new NibblerRegistry();

	private static class LogoItem extends NibblerItem implements NibblerRegisteredObject {
		public LogoItem(String modid, String name, CreativeTabs tab) {
			super(modid, name, tab);
		}

		/* (non-Javadoc)
		 * @see one.flexo.nibbler.registry.NibblerRegisteredObject#getRegistry()
		 */
		@Override
		public NibblerRegistry getRegistry() {
			return registry;
		}
	};

	public static CreativeTabs tab = new CreativeTabs("nibbler") {
		@Override
		public String getTabLabel() {
			return "nibbler";
		}
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(logo);
		}
	};

	public static final NibblerItem logo = new LogoItem(NibblerInfo.modid, "nibbler", null);

	public static final Nibble unknown = new Nibble(NibblerInfo.modid, "unknown");

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
