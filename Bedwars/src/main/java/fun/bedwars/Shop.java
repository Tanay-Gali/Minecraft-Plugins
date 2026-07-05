package fun.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;

public class Shop implements Listener {
    public Inventory shop;

    @EventHandler
    public void PlayerEntity(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.VILLAGER)){
            Player player = e.getPlayer();
            shop = Bukkit.createInventory(player, 45, ChatColor.RED + "Shop");
            ItemStack filler = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE,1);
            ItemMeta fillermeta = filler.getItemMeta();
            fillermeta.setDisplayName("");
            filler.setItemMeta(fillermeta);

            ItemStack wool = new ItemStack(Material.WHITE_WOOL,16);
            ItemMeta woolMeta = wool.getItemMeta();
            woolMeta.setDisplayName(ChatColor.WHITE + "Wool" + ChatColor.GRAY+" Cost: 4 iron");
            wool.setItemMeta(woolMeta);



            ItemStack wood = new ItemStack(Material.OAK_PLANKS,16);
            ItemMeta woodmeta = wood.getItemMeta();
            woodmeta.setDisplayName(ChatColor.WHITE + "Wood"+ ChatColor.GOLD + " Cost: 4 gold");
            wood.setItemMeta(woodmeta);
            ItemStack endstone = new ItemStack(Material.END_STONE, 12);
            ItemMeta endstonemeta = endstone.getItemMeta();
            endstonemeta.setDisplayName(ChatColor.WHITE + "Endstone" + ChatColor.GRAY + " Cost: 24 iron");
            endstone.setItemMeta(endstonemeta);
            ItemStack terracotta = new ItemStack(Material.TERRACOTTA,16);
            ItemMeta terracottameta = terracotta.getItemMeta();
            terracottameta.setDisplayName(ChatColor.WHITE + "Clay" + ChatColor.GRAY+" Cost: 12 iron");
            terracotta.setItemMeta(terracottameta);
            ItemStack arrow = new ItemStack(Material.ARROW,6);
            ItemMeta arrowmeta = arrow.getItemMeta();
            arrowmeta.setDisplayName(ChatColor.WHITE + "Arrows" + ChatColor.GOLD + " Cost: 6 gold");
            arrow.setItemMeta(arrowmeta);
            ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE,1);
            ItemMeta goldenapplemeta = goldenapple.getItemMeta();
            goldenapplemeta.setDisplayName(ChatColor.AQUA + "Golden Apples" + ChatColor.GOLD + " Cost: 3 gold");
            goldenapple.setItemMeta(goldenapplemeta);


            ItemStack invispot = new ItemStack(Material.POTION,1);
            PotionMeta invismeta = (PotionMeta) invispot.getItemMeta();
            PotionData invisdata = new PotionData(PotionType.INVISIBILITY);
            invismeta.setBasePotionData(invisdata);
            invismeta.setDisplayName(ChatColor.AQUA + "Invisibility (30 seconds)" + ChatColor.DARK_GREEN + " Cost: 2 emeralds");
            invispot.setItemMeta(invismeta);

            ItemStack stonesword = new ItemStack(Material.STONE_SWORD);
            ItemMeta stoneswordmeta = stonesword.getItemMeta();
            stoneswordmeta.setDisplayName(ChatColor.WHITE + "Stone sword" + ChatColor.GRAY + " Cost: 10 iron");
            stonesword.setItemMeta(stoneswordmeta);

            ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
            ItemMeta ironswordmeta = ironsword.getItemMeta();
            ironswordmeta.setDisplayName(ChatColor.WHITE + "Iron sword" + ChatColor.GOLD + " Cost: 7 gold");
            ironsword.setItemMeta(ironswordmeta);



            ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta diamondswordmeta = diamondsword.getItemMeta();
            diamondswordmeta.setDisplayName(ChatColor.WHITE + "Diamond sword" + ChatColor.DARK_GREEN + " Cost: 4 emeralds");
            diamondsword.setItemMeta(diamondswordmeta);



            ItemStack shield = new ItemStack(Material.SHIELD);
            ItemMeta shieldmeta = shield.getItemMeta();
            shieldmeta.setDisplayName(ChatColor.WHITE + "Shield" + ChatColor.GOLD + " Cost: 7 Gold");
            shield.setItemMeta(shieldmeta);


            ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
            ItemMeta fishingrodmeta = fishingrod.getItemMeta();
            fishingrodmeta.setDisplayName(ChatColor.WHITE + "Fishing Rod" + ChatColor.DARK_GREEN + " Cost: 4 emeralds");
            fishingrod.setItemMeta(fishingrodmeta);




            ItemStack bridgeEgg = new ItemStack(Material.EGG);
            ItemMeta bridgeEggmeta = bridgeEgg.getItemMeta();
            bridgeEggmeta.setDisplayName(ChatColor.WHITE + "Bridge Egg" + ChatColor.DARK_GREEN + " Cost: 1 emerald");
            bridgeEgg.setItemMeta(bridgeEggmeta);




            ItemStack jumppot = new ItemStack(Material.POTION);
            PotionMeta jumpmeta = (PotionMeta) jumppot.getItemMeta();
            PotionData jumpdata = new PotionData(PotionType.JUMP);
            jumpmeta.setBasePotionData(jumpdata);
            jumpmeta.setDisplayName(ChatColor.AQUA + "Jump Boost (45 Seconds)" + ChatColor.DARK_GREEN + " Cost: 1 emerald");
            jumppot.setItemMeta(jumpmeta);

            ItemStack woodenaxe = new ItemStack(Material.WOODEN_AXE);
            woodenaxe.addEnchantment(Enchantment.DIG_SPEED,2);
            ItemMeta woodenaxemeta = woodenaxe.getItemMeta();
            woodenaxemeta.setDisplayName(ChatColor.AQUA + "Wooden Axe" + ChatColor.GRAY + " Cost: 15 iron");
            woodenaxe.setItemMeta(woodenaxemeta);
            ItemStack stoneaxe = new ItemStack(Material.STONE_AXE);
            stoneaxe.addEnchantment(Enchantment.DIG_SPEED,2);
            ItemMeta stoneaxemeta = stoneaxe.getItemMeta();
            stoneaxemeta.setDisplayName(ChatColor.AQUA + "Stone Axe" + ChatColor.GRAY + " Cost: 25 iron");
            stoneaxe.setItemMeta(stoneaxemeta);
            ItemStack ironaxe = new ItemStack(Material.IRON_AXE);
            ironaxe.addEnchantment(Enchantment.DIG_SPEED,2);
            ItemMeta ironaxemeta = ironaxe.getItemMeta();
            ironaxemeta.setDisplayName(ChatColor.AQUA + "Iron Axe" + ChatColor.GOLD + " Cost: 9 gold");
            ironaxe.setItemMeta(ironaxemeta);
            ItemStack diamondaxe = new ItemStack(Material.DIAMOND_AXE);
            diamondaxe.addEnchantment(Enchantment.DIG_SPEED,3);
            ItemMeta diamondaxemeta = diamondaxe.getItemMeta();
            diamondaxemeta.setDisplayName(ChatColor.AQUA + "Diamond Axe" + ChatColor.DARK_GREEN + " Cost: 6 emeralds");
            diamondaxe.setItemMeta(diamondaxemeta);
            ItemStack xpbottle = new ItemStack(Material.EXPERIENCE_BOTTLE,8);
            ItemMeta xpbottlemeta = xpbottle.getItemMeta();
            xpbottlemeta.setDisplayName(ChatColor.YELLOW + "Xp Bottles" + ChatColor.GOLD + " Cost: 3 gold");
            xpbottle.setItemMeta(xpbottlemeta);

            ItemStack fireball = new ItemStack(Material.FIRE_CHARGE);
            ItemMeta fireballmeta = fireball.getItemMeta();
            fireballmeta.setDisplayName(ChatColor.WHITE + "Fireball" + ChatColor.GRAY + " Cost: 40 iron");
            fireball.setItemMeta(fireballmeta);
            ItemStack speedpot = new ItemStack(Material.POTION);
            PotionMeta speedpotmeta = (PotionMeta) speedpot.getItemMeta();
            PotionData speeddata = new PotionData(PotionType.SPEED);
            speedpotmeta.setBasePotionData(speeddata);
            speedpotmeta.setDisplayName(ChatColor.AQUA + "Speed (45 Seconds)" + ChatColor.DARK_GREEN + " Cost: 1 emerald");
            speedpot.setItemMeta(speedpotmeta);

            ItemStack woodenpick = new ItemStack(Material.WOODEN_PICKAXE);
            woodenpick.addEnchantment(Enchantment.DIG_SPEED,2);
            ItemMeta woodenpickmeta = woodenpick.getItemMeta();
            woodenpickmeta.setDisplayName(ChatColor.AQUA + "Wooden Pickaxe" + ChatColor.GRAY + " Cost: 10 iron");
            woodenpick.setItemMeta(woodenpickmeta);
            ItemStack stonepick = new ItemStack(Material.STONE_PICKAXE);
            stonepick.addEnchantment(Enchantment.DIG_SPEED,2);
            ItemMeta stonepickmeta = stonepick.getItemMeta();
            stonepickmeta.setDisplayName(ChatColor.AQUA + "Stone Pickaxe" + ChatColor.GRAY + " Cost: 20 iron");
            stonepick.setItemMeta(stonepickmeta);
            ItemStack ironpick = new ItemStack(Material.IRON_PICKAXE);
            ironpick.addEnchantment(Enchantment.DIG_SPEED,2);
            ItemMeta ironpickmeta = ironpick.getItemMeta();
            ironpickmeta.setDisplayName(ChatColor.AQUA + "Iron Pickaxe" + ChatColor.GRAY + " Cost: 40 iron");
            ironpick.setItemMeta(ironpickmeta);

            ItemStack diamondpick = new ItemStack(Material.DIAMOND_PICKAXE);
            diamondpick.addEnchantment(Enchantment.DIG_SPEED,3);
            ItemMeta diamondpickmeta = diamondpick.getItemMeta();
            diamondpickmeta.setDisplayName(ChatColor.AQUA + "Diamond Pickaxe" + ChatColor.GOLD + " Cost: 9 gold");
            diamondpick.setItemMeta(diamondpickmeta);

            ItemStack tntbow = new ItemStack(Material.BOW);
            ItemMeta tntbowmeta = (ItemMeta) tntbow.getItemMeta();
            tntbowmeta.setDisplayName(ChatColor.WHITE + "Tnt Bow" + ChatColor.DARK_GREEN + " Cost: 8 emeralds");
            tntbowmeta.setLore(Arrays.asList("Explodes on impact"));
            tntbow.setItemMeta(tntbowmeta);

            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowmeta = bow.getItemMeta();
            bowmeta.setDisplayName(ChatColor.WHITE + "Bow" + ChatColor.GOLD + " Cost: 9 gold");
            bow.setItemMeta(bowmeta);

            ItemStack crossbow = new ItemStack(Material.CROSSBOW);
            ItemMeta crossbowmeta = crossbow.getItemMeta();
            crossbowmeta.setDisplayName(ChatColor.WHITE + "Crossbow" + ChatColor.GOLD + " Cost: 15 gold");
            crossbow.setItemMeta(crossbowmeta);

            ItemStack chainmail = new ItemStack(Material.CHAINMAIL_BOOTS);
            ItemMeta chainmailmeta = chainmail.getItemMeta();
            chainmailmeta.setDisplayName(ChatColor.AQUA + "Chainmail Armor" + ChatColor.GRAY + " Cost: 30 iron");
            chainmail.setItemMeta(chainmailmeta);

            ItemStack iron = new ItemStack(Material.IRON_BOOTS);
            ItemMeta ironmeta = iron.getItemMeta();
            ironmeta.setDisplayName(ChatColor.AQUA + "Iron Armor" + ChatColor.GOLD + " Cost: 12 gold");
            iron.setItemMeta(ironmeta);


            ItemStack diamond = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta diamondmeta = diamond.getItemMeta();
            diamondmeta.setDisplayName(ChatColor.AQUA + "Diamond Armor" + ChatColor.DARK_GREEN + " Cost: 6 emeralds");
            diamond.setItemMeta(diamondmeta);


            ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL);
            ItemMeta enderpearlmeta = enderpearl.getItemMeta();
            enderpearlmeta.setDisplayName(ChatColor.WHITE + "Ender Pearl" + ChatColor.DARK_GREEN + " Cost: 4 emeralds");
            enderpearl.setItemMeta(enderpearlmeta);

            ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 4);
            ItemMeta obsidianmeta = obsidian.getItemMeta();
            obsidianmeta.setDisplayName(ChatColor.WHITE + "Obsidian" + ChatColor.DARK_GREEN + " Cost: 4 emeralds");
            obsidian.setItemMeta(obsidianmeta);



            ItemStack tnt = new ItemStack(Material.TNT);
            ItemMeta tntmeta = tnt.getItemMeta();
            tntmeta.setDisplayName(ChatColor.WHITE + "TNT" + ChatColor.GOLD + " Cost: 4 gold");
            tnt.setItemMeta(tntmeta);

            ItemStack waterbucket = new ItemStack(Material.WATER_BUCKET);
            ItemMeta waterbucketmeta = waterbucket.getItemMeta();
            waterbucketmeta.setDisplayName(ChatColor.WHITE + "Water Bucket" + ChatColor.GOLD + " Cost: 4 gold");
            waterbucket.setItemMeta(waterbucketmeta);


            ItemStack trident = new ItemStack(Material.TRIDENT);
            trident.addEnchantment(Enchantment.RIPTIDE,2);
            ItemMeta tridentmeta = trident.getItemMeta();
            tridentmeta.setDisplayName(ChatColor.AQUA + "Trident" + ChatColor.DARK_GREEN + " Cost: 8 emeralds");
            tridentmeta.setUnbreakable(true);
            trident.setItemMeta(tridentmeta);


            ItemStack snowball = new ItemStack(Material.SNOWBALL,8);





            shop.setItem(0,filler);
            shop.setItem(1,chainmail);
            shop.setItem(2,filler);
            shop.setItem(3,filler);
            shop.setItem(4,iron);
            shop.setItem(5,filler);
            shop.setItem(6,filler);
            shop.setItem(7,diamond);
            shop.setItem(8,filler);
            shop.setItem(9,filler);
            shop.setItem(10,wool);
            shop.setItem(11,wood);
            shop.setItem(12,endstone);
            shop.setItem(13,terracotta);
            shop.setItem(14,arrow);
            shop.setItem(15,goldenapple);
            shop.setItem(16,invispot);
            shop.setItem(17,filler);
            shop.setItem(18,trident);
            shop.setItem(19,stonesword);
            shop.setItem(20,ironsword);
            shop.setItem(21,diamondsword);
            shop.setItem(22,shield);
            shop.setItem(23,fishingrod);
            shop.setItem(24,bridgeEgg);
            shop.setItem(25,jumppot);
            shop.setItem(26,snowball);
            shop.setItem(27,tnt);
            shop.setItem(28,woodenaxe);
            shop.setItem(29,stoneaxe);
            shop.setItem(30,ironaxe);
            shop.setItem(31,diamondaxe);
            shop.setItem(32,xpbottle);
            shop.setItem(33,fireball);
            shop.setItem(34,speedpot);
            shop.setItem(35,waterbucket);
            shop.setItem(36,enderpearl);
            shop.setItem(37,woodenpick);
            shop.setItem(38,stonepick);
            shop.setItem(39,ironpick);
            shop.setItem(40,diamondpick);
            shop.setItem(41,tntbow);
            shop.setItem(42,bow);
            shop.setItem(43,crossbow);
            shop.setItem(44,obsidian);










            player.openInventory(shop);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.RED + "Shop")) {

            if (e.getClickedInventory().getSize() == 45) {
                e.setCancelled(true);
                switch (e.getRawSlot()) {
                    case 1:
                        armorcalc((Player) e.getWhoClicked(),30,Material.IRON_INGOT,Material.CHAINMAIL_BOOTS,"iron");
                        break;
                    case 4:
                        armorcalc((Player) e.getWhoClicked(),12,Material.GOLD_INGOT,Material.IRON_BOOTS,"gold");
                        break;
                    case 7:
                        armorcalc((Player) e.getWhoClicked(),6,Material.EMERALD,Material.DIAMOND_BOOTS,"emeralds");
                        break;
                    case 10:
                        shopcalc((Player) e.getWhoClicked(), 4,Material.IRON_INGOT,new ItemStack(Material.WHITE_WOOL, 16), "iron"," wool");
                        break;
                    case 11:
                        shopcalc((Player) e.getWhoClicked(),4,Material.GOLD_INGOT,new ItemStack(Material.OAK_PLANKS,16),"gold"," wood");
                        break;
                    case 12:
                        shopcalc((Player) e.getWhoClicked(), 24,Material.IRON_INGOT,new ItemStack(Material.END_STONE,12),"iron"," endstone");
                        break;
                    case 13:
                        shopcalc((Player) e.getWhoClicked(),12,Material.IRON_INGOT,new ItemStack(Material.TERRACOTTA,16),"iron"," clay");
                        break;
                    case 14:
                        shopcalc((Player) e.getWhoClicked(),6,Material.GOLD_INGOT,new ItemStack(Material.ARROW,6),"gold"," arrows");
                        break;
                    case 15:
                        shopcalc((Player) e.getWhoClicked(),3,Material.GOLD_INGOT,new ItemStack(Material.GOLDEN_APPLE),"gold", " gapples");
                        break;
                    case 16:
                        ItemStack invispotion = new ItemStack(Material.POTION);
                        PotionMeta invismeta = (PotionMeta) invispotion.getItemMeta();
                        PotionData invisdata = new PotionData(PotionType.INVISIBILITY);
                        invismeta.setBasePotionData(invisdata);
                        invispotion.setItemMeta(invismeta);

                        shopcalc((Player) e.getWhoClicked(),2,Material.EMERALD,invispotion,"emeralds"," invis (ur dad used this one)");

                        break;


                    case 18:
                        ItemStack trident = new ItemStack(Material.TRIDENT);
                        trident.addEnchantment(Enchantment.RIPTIDE,2);
                        ItemMeta tridentmeta = trident.getItemMeta();
                        tridentmeta.setUnbreakable(true);
                        trident.setItemMeta(tridentmeta);

                        shopcalc((Player) e.getWhoClicked(),8,Material.EMERALD,trident,"emeralds"," a trident");
                        break;


                    case 19:
                        ItemStack stonesword = new ItemStack(Material.STONE_SWORD);
                        ItemMeta stoneswordmeta = stonesword.getItemMeta();
                        stoneswordmeta.setUnbreakable(true);
                        stonesword.setItemMeta(stoneswordmeta);




                        shopcalc((Player) e.getWhoClicked(),10,Material.IRON_INGOT,stonesword,"iron"," a stone sword");
                        break;
                    case 20:
                        ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
                        ItemMeta ironswordmeta = ironsword.getItemMeta();
                        ironswordmeta.setUnbreakable(true);
                        ironsword.setItemMeta(ironswordmeta);




                        shopcalc((Player) e.getWhoClicked(),7,Material.GOLD_INGOT,ironsword,"gold"," an iron sword");




                        break;
                    case 21:

                        ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD);
                        ItemMeta diamondswordmeta = diamondsword.getItemMeta();
                        diamondswordmeta.setUnbreakable(true);
                        diamondsword.setItemMeta(diamondswordmeta);




                        shopcalc((Player) e.getWhoClicked(),4,Material.EMERALD,diamondsword,"emeralds", " a diamond sword");
                        break;
                    case 22:
                        ItemStack shield = new ItemStack(Material.SHIELD);
                        ItemMeta shieldmeta = shield.getItemMeta();
                        shieldmeta.setUnbreakable(true);
                        shield.setItemMeta(shieldmeta);




                        shopcalc((Player) e.getWhoClicked(),7,Material.GOLD_INGOT,shield,"gold", " a shield");




                        break;
                    case 23:
                        shopcalc((Player) e.getWhoClicked(),4,Material.EMERALD,new ItemStack(Material.FISHING_ROD),"emeralds", "a fishing rod (its kinda broken)");
                        break;
                    case 24:
                        shopcalc((Player) e.getWhoClicked(),1,Material.EMERALD,new ItemStack(Material.EGG),"emerald"," a bridge egg");
                        
                        break;
                    case 25:
                        ItemStack jumppotion = new ItemStack(Material.POTION);
                        PotionMeta jumpmeta = (PotionMeta) jumppotion.getItemMeta();
                        PotionData jumpdata = new PotionData(PotionType.JUMP);
                        jumpmeta.setBasePotionData(jumpdata);
                        jumppotion.setItemMeta(jumpmeta);

                        shopcalc((Player) e.getWhoClicked(),1,Material.EMERALD,jumppotion,"emeralds", " jump pot");


                        break;



                    case 26:
                        shopcalc((Player) e.getWhoClicked(),4,Material.EMERALD,new ItemStack(Material.SNOWBALL,8), "emeralds", " a shulker snowball");
                        break;
                    case 27:
                        shopcalc((Player) e.getWhoClicked(),4,Material.GOLD_INGOT,new ItemStack(Material.TNT),"gold"," tnt");
                        break;
                    case 28:
                        ItemStack woodenaxe = new ItemStack(Material.WOODEN_AXE);
                        ItemMeta woodenaxemeta = woodenaxe.getItemMeta();
                        woodenaxemeta.setUnbreakable(true);
                        woodenaxe.setItemMeta(woodenaxemeta);
                        woodenaxe.addEnchantment(Enchantment.DIG_SPEED,2);
                        shopcalc((Player) e.getWhoClicked(),15,Material.IRON_INGOT,woodenaxe,"iron", " a wooden axe");

                        break;
                    case 29:
                        ItemStack stoneaxe = new ItemStack(Material.STONE_AXE);
                        ItemMeta stoneaxemeta = stoneaxe.getItemMeta();
                        stoneaxemeta.setUnbreakable(true);
                        stoneaxe.setItemMeta(stoneaxemeta);
                        stoneaxe.addEnchantment(Enchantment.DIG_SPEED,2);
                        shopcalc((Player) e.getWhoClicked(),25,Material.IRON_INGOT,stoneaxe,"iron"," a stone axe");
                        break;
                    case 30:
                        ItemStack ironaxe = new ItemStack(Material.IRON_AXE);
                        ItemMeta ironaxemeta = ironaxe.getItemMeta();
                        ironaxemeta.setUnbreakable(true);
                        ironaxe.setItemMeta(ironaxemeta);
                        ironaxe.addEnchantment(Enchantment.DIG_SPEED,2);
                        shopcalc((Player) e.getWhoClicked(),9,Material.GOLD_INGOT,ironaxe,"gold", " an iron axe");

                        break;
                    case 31:
                        ItemStack diamondaxe = new ItemStack(Material.DIAMOND_AXE);
                        ItemMeta diamondaxemeta = diamondaxe.getItemMeta();
                        diamondaxemeta.setUnbreakable(true);
                        diamondaxe.setItemMeta(diamondaxemeta);
                        diamondaxe.addEnchantment(Enchantment.DIG_SPEED,3);
                        shopcalc((Player) e.getWhoClicked(),6,Material.EMERALD,diamondaxe,"emeralds", " a diamond axe");
                        break;
                    case 32:
                        shopcalc((Player) e.getWhoClicked(),3,Material.GOLD_INGOT,new ItemStack(Material.EXPERIENCE_BOTTLE,8),"gold", " xp bottles");
                        break;
                    case 33:
                        ItemStack fireball = new ItemStack(Material.FIRE_CHARGE);
                        ItemMeta fireballmeta = fireball.getItemMeta();
                        fireballmeta.setDisplayName(ChatColor.WHITE + "Fireball");
                        fireball.setItemMeta(fireballmeta);
                        shopcalc((Player) e.getWhoClicked(),40,Material.IRON_INGOT,fireball,"iron", " a fireball");
                        break;
                    case 34:
                        ItemStack speedpotion = new ItemStack(Material.POTION);
                        PotionMeta speedmeta = (PotionMeta) speedpotion.getItemMeta();
                        PotionData speeddata = new PotionData(PotionType.SPEED);
                        speedmeta.setBasePotionData(speeddata);
                        speedpotion.setItemMeta(speedmeta);

                        shopcalc((Player) e.getWhoClicked(),1,Material.EMERALD,speedpotion,"emeralds", " a speed pot");
                        break;


                    case 35:
                        shopcalc((Player) e.getWhoClicked(),4,Material.GOLD_INGOT,new ItemStack(Material.WATER_BUCKET),"gold"," a water bucket");
                        break;
                    case 36:
                        shopcalc((Player) e.getWhoClicked(),4,Material.EMERALD,new ItemStack(Material.ENDER_PEARL),"emeralds"," an ender pearl");

                        break;
                    case 37:
                        ItemStack woodenpick = new ItemStack(Material.WOODEN_PICKAXE);
                        ItemMeta woodenpickmeta = woodenpick.getItemMeta();
                        woodenpickmeta.setUnbreakable(true);
                        woodenpick.setItemMeta(woodenpickmeta);
                        woodenpick.addEnchantment(Enchantment.DIG_SPEED,2);
                        shopcalc((Player) e.getWhoClicked(),10,Material.IRON_INGOT,woodenpick,"iron", " a wooden pick");

                        break;
                    case 38:
                        ItemStack stonepick = new ItemStack(Material.STONE_PICKAXE);
                        ItemMeta stonepickmeta = stonepick.getItemMeta();
                        stonepickmeta.setUnbreakable(true);
                        stonepick.setItemMeta(stonepickmeta);
                        stonepick.addEnchantment(Enchantment.DIG_SPEED,2);
                        shopcalc((Player) e.getWhoClicked(),20,Material.IRON_INGOT,stonepick,"iron", " a stone pick");
                        break;
                    case 39:
                        ItemStack ironpick = new ItemStack(Material.IRON_PICKAXE);
                        ItemMeta ironpickmeta = ironpick.getItemMeta();
                        ironpickmeta.setUnbreakable(true);
                        ironpick.setItemMeta(ironpickmeta);
                        ironpick.addEnchantment(Enchantment.DIG_SPEED,2);
                        shopcalc((Player) e.getWhoClicked(),40,Material.IRON_INGOT,ironpick,"iron", " an iron pick");
                        break;
                    case 40:
                        ItemStack diamondpick = new ItemStack(Material.DIAMOND_PICKAXE);
                        ItemMeta diamondpickmeta = diamondpick.getItemMeta();
                        diamondpickmeta.setUnbreakable(true);
                        diamondpick.setItemMeta(diamondpickmeta);
                        diamondpick.addEnchantment(Enchantment.DIG_SPEED,3);
                        shopcalc((Player) e.getWhoClicked(),9,Material.GOLD_INGOT,diamondpick,"gold", " a diamond pick");
                        break;
                    case 41:
                        ItemStack tntbow = new ItemStack(Material.BOW);
                        ItemMeta tntbowmeta = (ItemMeta) tntbow.getItemMeta();
                        tntbowmeta.setUnbreakable(true);
                        tntbowmeta.setDisplayName("Tnt Bow");
                        tntbowmeta.setLore(Arrays.asList("Explodes on impact"));
                        tntbow.setItemMeta(tntbowmeta);
                        shopcalc((Player) e.getWhoClicked(),8,Material.EMERALD,tntbow,"emeralds"," a tnt bow. hehehe haw. it goes boom BOOM!!!");
                        break;
                    case 42:
                        ItemStack bow = new ItemStack(Material.BOW);
                        ItemMeta bowmeta = bow.getItemMeta();
                        bowmeta.setUnbreakable(true);
                        bow.setItemMeta(bowmeta);


                        shopcalc((Player) e.getWhoClicked(),9,Material.GOLD_INGOT,bow,"gold", " a bow");
                        break;
                    case 43:
                        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
                        ItemMeta crossbowmeta = crossbow.getItemMeta();
                        crossbowmeta.setUnbreakable(true);
                        crossbow.setItemMeta(crossbowmeta);

                        shopcalc((Player) e.getWhoClicked(),15,Material.GOLD_INGOT,crossbow,"gold", " a crossbow");
                        break;
                    case 44:
                        shopcalc((Player) e.getWhoClicked(),4,Material.EMERALD,new ItemStack(Material.OBSIDIAN,4),"emeralds", " obsidian. what's wrong with you");
                        break;


                }

            }
        }

    }
    public void shopcalc(Player p, int price, Material currency,ItemStack item,String name,String name2){
        int amount = 0;
        for (ItemStack slot : p.getInventory().getContents()) {
            if (slot != null) {
                if (slot.getType().equals(currency)) {
                    amount = amount + slot.getAmount();

                }

            }



        }
        if (amount >= price) {
            if (p.getInventory().getItemInOffHand().getType().equals(currency) && p.getInventory().getItemInOffHand().getAmount()>=price){
                p.getInventory().getItemInOffHand().setAmount(p.getInventory().getItemInOffHand().getAmount() - price);
            } else {
                p.getInventory().removeItem(new ItemStack(currency, price));
            }

            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.25F);
            p.sendMessage(ChatColor.GREEN+"You bought" +name2);
            p.getInventory().addItem(item);
        } else {
            p.playSound(p.getLocation(),Sound.ENTITY_ALLAY_HURT,1,1);
            p.sendMessage(ChatColor.RED + "You dont have enough " + name + ". Need " + (price - amount) + " more!");
        }


    }
    public void armorcalc(Player p, int price, Material currency, Material armor, String name){
        int amount = 0;
        for (ItemStack slot : p.getInventory().getContents()) {
            if (slot != null) {
                if (slot.getType().equals(currency)) {
                    amount = amount + slot.getAmount();

                }

            }



        }
        if (amount >= price) {
            if (p.getInventory().getItemInOffHand().getType().equals(currency) && p.getInventory().getItemInOffHand().getAmount()>=price){
                p.getInventory().getItemInOffHand().setAmount(p.getInventory().getItemInOffHand().getAmount() - price);
            } else {
                p.getInventory().removeItem(new ItemStack(currency, price));
            }

            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0.25F);

            if (armor.equals(Material.CHAINMAIL_BOOTS)){
                ItemStack chainmailboots = new ItemStack(Material.CHAINMAIL_BOOTS);
                ItemStack chainmailleggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                ItemStack chainmailchestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                if (p.getInventory().getBoots().getEnchantments() != null) {
                    chainmailboots.addEnchantments(p.getInventory().getBoots().getEnchantments());
                }
                if (p.getInventory().getLeggings().getEnchantments() != null) {
                    chainmailleggings.addEnchantments(p.getInventory().getLeggings().getEnchantments());
                }
                if (p.getInventory().getChestplate().getEnchantments() != null) {
                    chainmailchestplate.addEnchantments(p.getInventory().getChestplate().getEnchantments());
                }

                ArmorMeta chainmailbootsmeta = (ArmorMeta) chainmailboots.getItemMeta();
                ArmorMeta chainmailleggingsmeta = (ArmorMeta) chainmailleggings.getItemMeta();
                ArmorMeta chainmailchestplatemeta = (ArmorMeta) chainmailchestplate.getItemMeta();
                chainmailbootsmeta.setUnbreakable(true);
                chainmailleggingsmeta.setUnbreakable(true);
                chainmailchestplatemeta.setUnbreakable(true);
                chainmailboots.setItemMeta(chainmailbootsmeta);
                chainmailleggings.setItemMeta(chainmailleggingsmeta);
                chainmailchestplate.setItemMeta(chainmailchestplatemeta);

                p.getInventory().setBoots(chainmailboots);
                p.getInventory().setLeggings(chainmailleggings);
                p.getInventory().setChestplate(chainmailchestplate);

                p.sendMessage(ChatColor.GREEN + "You bought chainmail armor");

            } else if (armor.equals(Material.IRON_BOOTS)) {
                ItemStack ironboots = new ItemStack(Material.IRON_BOOTS);
                ItemStack ironleggings = new ItemStack(Material.IRON_LEGGINGS);
                ItemStack ironchestplate = new ItemStack(Material.IRON_CHESTPLATE);
                if (p.getInventory().getBoots().getEnchantments() != null) {
                    ironboots.addEnchantments(p.getInventory().getBoots().getEnchantments());
                }
                if (p.getInventory().getLeggings().getEnchantments() != null) {
                    ironleggings.addEnchantments(p.getInventory().getLeggings().getEnchantments());
                }
                if (p.getInventory().getChestplate().getEnchantments() != null) {
                    ironchestplate.addEnchantments(p.getInventory().getChestplate().getEnchantments());
                }

                ArmorMeta ironbootsmeta = (ArmorMeta) ironboots.getItemMeta();
                ArmorMeta ironleggingsmeta = (ArmorMeta) ironleggings.getItemMeta();
                ArmorMeta ironchestplatemeta = (ArmorMeta) ironchestplate.getItemMeta();
                ironbootsmeta.setUnbreakable(true);
                ironleggingsmeta.setUnbreakable(true);
                ironchestplatemeta.setUnbreakable(true);
                ironboots.setItemMeta(ironbootsmeta);
                ironleggings.setItemMeta(ironleggingsmeta);
                ironchestplate.setItemMeta(ironchestplatemeta);

                p.getInventory().setBoots(ironboots);
                p.getInventory().setLeggings(ironleggings);
                p.getInventory().setChestplate(ironchestplate);

                p.sendMessage(ChatColor.GREEN + "You bought iron armor");
            } else if (armor.equals(Material.DIAMOND_BOOTS)) {
                ItemStack diamondboots = new ItemStack(Material.DIAMOND_BOOTS);
                ItemStack ironleggings2 = new ItemStack(Material.IRON_LEGGINGS);
                ItemStack diamondchestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                if (p.getInventory().getBoots().getEnchantments() != null){
                    diamondboots.addEnchantments(p.getInventory().getBoots().getEnchantments());
                }
                if (p.getInventory().getLeggings().getEnchantments() != null){
                    ironleggings2.addEnchantments(p.getInventory().getLeggings().getEnchantments());
                }
                if (p.getInventory().getChestplate().getEnchantments() != null){
                    diamondchestplate.addEnchantments(p.getInventory().getChestplate().getEnchantments());
                }

                ArmorMeta diamondbootsmeta = (ArmorMeta) diamondboots.getItemMeta();
                ArmorMeta ironleggings2meta = (ArmorMeta) ironleggings2.getItemMeta();
                ArmorMeta diamondchestplatemeta = (ArmorMeta) diamondchestplate.getItemMeta();
                diamondbootsmeta.setUnbreakable(true);
                ironleggings2meta.setUnbreakable(true);
                diamondchestplatemeta.setUnbreakable(true);
                diamondboots.setItemMeta(diamondbootsmeta);
                ironleggings2.setItemMeta(ironleggings2meta);
                diamondchestplate.setItemMeta(diamondchestplatemeta);

                p.getInventory().setBoots(diamondboots);
                p.getInventory().setLeggings(ironleggings2);
                p.getInventory().setChestplate(diamondchestplate);

                p.sendMessage(ChatColor.GREEN+"You bought diamond armor");
            }




        } else {
            p.playSound(p.getLocation(),Sound.ENTITY_ALLAY_HURT,1,1);
            p.sendMessage(ChatColor.RED + "You dont have enough " + name + ". Need " + (price - amount) + " more!");
        }



    }


}
