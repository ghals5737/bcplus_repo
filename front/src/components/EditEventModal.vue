<template>
<v-app id="inspire">
  <div id="SA" class="rounded border">
      <fieldset>
        <v-card class="p-2">
          <v-container>
            <h4>Edit Schedule</h4>
            <v-text-field v-model="title_tmp" type="text" label="schedule"></v-text-field>
            <v-textarea v-model="description_tmp" type="textarea" label="detail"></v-textarea>
            <v-text-field v-model="start_tmp" type="date" label="start"></v-text-field>
            <v-text-field v-model="end_tmp" type="date" label="end"></v-text-field>
            <v-select
              :items="group_name"
              label="select group"
              v-model="selectgroup"
              item-value="group"
            >
            
              <v-chip v-if="group === 0">
                <span>{{ group }}</span>
              </v-chip>
            </v-select>
            <div class="color-picker">
                <div class="color" v-for="(theme, index) in colors" :key="index" @click="selectColor(theme)" :class="{selected: color_tmp === theme, [theme]: theme}"></div>
            </div>
            <div class="d-flex justify-content-end">
              <button class="btn text-primary" @click="updateEvent">Edit</button>
            </div>
          </v-container>
        </v-card>
      </fieldset>
  </div>
</v-app>
</template>

<script>
import swal from 'sweetalert'

export default {
    data: () => ({
        colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'red'],
        // colors: ['#2196F3', '#3F51B5', '#673AB7', '#00BCD4', '#4CAF50', '#FF9800', '#F44336'],
        group: '',
        title_tmp:'',
        description_tmp:'',
        start_tmp:'',
        end_tmp:'',
        color_tmp:'',
        
    }),
    
      created () {
        this.title_tmp=this.title
        this.description_tmp=this.description
        this.start_tmp=this.start
        this.end_tmp=this.end       
        if (this.color == '#2196F3' ) {
          this.color_tmp = 'blue'
        } else if (this.color == '#3F51B5') {
          this.color_tmp = 'indigo'
        } else if (this.color == '#673AB7' ) {
          this.color_tmp = 'deep-purple'
        } else if (this.color == '#00BCD4' ) {
          this.color_tmp = 'cyan'
        } else if (this.color == '#4CAF50' ) {
          this.color_tmp = 'green'
        } else if (this.color == '#FF9800' ) {
          this.color_tmp = 'orange'
        } else {
          this.color_tmp = 'red'
        }
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

        if (this.title_tmp == '') {
            swal('Warning', 'Please enter group name!', 'warning')
          } else if (this.selectgroup == null) {
            swal('Warning', 'Please select group!', 'warning')
          } else if (this.color_tmp == '') {
            swal('Warning', 'Please select schedule color!', 'warning')
          } else if (startDateCompare.getTime() >= endDateCompare.getTime() || dateValid == false) {
            swal('Warning', 'Please check the date form!', 'warning');
          } else {
          this.$store.dispatch("updateschedule", {
            id: this.id,
            title: this.title_tmp,
            description: this.description_tmp,
            start: this.$moment(this.start_tmp).add(-1, 'days').format('YYYY-MM-DD'),
            end: this.$moment(this.end_tmp).add(+1, 'days').format('YYYY-MM-DD'),
            rStart: this.start_tmp,
            rEnd: this.$moment(this.end_tmp).add(+1, 'days').format('YYYY-MM-DD'),
            realend: this.$moment(this.end_tmp).format('YYYY-MM-DD'),
            color: this.color_tmp,
            people: this.people,
          })
          swal('Success', 'Your request is approved', 'success')
          this.$emit('close')
        }
      },
      selectColor(color){
          this.color_tmp = color
      },
    
    },
  
  props: {
    id: String,
    title: String,
    description: String,
    color: String,
    start: String,
    end: String,
    rstart: String,
    rend: String,
    realend: String,
    group_name: Array,
    selectgroup: String,
    event: Object,
    people: String,
  },
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
