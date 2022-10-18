package net.ced7032.necromant.thirst;

import net.minecraft.nbt.CompoundTag;

public class PlayerThirst {
    private float thirst;
    private final int MIN_THIRST = 0;
    private final int MAX_THIRST = 10;

    public float getThirst(){
        return thirst;
    }

    public void addThirst(float add){
        this.thirst = Math.min(thirst + add, MAX_THIRST);
    }

    public void subThirst(int sub){
        this.thirst = Math.max(thirst - sub, MIN_THIRST);
    }

    public void copyFrom(PlayerThirst source) {
        this.thirst = source.thirst;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("thirst", thirst);
    }

    public void loadNBTData(CompoundTag nbt) {
        thirst = nbt.getInt("thirst");
    }
}
