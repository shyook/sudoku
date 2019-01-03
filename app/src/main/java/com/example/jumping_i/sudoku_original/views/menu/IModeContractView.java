package com.example.jumping_i.sudoku_original.views.menu;

import com.example.jumping_i.sudoku_original.base.IBaseView;

public interface IModeContractView extends IBaseView {
    // 위아래 버튼 클릭시 TextView 영역의 Display 변경.
    void updateGameModeView(int mode);
}
