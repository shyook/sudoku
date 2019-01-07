package com.example.jumping_i.sudoku_original.views.history;

public class RequestRedoSet extends BaseRequestSet {
    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public RequestCommandID.eRequestID getRequestID() {
        return RequestCommandID.eRequestID.REQUEST_REDO_BUTTON;
    }
}
