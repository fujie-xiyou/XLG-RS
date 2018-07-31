package org.xiyoulinux.recruitment.model;

public class Evaluation {
    private Integer evaluationID;

    private Integer userID;

    private Integer joinID;

    private Integer processID;

    private Byte times;

    private String key_words;

    private String foundation;

    private String strength;

    private String overall_evaluation;

    private Integer rankID;

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getJoinID() {
        return joinID;
    }

    public void setJoinID(Integer joinID) {
        this.joinID = joinID;
    }

    public Integer getProcessID() {
        return processID;
    }

    public void setProcessID(Integer processID) {
        this.processID = processID;
    }

    public Byte getTimes() {
        return times;
    }

    public void setTimes(Byte times) {
        this.times = times;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getOverall_evaluation() {
        return overall_evaluation;
    }

    public void setOverall_evaluation(String overall_evaluation) {
        this.overall_evaluation = overall_evaluation;
    }

    public Integer getRankID() {
        return rankID;
    }

    public void setRankID(Integer rankID) {
        this.rankID = rankID;
    }
}