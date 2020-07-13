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
                    type="success"
                >
                    <strong>로그아웃 성공</strong>
                    <br/>
                    다시 로그인 하시겠습니까?
                    <v-divider
                        class="my-4 success"
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
                                color="success"
                                outlined
                                @click="login"
                            >
                                Login
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
        let $this = this
        $this.$store.dispatch('LOGOUT')
            .then(() => console.log('Logout Success!'))
            .catch((e) => console.log(e))
    },
    methods: {
        login: function() {
            let $this = this
            $this.$router.push('/login')
        }
    }
  }
</script>
