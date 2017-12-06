/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.item.tool;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.item.Item.ToolMaterial;

public class NibblerToolData {
	public final float damage;
	public final float speed;
	public final ToolMaterial material;
	public final Set<Block> effectiveBlocks;

	public NibblerToolData(float damage, float speed) {
		this(damage, speed, ToolMaterial.WOOD, Collections.emptySet());
	}

	public NibblerToolData(float damage, float speed, ToolMaterial material) {
		this(damage, speed, material, Collections.emptySet());
	}

	public NibblerToolData(float damage, float speed, ToolMaterial material, Set<Block> effectiveBlocks) {
		this.damage = damage;
		this.speed = speed;
		this.material = material;
		this.effectiveBlocks = effectiveBlocks;
	}

	public NibblerToolData as(ToolMaterial newMaterial) {
		return new NibblerToolData(this.damage, this.speed, newMaterial, this.effectiveBlocks);
	}

	public NibblerToolData as(ToolMaterial newMaterial, Block... addedEffectiveBlocks) {
		Set<Block> newEffectiveBlocks = Sets.newHashSet(addedEffectiveBlocks);
		newEffectiveBlocks.addAll(this.effectiveBlocks);
		return new NibblerToolData(this.damage, this.speed, newMaterial, newEffectiveBlocks);
	}

	public NibblerToolData add(Block... addedEffectiveBlocks) {
		Set<Block> newEffectiveBlocks = Sets.newHashSet(addedEffectiveBlocks);
		newEffectiveBlocks.addAll(this.effectiveBlocks);
		return new NibblerToolData(this.damage, this.speed, this.material, newEffectiveBlocks);
	}
}
