package net.minecraft.client.particle;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFishWakeFX extends EntityFX
{
    protected EntityFishWakeFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i45073_8_, double p_i45073_10_, double p_i45073_12_)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        this.xSpeed *= 0.30000001192092896D;
        this.ySpeed = Math.random() * 0.20000000298023224D + 0.10000000149011612D;
        this.zSpeed *= 0.30000001192092896D;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.setParticleTextureIndex(19);
        this.setSize(0.01F, 0.01F);
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleGravity = 0.0F;
        this.xSpeed = p_i45073_8_;
        this.ySpeed = p_i45073_10_;
        this.zSpeed = p_i45073_12_;
    }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.ySpeed -= (double)this.particleGravity;
        this.moveEntity(this.xSpeed, this.ySpeed, this.zSpeed);
        this.xSpeed *= 0.9800000190734863D;
        this.ySpeed *= 0.9800000190734863D;
        this.zSpeed *= 0.9800000190734863D;
        int i = 60 - this.particleMaxAge;
        float f = (float)i * 0.001F;
        this.setSize(f, f);
        this.setParticleTextureIndex(19 + i % 4);

        if (this.particleMaxAge-- <= 0)
        {
            this.setExpired();
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new EntityFishWakeFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
        }
}