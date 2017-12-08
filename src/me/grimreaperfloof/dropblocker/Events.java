package me.grimreaperfloof.dropblocker;

import java.util.Iterator;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
	
	private Main plugin;
	public Events(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onItemSpawn(org.bukkit.event.entity.ItemSpawnEvent event) {
    	plugin.reloadConfig();
    	if (plugin.getConfig().getBoolean("onItemSpawn")) {
        	List<String> itemsInConfig = plugin.getConfig().getStringList("items");
        	
        	if (plugin.getConfig().getBoolean("items-is-whitelist")) {
        		for (int i = 0; i < itemsInConfig.size(); i++) {
        			String[] itemInConfig = itemsInConfig.get(i).split(":");
        			
        			if (itemInConfig.length >= 2) {
        				if (event.getEntity().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && event.getEntity().getItemStack().getDurability() == Integer.parseInt(itemInConfig[1])) {
        	    			break;
        	    		}
        			} else {
        				if (event.getEntity().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
        	    			break;
        	    		}
        			}
    	    		
    	    		if (i == itemsInConfig.size() - 1) {
    	    			event.setCancelled(true);
    	    		}
        		}
    		} else {
    			for (int i = 0; i < itemsInConfig.size(); i++) {
    				String[] itemInConfig = itemsInConfig.get(i).split(":");
        			
        			if (itemInConfig.length >= 2) {
        				if (!event.getEntity().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && event.getEntity().getItemStack().getDurability() != Integer.parseInt(itemInConfig[1])) {
        	    			break;
        	    		}
        			} else {
        				if (!event.getEntity().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
        	    			break;
        	    		}
        			}
    	    		
    	    		if (i == itemsInConfig.size() - 1) {
    	    			event.setCancelled(true);
    	    		}
    			}
    		}
    	}
	}
    
    @EventHandler
    public void onPlayerDrop(org.bukkit.event.player.PlayerDropItemEvent event) {
    	plugin.reloadConfig();
    	if (plugin.getConfig().getBoolean("onPlayerDrop")) {
        	List<String> itemsInConfig = plugin.getConfig().getStringList("items");
        	
        	if (plugin.getConfig().getBoolean("items-is-whitelist")) {
        		for (int i = 0; i < itemsInConfig.size(); i++) {
        			String[] itemInConfig = itemsInConfig.get(i).split(":");
        			
        			if (itemInConfig.length >= 2) {
        				if (event.getItemDrop().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && event.getItemDrop().getItemStack().getDurability() == Integer.parseInt(itemInConfig[1])) {
        	    			break;
        	    		}
        			} else {
        				if (event.getItemDrop().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
        	    			break;
        	    		}
        			}
    	    		
    	    		if (i == itemsInConfig.size() - 1) {
    	    			event.setCancelled(true);
    	    		}
        		}
    		} else {
    			for (int i = 0; i < itemsInConfig.size(); i++) {
        			String[] itemInConfig = itemsInConfig.get(i).split(":");
        			
        			if (itemInConfig.length >= 2) {
        				if (!event.getItemDrop().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0]) && event.getItemDrop().getItemStack().getDurability() != Integer.parseInt(itemInConfig[1])) {
        	    			break;
        	    		}
        			} else {
        				if (!event.getItemDrop().getItemStack().getType().toString().equalsIgnoreCase(itemInConfig[0])) {
        	    			break;
        	    		}
        			}
    	    		
    	    		if (i == itemsInConfig.size() - 1) {
    	    			event.setCancelled(true);
    	    		}
    			}
    		}
    	}
	}
    
    @EventHandler
    public void onEntityDeath(org.bukkit.event.entity.EntityDeathEvent event) {
    	plugin.reloadConfig();
    	if (plugin.getConfig().getBoolean("onEntityDeath")) {
        	List<String> itemsInConfig = plugin.getConfig().getStringList("items");
        	
        	Iterator<ItemStack> drops = event.getDrops().iterator();
        	if (plugin.getConfig().getBoolean("items-is-whitelist")) {
    			while (drops.hasNext()) {
    				ItemStack droppedItem = drops.next();
    				for (int i = 0; i < itemsInConfig.size(); i++) {
    					String[] itemInConfig = itemsInConfig.get(i).split(":");
    					
    					if (itemInConfig.length >= 2) {
             			    if (droppedItem.getType().toString().equalsIgnoreCase(itemInConfig[0]) && droppedItem.getDurability() == Integer.parseInt(itemInConfig[1])) {
             				    break;
             			    }
             			    
             			   if (i == itemsInConfig.size() - 1) {
           	    			drops.remove();
             			   }
    					} else {
    						if (droppedItem.getType().toString().equalsIgnoreCase(itemInConfig[0])) {
             				    break;
             			    }
             			    
             			   if (i == itemsInConfig.size() - 1) {
           	    			drops.remove();
             			   }
    					}
    				}
    			}
    		} else {
    			while (drops.hasNext()) {
    				ItemStack droppedItem = drops.next();
    				for (int i = 0; i < itemsInConfig.size(); i++) {
    					String[] itemInConfig = itemsInConfig.get(i).split(":");
    					
    					if (itemInConfig.length >= 2) {
    						if (!droppedItem.getType().toString().equalsIgnoreCase(itemInConfig[0]) && droppedItem.getDurability() != Integer.parseInt(itemInConfig[1])) {
             				    break;
             			    }
             			    
             			   	if (i == itemsInConfig.size() - 1) {
             				   drops.remove();
             			   	}
    					} else {
    						if (!droppedItem.getType().toString().equalsIgnoreCase(itemInConfig[0]) && droppedItem.getDurability() != Integer.parseInt(itemInConfig[1])) {
             				    break;
             			    }
             			    
    						if (i == itemsInConfig.size() - 1) {
    							drops.remove();
    						}
    					}
    				}
    			}
    		}
    	}
    }
}
