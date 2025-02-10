package org.example.memo_crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.memo_crud.dto.MemoRequestDto;
import org.example.memo_crud.dto.MemoResponseDto;
import org.example.memo_crud.entity.Memo;
import org.example.memo_crud.service.MemoService;
import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // 저장
    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.save(dto));
    }
    //다건조회
    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> findAll() {
        return ResponseEntity.ok(memoService.findAll());
    }
    //단건조회
    @GetMapping("/memos/{id}")
    public ResponseEntity<MemoResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(memoService.findById(id));
    }
    //수정
    @PutMapping("/memos/{id}")
    public ResponseEntity<MemoResponseDto> update(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ) {
        return ResponseEntity.ok(memoService.update(id, dto));
    }
    //삭제
    @DeleteMapping("/memo/{id}")
    public void delete(@PathVariable Long id){
        MemoService.deleteById(id);
    }
}
