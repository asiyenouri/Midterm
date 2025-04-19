package spotify;
import spotify.InvalidOperationException;
import spotify.PremiumBehavior;
import spotify.UserBehavior;

public class RegularBehavior implements UserBehavior {

    private int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner) {
        throw new InvalidOperationException("You cannot creat play list.");
    }

    @Override
    public void playMusic(spotify.Music music) {
        if (playingLimit <= 0) {
            throw new InvalidOperationException("You cannot play anything.");
        }
        music.play();
        playingLimit--;
    }

    @Override
    public void buyPremium(User owner, int month) {
        owner.setBehavior(new PremiumBehavior(month));
    }
}
