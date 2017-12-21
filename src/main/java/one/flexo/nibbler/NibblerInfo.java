/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler;

/**
 * Top level info related to the mod.  Real important stuff here! ...and yes I know constants are typically all
 * upper-cased, but I felt like using lower-case for these.
 *
 * @author "oneandonlyflexo"
 */
public class NibblerInfo {

	public static final String modid = "nibbler";
	public static final String name = "Nibbler";

	/**This gets replaced with a value from build.properties during the gradle build. */
	public static final String version = /*${gradle.mod_version}*/ "0.4";

	public static final String description = "Library mod for oneandonlyflexo's mods.";

}
