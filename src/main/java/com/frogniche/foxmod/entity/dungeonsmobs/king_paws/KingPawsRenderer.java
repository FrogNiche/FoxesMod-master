package com.frogniche.foxmod.entity.dungeonsmobs.king_paws;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KingPawsRenderer extends GeoEntityRenderer<KingPawsEntity> {
    public KingPawsRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KingPawsModel());
        this.shadowRadius = 0.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(KingPawsEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}