/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.registry;

/**
 * This lets sub classes of Nibbler classes know to auto-register, and with which registry to register through.
 *
 * @author "oneandonlyflexo"
 */
public interface NibblerRegisteredObject {

	NibblerRegistry getRegistry();

}
