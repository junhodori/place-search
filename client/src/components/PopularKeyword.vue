<template>
    <v-card>
        <v-system-bar window color="amber lighten-2"
            height="40"
        >
            <!-- <v-icon>mdi-message</v-icon> -->
            <span>인기검색어</span>
            <v-chip
            class="ma-2"
            :color="color[index % color.length]"
            text-color="white"
            v-for="(popularKeyword, index) in popularKeywordList"
            :key="popularKeyword.keyword"
            @click="popularKeywordSearch(popularKeyword.keyword)"
            >
                {{popularKeyword.keyword}}
                <v-avatar
                    right
                    class="darken-4"
                    :class="color[index % color.length]"
                >
                    {{popularKeyword.num}}
                </v-avatar>
            </v-chip>            
        </v-system-bar>


        <v-card
          class="mx-auto"
          outlined
        >

        </v-card>
    </v-card>
</template>

<script>

export default {
    data() {
        return {
            popularKeywordList: [],
            color: ['primary','pink','red','cyan','green','indigo','orange','teal']
        }
    },
    mounted: function() {
        let $this = this
        $this.axiosPopularKeyword()

    },
    methods: {
        axiosPopularKeyword: function() {
            let $this = this
            let url = 'api/popularKeyword'
            let headers = {
                Authorization: $this.$store.state.bearer + $this.$store.state.accessToken
            }

            $this.$http.get(url, {headers: headers})
            .then((response) => {
                $this.popularKeywordList = response.data

                console.log(response)
            }).catch(function(e) {
                console.error(e)
            })
        },
        popularKeywordSearch: function(keyword) {
            let $this = this
            $this.$emit('popularKeywordSearch', keyword);
        }
    }
}
</script>
