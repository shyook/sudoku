# Android Sudoku Game


안드로이드 MVP 패턴을 이용한 스도쿠 게임 입니다.

패턴 사용과 외부 라이브러리 사용을 테스트 해보기 위해 만들었습니다.


## 개발 환경


- 개발 툴 : Android Studio 3.1.4
- SDK 버전 :
 > + compileSdkVersion 27
 > + minSdkVersion 19
 > + targetSdkVersion 27
 
 
## 주요 내용


- State Pattern을 이용한 게임 모드 선택
- Command Pattern을 이용한 Undo / Redo 기능 구현
- Retrofit2를 이용한 SK 공개 API 연동 (날씨 정보)
- ORMLite를 이용한 DB 저장


## 테스트


- Presenter Unit Test


## Diagram


- Activity 상속 Diagram

![activity](https://user-images.githubusercontent.com/33274284/51365390-68595100-1b23-11e9-85d5-57cbe9fcb49b.png)

- Presenter 상속 Diagram

![presenter](https://user-images.githubusercontent.com/33274284/51365391-68f1e780-1b23-11e9-9dbd-1840d721f5ae.png)

- Class Diagram

![diagram](https://user-images.githubusercontent.com/33274284/51367795-8fb61b00-1b2f-11e9-86b1-2e361e450244.png)


## Screen Shot


![game_mode](https://user-images.githubusercontent.com/33274284/51101796-8b33ee80-181f-11e9-8da4-9224727b969c.png)
![game_start](https://user-images.githubusercontent.com/33274284/51101797-8bcc8500-181f-11e9-8dc0-fda6c92b82f7.png)
![game](https://user-images.githubusercontent.com/33274284/51101795-8b33ee80-181f-11e9-916e-ae39fbe67ccb.png)

