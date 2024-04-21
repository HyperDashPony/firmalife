package com.eerussianguy.firmalife.common.recipes.data;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.dries007.tfc.common.capabilities.food.DynamicBowlHandler;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.dries007.tfc.common.recipes.outputs.ItemStackModifier;

public enum CopyBowlModifier implements ItemStackModifier.SingleInstance<CopyBowlModifier>
{
    INSTANCE;

    @Override
    public ItemStack apply(ItemStack stack, ItemStack input)
    {
        stack.getCapability(FoodCapability.CAPABILITY).ifPresent(cap -> {
            input.getCapability(FoodCapability.CAPABILITY).ifPresent(inputCap -> {
                if (cap instanceof DynamicBowlHandler outBowl && inputCap instanceof DynamicBowlHandler inBowl)
                {
                    ItemStack newBowl = inBowl.getBowl();
                    if (newBowl.isEmpty())
                    {
                        newBowl = new ItemStack(Items.BOWL);
                    }
                    outBowl.setBowl(newBowl);
                }
            });
        });
        return stack;
    }

    @Override
    public CopyBowlModifier instance()
    {
        return INSTANCE;
    }
}
