package com.frogniche.foxmod.entity.legendsmobs.variants.seeker;

import com.frogniche.foxmod.FoxMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeekerModel extends AnimatedGeoModel<SeekerEntity> {
    @Override
    public ResourceLocation getModelResource(SeekerEntity object) {
        return FoxMod.modLoc("geo/entity/variants/seeker/seeker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeekerEntity object) {
        return FoxMod.modLoc("textures/entity/variants/seeker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeekerEntity animatable) {
        return FoxMod.modLoc("animations/variants/seeker/seeker.animation.json");
    }
}
