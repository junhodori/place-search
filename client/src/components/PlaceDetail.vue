<template>
    <v-card>
      <div id="map" style="width:500px;height:400px;"></div>
    </v-card>
</template>

<script>
  export default {
    mounted: function() {

    },
    methods: {
        beforePage: () => {
            let $this = this
            $this.$router.go(-1)
        }
    }
  }
</script>
