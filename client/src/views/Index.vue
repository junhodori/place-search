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
            <span v-if="metaInfo.total_count > 0">Total Count : {{metaInfo.total_count}}</span>
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
                        <PopularKeyword @popularKeywordSearch="keywordSearch"/>
                    </v-flex>

                    <v-flex
                        md12
                        sm12
                        lg12
                        v-if="placeList.length < 1"
                    >                    
                        <v-alert
                            border="left"
                            colored-border
                            color="amber accent-4"
                            elevation="2"
                        >
                            검색어를 입력해주세요
                            <br/>
                        </v-alert>                    
                    </v-flex>

                    <v-flex
                        md4
                        sm4
                        lg4
                        v-if="placeList.length > 0"
                    >
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
            size: 5,
            pageLimit: 1,
            visiblePageLimit: 7
        }
    },
    methods: {
        axiosPlaceSearch: function() {
            let $this = this
            let url = 'api/placeSearch'
            let headers = {
                Authorization: $this.$store.state.bearer + $this.$store.state.accessToken
            }

            let params = {
                query: $this.query,
                page: $this.page,
                size: $this.size
            }

            if (params.query) {
                $this.$http.get(url, {params: params, headers: headers})
                .then((response) => {
                    $this.placeList = response.data.documents
                    $this.metaInfo = response.data.meta
                    console.log(response)
                }).catch(function(e) {
                    console.error(e)
                })
            } else {
                console.log('키워드를 입력하세요')
            }
        },
        keywordSearch: function(keyword) {
            let $this = this
            $this.query = keyword
            $this.axiosPlaceSearch()
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
