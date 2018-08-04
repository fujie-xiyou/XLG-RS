package org.xiyoulinux.recruitment.model;

public class Evaluation {
    private Integer id;

    private Integer user_id;

    private Integer join_id;

    private Integer process_id;

    private Byte times;

    private String key_words;

    private String foundation;

    private String strength;

    private String overall_evaluation;

    private Integer rank_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getJoin_id() {
        return join_id;
    }

    public void setJoin_id(Integer join_id) {
        this.join_id = join_id;
    }

    public Integer getProcess_id() {
        return process_id;
    }

    public void setProcess_id(Integer process_id) {
        this.process_id = process_id;
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

    public Integer getRank_id() {
        return rank_id;
    }

    public void setRank_id(Integer rank_id) {
        this.rank_id = rank_id;
    }
}