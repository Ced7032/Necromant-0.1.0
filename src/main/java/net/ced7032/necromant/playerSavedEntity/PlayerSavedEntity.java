package net.ced7032.necromant.playerSavedEntity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class PlayerSavedEntity {
    private LivingEntity entity;

    public LivingEntity getEntity(){
        return entity;
    }

    public void setEntity(LivingEntity newEntity) {
        this.entity = newEntity;
    }

    public void saveNBTData(CompoundTag nbt) {
        if(entity != null) {
            nbt.put("savedEntity", entity.serializeNBT());
        }
    }

    public void loadNBTData(CompoundTag nbt) {
        entity.deserializeNBT((CompoundTag) nbt.get("savedEntity"));
    }
}
