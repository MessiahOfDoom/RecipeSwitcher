package de.schneider_oliver.recipeswitcher.mixin;

import org.spongepowered.asm.mixin.Mixin;

import de.schneider_oliver.recipeswitcher.common.AdvancedRecipeInventory;
import de.schneider_oliver.recipeswitcher.common.WrappedInt;
import net.minecraft.inventory.CraftingInventory;

@Mixin(CraftingInventory.class)
public class InventoryMixin implements AdvancedRecipeInventory{

	private WrappedInt wrappedMatch = new WrappedInt(1, 1);
	
	@Override
	public void setWrappedMatch(WrappedInt wi) {
		wrappedMatch = wi;
	}

	@Override
	public WrappedInt getWrappedMatch() {
		return wrappedMatch;
	}

	@Override
	public void setMatchInWrapper(int i) {
		wrappedMatch = wrappedMatch.with(i);
	}

	
	
}
