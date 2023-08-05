package com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer;

public interface RoarEntity {

    /**
     * must work on server side, not on client side
     */
    public void roar();

    /**
     *
     * @return whether the entity can roar again or not, only correct on the server side
     */
    public boolean canRoar();
}
