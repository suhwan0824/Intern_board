package com.example.bulletin.intern_board;

import com.example.bulletin.dto.BoardRequestDto;
import com.example.bulletin.dto.BoardResponseDto;
import com.example.bulletin.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InternBoardApplicationTests {

    @Autowired
    private BoardService boardService;

    @Test
    void save(){
        BoardRequestDto boardSaveDto = new BoardRequestDto();

        boardSaveDto.setTitle("제목입니다.");
        boardSaveDto.setContent("내용입니다.");
        boardSaveDto.setRegisterId("작성자.");

        Long result = boardService.save(boardSaveDto);

        if (result > 0) {
            System.out.println("# Success save() ~");
//            findAll();
            findById(result);
        } else {
            System.out.println("# Fail Save() ~");
        }
    }

    /*void findAll() {
        List<BoardResponseDto> list = boardService.findAll();

        if (list != null) {
            System.out.println("# Success findAll() : " + list.toString());
        } else {
            System.out.println("# Fail findAll() ~");
        }
    }*/

    void findById(Long id) {
        BoardResponseDto info = boardService.findById(id);

        if (info != null) {
            System.out.println("# Success findByID() : " + info.toString());
            updateBoard(id);
        } else {
            System.out.println("# Fail findByID() ~");
        }
    }

    void updateBoard(Long id) {
        BoardRequestDto boardRequestDto = new BoardRequestDto();

        boardRequestDto.setId(id);
        boardRequestDto.setTitle("업데이트 제목");
        boardRequestDto.setContent("업데이트 내용");
        boardRequestDto.setRegisterId("작성자");

        int result = boardService.updateBoard(boardRequestDto);

        if (result > 0) {
            System.out.println("# Success UpdateBoard() ~");
        } else {
            System.out.println("# Fail updateBoard() ~");
        }
    }

}
