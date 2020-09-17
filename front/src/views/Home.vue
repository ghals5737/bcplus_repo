<template>
  <div id="SA">
    <div id="Home">
      <div id="featured-wrapper">
        <div id="featured" class="container">
          <div class="major">
            <h2>비즈니스와 캘린더를 잇다</h2>
            <span class="byline">직장인을 위한 BUSINESS GROUP CALENDAR - BC+</span>
          </div>
        </div>
      </div>
      <div class="container mb-5">
        <div class="d-flex justify-content-between">
          <!-- <img class="homeimg" src="@/assets/cal1.gif"> -->
          <div class="container main-container">
          <h1 align="center">Login</h1>
            <div class="container">
              <!-- <enter event> -->
              <!-- <form action=""> -->
                  <div class="container">
                    
                      <div>
                          <input v-model="userId" type="text" placeholder="Enter Username" class="form-control" name="userId" required>
                      </div>
                      <div>
                          <input v-model="pw" type="password" placeholder="Enter Password" class="form-control" name="pw" required @keyup.up.enter="submit">
                      </div>
                      <button class="btn btn-light btn-lg mb-0" @click="login">login</button>
                      <div class="col four">
                        <a href="#" class="btn btn-google-plus btn-lg mt-0"><i class="fa fa-google"></i>  Sign in with Google</a>	
                      </div>
                  </div>
              <!-- </form> -->
            </div>
          <hr class="line1 mb-4">
          <div class="signupdiv">
            <p class="mb-0">New to BC+? Create an account.</p>
            <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#signupModal">signup</button>
          </div>
        </div>
        </div>
      </div>
      <div id="portfolio-wrapper">
        <div id="portfolio" class="container">
          <div id="page-wrapper">
            <div id="page" class="container d-flex justify-content-between">
              <div id="content">
                <div class="title">
                  <h2>Welcome to our website</h2>
                  <span class="byline">Mauris vulputate dolor sit amet nibh</span>
                </div>
                <p>This is <strong>Swarming</strong>, a free, fully standards-compliant CSS template designed by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>. The photos in this template are from <a href="http://fotogrph.com/"> Fotogrph</a>. This free template is released under the <a href="http://templated.co/license">Creative Commons Attribution</a> license, so you're pretty much free to do whatever you want with it (even use it commercially) provided you give us credit for it. Have fun :) </p>
              </div>
              <!-- <div id="sidebar"><a href="#" class="image image-full"><img class="homeimg" src="@/assets/cal1.gif" style="width: 500px;"></a></div> -->
            </div>
            </div>
        </div>
      </div>
      <div id="contact" class="container">
        <div id="portfolio" class="container">
          <div id="page-wrapper">
            <div id="page2" class="container d-flex justify-content-between">
              <!-- <div id="sidebar"><a href="#" class="image image-full"><img class="homeimg" src="@/assets/cal1.gif" style="width: 500px;"></a></div> -->
              <div id="content2">
                <div class="title">
                  <h2>Welcome to our website</h2>
                  <span class="byline">Mauris vulputate dolor sit amet nibh</span>
                </div>
                <p>This is <strong>Swarming</strong>, a free, fully standards-compliant CSS template designed by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>. The photos in this template are from <a href="http://fotogrph.com/"> Fotogrph</a>. This free template is released under the <a href="http://templated.co/license">Creative Commons Attribution</a> license, so you're pretty much free to do whatever you want with it (even use it commercially) provided you give us credit for it. Have fun :) </p>
              </div>
            </div>
            </div>
          </div>
      </div>
      <Modal />
  </div>
    </div>
</template>

<script>
import Modal from '@/components/modal.vue'

export default {
    name: 'Home',
    components: {
    Modal,
  },
    data(){
        return {
           birthDay: '',
           groups: [
              
            ],
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
    submit(){
      this.$store.dispatch("loginVuex", {
        userId: this.userId,
        pw: this.pw
      })
    },
    login() {     
      this.$store.dispatch("loginVuex", {
        userId: this.userId,
        pw: this.pw
      })
     // this.$router.push('/agenda')
    },
  }
}
</script>

<style scoped>
.homeimg {
  width: 800px;
  border-radius: 1%;
}

.main-container {
  width: 400px;
}

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
  /* color: white; */
  /* padding: 14px 20px; */
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

/* .btn {
  width: 300px;
  margin: 10px;
  color: white;
} */

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

div.col.four {
  padding-left: 0;
}

.signupdiv {
  padding-left: 35px;
}

#featured-wrapper
	{
		overflow: hidden;
		padding: 4em 0em;
    padding-bottom: 0px;
		background: #FFF;
	}

  #featured h2
	{
		text-align: center;
	}

  .major
	{
		overflow: hidden;
		margin-bottom: 4em;
		text-align: center;
	}
	
	.major h2
	{
		letter-spacing: 0.10em;
		text-transform: uppercase;
		font-size: 3em;
		font-weight: 700;
		color: #3f3f3f;
	}

	.major .byline
	{
		display: block;
		padding-top: 0.2em;
		font-weight: 300;
		font-size: 2em;
	}

  #portfolio-wrapper
	{
		padding: 8em 0em 6em 0em;
    background: #191919;
	}

  #portfolio
	{
		overflow: hidden;
		text-align: center;
	}

  #page-wrapper
	{
		background: #EEEEEE;
	}

	#page
	{
		overflow: hidden;
		padding: 6em 0em;
    background: #191919
	}

  #page2
	{
		overflow: hidden;
		padding: 6em 0em;
    background: white;
	}
	
  #content
	{
		float: left;
		width: 588px;
		text-align: center;
    color: white;
	}

  #content2
	{
		float: left;
		width: 588px;
		text-align: center;
    color: black;
	}
	
	#content .title
	{
		padding: 1em 2em 2em 2em;
		text-align: center;
	}
	
	#content h2
	{
		font-size: 2em;
	}
	
	#content .byline
	{
		padding-top: 0;
		font-size: 1.5em;
	}

  #contact {
    padding: 8em 0em 6em 0em;
    background: white;
  }

</style>