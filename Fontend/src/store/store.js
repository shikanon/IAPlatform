/**
 * Created by superman on 17/2/16.
 */
import Vuex from 'vuex'
import Vue from 'vue'
import * as types from './types'
Vue.use(Vuex);
export default new Vuex.Store({
    state: {
        user: {},
        token: null,
        title: null
    },
    mutations: {
        [types.LOGIN]: (state, data) => {
            localStorage.token = data;
            state.token = data;
        },
        [types.LOGOUT]: (state) => {
            localStorage.removeItem('token');
          localStorage.removeItem('title');
            state.token = null;
            state.title=null;
        },
        [types.TITLE]: (state, data) => {
           localStorage.title= data;
            state.title = data;
        }
    }
})
