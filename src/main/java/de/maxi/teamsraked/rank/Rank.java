package de.maxi.teamsraked.rank;

import org.bukkit.ChatColor;

public class Rank {
    private final RankType rankType;
    public Rank(RankType rankType){
        this.rankType = rankType;
    }

    public RankType getRankType() {
        return rankType;
    }

    public String getPrefix(){
        switch (rankType){
            case OWNER:
                return "[§6Owner]";
            case ADMIN:
                return "[§cAdmin]";
            case VIP:
                return "[§bVIP]";
            case PLAYER:
                return "[Player]";
            default:
                return "§4[ERROR]";
        }
    }

    public ChatColor getColor(){
        switch (rankType){
            case OWNER:
                return ChatColor.GOLD;
            case ADMIN:
                return ChatColor.RED;
            case VIP:
                return ChatColor.AQUA;
            case PLAYER:
                return ChatColor.GRAY;
            default:
                return ChatColor.DARK_RED;
        }
    }


    public String getName() {
        switch (rankType){
            case OWNER:
                return "1Owner";
            case ADMIN:
                return "2Admin";
            case VIP:
                return "3VIP";
            case PLAYER:
                return "4Player";
            default:
                return "9Error";
        }
    }
}
