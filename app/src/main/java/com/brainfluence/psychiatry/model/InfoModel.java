package com.brainfluence.psychiatry.model;

public class InfoModel {
    public String hasMentalIllness,isAcademicallyHappy,cgpa,isDrugAddicted,isInARelationship,isHappyInRelationship,hasRecentBreakUp,hasConflictWithFriends,
    hasFinancialProblem,hasSadness,hasBadHabit,gotBullied,gotRagged,gotHarassed;

    public InfoModel() {
        this.hasMentalIllness = null;
        this.isAcademicallyHappy = null;
        this.cgpa = null;
        this.isDrugAddicted = null;
        this.isInARelationship = null;
        this.isHappyInRelationship = null;
        this.hasRecentBreakUp = null;
        this.hasConflictWithFriends = null;
        this.hasFinancialProblem = null;
        this.hasSadness = null;
        this.hasBadHabit = null;
        this.gotBullied = null;
        this.gotRagged = null;
        this.gotHarassed = null;
    }

    public InfoModel(String hasMentalIllness, String isAcademicallyHappy, String cgpa, String isDrugAddicted, String isInARelationship, String isHappyInRelationship, String hasRecentBreakUp, String hasConflictWithFriends, String hasFinancialProblem, String hasSadness, String hasBadHabit, String gotBullied, String gotRagged, String gotHarassed) {
        this.hasMentalIllness = hasMentalIllness;
        this.isAcademicallyHappy = isAcademicallyHappy;
        this.cgpa = cgpa;
        this.isDrugAddicted = isDrugAddicted;
        this.isInARelationship = isInARelationship;
        this.isHappyInRelationship = isHappyInRelationship;
        this.hasRecentBreakUp = hasRecentBreakUp;
        this.hasConflictWithFriends = hasConflictWithFriends;
        this.hasFinancialProblem = hasFinancialProblem;
        this.hasSadness = hasSadness;
        this.hasBadHabit = hasBadHabit;
        this.gotBullied = gotBullied;
        this.gotRagged = gotRagged;
        this.gotHarassed = gotHarassed;
    }

    public String getHasMentalIllness() {
        return hasMentalIllness;
    }

    public void setHasMentalIllness(String hasMentalIllness) {
        this.hasMentalIllness = hasMentalIllness;
    }

    public String getIsAcademicallyHappy() {
        return isAcademicallyHappy;
    }

    public void setIsAcademicallyHappy(String isAcademicallyHappy) {
        this.isAcademicallyHappy = isAcademicallyHappy;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getIsDrugAddicted() {
        return isDrugAddicted;
    }

    public void setIsDrugAddicted(String isDrugAddicted) {
        this.isDrugAddicted = isDrugAddicted;
    }

    public String getIsInARelationship() {
        return isInARelationship;
    }

    public void setIsInARelationship(String isInARelationship) {
        this.isInARelationship = isInARelationship;
    }

    public String getIsHappyInRelationship() {
        return isHappyInRelationship;
    }

    public void setIsHappyInRelationship(String isHappyInRelationship) {
        this.isHappyInRelationship = isHappyInRelationship;
    }

    public String getHasRecentBreakUp() {
        return hasRecentBreakUp;
    }

    public void setHasRecentBreakUp(String hasRecentBreakUp) {
        this.hasRecentBreakUp = hasRecentBreakUp;
    }

    public String getHasConflictWithFriends() {
        return hasConflictWithFriends;
    }

    public void setHasConflictWithFriends(String hasConflictWithFriends) {
        this.hasConflictWithFriends = hasConflictWithFriends;
    }

    public String getHasFinancialProblem() {
        return hasFinancialProblem;
    }

    public void setHasFinancialProblem(String hasFinancialProblem) {
        this.hasFinancialProblem = hasFinancialProblem;
    }

    public String getHasSadness() {
        return hasSadness;
    }

    public void setHasSadness(String hasSadness) {
        this.hasSadness = hasSadness;
    }

    public String getHasBadHabit() {
        return hasBadHabit;
    }

    public void setHasBadHabit(String hasBadHabit) {
        this.hasBadHabit = hasBadHabit;
    }

    public String getGotBullied() {
        return gotBullied;
    }

    public void setGotBullied(String gotBullied) {
        this.gotBullied = gotBullied;
    }

    public String getGotRagged() {
        return gotRagged;
    }

    public void setGotRagged(String gotRagged) {
        this.gotRagged = gotRagged;
    }

    public String getGotHarassed() {
        return gotHarassed;
    }

    public void setGotHarassed(String gotHarassed) {
        this.gotHarassed = gotHarassed;
    }
}
