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
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>Login form</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    label="username"
                    name="username"
                    prepend-icon="mdi-account"
                    type="text"
                    v-model="username"
                  ></v-text-field>

                  <v-text-field
                    label="password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    v-model="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="login">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
    data() {
        return {
            username: 'ryan',
            password: '12345'
        }
    },
    mounted: function() {

    },
    methods: {
        login: function() {
            let $this = this
            $this.axiosLogin($this.username, $this.password)
        },
        axiosLogin: function(username, password) {
            let $this = this

            $this.$store.dispatch('LOGIN', {username, password})
                .then(() => $this.$router.push('/index'))
                .catch(({message}) => this.msg = message)

            // let url = 'login'
            // let params = {
            //     username: username,
            //     password: password
            // }

            // $this.$http.post(url, params)
            // .then((response) => {
            //     console.log(response)
            // }).catch(function(e) {
            //     console.error(e)
            // })
        },
        redirect: function() {
            const {search} = window.location
            const tokens = search.replace(/^\?/, '').split('&')
            const {returnPath} = tokens.reduce((qs, tkn) => {
                const pair = tkn.split('=')
                qs[pair[0]] = decodeURIComponent(pair[1])
                return qs
            }, {})
            // 리다이렉트 처리
            this.$router.push(returnPath)
        },
    }
 }
</script>
