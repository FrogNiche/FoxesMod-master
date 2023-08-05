package com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer;

import com.frogniche.foxmod.FoxMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DevourerModel extends AnimatedGeoModel<DevourerEntity> {
    @Override
    public ResourceLocation getModelResource(DevourerEntity object) {
        return FoxMod.modLoc("geo/entity/horde_of_the_spore/devourer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DevourerEntity object) {
        return FoxMod.modLoc("textures/entity/horde_of_the_spore/devourer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DevourerEntity animatable) {
        return FoxMod.modLoc("animations/horde_of_the_spore/devourer.animation.json");
    }
}
