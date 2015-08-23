package edu.myinst.model;

import blackboard.data.AbstractIdentifiable;
import blackboard.data.user.User;
import blackboard.persist.DataType;
import blackboard.persist.Id;
import blackboard.persist.impl.mapping.annotation.Column;
import blackboard.persist.impl.mapping.annotation.RefersTo;
import blackboard.persist.impl.mapping.annotation.Table;

import java.util.Date;

@Table("myu_stub_example")
public class Example extends AbstractIdentifiable {

    public static final DataType DATA_TYPE = new DataType(Example.class);

    @Column({"user_pk1"})
    @RefersTo(User.class)
    private Id userId;

    @Column({"a_int"})
    private int aInt;

    @Column({"a_string"})
    private int aString;

    @Column({"a_date"})
    private Date aDate;

    @Column({"a_bool"})
    private boolean aBool;

    public Id getUserId() {
        return userId;
    }

    public void setUserId(Id userId) {
        this.userId = userId;
    }

    public int getAInt() {
        return aInt;
    }

    public void setAInt(int aInt) {
        this.aInt = aInt;
    }

    public int getAString() {
        return aString;
    }

    public void setAString(int aString) {
        this.aString = aString;
    }

    public Date getADate() {
        return aDate;
    }

    public void setADate(Date aDate) {
        this.aDate = aDate;
    }

    public boolean isABool() {
        return aBool;
    }

    public void setABool(boolean aBool) {
        this.aBool = aBool;
    }
}