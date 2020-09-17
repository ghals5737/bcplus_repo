<template>
  <div id="SA">
    <br>
    <div class="container wholebox">
      <div class="bg-cover d-flex justify-content-end">
        <router-link
          :to="{ name: 'Agenda'}"
          class="bg-transparent mr-3 mt-3"
          style="box-shadow: none; text-decoration: none;"
        >
          <v-icon large>
            mdi-arrow-left-bold
          </v-icon>
        </router-link>
      </div>
      <section>
        <div class="p-3">
          <div class="mb-3 ml-3" style="height: 80px; width: 80px">
            <Gravatar :email="userId" />
          </div>
          <div v-if="userId" class="namearea mt-1 pl-3">
            <h4><strong id="SA" class="font-weight-bold h3">{{ userId }}</strong></h4>
          </div>
          <div v-else id="SA" class="namearea mt-1 pl-3">
            <h4><strong>GUEST USER</strong></h4>
          </div>
          <div class="profilearea pl-3 mb-0">
            <v-icon>mdi-cake-variant</v-icon> {{ this.birth.substring(0, 10) }}
          </div>
          <div class="profilearea pl-3 mb-3 mt-3">
            <button class="btn btn-primary" @click="EditMypage">프로필 수정하기</button>
          </div>
        </div>
      </section>
    </div>
    <br>
  </div>
</template>

<script>
import axios from 'axios';
import Gravatar from 'vue-gravatar'
import EditMypage from '@/components/EditMypage.vue'

let https = require('https')
const instance = axios.create({
  baseURL: 'http://52.14.190.92/api',
  timeout: 120000,
  httpsAgent: new https.Agent({
    rejectUnauthorized: false
  })
});

export default {
  name: 'MyPage',
  data: () => ({
  }),
  components: {Gravatar},
  methods: {
    EditMypage() {
      this.$modal.show(EditMypage, {
        modal: this.$modal},{
            height: 'auto'
      })
      this.$emit('close')
    }
  },
  created: function(){
    this.token=sessionStorage.getItem("access-token")
    this.userId=sessionStorage.getItem("username")
    instance.get('/member/'+ sessionStorage.getItem("username"))
        .then(res =>{
          sessionStorage.setItem("birth", res.data.birthDay)
    })
    this.birth=sessionStorage.getItem("birth")
    this.ggl=sessionStorage.getItem("isGoogle")
  }
}

</script>

<style scoped>
  div.mypage {
    border: 3px solid black;
    padding: 100px;
  }
  div.card-header {
    text-align: left;
  }
  h5.card-title {
    text-align: left;
  }
  .bg-cover {
    background-image: url(https://files.123freevectors.com/wp-content/original/110440-black-and-blue-blurred-background-vector.jpg);
    background-size: cover;
    height: 250px;
    border-top-left-radius: 10px 10px;
    border-top-right-radius: 10px 10px;
    width: 100%;
  }
  .settingbtn {
    float: right;
    margin: 10px;
    border: none;
    outline: none;
    background-color: rgba(0,0,0,0);
    padding: 5px;
    border-radius: 5px;
    cursor: pointer;
    color: gray;
  }
  .wholebox {
    width: 700px;
    border: 1px solid lightgray;
    padding: 0px;
    border-top-left-radius: 10px 10px;
    border-top-right-radius: 10px 10px;
    border-bottom-left-radius: 10px 10px;
    border-bottom-right-radius: 10px 10px;
  }
  .fsec {
    cursor: pointer;
  }
  .box {
    width: 50px;
    height: 50px; 
    border-radius: 70%;
    overflow: hidden;
  }
  .profile {
      width: 100%;
      height: 100%;
      object-fit: cover;
  }
  .namearea {
    text-align: left;
  }
  .profilearea {
    text-align: left;
  }

</style>