<template>
  <div id="SA">
    <div id="app">
      <div class="container main-container">
        <h1>Login</h1>
        <div class="container">
          <div class="container">
            <div>
              <input v-model="name" type="text" placeholder="Enter ID" class="form-control" name="userId" required>
            </div>
            <div>
              <input v-model="pw" type="password" placeholder="Enter Password" class="form-control" name="pw" required>
            </div>
            <button class="btn btn-light btn-lg mb-0" @click="login">login</button>
            <div class="col four">
              <a href="#" class="btn btn-google-plus btn-lg mt-0"><i class="fa fa-google"></i>  Sign in with Google</a>	
            </div>
          </div>
        </div>
        <hr class="line1 mb-4">
        <div>
          <p class="mb-0">New to TimeTable? Create an account.</p>
          <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#signupModal">signup</button>
        </div>
      </div>
      <Modal />
    </div>
  </div>
</template>

<script>
import Modal from '@/components/modal.vue'

export default {
  name: 'Login',
  components: {
    Modal,
  },
  data(){
    return {
      birthDay: '',
      groups: [],
      id: {
        date: new Date(),
        timestamp: 0
      },
      name: '',
      pw: '',
      userId: '',
      token:'',
      isInit: false,
      isSignIn: false
    };
  },
  methods: {
    login() {
      if (this.userId == '') {
        alert('Please enter your ID')
      } else if (this.pw == '') {
        alert('Please enter your Password')
      } else {
        this.$store.dispatch("loginVuex", {
          userId: this.userId,
          pw: this.pw
        })
      }
    }
}}
</script>

<style scoped>
/* Full-width inputs */
input {
  width: 300px;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 300px;
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}

/* Add padding to containers */
.container {
  padding: 16px;
}

.main-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.line1 {
  height:1;
  width: 300px;
  margin: 0 auto;
}

.btn.btn-google-plus{
  margin: 8px 0;
  border-color: lightgray;
  cursor: pointer;
  width: 300px;
}

.btn.btn-google-plus:active{
  top: 3px; 
  outline: none; 
  -webkit-box-shadow: none; 
  box-shadow: none;
}

</style>