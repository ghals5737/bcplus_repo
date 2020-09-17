<template>
  <v-app id="inspire">
    <fieldset>
      <!-- Dark Theme -->
      <v-card id="SA" class="py-3 px-3 rounded border">
        <v-container id="SA">
          <h4>New Schedule</h4>
          <div class="small ml-3">
            Selected Group: {{ this.people }}
          </div>
          <v-text-field v-model="title" type="text" label="insert schedule title here"></v-text-field>
          <v-textarea v-model="description" type="textarea" label="insert schedule detail here"></v-textarea>
          <v-text-field v-model="start" type="date" label="start date"></v-text-field>
          <v-text-field v-model="rend" type="date" label="end date"></v-text-field>       
          <div class="color-picker">
            <div class="color" v-for="(theme, index) in colors" :key="index" @click="selectColor(theme)" :class="{selected: color === theme, [theme]: theme}"></div>
          </div>
          <div class="d-flex justify-content-end">
            <button class="btn text-primary" @click="updateEvent">Create</button>
          </div>
        </v-container>
      </v-card>
    </fieldset>    
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import $ from 'jquery'
import swal from 'sweetalert'

export default {
    name: "NewEventModal",
    computed: {
        ...mapGetters(["EVENTS", "GROUPS"]),
    },
    data: () => ({
      title: "",
      description: "",
      start: {},
      end: {},
      realend: {},
      color: '',
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'red'],
      group: '',
      selectgroup: '',
      rstart: {},
      rend: {},
    }),
    props: {
        start_cal: Date,
        end_cal: Date,
        event: Object,
        group_name: Array,
        people: String,
        goDark:Boolean,
    },
    methods: {
        updateEvent () {
          var startDateArr = this.start.split('-');
          var endDateArr = this.rend.split('-');
          var startDateCompare = new Date(startDateArr[0], parseInt(startDateArr[1])-1, startDateArr[2]);
          var endDateCompare = new Date(endDateArr[0], parseInt(endDateArr[1])-1, endDateArr[2]);
          let dateValid = true;
          if (this.start.substring(0, 4) < 1850 || this.rend.substring(0, 4) < 1850 || this.start.length > 10 || this.rend.length > 10) {
            dateValid = false;
          }
          if (this.title == '') {
            swal('Warning', 'Please enter group name!', 'warning')
          } else if (this.color == '') {
            swal('Warning', 'Please select schedule color!', 'warning')
          } else if (startDateCompare.getTime() > endDateCompare.getTime() || dateValid == false) {
            swal('Warning', 'Please check the date form!', 'warning');
          } else {
            this.rend = this.$moment(this.rend).add(+1, 'days').format('YYYY-MM-DD')
            this.$store.dispatch("createEvent", {
              id: (new Date()).getTime(),
              title: this.title,
              description: this.description,
              start: this.start,
              rStart: this.start,
              rEnd: this.rend,
              end: this.rend,
              realend: this.$moment(this.end).format('YYYY-MM-DD'),
              group: this.people,
              color: this.color,
              allDay:true,
            })
            this.$emit('close')
          }
        },
        selectColor(color){
          this.color = color
        }
    },
    mounted() {
      this.selectgroup = this.people;
      this.start = this.$moment(this.start_cal).format('YYYY-MM-DD');
      this.rend = this.$moment(this.end_cal).add(-1, 'days').format('YYYY-MM-DD');
    },
    created () {
      if(this.goDark){
        $('.v-card').css("color","red")
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