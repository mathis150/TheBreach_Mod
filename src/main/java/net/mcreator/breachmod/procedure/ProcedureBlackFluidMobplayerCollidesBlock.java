package net.mcreator.breachmod.procedure;

import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.mcreator.breachmod.ElementsBreachMod;

@ElementsBreachMod.ModElement.Tag
public class ProcedureBlackFluidMobplayerCollidesBlock extends ElementsBreachMod.ModElement {
	public ProcedureBlackFluidMobplayerCollidesBlock(ElementsBreachMod instance) {
		super(instance, 52);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BlackFluidMobplayerCollidesBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, (int) 1, (int) 0, (true), (false)));
	}
}
