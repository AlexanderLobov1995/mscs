package service;

import matrix.AccessMatrix;

import java.util.LinkedList;
import java.util.List;

public class Service {

    private int currentIndex;

    private AccessMatrix accessMatrix;

    public Service() {
        setCurrentIndex(0);
        accessMatrix = new AccessMatrix();
    }

    public String filter(String text) {
        StringBuilder regExp = new StringBuilder();

        List<List<String>> mtx = getAccessMatrix().getMtx();

        regExp.append("(");
        for (int i = 1; i < mtx.get(0).size(); i++) {
            if (mtx.get(currentIndex).get(i).equalsIgnoreCase("-")) {
                regExp.append(mtx.get(0).get(i));
                if (i != mtx.get(0).size() - 1) {
                    regExp.append("|");
                }

            }
        }
        regExp.append(")");

        return text.replaceAll(regExp.toString(), "");
    }

    public void selectUser(int index) {
        setCurrentIndex(index);
    }


    /*Getter&Setter*/

    public long getCurrentIndex() {
        return currentIndex;
    }

    public AccessMatrix getAccessMatrix() {
        return accessMatrix;
    }

    public void grant(int currentFromUser, int currentToUser, List<Integer> checkedList) {
        List<List<String>> mtx = getAccessMatrix().getMtx();
        for (Integer index : checkedList) {
            String temp = mtx.get(currentFromUser + 1).get(index + 1);
            mtx.get(currentToUser + 1).set(index + 1, temp);
        }
        getAccessMatrix().writeToFile();
    }

    public void remove(int currentUsernameRemove, List<Integer> checkedList) {
        List<String> mtx = getAccessMatrix().getMtx().get(currentUsernameRemove + 1);
        for (Integer index : checkedList) {
            mtx.set(index + 1, "-");
        }
        getAccessMatrix().writeToFile();
    }

    public void create(String currentUsernameCreate, List<String> rights) {

        List<String> rightList = new LinkedList<>(rights);
        List<List<String>> mtx = getAccessMatrix().getMtx();
        List<String> newUser = new LinkedList<>();

        newUser.add(currentUsernameCreate);
        for (int column=1; column< mtx.get(0).size();column++) {
            newUser.add("-");
        }

        mtx.add(newUser);

        for (String right : rights) {

            for (int i = 1; i < mtx.get(0).size(); i++) {
                if (right.equalsIgnoreCase(mtx.get(0).get(i))) {
                    newUser.set(i, "+");
                    rightList.remove(right);
                }
            }
        }

        for (int i = 0; i < rightList.size(); i++) {
            mtx.get(0).add(rightList.get(i));
            for (int indexUser = 1; indexUser < mtx.size(); indexUser++) {
                if (mtx.get(indexUser).get(0).equalsIgnoreCase(newUser.get(0))) {
                    mtx.get(indexUser).add("+");
                } else {
                    mtx.get(indexUser).add("-");
                }
            }
        }

        getAccessMatrix().writeToFile();
    }

    private void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
