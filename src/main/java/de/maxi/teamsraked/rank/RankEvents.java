package de.maxi.teamsraked.rank;

import de.maxi.teamsraked.sql.RankDB;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RankEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        RankDB.addPlayerToDB(p.getUniqueId().toString(), p.getName());
        Scoreboard sb = p.getScoreboard();
        ArrayList<Rank> possibleRanks = new ArrayList<>();
        Arrays.stream(RankType.values()).forEach((rankType -> {
            Rank rank = new Rank(rankType);
            if(sb.getTeam(rank.getName()) != null){
                sb.getTeam(rank.getName()).unregister();
            }
            Team t = sb.registerNewTeam(rank.getName());
            t.setPrefix(rank.getPrefix() + " §l§f| §r");
            t.setColor(rank.getColor());
        }));
        RankManager.applyRankToPlayer(p);
    }

}
