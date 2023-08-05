package com.frogniche.foxmod.entity.dungeonsmobs.king_paws;

import com.frogniche.foxmod.FoxMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KingPawsModel extends AnimatedGeoModel<KingPawsEntity> {
    @Override
    public ResourceLocation getModelResource(KingPawsEntity object) {
        return FoxMod.modLoc("geo/entity/dungeons_mobs/king_paws.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KingPawsEntity object) {
        return FoxMod.modLoc("textures/entity/dungeons/king_paws.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KingPawsEntity animatable) {
        return FoxMod.modLoc("animations/dungeons_mobs/king_paws.animation.json");
    }
}