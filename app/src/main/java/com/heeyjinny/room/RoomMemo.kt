package com.heeyjinny.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//1
//@Entity 어노테이션 적용: 정의된 class 위에 사용
//@Entity: Room라이브러리는 @Entity이 적용된 클래스를 찾아 테이블로 변환
//@Entity(tableName = "테이블명"): 테이블명을 클래스명과 다르게 하고 싶을 때 옵션
//RoomMemo클래스의 내용을 테이블명이 room_memo인 테이블로 생성하여 변환
@Entity(tableName = "room_memo")
class RoomMemo {

    //2
    //테이블의 컬럼인 num, content, date를 멤버변수로 선언하고 위에
    //@ColumnInfo 어노테이션 작성
    //@ColumnInfo: 테이블의 컬럼명으로 사용
    //@ColumnInfo(name = "컬럼명")테이블의 컬럼명을 변수명과 다르게 하고 싶을 때 옵션

    //3
    //num변수에 키(Key)라는 점을 명시하고 자동증가 옵션 추가
    //@PrimaryKey(autoGenerate = true)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var num: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date")
    var datetime: Long = 0

    //4
    //content와 datetime을 받는 생성자 작성
    //num은 값이 자동증가되므로 생성자로 내용을 받지 않아도 됨
    constructor(content: String, datetime: Long) {
        //4-1
        //이 클래스에 있는(this) 변수 content와 datetime에 생성자로 받은 값넣기
        this.content = content
        this.datetime = datetime
    }

    //5
    //만약 변수를 테이블의 컬럼으로 사용하고 싶지 않을 때
    //@Ignore: 해당 변수가 테이블과 관계없는 변수라는 정보를 알림
    @Ignore
    var temp: String = "임시로 사용되는 데이터"

    //6
    //DAO(Data Access Object): 데이터베이스에 접근해서 DML쿼리를 실행하는 메서드 모음
    //DML쿼리: SELECT, INSERT, UPDATE, DELETE
    //RoomMemoDAO 인터페이스 정의하기
    //app - java밑 패키지 우클릭 - New - Kotlin Class/File
    //Interface선택 - RoomMemoDao 생성

}//class RoomMomo