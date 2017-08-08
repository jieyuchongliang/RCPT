package com.rcpt.bean;

/**
 * Created by lvzp on 2017/4/7.
 * 类描述：
 */

public class AnswerBean {


    /**
     * answer : A
     * question_type_id : 1
     * point : 0
     */

    private String answer;
    private int question_type_id;
    private float point;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestion_type_id() {
        return question_type_id;
    }

    public void setQuestion_type_id(int question_type_id) {
        this.question_type_id = question_type_id;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
}
