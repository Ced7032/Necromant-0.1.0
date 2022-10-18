package net.ced7032.necromant.thirst;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerThirstProvider implements ICapabilityProvider, INBTSerializable {
    public static Capability<PlayerThirst> PLAYER_THIRST = CapabilityManager.get(new CapabilityToken<PlayerThirst>() { });

    private PlayerThirst thirst = null;
    private final LazyOptional<PlayerThirst> optional = LazyOptional.of(this::createPlayerThirst);

    private PlayerThirst createPlayerThirst() {
        if(this.thirst == null) {
            this.thirst = new PlayerThirst();
        }

        return this.thirst;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_THIRST) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public Tag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerThirst().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        createPlayerThirst().loadNBTData((CompoundTag) nbt);
    }
}
