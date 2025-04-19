
import spotify.InvalidOperationException;
import spotify.Music;
import spotify.Playlist;
import spotify.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            User user1 = new User("Asi_Nri", "123123123");
            User user2 = new User("Amir_Khe", "456456456");
            User artist1 = new User("Lady Gaga", "789789789");

            try {
                User invalidUser = new User("test", "short");
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }

            user1.follow(artist1);
            user2.follow(artist1);
            System.out.println(user1.getUsername() + " is following: " + user1.getFollowingList().size() + " users");
            System.out.println(artist1.getUsername() + " has " + artist1.getFollowerList().size() + " followers");

            Music song1 = new Music("Hello");
            song1.setSinger(artist1);
            Music song2 = new Music("GoodBye");
            song2.setSinger(artist1);
            Music song3 = new Music("Die with a smile");

            Music.getAllMusic().add(song1);
            Music.getAllMusic().add(song2);
            Music.getAllMusic().add(song3);


            System.out.println("\nTest \"regular user behavior\":");
            user1.playMusic(song1);
            user1.playMusic(song2);
            System.out.println("Stream count for 'Hello': " + song1.getNumberOfStream());


            try {
                user1.creatPlayList("My Playlist", user1);
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }


            System.out.println("\nTest \"play limit\":");
            for (int i = 0; i < 3; i++) {
                user1.playMusic(song1);
            }
            try {
                user1.playMusic(song1);
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\nUpgrade to premium:");
            user1.buyPremium(user1, 3);

            System.out.println("\nTest premium: ");
            user1.creatPlayList("Favorites", user1);
            for (int i = 0; i < 10; i++) {
                user1.playMusic(song2);
            }
            System.out.println("Stream count for 'GoodBye': " + song2.getNumberOfStream());

            Playlist playlist = user1.getPlaylists().get(0);
            playlist.setTitle("My Favorites");
            playlist.setOwner(user1);

            playlist.addMusic(song1, "123123123");
            playlist.addMusic(song2, "123123123");

            try {
                playlist.addMusic(song1, "123123123");
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }

            try {
                playlist.addMusic(song3, "wrongpass");
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\nPlay playlist:");
            playlist.Playlist();
            System.out.println("\nTest search:");
            ArrayList<Music> beatlesSongs = song1.search("Hello", artist1);
            System.out.println("Found " + beatlesSongs.size() + " songs matching search");

            System.out.println("\nTest music without singer:");
            song3.play(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}