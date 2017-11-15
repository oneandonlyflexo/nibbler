/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.util;

import java.util.function.IntFunction;

import net.minecraft.util.ResourceLocation;

/**
 * A utility enum for getting the different types of ids that need the modid as a prefix.
 *
 * @author "oneandonlyflexo"
 */
public class NibblerName extends ResourceLocation {


	private IntFunction<String> metaToPath;

	public NibblerName(String resourceDomainIn, String resourcePathIn) {
		super(resourceDomainIn, resourcePathIn);
	}

	public NibblerName(String resourceName) {
		super(resourceName);
	}

	public NibblerName withMeta(IntFunction<String> metaToPath) {
		this.metaToPath = metaToPath;
		return this;
	}

	public String getUnlocalizedName() {
		return this.resourceDomain + "." + this.resourcePath;
	}

	public String getItemBlockName() {
		return this.resourceDomain + ":itemblock/" + this.resourcePath;
	}

	public String getItemBlockName(int meta) {
		String path = (metaToPath == null) ? this.resourcePath : metaToPath.apply(meta);
		return this.resourceDomain + ":itemblock/" + path;
	}

}
