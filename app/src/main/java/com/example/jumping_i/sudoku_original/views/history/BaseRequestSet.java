package com.example.jumping_i.sudoku_original.views.history;

public abstract class BaseRequestSet<T> implements IRequestSet<T> {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    /* 응답 처리 리스너 */
    protected ISuccessResponse<T> mSuccessResponse;
    protected boolean mIsRequestRedo;

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * 성공 응답 리스너를 등록 한다.
     * @param successResponse
     */
    public void setSuccessResponse(ISuccessResponse<T> successResponse) {
        this.mSuccessResponse = successResponse;
    }

    /**
     * 성공시 리스너로 결과를 전달 한다.
     * @param result
     */
    public void response(T result) {
        if (null != mSuccessResponse) {
            mSuccessResponse.onSuccess(result);
        }
    }

    /*******************************************************************************
     * Override Method.
     *******************************************************************************/
    /**
     * Redo 요청으로 실행 되었는지를 반환 한다.
     * @return
     */
    @Override
    public boolean isRequestRedo() {
        return mIsRequestRedo;
    }

    /**
     * Redo 요청으로 Command가 실행 된경우 셋팅 한다. (Redo Stack을 지우지 않기 위해서)
     * @param isRedo
     */
    @Override
    public void setRequestRedo(boolean isRedo) {
        mIsRequestRedo = isRedo;
    }
}
