package com.example.jumping_i.sudoku_original.data;

public class ButtonData {
    private String mButtonName;
    private static boolean mIsUndoAvailable;
    private static boolean mIsRedoAvailable;

    public String getButtonName() {
        return mButtonName;
    }

    public void setButtonName(String buttonName) {
        this.mButtonName = buttonName;
    }

    public boolean isUndoAvailable() {
        return mIsUndoAvailable;
    }

    public void setUndoAvailable(boolean isUndoAvailable) {
        this.mIsUndoAvailable = isUndoAvailable;
    }

    public boolean isRedoAvailable() {
        return mIsRedoAvailable;
    }

    public void setRedoAvailable(boolean isRedoAvailable) {
        this.mIsRedoAvailable = isRedoAvailable;
    }
}
