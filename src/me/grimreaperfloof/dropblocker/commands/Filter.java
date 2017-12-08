package me.grimreaperfloof.dropblocker.commands;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.grimreaperfloof.dropblocker.Main;

public class Filter implements CommandExecutor{

	private Main plugin;
	public Filter(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		plugin.reloadConfig();
		int itemCount = 0;
		
		if (sender instanceof org.bukkit.entity.Player && args.length == 0) {
			org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
			Iterator<org.bukkit.entity.Item> itemsInWorld = player.getWorld().getEntitiesByClass(org.bukkit.entity.Item.class).iterator();
			while (itemsInWorld.hasNext()) {
				org.bukkit.entity.Item itemInWorld = itemsInWorld.next();
				
				List<String> itemsInConfig = plugin.getConfig().getStringList("items");
	        	
	        	if (plugin.getConfig().getBoolean("items-is-whitelist")) {
	        		for (int i = 0; i < itemsInConfig.size(); i++) {
	        			String[] itemInConfig = itemsInConfig.get(i).split(":");
	        			
	        			if (itemInConfig.length >= 2) {
	        				if (itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && itemInWorld.getItemStack().getDurability() == Integer.parseInt(itemInConfig[1])) {
		        				break;
		        			}
	        				
	        				if (i == itemsInConfig.size() - 1) {
	           	    			itemInWorld.remove();
	           	    			itemCount++;
	             			}
	        			} else {
	        				if (itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
		        				break;
		        			}
	        				
	        				if (i == itemsInConfig.size() - 1) {
	           	    			itemInWorld.remove();
	           	    			itemCount++;
	             			}
	        			}
	        		}
	    		} else {
	    			for (int i = 0; i < itemsInConfig.size(); i++) {
	    				String[] itemInConfig = itemsInConfig.get(i).split(":");
	        			
	        			if (itemInConfig.length >= 2) {
	        				if (!itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && itemInWorld.getItemStack().getDurability() != Integer.parseInt(itemInConfig[1])) {
		        				break;
		        			}
	        				
	        				if (i == itemsInConfig.size() - 1) {
	           	    			itemInWorld.remove();
	           	    			itemCount++;
	             			}
	        			} else {
	        				if (!itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
		        				break;
		        			}
	        				
	        				if (i == itemsInConfig.size() - 1) {
	           	    			itemInWorld.remove();
	           	    			itemCount++;
	             			}
	        			}
	        		}
	    		}
			}
			if (itemCount == 1) {
				sender.sendMessage(itemCount + " group of items have been removed from world: \"" + player.getWorld().getName() + "\"");
			} else {
				sender.sendMessage(itemCount + " groups of items have been removed from world: \"" + player.getWorld().getName() + "\"");
			}
			return true;
		}
		
		if (args.length == 1) {
			if (Bukkit.getWorld(args[0]) != null) {
				Iterator<org.bukkit.entity.Item> itemsInWorld = Bukkit.getWorld(args[0]).getEntitiesByClass(org.bukkit.entity.Item.class).iterator();
				while (itemsInWorld.hasNext()) {
					org.bukkit.entity.Item itemInWorld = itemsInWorld.next();
					
					List<String> itemsInConfig = plugin.getConfig().getStringList("items");
		        	
		        	if (plugin.getConfig().getBoolean("items-is-whitelist")) {
		        		for (int i = 0; i < itemsInConfig.size(); i++) {
		        			String[] itemInConfig = itemsInConfig.get(i).split(":");
		        			
		        			if (itemInConfig.length >= 2) {
		        				if (itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && itemInWorld.getItemStack().getDurability() == Integer.parseInt(itemInConfig[1])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			} else {
		        				if (itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			}
		        		}
		    		} else {
		    			for (int i = 0; i < itemsInConfig.size(); i++) {
		    				String[] itemInConfig = itemsInConfig.get(i).split(":");
		        			
		        			if (itemInConfig.length >= 2) {
		        				if (!itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && itemInWorld.getItemStack().getDurability() != Integer.parseInt(itemInConfig[1])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			} else {
		        				if (!itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			}
		        		}
		    		}
				}
				if (itemCount == 1) {
					sender.sendMessage(itemCount + " group of items have been removed from world: \"" + Bukkit.getWorld(args[0]).getName() + "\"");
				} else {
					sender.sendMessage(itemCount + " groups of items have been removed from world: \"" + Bukkit.getWorld(args[0]).getName() + "\"");
				}
				return true;
			} else if (Bukkit.getPlayer(args[0]) != null) {
				org.bukkit.entity.Player player = Bukkit.getPlayer(args[0]);
				Iterator<org.bukkit.entity.Item> itemsInWorld = player.getWorld().getEntitiesByClass(org.bukkit.entity.Item.class).iterator();
				while (itemsInWorld.hasNext()) {
					org.bukkit.entity.Item itemInWorld = itemsInWorld.next();
					
					List<String> itemsInConfig = plugin.getConfig().getStringList("items");
		        	
		        	if (plugin.getConfig().getBoolean("items-is-whitelist")) {
		        		for (int i = 0; i < itemsInConfig.size(); i++) {
		        			String[] itemInConfig = itemsInConfig.get(i).split(":");
		        			
		        			if (itemInConfig.length >= 2) {
		        				if (itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && itemInWorld.getItemStack().getDurability() == Integer.parseInt(itemInConfig[1])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			} else {
		        				if (itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			}
		        		}
		    		} else {
		    			for (int i = 0; i < itemsInConfig.size(); i++) {
		    				String[] itemInConfig = itemsInConfig.get(i).split(":");
		        			
		        			if (itemInConfig.length >= 2) {
		        				if (!itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && itemInWorld.getItemStack().getDurability() != Integer.parseInt(itemInConfig[1])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			} else {
		        				if (!itemInWorld.getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
			        				break;
			        			}
		        				
		        				if (i == itemsInConfig.size() - 1) {
		           	    			itemInWorld.remove();
		           	    			itemCount++;
		             			}
		        			}
		        		}
		    		}
				}
				if (itemCount == 1) {
					sender.sendMessage(itemCount + " group of items have been removed from world: \"" + player.getWorld().getName() + "\"");
				} else {
					sender.sendMessage(itemCount + " groups of items have been removed from world: \"" + player.getWorld().getName() + "\"");
				}
				return true;
			}
			
			sender.sendMessage(org.bukkit.ChatColor.RED + "There is no player or world called \"" + args[0] + "\"");
			return true;
		} else if (args.length >= 2) {
			sender.sendMessage("Apply DropBlocker to all dropped items in a world or world a player is in.");
			return false;
		}
		
		if (!(sender instanceof org.bukkit.entity.Player) && args.length == 0) {
			sender.sendMessage("Apply DropBlocker to all dropped items in a world or world a player is in.");
			return false;
		}
		return false;
	}
}
