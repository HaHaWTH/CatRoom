package org.bukkit.craftbukkit.v1_12_R1.enchantments;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class CraftEnchantment extends Enchantment {
    private final net.minecraft.enchantment.Enchantment target;

    public CraftEnchantment(net.minecraft.enchantment.Enchantment target) {
        super(net.minecraft.enchantment.Enchantment.getEnchantmentID(target));
        this.target = target;
    }

    @Override
    public int getMaxLevel() {
        return target.getMaxLevel();
    }

    @Override
    public int getStartLevel() {
        return target.getMinLevel();
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        switch (target.type) {
        case ALL:
            return EnchantmentTarget.ALL;
        case ARMOR:
            return EnchantmentTarget.ARMOR;
        case ARMOR_FEET:
            return EnchantmentTarget.ARMOR_FEET;
        case ARMOR_HEAD:
            return EnchantmentTarget.ARMOR_HEAD;
        case ARMOR_LEGS:
            return EnchantmentTarget.ARMOR_LEGS;
        case ARMOR_CHEST:
            return EnchantmentTarget.ARMOR_TORSO;
        case DIGGER:
            return EnchantmentTarget.TOOL;
        case WEAPON:
            return EnchantmentTarget.WEAPON;
        case BOW:
            return EnchantmentTarget.BOW;
        case FISHING_ROD:
            return EnchantmentTarget.FISHING_ROD;
        case BREAKABLE:
            return EnchantmentTarget.BREAKABLE;
        case WEARABLE:
            return EnchantmentTarget.WEARABLE;
        default:
            return null;
        }
    }

    @Override
    public boolean isTreasure() {
        return target.isTreasureEnchantment();
    }

    @Override
    public boolean isCursed() {
        return target.isCurse();
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return target.canApply(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public String getName() {
        int id = getId();
        String name = switch (id) {
            case 0 -> "PROTECTION_ENVIRONMENTAL";
            case 1 -> "PROTECTION_FIRE";
            case 2 -> "PROTECTION_FALL";
            case 3 -> "PROTECTION_EXPLOSIONS";
            case 4 -> "PROTECTION_PROJECTILE";
            case 5 -> "OXYGEN";
            case 6 -> "WATER_WORKER";
            case 7 -> "THORNS";
            case 8 -> "DEPTH_STRIDER";
            case 9 -> "FROST_WALKER";
            case 10 -> "BINDING_CURSE";
            case 16 -> "DAMAGE_ALL";
            case 17 -> "DAMAGE_UNDEAD";
            case 18 -> "DAMAGE_ARTHROPODS";
            case 19 -> "KNOCKBACK";
            case 20 -> "FIRE_ASPECT";
            case 21 -> "LOOT_BONUS_MOBS";
            case 22 -> "SWEEPING_EDGE";
            case 32 -> "DIG_SPEED";
            case 33 -> "SILK_TOUCH";
            case 34 -> "DURABILITY";
            case 35 -> "LOOT_BONUS_BLOCKS";
            case 48 -> "ARROW_DAMAGE";
            case 49 -> "ARROW_KNOCKBACK";
            case 50 -> "ARROW_FIRE";
            case 51 -> "ARROW_INFINITE";
            case 61 -> "LUCK";
            case 62 -> "LURE";
            case 70 -> "MENDING";
            case 71 -> "VANISHING_CURSE";
            default -> "UNKNOWN_ENCHANT_" + id;
        };
        return name;
    }

    public static net.minecraft.enchantment.Enchantment getRaw(Enchantment enchantment) {
        if (enchantment instanceof EnchantmentWrapper) {
            enchantment = ((EnchantmentWrapper) enchantment).getEnchantment();
        }

        if (enchantment instanceof CraftEnchantment) {
            return ((CraftEnchantment) enchantment).target;
        }

        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (other instanceof EnchantmentWrapper) {
            other = ((EnchantmentWrapper) other).getEnchantment();
        }
        if (!(other instanceof CraftEnchantment)) {
            return false;
        }
        CraftEnchantment ench = (CraftEnchantment) other;
        return !target.isCompatibleWith(ench.target);
    }

    public net.minecraft.enchantment.Enchantment getHandle() {
        return target;
    }
}
