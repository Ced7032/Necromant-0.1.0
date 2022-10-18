package net.ced7032.necromant.playerSavedEntity;

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

public class PlayerSavedEntityProvider implements ICapabilityProvider, INBTSerializable {
    public static Capability<PlayerSavedEntity> PLAYER_SAVED_ENTITY = CapabilityManager.get(new CapabilityToken<PlayerSavedEntity>() { });

    private PlayerSavedEntity savedEntity = null;
    private final LazyOptional<PlayerSavedEntity> optional = LazyOptional.of(this::createPlayerSavedEntity);

    private PlayerSavedEntity createPlayerSavedEntity() {
        if(this.savedEntity == null) {
            this.savedEntity = new PlayerSavedEntity();
        }

        return this.savedEntity;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_SAVED_ENTITY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public Tag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerSavedEntity().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        createPlayerSavedEntity().loadNBTData((CompoundTag) nbt);
    }
}
