<template>
  <v-app id="inspire">
    <v-main>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">             
                <v-alert
                    dense
                    outlined
                    type="error"
                >
                    <strong>잘못된 경로</strong>
                    <br/>
                    잘못된 경로로 접근하였습니다. 되돌아 가시겠습니까?
                    <v-divider
                        class="my-4 error"
                        style="opacity: 0.22"
                    ></v-divider>
                    <v-row
                            align="center"
                            no-gutters
                        >
                        <v-spacer></v-spacer>
                        <v-col class="shrink">
                            <v-btn
                                align="right" 
                                color="error"
                                outlined
                                @click="beforePage"
                            >
                                Ok
                            </v-btn>                            
                        </v-col>
                    </v-row>
                </v-alert>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
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
