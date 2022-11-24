package com.heeyjinny.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.heeyjinny.room.databinding.ActivityMainBinding

//1
//ORM의 Room라이브러리 사용하기
//app - java 밑 패키지명 우클릭 - New - Kotlin Class/File 생성
//RoomMemo.kt 클래스 생성

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //2
    //helper변수를 생성하여 RoomHelper를 사용할 수 있도록 함
    var helper: RoomHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        setContentView(binding.root)

        //2-1
        //RoomHelpe를 사용할 수 있는 변수 helper생성
        //Room라이브러리의 databaseBuilder 속성 사용
        //databaseBuilder의 세 번째 파라미터는 실제 생성되는 DB파일의 이름
        //Room은 기본적으로 서브 스레드에서 동작하도록 설계되어 있어서
        //메인 스레드에서 동작하도록 만들어줘야함
        //이전에 메인스레드에서 동작할 수 있도록 만드는 코드 찾아와 적용하기...ㅠㅠ
        //지금은 allowMainThreadQueries()옵션을 사용하지만 권장되지 않음
        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")
            .allowMainThreadQueries().build()


        //3
        //어댑터 생성
        val adapter = RecyclerAdapter()

        //8
        //데이터 삭제를 위해 생성해둔 helper를 어댑터에 전달
        //RecyclerAdapter.kt에 helper프로퍼티 생성...
        adapter.helper = helper

        //4
        //어댑터에있는 목록(listData)에서 조회하여(select) 가져온 데이터 세팅
        //helper에 null이 허용되므로 ?. 의 형태로 사용
        //roomMemoDao() 와 Dao의 메서드 getAll()도 null허용 ?. 사용하고
        //adapter의 listData에는 null이 허용되지 않기 때문에 앞의2개가 null일 경우 사용하는
        //엘비스 연산자 ?: 를 사용하여 디폴트 값 설정함
        adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())

        //5
        //리사이블러뷰 위젯에 어댑터를 연결하고 레이아웃 매니저 설정
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        //6
        //리사이클러뷰 아이템 목록에 있는 저장버튼에 클릭이벤트 설정
        binding.buttonSave.setOnClickListener {

            //6-1
            //조건식을 사용하여 메모입력 위젯인 EditText에 값이 있으면 해당 내용으로 Memo클래스 생성
            if (binding.editText.text.toString().isNotEmpty()){

                //6-2
                //입력한 텍스트 값이 있다면 RoomMemo클래스를 생성해 파라미터로 값을 전달하고 변수에 저장
                val memo = RoomMemo(binding.editText.text.toString(), System.currentTimeMillis())
                //6-3
                //helper클래스의 roomMemoDao()메서드에 변수memo의 값을 삽입해(insert) 데이터베이스에 저장
                helper?.roomMemoDao()?.insert(memo)
                //6-4
                //저장이 끝났으면 어댑터의 데이터 모두 초기화
                adapter.listData.clear()
                //6-5
                //데이터베이스에서 새로운 목록을 읽어와 어댑터에 다시 세팅하고 갱신(새로고침 개념...)
                //새로 생성되는 메모에는 번호가 자동 입력되므로 번호를 갱신하기 위해 새로운 데이터를 세팅함
                adapter.listData.addAll(helper?.roomMemoDao()?.getAll() ?: listOf())
                //6-6
                //어댑터의 세팅이 끝났다는 것(데이터가 변경되었다는 것) 알려주고 갱신
                adapter.notifyDataSetChanged()
                //6-7
                //editText위젯에 써있는 텍스트 내용 지워서(빈 칸으로) 초기화
                binding.editText.setText("")

            }

        }//클릭리스너...

        //7
        //메모 목록에 삭제버튼 추가하여 메모 삭제 기능 구현
        //item_recycler.xml 수정...

    }//onCreate
}//ManinActivity