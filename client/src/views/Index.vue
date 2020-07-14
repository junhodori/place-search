<template>
    <v-app id="keep">
        <v-app-bar
            app
            clipped-left
            color="amber"
        >
            <span class="title ml-3 mr-5"># Place&nbsp;<span class="font-weight-light">search</span></span>
            <v-text-field
                solo-inverted
                flat
                hide-details
                label="Search"
                v-model="query"
                id="searchQuery"
                @keyup.enter="axiosPlaceSearch()"
            ></v-text-field>
            <v-spacer></v-spacer>
            <v-btn class="ma-2" outlined color="indigo" @click="logout()">Logout</v-btn>
        </v-app-bar>
        
        <v-main>
            <v-container
                fluid
                grid-list-xl
            >
                <v-layout wrap>
                    <v-flex
                        md12
                        sm12
                        lg12
                    >
                        <PopularKeyword @popularKeywordSearch="keywordSearch" ref="popularKeyword"/>
                    </v-flex>

                    <v-flex
                        md12
                        sm12
                        lg12
                    >                    
                        <v-alert
                            border="left"
                            colored-border
                            :color="noQueryMessage ? 'error accent-4' : 'amber accent-4'"
                            elevation="2"
                            :icon="noQueryMessage ? 'mdi-alert-circle' : 'mdi-alert-circle-outline'"
                        >
                            <span v-if="noQueryMessage" class="red--text">검색어를 입력해주세요.</span>
                            <span v-if="!noQueryMessage && metaInfo.total_count < 1">검색 결과가 없습니다.</span>
                            <span v-if="!noQueryMessage && metaInfo.total_count > 0">총 {{metaInfo.total_count}}개의 검색결과가 있습니다.</span>
                            <br/>
                        </v-alert>                    
                    </v-flex>

                    <v-flex
                        md12
                        sm12
                        lg12
                        v-if="placeList.length > 0"
                    >
                        <v-timeline>
                            <v-timeline-item icon="mdi-home">
                                <h2>현재위치는 강남구청역입니다</h2>
                                <span>위치 좌표 (x: {{longitude}} / y: {{latitude}})</span>
                                <template v-slot:opposite>
                                    <span class="`headline font-weight-bold pink--text`">여기서부터..</span>
                                </template>                              
                            </v-timeline-item>
                        </v-timeline>                    
                        
                        <PlaceSearch :placeList="placeList" @categoryKeywordSearch="keywordSearch"/>
                        <div class="text-center">
                            <v-pagination
                                v-model="page"
                                :length="pageLimit"
                                :total-visible="visiblePageLimit"
                            ></v-pagination>
                        </div>                
                    </v-flex>
                </v-layout>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import PopularKeyword from '../components/PopularKeyword'
import PlaceSearch from '../components/PlaceSearch'

export default {
    components: {
        PopularKeyword,
        PlaceSearch
    },
    data() {
        return {
            query: '',
            placeList: [],
            metaInfo: {},
            timer: null,
            page: 1,
            size: 10,
            pageLimit: 1,
            visiblePageLimit: 5,
            noQueryMessage: false,
            longitude: '37.5172187',
            latitude: '127.0411989'
        }
    },
    methods: {
        axiosPlaceSearch: function() {
            let $this = this
            let url = 'api/kakao/placeSearch'
            let headers = {
                Authorization: $this.$store.state.bearer + $this.$store.state.accessToken
            }

            let params = {
                query: $this.query,
                page: $this.page,
                size: $this.size,
                longitude: $this.longitude,
                latitude: $this.latitude
            }

            if (params.query) {
                $this.noQueryMessage = false

                $this.$http.get(url, {params: params, headers: headers})
                .then((response) => {
                    $this.placeList = response.data.documents
                    $this.metaInfo = response.data.meta

                    $this.$refs.popularKeyword.axiosPopularKeyword()
                }).catch(function(e) {
                    console.error(e)
                    $this.logout()
                })
            } else {
                $this.noQueryMessage = true
                console.log('키워드를 입력하세요')
            }
        },
        keywordSearch: function(keyword) {
            let $this = this
            $this.query = keyword
            $this.axiosPlaceSearch()
        },
        logout: function() {
            let $this = this
            $this.$router.push('/logout')
        }
    },
    mounted: function() {

    },
    watch: {
        page: function() {
            let $this = this
            $this.axiosPlaceSearch()
        },
        metaInfo: function() {
            let $this = this
            $this.pageLimit = Math.ceil($this.metaInfo.pageable_count / $this.size)
        }
    }
}
</script>

<style>
#keep .v-navigation-drawer__border {
  display: none
}
</style>
