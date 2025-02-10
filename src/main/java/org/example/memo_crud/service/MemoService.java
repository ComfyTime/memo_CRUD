package org.example.memo_crud.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.memo_crud.dto.MemoRequestDto;
import org.example.memo_crud.dto.MemoResponseDto;
import org.example.memo_crud.entity.Memo;
import org.example.memo_crud.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo =  memoRepository.save(memo);

        MemoResponseDto memoResponseDto = new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());

        return memoResponseDto;
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            MemoResponseDto dto = new MemoResponseDto(memo.getId(), memo.getContent());
            dtos.add(dto);
            
        }

        return dtos;
    }
    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id 없음")
        );
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id 없음")
        );
        memo.update(dto.getContent());
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }
    @Transactional
    public void deleteById(Long id) {
        if (!memoRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id 없음");
        }
        memoRepository.deleteById(id);
    }
}
