package com.heeyjinny.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

//1
//DAO(Data Access Object): 데이터베이스에 접근해서 DML쿼리를 실행하는 메서드 모음
//DML쿼리: SELECT, INSERT, UPDATE, DELETE
//RoomMemoDAO 인터페이스에 @Dao(쿼리메서드모음) 명시
@Dao
interface RoomMemoDao {
    //2
    //조회, 삽입수정, 삭제 3개 메서드 생성 후 각 어노테이션 붙임

    //2-1
    //조회메서드 생성
    //다른 ORM툴과 다르게 조회하는 select쿼리는 직접 작성해야함
    //그래서 @Query어노테이션을 사용해 직접 작성
    //조회한다(select) 모든내용을(*) room_memo에 있는(from)
    //room_memo안에 있는 테이블의 모든 내용을 RoomMemo클래스의 배열타입을 가지는 메서드 getAll()생성
    @Query("select * from room_memo")
    fun getAll(): List<RoomMemo>

    //2-2
    //삽입, 수정 메서드 생성
    //onConflict = REPLACE 옵션 적용: 동일한 키를 가진 값이 입력되었을 때 덮어쓰기(Update,수정됨)
    //REPLACE 옵션 적용 시 import할 때 androidx.room 패키지로 선택함
    @Insert(onConflict = REPLACE)
    fun insert(memo: RoomMemo)

    //2-3
    //삭제 메서드 생성
    @Delete
    fun delete(memo: RoomMemo)

    //3
    //RoomHelper 클래스 정의
    //SQLiteOpenHelper()를 상속받아 구현했던 것 처럼 Room도 유사한 구조로 사용
    //RoomDatabase()를 상속받아 클래스 생성하여 사용함
    //상속받아서 클래스 생성 시 주의할점은 추상클래스(abstract)로 생성해야 함
    //app - java밑 클래스명 우클릭 - New - Kotlin Class/File -
    //Class클릭 - RoomHelper클래스 생성

}