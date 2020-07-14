package com.search.place.application.service;

import com.search.place.application.model.Keyword;
import com.search.place.application.model.PopularKeyword;
import com.search.place.application.repository.KeywordRepository;
import com.search.place.application.repository.PopularKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PopularKeywordServiceImpl implements PopularKeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private PopularKeywordRepository popularKeywordRepository;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void insertPopularKeyword(String keyword) {
        List<PopularKeyword> popularKeywordList = popularKeywordRepository.findAllByOrderById();

        /**
         * 검색한 키워드를 모두 저장
         *  - 새로운 키워드는 카운트 1로 저장
         *  - 기존 키워드는 기존 카운트 +1로 저장
         */
        Optional<Keyword> _keyword = keywordRepository.findById(keyword);

        Keyword __keyword = null;

        if (_keyword.isPresent()) {
            __keyword = Keyword.builder()
                    .id(_keyword.get().getId())
                    .count(_keyword.get().getCount() + 1)
                    .build();
        } else {
            __keyword = Keyword.builder()
                    .id(keyword)
                    .count(1)
                    .build();
        }
        /**
         * 키워드 저장
         */
        keywordRepository.save(__keyword);

        /**
         * 키워드 중복 여부 체크
         */
        int index = 0;
        boolean existKeyword = false;
        for (PopularKeyword ___keyword : popularKeywordList) {
            if (___keyword.getKeyword() != null && ___keyword.getKeyword().equals(__keyword.getId())) {
                ___keyword.setCount(__keyword.getCount());

                /**
                 * 키워드 중복이 발견되면 인기검색어 순위내에서 최상단으로 옮김
                 */
                int _index = 0;
                for (PopularKeyword ____keyword : popularKeywordList) {
                    if (____keyword.getCount() <= __keyword.getCount()) {
                        popularKeywordList.remove(index);
                        popularKeywordList.add(_index, PopularKeyword.builder().id(_index).keyword(__keyword.getId()).count(__keyword.getCount()).build());
                        break;
                    }
                    _index++;
                }

                existKeyword = true;
                break;
            }

            index++;
        }

        /**
         * 쿼리량을 체크하여 인기검색어에 추가
         */
        index = 0;
        if (!existKeyword) {
            for (PopularKeyword ___keyword : popularKeywordList) {
                if (___keyword.getKeyword() == null || ___keyword.getCount() <= __keyword.getCount()) {
                    popularKeywordList.add(index, PopularKeyword.builder().id(index).keyword(__keyword.getId()).count(__keyword.getCount()).build());

                    break;
                }
                index++;
            }
        }

        /**
         * 인기검색어 저장
         */
        index = 0;
        for (PopularKeyword popularKeyword : popularKeywordList) {
            popularKeywordRepository.save(PopularKeyword.builder().id(index).keyword(popularKeyword.getKeyword()).count(popularKeyword.getCount()).build());
            index++;
            if (index > 9) break;
        }

    }

    @Override
    public Optional<Keyword> selectOnePopularKeyword(Keyword keyword) {
        return keywordRepository.findById(keyword.getId());
    }

    @Override
    public List<PopularKeyword> selectPopularKeyword() {
        return popularKeywordRepository.findAllByOrderById();
    }
}
