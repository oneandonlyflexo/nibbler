/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import one.flexo.nibbler.registry.ModelRegisteredObject;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		initRenderers();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {

		for(Object block : Block.REGISTRY) {
			if(block instanceof ModelRegisteredObject)
				((ModelRegisteredObject) block).registerModels();
		}

		for(Object item : Item.REGISTRY) {
			if(item instanceof ModelRegisteredObject)
				((ModelRegisteredObject) item).registerModels();
		}
	}

	private void initRenderers() {

	}
}
