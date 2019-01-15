package com.example.jumping_i.sudoku_original.views.game2;

import com.example.jumping_i.sudoku_original.base.IBaseView;

public interface IGameContractView2 extends IBaseView {
    // 확인 버튼 클릭시 성공 여부를 디스플레이 한다.
    void doResultDisplay(boolean result);
}
