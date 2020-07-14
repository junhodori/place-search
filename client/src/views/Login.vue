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
                color="amber"
                dark
                flat
              >
                <v-toolbar-title># Login</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form
                    ref="form"
                >
                  <v-text-field
                    label="username"
                    name="username"
                    prepend-icon="mdi-account"
                    type="text"
                    :rules="rulesUsername"
                    v-model="username"
                    @keyup.enter="login()"
                  ></v-text-field>

                  <v-text-field
                    label="password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    :rules="rulesPassword"
                    v-model="password"
                    @keyup.enter="login()"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="amber" @click="login()">Login</v-btn>
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
            username: 'muzi',
            password: '12345',
            rulesUsername: [
                v => !!v || 'username 을 확인하세요'
            ],
            rulesPassword: [
                v => !!v || 'password 를 확인하세요'
            ],            
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
                .catch(() => $this.validate())
        },
        validate: function () {
            let $this = this
            $this.$refs.form.validate()
            $this.username = null
            $this.password = null
        },        
    }
 }
</script>
