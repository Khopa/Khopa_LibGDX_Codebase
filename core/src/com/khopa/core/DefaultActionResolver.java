package com.khopa.core;


/**
 *
 */
public class DefaultActionResolver implements ActionResolver {

    @Override
    public boolean playServiceAvailable() {
        return false;
    }

    @Override
    public void redirectToURL(String url) {

    }

    @Override
    public boolean getSignedInGPGS() {
        return false;
    }

    @Override
    public void loginGPGS() {

    }

    @Override
    public void submitScoreGPGS(int score) {

    }

    @Override
    public void unlockAchievementGPGS(String achievementId) {

    }

    @Override
    public void getLeaderboardGPGS() {

    }

    @Override
    public void incrementAchievement(String achievementId, int step) {
        
    }

    @Override
    public void toast(String text) {

    }

    @Override
    public void getAchievementsGPGS() {

    }

    @Override
    public void getUserScore() {

    }
}
