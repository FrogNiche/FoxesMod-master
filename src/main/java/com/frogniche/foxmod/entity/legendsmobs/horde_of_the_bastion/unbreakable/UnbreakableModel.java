package com.frogniche.foxmod.entity.legendsmobs.horde_of_the_bastion.unbreakable;

import com.frogniche.foxmod.FoxMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class UnbreakableModel extends AnimatedGeoModel<UnbreakableEntity> {
    @Override
    public ResourceLocation getModelResource(UnbreakableEntity object) {
        return FoxMod.modLoc("geo/entity/horde_of_the_bastion/unbreakable/unbreakable.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(UnbreakableEntity object) {
        return FoxMod.modLoc("textures/entity/horde_of_the_bastion/unbreakable.png");
    }

    @Override
    public ResourceLocation getAnimationResource(UnbreakableEntity animatable) {
        return FoxMod.modLoc("animations/horde_of_the_bastion/unbreakable/unbreakable.animation.json");
    }
}
