package com.example.jumping_i.sudoku_original.views.history;

public class RequestCommandID {

    public static enum eRequestID {
        REQUEST_NONE(0x0000)
        , REQUEST_NUMBER_ONE_BUTTON(0x0001)     // 1번 버튼 클릭
        , REQUEST_NUMBER_TWO_BUTTON(0x0002)     // 2번 버튼 클릭
        , REQUEST_NUMBER_THREE_BUTTON(0x0003)   // 3번 버튼 클릭
        , REQUEST_NUMBER_FORE_BUTTON(0x0004)    // 4번 버튼 클릭
        , REQUEST_NUMBER_FIVE_BUTTON(0x0005)    // 5번 버튼 클릭
        , REQUEST_NUMBER_SIX_BUTTON(0x0006)     // 6번 버튼 클릭
        , REQUEST_NUMBER_SEVEN_BUTTON(0x0007)   // 7번 버튼 클릭
        , REQUEST_NUMBER_EIGHT_BUTTON(0x0008)   // 8번 버튼 클릭
        , REQUEST_NUMBER_NINE_BUTTON(0x0009)    // 9번 버튼 클릭
        , REQUEST_DELETE_BUTTON(0x0010)         // 삭제 버튼 클릭
        , REQUEST_UNDO_BUTTON(0x0011)           // Undo 버튼 클릭
        , REQUEST_REDO_BUTTON(0x0012);          // Redo 버튼 클릭

        private int mCode;

        eRequestID(int code) {
            mCode = code;
        }

        public int getRequestIDCode() {
            return mCode;
        }

    }
}
