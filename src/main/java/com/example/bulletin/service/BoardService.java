package com.example.bulletin.service;


import com.example.bulletin.dto.BoardRequestDto;
import com.example.bulletin.dto.BoardResponseDto;
import com.example.bulletin.entity.board.Board;
import com.example.bulletin.entity.board.BoardRepository;
import com.example.bulletin.json.JsonReader;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequestDto boardSaveDto){
        return boardRepository.save(boardSaveDto.toEntity()).getId();
    }

    @Transactional
    public Long saveJson(BoardRequestDto boardRequestDto) throws IOException, JSONException {
        JSONObject bigJson = JsonReader.readJsonFromUrl("https://nzin.nongshim.com/api/nz_webzine.php?rows=300&type=json");

        try {
            JSONArray next = (JSONArray) bigJson.get("array");
            for (int i = 0; i < next.length(); i++) {
                JSONObject smallJson = (JSONObject) next.get(i);

                return Board
                        .builder()
                        .title(smallJson.get("title"))
                        .registerId().build().getId();
            }
        }
    }

    @Transactional(readOnly=true)
    public HashMap<String, Object> findAll(Integer page, Integer size) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Page<Board> list = boardRepository.findAll(PageRequest.of(page, size));
        resultMap.put("list", list.stream().map(BoardResponseDto::new).collect(Collectors.toList()));
        resultMap.put("paging", list.getPageable());
        resultMap.put("totalCnt", list.getTotalElements());
        resultMap.put("totalPage", list.getTotalPages());

        return resultMap;
    }

    public BoardResponseDto findById(Long id){
        boardRepository.updateBoardRadCntInc(id);
        return new BoardResponseDto(boardRepository.findById(id).get());
    }

    public int updateBoard(BoardRequestDto boardRequestDto){
        return boardRepository.updateBoard(boardRequestDto);
    }

    public int updateBoardReadCntInc(Long id) {
        return boardRepository.updateBoardRadCntInc(id);
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    public void deleteAll(Long[] deleteId) {
        boardRepository.deleteBoard(deleteId);
    }
}
