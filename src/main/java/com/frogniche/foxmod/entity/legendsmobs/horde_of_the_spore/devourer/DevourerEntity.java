package com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import static com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsEntity.Sleeping;

public class DevourerEntity extends Monster implements IAnimatable, RoarEntity {

    protected ServerBossEvent bossBar = (ServerBossEvent) new ServerBossEvent(this.getDisplayName(),

            BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(false);

    public static final EntityDataAccessor<Boolean> ROAR = SynchedEntityData.defineId(DevourerEntity.class, EntityDataSerializers.BOOLEAN);
    public static final String CONTROLLER_NAME = "controller";
    public static final AttributeSupplier makeAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.ARMOR, 15000)
                .add(Attributes.MOVEMENT_SPEED, 0.6d)
                .add(Attributes.ATTACK_DAMAGE, 19)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1000000).build();

    }

    protected AnimationFactory factory = new AnimationFactory(this);
    private int roarCooldown;

    public DevourerEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    @Override
    public boolean doHurtTarget(Entity opfer) {
        if (super.doHurtTarget(opfer)) {
            this.level.broadcastEntityEvent(this, (byte) 10);
            return true;
        } else {
            if (opfer instanceof LivingEntity) {
                ((LivingEntity) opfer).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), this);
            }
        }
        return true;
    }
    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            if (this.roarCooldown > 0) {
                roarCooldown--;
                if (roarCooldown == 0)
                    this.entityData.set(ROAR, false);
            }
        }
    }
    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (this.isSleeping() && source.getEntity() != null)
            this.entityData.set(Sleeping, false);
        return super.hurt(source, damage);


    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RoarGoal<>(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6f, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));

        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 10f));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.5d));

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ROAR, false);
    }
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 10) {
            AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, this.getId(), CONTROLLER_NAME);
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.devourer.smash"));
        } else
            super.handleEntityEvent(id);
    }

    private PlayState predicate(AnimationEvent<DevourerEntity> event) {
        if (entityData.get(ROAR)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.devourer.roar_when_failed_player_harm", false));
            return PlayState.CONTINUE;
        } else if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.devourer.new2", true));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.devourer.idle", true));
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent<DevourerEntity> event) {
        if (this.swinging && event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.devourer.smash", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attack_controller", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void roar() {
        if (!level.isClientSide()) {
            this.entityData.set(ROAR, true);
            this.roarCooldown = 84;
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 84, 99, false, false));
        }
    }

    @Override
    public boolean canRoar() {
        return this.roarCooldown <= 0;
    }

    public static class RoarGoal<T extends Mob & RoarEntity> extends Goal {

        protected final T entity;
        protected LivingEntity prevTarget = null;

        /**
         * @param entity must implement the RoarEntity interface
         */
        public RoarGoal(T entity) {
            this.entity = entity;
        }

        @Override
        public boolean canUse() {
            if (entity.getTarget() == null)
                prevTarget = null;
            return entity.canRoar() && prevTarget == null && entity.getTarget() != prevTarget;
        }

        @Override
        public void start() {
            entity.roar();
            prevTarget = entity.getTarget();
        }
    }
    @Override
    public void startSeenByPlayer(ServerPlayer p31483) {
        super.startSeenByPlayer(p31483);
        this.bossBar.addPlayer(p31483);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer p31488) {
        super.stopSeenByPlayer(p31488);
        this.bossBar.removePlayer(p31488);
    }

    public void setCustomName(@Nullable Component p31476) {
        super.setCustomName(p31476);
        this.bossBar.setName(this.getDisplayName());
    }
}


