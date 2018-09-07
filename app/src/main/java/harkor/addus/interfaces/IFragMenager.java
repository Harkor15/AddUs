package harkor.addus.interfaces;

public interface IFragMenager {
    void goMenu(Boolean play);
    void goResult(int points);
    void goGame(Boolean play);
    boolean isSignedIn();
    void logButtonClick();
    void showRanking();
    void showResult();
    void showAchievements();
}
