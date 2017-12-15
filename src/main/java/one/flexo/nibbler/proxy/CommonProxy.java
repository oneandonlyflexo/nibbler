/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistry;
import one.flexo.nibbler.Nibbler;

@Mod.EventBusSubscriber
public abstract class CommonProxy {

	public static Configuration config;

	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
	 */
	public void preInit(FMLPreInitializationEvent e) {
	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes,
	 * send FMLInterModComms messages to other mods.
	 */
	public void init(FMLInitializationEvent e) {

	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit(FMLPostInitializationEvent e) {

	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		Nibbler.registry.registerBlocks(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Nibbler.registry.registerItems(event);

		initOreRegistry();
	}

	private static final Map<ItemStack, String> oreReplacements = new HashMap<ItemStack, String>();

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

		//Replace Book recipes


		for(Entry<ItemStack, String> entry : oreReplacements.entrySet()) {
			ItemStack itemStack = entry.getKey();
			String oreName = entry.getValue();
			OreIngredient oreIngredient = new OreIngredient(oreName);

			IForgeRegistry<IRecipe> registry = event.getRegistry();
			for(IRecipe recipe : registry.getValues()) {

				NonNullList<Ingredient> ingredients = recipe.getIngredients();
				for(int i = 0; i < ingredients.size(); i++) {
					if(ingredients.get(i) instanceof OreIngredient) {
						continue;
					}
					if(ingredients.get(i).test(itemStack)) {
						ingredients.set(i, oreIngredient);
					}
				}
			}
		}
	}

	/**
	 * Set up the a common ore registry for all the things I want to assume for all my mods
	 *
	 * This also handles setting up any ingredients that should be replaced with ore ingredients.
	 */
	public static void initOreRegistry() {
		OreDictionary.registerOre("book", Items.BOOK);

		oreReplacements.put(new ItemStack(Items.BOOK), "book");
	}
}
