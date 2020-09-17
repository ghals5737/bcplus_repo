<template>
  <div class="rounded border">
    <v-app id="inspire">
    <v-card
      width="344"
      class="mx-auto"
    >
      <v-app-bar
        dark
        :color="color"
        height="65px"
      >
      <v-list-item class="pl-0">
        <v-list-item-content>
          <v-list-item-title class="headline mt-1" style="font-size: 1rem !important; text-overflow:ellipsis;">{{ event.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
        <v-spacer></v-spacer>
      </v-app-bar>

      <v-card-text class="pb-0">
        {{ start }}  -  {{ this.printend }}
      </v-card-text>

      <div class="pt-0" v-for="group in this.TASKS" :key="group.sid">
        <v-card-text v-if="group.sid == event.extendedProps.sid">
          <div v-if="group.group">
            <p>
              <span class="font-weight-bold">{{ group.group }}</span> calendar
            </p>
          </div>
          <div v-else>
            <p>
              <span class="font-weight-bold">{{ event.extendedProps.group }}</span> 's personal calendar
            </p>
          </div>
        </v-card-text>
      </div>
  
      <v-card-text class="pb-2 pt-1">
        {{ event.extendedProps.description }}
      </v-card-text>
      
      <v-card-actions>
        <v-btn
          text
          color="deep-purple accent-4"
          @click="changeForm"
          v-if="this.master==this.userId && this.people!=null"
        >
          EDIT
        </v-btn>
        <v-btn
          text
          color="deep-purple accent-4"
          @click="deleteForm"
          v-if="this.master==this.userId && this.people!=null"
        >
          DELETE
        </v-btn>
        <v-spacer></v-spacer>
      </v-card-actions>
    </v-card>
  </v-app>
  </div>
</template>

<script>
import EditEventModal from '@/components/EditEventModal.vue'
import { mapGetters } from 'vuex'
import axios from 'axios'
let https = require('https')
const instance = axios.create({
  baseURL: 'http://52.14.190.92/api',
  timeout: 120000,
  httpsAgent: new https.Agent({
    rejectUnauthorized: false
  })
});

export default {
    data: () => ({
        title: "",
        description: "",
        start: {},
        end: {},
        realend: {},
        color: '',
        colors: ['#2196F3', '#3F51B5', '#673AB7', '#00BCD4', '#4CAF50', '#FF9800', '#F44336'],
        colorHex: null,
        // colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'red'],
        rstart: {},
        rend: {},
        dynamic: this.color,
        group: '',
        printend: '',
        people: null,
    }),
    // components: {Gravatar},
    methods: {
      changeForm() {
        this.$modal.show(EditEventModal, {
          group_name: this.group_name,
          id: this.event.id,
          title: this.title,
          description: this.description,
          color: this.colorHex[this.color],
          start: this.start,
          end: this.$moment(this.end).add(-1, 'days').format('YYYY-MM-DD'),
          rstart: this.start,
          rend: this.end,
          realend: this.$moment(this.end).format('YYYY-MM-DD'),
          selectgroup: this.selectgroup,
          colors: this.colors,
          event: this.event,
          modal: this.$modal}, {
            height: "auto"
        })
        this.$emit('close')
      },
      selectColor(color){
          this.color = color
      },

      deleteForm() {
        var con_test = confirm("Are you sure to delete this schedule?")
        if (con_test==true) {
          this.$store.dispatch("deleteschedule", {
            sid: this.event.extendedProps.sid
            })
        }
        this.$emit('close')

      }
    },
  props: {
    event: Object,
    groupInfo: Array,
    people: String,
    master: String,
    userId: String,
  },
    computed: {
      ...mapGetters(["EVENTS", "GROUPS", "USER", "group_name", "LUN_DAYS", "TASKS", "MEMBER_INFO" ]),
        foundKing() {
          let king
          for(var idx in this.GROUPS) {
            if(this.GROUPS[idx].groupName == this.people) {
              if(sessionStorage.getItem("username")==this.GROUPS[idx].groupMaster) {
                king = this.GROUPS[idx].groupMaster
              }
            }
          }
          return king
        },
  },
  mounted() {
      this.title = this.event.title;
      this.description = this.event.extendedProps.description;
      this.color = this.event.backgroundColor;
      this.start = this.$moment(this.event.start).format('YYYY-MM-DD');
      // render시 날짜표기수정
      this.realend = this.$moment(this.event.realend).format('YYYY-MM-DD');
      // DB에 넣을때(선택)     
      this.end = this.$moment(this.event.end).format('YYYY-MM-DD');
      this.printend = this.$moment(this.event.end).add(-1, 'days').format('YYYY-MM-DD');
      this.selectgroup = this.event.extendedProps.group;
  },
  created() {
    this.color = this.event.backgroundColor;
    this.start = this.$moment(this.event.start).format('YYYY-MM-DD');
    this.printend=this.$moment(this.event.end).add(-1, 'days').format('YYYY-MM-DD');
    this.realend = this.$moment(this.event.realend).add(-1, 'days').format('YYYY-MM-DD');
    this.token=sessionStorage.getItem("access-token")
    this.userId=sessionStorage.getItem("username")
    instance.get('/member/'+ sessionStorage.getItem("username"))
        .then(res =>{
          sessionStorage.setItem("userId", res.data.userId)
    })
    this.userId=sessionStorage.getItem("userId")

    this.colorHex = {
          '#2196F3': 'blue',
          '#3F51B5': 'indigo',
          '#673AB7': 'deep-purple',
          '#00BCD4': 'cyan',
          '#4CAF50': 'green',
          '#FF9800': 'orange',
          '#F44336': 'red',
        }
  }
};
</script>

<style scoped>
.color-picker {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}
.color-picker > .color {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1.5px solid whitesmoke;
  cursor: pointer;
}
.color.selected{
  box-shadow: 0 2px 3px 1px rgba(0, 0, 0, 0.2);
  border: 3px solid rgba(0, 0, 0, 0.4);
}
.color.red {
    background: #F44336;
}
.color.blue {
    background: #2196F3;
}
.color.orange {
    background: #FF9800;
}
.color.green {
    background: #4CAF50;
}
.color.indigo {
    background: #3F51B5;
}
.color.deep-purple {
    background: #673AB7;
}
.color.cyan {
    background: #00BCD4;
}
</style>
