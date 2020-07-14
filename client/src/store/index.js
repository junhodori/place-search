import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [
    createPersistedState({
        paths: ['accessToken']
    })
  ],
  state: {
    accessToken: null,
    bearer: 'Bearer '
  },
  getters: {

  },
  mutations: {
    LOGIN (state, accessToken) {
      state.accessToken = accessToken.split(state.bearer)[1]
    },
    LOGOUT (state) {
      state.accessToken = null
    }
  },
  actions: {
    LOGIN ({commit}, {username, password}) {
      return new Promise(function(resolve, reject) {
            axios.post('login', {username: username, password: password})
            .then((response) => {
                if (response.status == 200) {
                    commit('LOGIN', response.headers['authorization'])
                    resolve(response.status)
                } else {
                  reject(response.status)
                }
                
            }).catch((e) => { reject(e.status)})
        })
    },
    LOGOUT ({commit}) {
      commit('LOGOUT')
    },
  }
});