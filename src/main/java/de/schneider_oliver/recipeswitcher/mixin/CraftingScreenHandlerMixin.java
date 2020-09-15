package de.schneider_oliver.recipeswitcher.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import de.schneider_oliver.recipeswitcher.common.AdvancedRecipeInventory;
import de.schneider_oliver.recipeswitcher.common.AdvancedRecipeManager;
import de.schneider_oliver.recipeswitcher.common.AdvancedRecipeScreenHandler;
import de.schneider_oliver.recipeswitcher.common.WrappedInt;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.world.World;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin implements AdvancedRecipeScreenHandler{

	@Shadow
	private CraftingInventory input;
	
	@Shadow
	public void onContentChanged(Inventory inventory) {}
	
	@Override
	public void incrementMatch(World world) {
		int min = 1;
		int max = 1;
		int value = 0;
		if(world.getServer().getRecipeManager() instanceof AdvancedRecipeManager) {
			max = ((AdvancedRecipeManager)world.getServer().getRecipeManager()).getNumMatches(RecipeType.CRAFTING, input, world);
		}
		if(input instanceof AdvancedRecipeInventory) {
			value = ((AdvancedRecipeInventory)input).getWrappedMatch().intValue();
			((AdvancedRecipeInventory)input).setWrappedMatch(new WrappedInt(min, max, value + 1));
		}
		this.onContentChanged(input);
	}

}
