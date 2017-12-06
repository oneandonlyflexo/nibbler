/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.registry;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import one.flexo.nibbler.INibble;
import one.flexo.nibbler.item.NibblerBlockItem;

/**
 * This class is used for making automatically registering items easier.  All a mod has to do to make it so that
 * blocks/items get auto-registered is create a mod-local NibblerRegistry and then have its blocks/items implement
 * NibblerRegisteredObject.  The mod also has to make sure that when you would normally register blocks/items, the
 * mod-local NibblerRegistry has it's registration methods called.
 *
 * @author "oneandonlyflexo"
 */
@Mod.EventBusSubscriber
public class NibblerRegistry {

	private ArrayList<Block> blocks = new ArrayList<>();
	private ArrayList<Block> blockItems = new ArrayList<>();
	private ArrayList<Item> items = new ArrayList<>();

	public <B extends Block & INibble> void addBlockForRegistry(B block, boolean addItem) {
		blocks.add(block);
		if(addItem) {
			blockItems.add(block);
		}
	}

	public <I extends Item & INibble> void addItemForRegistry(I item) {
		items.add(item);
	}

	public void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		for(int i = 0; i < blocks.size(); i++) {
			registry.register(blocks.get(i));
		}

	}

	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		for(int i = 0; i < items.size(); i++) {
			registry.register(items.get(i));
		}

		for(int i = 0; i < blockItems.size(); i++) {
			registry.register(new NibblerBlockItem((Block & INibble)blockItems.get(i)));
		}

		for(int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			if(block instanceof TileEntityRegisteredBlocked) {
				registerTile(((TileEntityRegisteredBlocked)block).getTileEntityClass(), block);
			}
		}
	}

	public static void registerTile(Class<? extends TileEntity> clazz, Block block) {
		GameRegistry.registerTileEntity(clazz, block.getRegistryName().toString());
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		for(Object block : Block.REGISTRY) {
			if(block instanceof ModelRegisteredObject)
				((ModelRegisteredObject) block).registerModels();
		}

		for(Object item : Item.REGISTRY) {
			if(item instanceof ModelRegisteredObject)
				((ModelRegisteredObject) item).registerModels();
		}
	}
}
