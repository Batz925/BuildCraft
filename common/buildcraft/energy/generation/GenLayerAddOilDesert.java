/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.energy.generation;

import java.util.Arrays;
import java.util.List;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;

import net.minecraftforge.common.BiomeDictionary;

import buildcraft.api.core.BCLog;

import buildcraft.lib.BCLib;

public class GenLayerAddOilDesert extends GenLayerBiomeReplacer {
    private static final double NOISE_FIELD_SCALE = 0.001;
    private static final double NOISE_FIELD_THRESHOLD = 0.7;

    private static final List<BiomeDictionary.Type> REQUIRED_TYPES = Arrays.asList(
        BiomeDictionary.Type.HOT,
        BiomeDictionary.Type.DRY,
        BiomeDictionary.Type.SANDY
    );

    public GenLayerAddOilDesert(long worldSeed, long seed, GenLayer parent) {
        super(worldSeed, seed, parent, NOISE_FIELD_SCALE, NOISE_FIELD_THRESHOLD, Biome.getIdForBiome(BiomeOilDesert.INSTANCE));
    }

    @Override
    protected boolean canReplaceBiome(int biomeId) {
        Biome biome = Biome.getBiomeForId(biomeId);
        if (biome == null) {
            if (BCLib.DEV) //don't spam outside of dev, it's way to noisy
                BCLog.logger.warn("Couldn't find a biome with an ID of " + biomeId);
            return false;
        }
        return BiomeDictionary.getTypes(biome).containsAll(REQUIRED_TYPES);
    }
}
