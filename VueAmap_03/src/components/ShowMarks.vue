<template>

  <div>
    <el-amap  id="map-test":vid="'amap-vue'"></el-amap>

  </div>

</template>



<script>

  import { lazyAMapApiLoaderInstance } from 'vue-amap'
  import axios from 'axios'

  export default {

    data() {

      return {

        center: [//点标记的坐标

        ],
        DriverId:""
      }

    },
    created() {

      const _this =this
      _this.DriverId=this.$route.params.DriverId
      axios.get('http://localhost:8181/Pos/FindPos',
        {params: { 'key': _this.DriverId }}).then(function(resp){_this.$set(_this.center,0,resp.data)})//=resp.data})


    },
    mounted:function (){
        this.SetMarks();

     /* lazyAMapApiLoaderInstance.load().then(() => {

        //map-test为div的id

        var map = new AMap.Map('map-test', {

          center: new AMap.LngLat(104.07, 30.67)// 地图中心点

        })

        //点标记
        console.log(this.center[0])
        this.center[0].forEach((center) => {

          new AMap.Marker({

            map: map,//放置点标记的地图

            position: [center.lag, center.lnt],

            title: '点标记'

          }).on('click', function() {

            alert('点击了标记')

          })

        })

      })   */

    }
      ,

    methods: {
     SetMarks(){
       lazyAMapApiLoaderInstance.load().then(() => {

         //map-test为div的id

         var map = new AMap.Map('map-test', {

           center: new AMap.LngLat(104.07, 30.67)// 地图中心点

         })

         //点标记
         console.log(this)

        this.center[0].forEach((center) => {
            console.log(center.id)
           new AMap.Marker({

             map: map,//放置点标记的地图

             position: [center.lag, center.lnt],

             title: '点标记'

           }).on('click', function() {

             alert('点击了标记')

           })

         })


       })
     }
    }

  }

</script>



<style scoped>

  #map-test{

    height:600px;

  }
  .Form{
    margin-left: 300px;
  }

</style>



