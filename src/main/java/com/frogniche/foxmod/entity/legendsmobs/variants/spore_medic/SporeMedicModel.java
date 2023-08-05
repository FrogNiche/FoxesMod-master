package com.frogniche.foxmod.entity.legendsmobs.variants.spore_medic;

import com.frogniche.foxmod.FoxMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SporeMedicModel extends AnimatedGeoModel<SporeMedicEntity> {
    @Override
    public ResourceLocation getModelResource(SporeMedicEntity object) {
        return FoxMod.modLoc("geo/entity/variants/medics/spore_medic.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SporeMedicEntity object) {
        return object.getTextureType().getTexture();
    }

    @Override
    public ResourceLocation getAnimationResource(SporeMedicEntity animatable) {
        return FoxMod.modLoc("animations/variants/medics/spore_medic.animation.json");
    }
}
