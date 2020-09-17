<template>
  <div id="App">
    <link src="@/assets/favicon/favicon.ico"/>
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<script>
export default {
  name: 'App', 
  components: {
  },
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  height: 100vh;
}
.app{
  width: 100vw;
  min-height: 100vh;
  background: #F3F3F3;
  color: #15202B;
}
.dark{
  background: #192734;
  color: #F3F3F3;
}
</style>
