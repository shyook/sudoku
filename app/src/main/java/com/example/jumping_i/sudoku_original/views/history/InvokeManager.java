package com.example.jumping_i.sudoku_original.views.history;

import android.util.Log;

import java.util.Stack;

public class InvokeManager {
    private static final String TAG = InvokeManager.class.getSimpleName();

    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static InvokeManager mInstance;
    private final Stack<IRequestSet<?>> mUndoStack; // Undo Stack
    private final Stack<IRequestSet<?>> mRedoStack; // Redo Stack

    /*******************************************************************************
     * Init.
     *******************************************************************************/
    private InvokeManager() {
        mUndoStack = new Stack<IRequestSet<?>>();
        mRedoStack = new Stack<IRequestSet<?>>();
    }

    public static InvokeManager getInstance() {
        if (mInstance == null) {
            mInstance = new InvokeManager();
        }

        return mInstance;
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * 요청에 대한 Command를 생성해서(Factory Pattern) 실행 한다.
     * 완료 후 결과를 전달 한다.
     *
     * @param request
     */
    public void startCommand(IRequestSet<?> request) {
        Log.d(TAG, "startCommand()");
        ICommand command = CommandFactory.craeteCommand(request.getRequestID());

        if (command == null) {
            Log.e(TAG, "sequence not found");
            return;
        }

        // Command에 대한 응답값을 전달 받는다.
        AbstractResponseSet response = command.execute(request);

        if (response.getResponseCode() == IResponseCode.RESULT_OK) {
            // Undo Stack에 쌓는다.
            mUndoStack.push(request);
            if (! request.isRequestRedo()) {
                clearRedoStack();
            }
        }

        // 결과를 전달 한다.
        command.response(response);
    }

    /**
     * Undo button 클릭시 이전 명령을 실행 한다.
     */
    public void startUndo() {
        Log.d(TAG, "startUndo()");
        if (isUndoAvailable()) {
            IRequestSet<?> request = mUndoStack.pop();
            ICommand command = CommandFactory.craeteCommand(request.getRequestID());
            AbstractResponseSet response = command.undo(request);

            if (response.getResponseCode() == IResponseCode.RESULT_OK) {
                // 실행 성공시 Redo Stack에 쌓는다.
                mRedoStack.push(request);
            }
            command.response(response);
        }
    }

    /**
     * Redo button 클릭시 Undo에 의해 실행된 명령을 취소 한다.
     */
    public void startRedo() {
        Log.d(TAG, "startRedo()");
        if (isRedoAvailable()) {
            IRequestSet<?> request = mRedoStack.pop();
            request.setRequestRedo(true);
            startCommand(request);
        }
    }

    /**
     * Undo Stack 삭제
     */
    public void clearUndoStack() {
        Log.d(TAG, "clearUndoStack()");
        mUndoStack.clear();
    }

    /**
     * Redo Stack 삭제
     */
    public void clearRedoStack() {
        Log.d(TAG, "clearRedoStack()");
        mRedoStack.clear();
    }

    /**
     * Undo Stack을 확인해 가능 여부를 리턴 한다.
     *
     * @return true is available
     */
    public boolean isUndoAvailable() {
        Log.d(TAG, "isUndoAvailable()");
        return !mUndoStack.empty();
    }

    /**
     * Redo Stack을 확인해 가능 여부를 리턴 한다.
     *
     * @return true is available.
     */
    public boolean isRedoAvailable() {
        Log.d(TAG, "isRedoAvailable()");
        return !mRedoStack.empty();
    }


}
