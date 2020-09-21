package com.kakao.pjh.db;

import com.kakao.pjh.data.entity.Keyword;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
@Rollback
class KeywordRepositoryTest {
    @Autowired
    KeywordRepository keywordRepository;

    private static Stream<Arguments> setupKeyword(){
        return Stream.of(
                Arguments.of(Integer.toUnsignedLong(11), "카카오뱅크1", 123),
                Arguments.of(Integer.toUnsignedLong(12), "카카오뱅크2", 1),
                Arguments.of(Integer.toUnsignedLong(13), "카카오뱅크3", 2),
                Arguments.of(Integer.toUnsignedLong(14), "카카오뱅크4", 23),
                Arguments.of(Integer.toUnsignedLong(15), "카카오뱅크5", 23),
                Arguments.of(Integer.toUnsignedLong(16), "카카오뱅크6", 3),
                Arguments.of(Integer.toUnsignedLong(17), "카카오뱅크7", 4),
                Arguments.of(Integer.toUnsignedLong(18), "카카오뱅크8", 5),
                Arguments.of(Integer.toUnsignedLong(19), "카카오뱅크9", 6),
                Arguments.of(Integer.toUnsignedLong(20), "카카오뱅크10", 7),
                Arguments.of(Integer.toUnsignedLong(21), "카카오뱅크11", 8),
                Arguments.of(Integer.toUnsignedLong(22), "카카오뱅크12", 9),
                Arguments.of(Integer.toUnsignedLong(23), "카카오뱅크13", 10),
                Arguments.of(Integer.toUnsignedLong(24), "카카오뱅크14", 11),
                Arguments.of(Integer.toUnsignedLong(25), "카카오뱅크15", 12),
                Arguments.of(Integer.toUnsignedLong(26), "카카오뱅크16", 13)
        );
    }

    @ParameterizedTest
    @MethodSource("setupKeyword")
    public void findKeyword(Long id, String word, Integer hitCnt){
        //given
        Keyword keyword = new Keyword();
        keyword.setId(id);
        keyword.setKeyword(word);
        keyword.setHitCnt(hitCnt);
        keywordRepository.save(keyword);

        //then
        Optional<Keyword> byKeyword = keywordRepository.findByKeyword(word);
        assertTrue(byKeyword.isPresent());
        Keyword selectedKeyword = byKeyword.get();
        assertThat(selectedKeyword.getKeyword()).isEqualTo(word);
        assertThat(selectedKeyword.getHitCnt()).isEqualTo(hitCnt);
    }

}