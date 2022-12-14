package net.ced7032.necromant.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SpeedyBlock extends Block {
    public SpeedyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        if(!pLevel.isClientSide()){
            if(pEntity instanceof LivingEntity livingEntity){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300));
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
