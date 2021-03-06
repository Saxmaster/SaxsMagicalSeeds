package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemOverrideList
{
    public static final ItemOverrideList NONE = new ItemOverrideList();
    private final List<ItemOverride> overrides = Lists.<ItemOverride>newArrayList();

    private ItemOverrideList()
    {
    }

    public ItemOverrideList(List<ItemOverride> overridesIn)
    {
        for (int i = overridesIn.size() - 1; i >= 0; --i)
        {
            this.overrides.add(overridesIn.get(i));
        }
    }

    @Deprecated
    public ResourceLocation applyOverride(ItemStack p_188021_1_, World p_188021_2_, EntityLivingBase p_188021_3_)
    {
        if (!this.overrides.isEmpty())
        {
            for (ItemOverride itemoverride : this.overrides)
            {
                if (itemoverride.matchesItemStack(p_188021_1_, p_188021_2_, p_188021_3_))
                {
                    return itemoverride.getLocation();
                }
            }
        }

        return null;
    }

    public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity)
    {
        net.minecraft.item.Item item = stack.getItem();
        if (item != null && item.hasCustomProperties())
        {
            ResourceLocation location = applyOverride(stack, world, entity);
            if (location != null)
            {
                return net.minecraft.client.Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(net.minecraftforge.client.model.ModelLoader.getInventoryVariant(location.toString()));
            }
        }
        return originalModel;
    }

    public com.google.common.collect.ImmutableList<ItemOverride> getOverrides()
    {
        return com.google.common.collect.ImmutableList.copyOf(overrides);
    }
}