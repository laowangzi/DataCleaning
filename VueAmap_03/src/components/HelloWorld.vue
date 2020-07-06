<template>
  <div class="hello">
    <form >
      司机ID:<br>
      <input placeholder="请输入司机ID" type="text" v-model="DriverId">
      <br><br>
      <input type="submit" value="Submit" @click="SendId">
    </form>
  </div>
</template>

<script>
export default {
data(){
  return{
    DriverId:""
  }
},
  methods:{
  SendId(){
    this.$router.push('/ShowMarks/' + this.DriverId)
  }
  }
}
</script>

<style scoped>
</style>
