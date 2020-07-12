<template>
    <v-timeline>
        <v-timeline-item
            v-for="(item, index) in placeList"
            :key="index"
            :color="color[index % color.length]"
            small
            icon="mdi-star"
        >
            <template v-slot:opposite>
                <span
                    :class="`headline font-weight-bold ${color[index % color.length]}--text`"
                    v-text="item.distance + 'm'"
                ></span>
            </template>
            <div class="py-4">
                <h2 :class="`headline font-weight-light mb-4 ${color[index % color.length]}--text`">{{item.place_name}}</h2>
                <div>
                    {{item.road_address_name}}
                </div>
                <div>
                    {{item.phone}}
                </div>
                <div>
                    <v-chip
                        class="ma-2"
                        small
                        label
                        v-for="(category, index) in item.category_name.split('>')"
                        @click="categoryKeywordSearch(category)"
                        :key="index"
                    >
                        {{'# ' + category}}
                    </v-chip>                    
                </div>                
                <div class="my-2">
                    <v-btn @click="openMap(item.id)" x-small :color="color[index % color.length]" dark>바로가기</v-btn>
                </div>
            </div>
        </v-timeline-item>
    </v-timeline>
</template>

<script>
export default {
    props: [
      'placeList'
    ],
    data() {
        return {
            // color: ['amber','cyan','red','green','purple']
            color: ['cyan','green','pink','amber','orange']
        }
    },
    mounted: function() {

    },
    methods: {
        openMap: function(id) {
            window.open('https://map.kakao.com/link/map/' + id, '_blank')
        },
        categoryKeywordSearch: function(keyword) {
            let $this = this
            $this.$emit('categoryKeywordSearch', keyword);
        }        
    }
}
</script>
