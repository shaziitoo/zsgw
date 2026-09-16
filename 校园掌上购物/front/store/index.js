// store.js
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    ws: null,
    letterWs: null,
  },
  mutations: {
    setWs(state, payload) {
      state.ws = payload;
    },
    setLetterWs(state, payload) {
      state.letterWs = payload
    },
  }
});

export default store;