package com.khopa.core;

/**
 * @author Clément Perreau (Clement)
 * @date 02/09/2014.
 * @description Action resolver for platform specific stuff
 */
public interface ActionResolver{

    public boolean playServiceAvailable();
    public void redirectToURL(String url);
    public boolean getSignedInGPGS();
    public void loginGPGS();
    public void submitScoreGPGS(int score);
    public void unlockAchievementGPGS(String achievementId);
    public void incrementAchievement(String achievementId, int step);
    public void getLeaderboardGPGS();
    public void getAchievementsGPGS();
    public void getUserScore();
    public void toast(String text);

}
