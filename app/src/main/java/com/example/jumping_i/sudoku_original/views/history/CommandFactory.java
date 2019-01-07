package com.example.jumping_i.sudoku_original.views.history;

public class CommandFactory {
    /*******************************************************************************
     * Constructor.
     *******************************************************************************/
    private CommandFactory() {
    }

    /*******************************************************************************
     * Command Factory.
     *******************************************************************************/
    public static ICommand craeteCommand(RequestCommandID.eRequestID commandID) {
        switch (commandID) {
            case REQUEST_NUMBER_ONE_BUTTON:
            case REQUEST_NUMBER_TWO_BUTTON:
            case REQUEST_NUMBER_THREE_BUTTON:
            case REQUEST_NUMBER_FORE_BUTTON:
            case REQUEST_NUMBER_FIVE_BUTTON:
            case REQUEST_NUMBER_SIX_BUTTON:
            case REQUEST_NUMBER_SEVEN_BUTTON:
            case REQUEST_NUMBER_EIGHT_BUTTON:
            case REQUEST_NUMBER_NINE_BUTTON:
                /* 숫자 버튼 명령 */
                return new NumberButtonCommand();

            case REQUEST_DELETE_BUTTON:
                /* 삭제 버튼 명령 */
                return new DeleteButtonCommand();

//            case REQUEST_UNDO_BUTTON:
//                return new UndoButtonCommand();
//
//            case REQUEST_REDO_BUTTON:
//                return new RedoButtonCommand();

            default:
                return new NumberButtonCommand();
        }
    }
}
