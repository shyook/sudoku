package com.example.jumping_i.sudoku_original.views.history;

import com.example.jumping_i.sudoku_original.data.SudokuData;

import java.util.ArrayList;

class DeleteButtonCommand implements ICommand<ResultSet> {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private RequestDeleteSet mRequestSet = null;

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    @Override
    public ResultSet execute(IRequestSet<?> request) {
        mRequestSet = (RequestDeleteSet) request;
        // 요청된 command를 실행한다.
        ArrayList<SudokuData> arrItems = mRequestSet.getArraySudokuData();
        arrItems.remove(mRequestSet.getPosition());
        arrItems.add(mRequestSet.getPosition(), new SudokuData(0, true));

        // Response Data 생성한다.
        ResultSet resultSet = new ResultSet(0, "");
        resultSet.setArraySudokuData(arrItems);
        return resultSet;
    }

    @Override
    public ResultSet undo(IRequestSet<?> request) {
        mRequestSet = (RequestDeleteSet) request;

        if (mRequestSet != null) {
            // 요청된 command를 실행한다.
            ArrayList<SudokuData> arrItems = mRequestSet.getArraySudokuData();
            arrItems.remove(mRequestSet.getPosition());
            arrItems.add(mRequestSet.getPosition(), new SudokuData(mRequestSet.getRequestDeleteNumber(), true));

            // Response Data 생성한다.
            ResultSet resultSet = new ResultSet(IResponseCode.RESULT_OK, "Success");
            resultSet.setArraySudokuData(arrItems);
            return resultSet;
        }

        return new ResultSet(IResponseCode.RESULT_ERROR, "Error");
    }

    @Override
    public boolean redo(ICommand cmd) {
        return false;
    }

    @Override
    public void response(ResultSet result) {
        mRequestSet.response(result);
    }
}
