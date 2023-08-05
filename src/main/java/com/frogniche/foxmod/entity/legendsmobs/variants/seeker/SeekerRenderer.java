package com.frogniche.foxmod.entity.legendsmobs.variants.seeker;

import com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsEntity;
import com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SeekerRenderer extends GeoEntityRenderer<SeekerEntity> {
    public SeekerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SeekerModel());
        this.shadowRadius = 0.1f;
    }

    @Override
    public ResourceLocation getTextureLocation(SeekerEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}