package com.search.place.application.service;

import com.search.place.application.model.Keyword;
import com.search.place.application.model.PopularKeyword;
import com.search.place.application.repository.KeywordRepository;
import com.search.place.application.repository.PopularKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PopularKeywordServiceImpl implements PopularKeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private PopularKeywordRepository popularKeywordRepository;

    @Override
    public void insertPopularKeyword(String keyword) {

        Optional<Keyword> _keyword = keywordRepository.findById(keyword);
        if (_keyword.isPresent()) {
            Keyword __keyword = Keyword.builder()
                    .id(_keyword.get().getId())
                    .num(_keyword.get().getNum() + 1)
                    .build();
            keywordRepository.save(__keyword);
        } else {
            Keyword __keyword = Keyword.builder()
                    .id(keyword)
                    .num(1)
                    .build();
            keywordRepository.save(__keyword);
        }

        /**
         * 인기 검색어 키워드를 정렬해서 모두 받아오기
         */
        List<PopularKeyword> popularKeywordList = popularKeywordRepository.findAllByOrderById();

        /**
         * 인기 검색어 키워드가 1개도 없을 경우는 새로운 키워드로 초기화
         */
        if (popularKeywordList.size() < 1) {
            PopularKeyword _popularKeyword = null;
            /**
             * 기존 키워드 리스트에 있을경우 해당 카운트를 이용
             */
            if (_keyword.isPresent()) {
                _popularKeyword = PopularKeyword.builder()
                        .id(1)
                        .keyword(keyword)
                        .num(_keyword.get().getNum() + 1)
                        .build();
            } else {
                _popularKeyword = PopularKeyword.builder()
                        .id(1)
                        .keyword(keyword)
                        .num(1)
                        .build();
            }

            popularKeywordRepository.save(_popularKeyword);

        } else {
            int index = 0;
            boolean rankCheck = false;
            boolean equalCheck = false;

            /**
             * 인기 검색어 키워드와 새로운 검색어의 쿼리 숫자를 비교해서
             * 새로운 검색어 키워드가 많으면 해당 순위 업데이트
             * 그리고 그 이후의 기존 검색어 키워드들은 모두 한칸씩 순위 내리기 (전체 총 10개까지만)
             */
            for (PopularKeyword popularKeyword : popularKeywordList) {
                index++;
                System.out.println(popularKeyword.getKeyword());
                System.out.println(popularKeyword.getNum());

                if (_keyword.isPresent()) {

                    System.out.println(_keyword.get().getNum() + 1);

                    if (!rankCheck && popularKeyword.getNum() <= _keyword.get().getNum() + 1) {
                        rankCheck = true;
                        PopularKeyword _popularKeyword = PopularKeyword.builder()
                            .id(index)
                            .keyword(_keyword.get().getId())
                            .num(_keyword.get().getNum() + 1)
                            .build();
                        popularKeywordRepository.save(_popularKeyword);

                        /**
                         * 동일한 검색어 쿼리일 경우는 인기검색어에서 카운트만 업데이트하고 나머지 키워드 순위 변동을 막는다
                         */
                        if (popularKeyword.getKeyword().equals(_keyword.get().getId())) {
                            equalCheck = true;
                        }
                    }
                } else {
                    System.out.println("없는뎁쇼");

                    if (!rankCheck && popularKeyword.getNum() <= 1) {
                        rankCheck = true;
                        PopularKeyword _popularKeyword = PopularKeyword.builder()
                                .id(index)
                                .keyword(keyword)
                                .num(1)
                                .build();
                        popularKeywordRepository.save(_popularKeyword);

                        /**
                         * 동일한 검색어 쿼리일 경우는 인기검색어에서 카운트만 업데이트하고 나머지 키워드 순위 변동을 막는다
                         */
                        if (popularKeyword.getKeyword().equals(keyword)) {
                            equalCheck = true;
                        }
                    }
                }

                /**
                 * 인기검색어 리스트 저장 공간이 남아 있을 경우 인기검색어에 추가
                 */
                System.out.println(rankCheck);
                System.out.println(popularKeywordList.size());
                System.out.println(index);
                System.out.println(!rankCheck && popularKeywordList.size() < 10 && index == popularKeywordList.size());

                if (!rankCheck && popularKeywordList.size() < 10 && index == popularKeywordList.size()) {
                    PopularKeyword _popularKeyword = null;
                    System.out.println(_keyword.isPresent());
                    if (_keyword.isPresent()) {
                        _popularKeyword = PopularKeyword.builder()
                                .id(index)
                                .keyword(keyword)
                                .num(_keyword.get().getNum() + 1)
                                .build();
                    } else {
                        _popularKeyword = PopularKeyword.builder()
                                .id(index)
                                .keyword(keyword)
                                .num(1)
                                .build();
                    }
                    popularKeywordRepository.save(_popularKeyword);
                }

                if (rankCheck && !equalCheck) {
                    if (index <= 10) {
                        PopularKeyword _popularKeyword = PopularKeyword.builder()
                                .id(index + 1)
                                .keyword(popularKeyword.getKeyword())
                                .num(popularKeyword.getNum())
                                .build();

                        popularKeywordRepository.save(_popularKeyword);
                    }
                }
            }
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
