package com.heeyjinny.room

import androidx.room.Database
import androidx.room.RoomDatabase

//1
//RoomHelper 클래스 정의하기
//SQLiteOpenHelpe())를 상속받아 구현했던 것 처럼 Room도 유사한 구조로 사용
//RoomDatabase()를 상속받아 클래스 생성하여 사용함
//상속받아서 클래스 생성 시 주의할점은 추상클래스(abstract)로 생성해야 함

//RoomDatabase를 상속받는 추상클래스 RoomHelper 클래스 생성하고
//@Database 어노테이션 작성
//@Database의 속성
//entities: Room라이브러리가 사용할 엔터티(테이블) 클래스 목록
//version: 데이터 베이스의 버전
//exportSchema: true사용 시 스키마 정보를 파일로 출력함
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {

    //2
    //RoomMemoDao 인터페이스 구현하는 메서드 생성
    //인터페이스 구현하는 메서드만 작성해도 Room라이브러리를 통해 미리 만들어져있는
    //코드를 사용할 수 있음
    abstract fun roomMemoDao(): RoomMemoDao

    //3
    //화면에 보여질 뷰 작성을 위해
    //activity_main.xml, item_recycler.xml 작성 후
    //어댑터 클래스 생성...RecyclerAdapter

}