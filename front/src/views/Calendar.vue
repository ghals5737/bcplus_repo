
<template>
  <v-app id="inspire">
    <!-- right drawer: Calendar List -->
    <v-navigation-drawer
      v-model="drawerRight"
      app
      clipped
      right
    >
      <v-list dense>
        <v-list-item >
            <div class="mx-auto ">
            <h5>My calendars</h5>
            </div>
        </v-list-item>
      <!-- 그룹 추가 버튼 -->
          <v-btn
            class="mb-10"
            color="pink"
            fab
            dark
            small
            absolute
            bottom
            right
            data-toggle="modal"
            data-target="#addGroupModal"
          >
            <v-icon>mdi-plus</v-icon>
          </v-btn>



       <!-- 나중에 Data 들어오면 icon, calendar v-for로 다시 -->
       
        <v-list-item link>
          <div class="d-flex justify-content-between">
          <v-list-item-action 
          style="margin-right: 2px;"
          data-toggle="modal"
          data-target="#setGroupModal"
          >
            <v-icon>mdi-calendar-check</v-icon>
          </v-list-item-action>
          <v-list-item-action>
            <v-container fluid class="ml-4">
              <v-switch
                class="ma-2"
                v-model="people"
                label="Jiheyon"
                value="Jihyeon"
              ></v-switch>
            </v-container>
          </v-list-item-action>
          <!-- <v-list-item-content>
            <v-list-item-title class="ml-5">Jihyeon</v-list-item-title>
          </v-list-item-content> -->
          </div>
        </v-list-item>
       

        <v-list-item link>
          <v-list-item-action 
          style="margin-right: 2px;"
          data-toggle="modal"
          data-target="#setGroupModal"
          >
            <v-icon>mdi-calendar-check</v-icon>
          </v-list-item-action>
          <v-list-item-action>
            <v-container fluid  class="ml-4">
              <v-switch
                v-model="people"
                label="Group A"
                value="Group A"
              ></v-switch>
            </v-container>
          </v-list-item-action>
          <!-- <v-list-item-content>
            <v-list-item-title class="ml-5">Group-A</v-list-item-title>
          </v-list-item-content> -->
        </v-list-item>

        <v-list-item link>
          <v-list-item-action 
          style="margin-right: 2px;"
          data-toggle="modal"
          data-target="#setGroupModal"
          >
            <v-icon>mdi-calendar-check</v-icon>
          </v-list-item-action>
          <v-list-item-action>
            <v-container fluid class="ml-4">
              <v-switch
                v-model="people"
                label="Group B"
                value="Group B"
              ></v-switch>
            </v-container>
          </v-list-item-action>
          <!-- <v-list-item-content>
            <v-list-item-title class="ml-5">Group-B</v-list-item-title>
          </v-list-item-content> -->
        </v-list-item>
        
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      clipped-right
      color="blue-grey"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Calendar</v-toolbar-title>
      

      <!-- 계정 정보 -->
      <div 
        class="dropdown p-2 mr-5" 
        style="width: 200px; position: absolute; right: 0;"
        >
        <div class="d-flex" type="button" id="dropdownMenuButton" data-toggle="dropdown">
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/men/81.jpg">
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>Jeyeonkim</v-list-item-title>
            <!-- <v-list-item-subtitle>어쩌구저쩌구</v-list-item-subtitle> -->
          </v-list-item-content>
        </div>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <a class="dropdown-item" href="#">
            <router-link class="nav-link p-0 dropdown-item" :to="{ name: 'Mypage'}">MyPage</router-link>
          </a>
          <a class="dropdown-item" href="#">Logout</a>
        </div>
      </div>

      
    

      <v-spacer></v-spacer>
      <v-app-bar-nav-icon @click.stop="drawerRight = !drawerRight"></v-app-bar-nav-icon>
    </v-app-bar>

    <!-- left drawer: Tasks -->
    <v-navigation-drawer
      v-model="drawer"
      app
      clipped
      left
      class="p-2"
      width="300px"
    >
    
      
      <div class="mx-auto pb-2 d-flex justify-content-between">
        <div class="card p-2" style="width: 70%; border: none; text-align: center;">
          <h4 class="mb-0">Tasks</h4>
          
          <!-- Monthly 접근 -->
          <!-- <div 
          v-for="lunCal in lunCalList"
          :key = "lunCal._id"> -->
            <!-- Daily 접근 -->
            <!-- <div v-for="k in lunCal.day"
            :key="k">
              {{ k.solDay }}
            </div> -->
        </div>
        <!-- <div class="card bg-light p-2" style="width: 30%; text-align: center;">
          <v-btn
          data-toggle="modal"
          data-target="#addTaskModal"
          class="bg-transparent"
          style="box-shadow: none;"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        </div> -->
        <v-btn
        @click.stop="TaskDialog = true"
        class="bg-transparent mt-1"
        style="box-shadow: none;"
      >
        <v-icon>
          mdi-plus
        </v-icon>
      </v-btn>
      <v-dialog v-model="TaskDialog" max-width="500">
        <v-card>
          <v-container>
            <v-form @submit.prevent="addTask">
              <v-text-field v-model="name" type="text" label="schedule"></v-text-field>
              <v-textarea v-model="details" type="textarea" label="detail"></v-textarea>
              <v-text-field v-model="start" type="date" label="start"></v-text-field>
              <v-text-field v-model="end" type="date" label="end"></v-text-field>
              <v-select
                  :items="groups"
                  label="select group"
                  v-model="selectgroup"
                  item-value="group"
                >
                
                  <v-chip v-if="group === 0">
                    <span>{{ group }}</span>
                  </v-chip>
                </v-select>
              <div class="color-picker">
                <div class="color" v-for="(theme, index) in colors" :key="index" @click="selectColor(theme)" :class="{selected: color === theme, [theme]: theme}"></div>
              </div>
              <v-btn type="submit" color="success" class="mr-4" @click.stop="TaskDialog = false">
                submit
              </v-btn>
            </v-form>
          </v-container>
        </v-card>
      </v-dialog>
      </div>

        <v-card
          class="mx-auto mb-2"
          max-width="344"
          v-for="task in tasks" :key="task.id"
        >
          <div class="d-flex pt-2 justify-content-between">
            <v-card-title class="pt-0 pb-0">{{ task.name }}</v-card-title>
            <div class="d-flex">
              <v-btn 
                icon
                @click="addTaskToCal(task)"
              >
                <v-icon>mdi-pencil</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </div>
          </div>
          <v-card-subtitle class="pb-1">{{ task.details }}</v-card-subtitle>
          <div class="d-flex">
            <v-card-subtitle class="pt-0 pb-0 pt-2">{{ task.start }} - {{ task.end }}</v-card-subtitle>
            <v-card-actions>

              <v-spacer></v-spacer>

              <v-btn
                icon
                @click="show = !show"
                class="pb-3 ml-10"
              >
                <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
              </v-btn>
            </v-card-actions>
          </div>
          <v-expand-transition>
              <div v-show="show">
                <v-divider></v-divider>

                <v-card-text></v-card-text>
              </div>
            </v-expand-transition>
          
        </v-card>
      
    </v-navigation-drawer>

    <v-navigation-drawer
      v-model="left"
      fixed
      temporary
    ></v-navigation-drawer>

    <v-main>
      <v-container
        class="fill-height p-0"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col class="text-center p-0">


            <!-- calendar start -->

            <v-sheet height="64">
              <v-toolbar flat color="white" class="m-0">
                <v-btn outlined class="mr-4 ml-2" color="grey darken-2" @click="setToday">
                  Today
                </v-btn>
                <v-btn fab text small color="grey darken-2" @click="prev">
                  <v-icon small>mdi-chevron-left</v-icon>
                </v-btn>
                <v-btn fab text small color="grey darken-2" @click="next">
                  <v-icon small>mdi-chevron-right</v-icon>
                </v-btn>
                <v-toolbar-title v-if="$refs.calendar">
                  {{ $refs.calendar.title }}
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <!-- new event -->
      <v-btn
        @click.stop="dialog = true"
        class="bg-transparent"
        style="box-shadow: none;"
      >
        <v-icon>
          mdi-pencil
        </v-icon>
      </v-btn>
      <!-- v-dialog -->
      <v-dialog v-model="dialog" max-width="500">
        <v-card>
          <v-container>
            <v-form @submit.prevent="addEvent">
              <v-text-field v-model="name" type="text" label="schedule"></v-text-field>
              <v-textarea v-model="details" type="textarea" label="detail"></v-textarea>
              <v-text-field v-model="start" type="date" label="start"></v-text-field>
              <v-text-field v-model="end" type="date" label="end"></v-text-field>
              <!-- <label for="selectgroup">Select Group</label> -->
              <!-- <div class="form-group"> -->
                <v-select
                  :items="groups"
                  label="select group"
                  v-model="selectgroup"
                  item-value="group"
                >
                
                  <v-chip v-if="group === 0">
                    <span>{{ group }}</span>
                  </v-chip>
                </v-select>
              <!-- </div> -->
              <!-- <v-text-field v-model="color" label="color"></v-text-field> -->
              <div class="color-picker">
                <div class="color" v-for="(theme, index) in colors" :key="index" @click="selectColor(theme)" :class="{selected: color === theme, [theme]: theme}"></div>
              </div>
              <v-btn type="submit" color="success" class="mr-4" @click.stop="dialog = false">
                submit
              </v-btn>
            </v-form>
          </v-container>
        </v-card>
      </v-dialog>
      <!-- 참가자 목록 -->
      <v-btn
        data-toggle="modal"
        data-target="#memberListModal"
        class="bg-transparent"
        style="box-shadow: none;"
      >
        <v-icon>mdi-account-group</v-icon>
      </v-btn>
      <!-- new event -->
                <!-- <v-menu bottom right>
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      outlined
                      color="grey darken-2"
                      v-bind="attrs"
                      v-on="on"
                    >
                      <span>{{ typeToLabel[type] }}</span>
                      <v-icon right>mdi-menu-down</v-icon>
                    </v-btn>
                  </template>
                  <v-list>
                    <v-list-item @click="type = 'day'">
                      <v-list-item-title>Day</v-list-item-title>
                    </v-list-item>
                    <v-list-item @click="type = 'week'">
                      <v-list-item-title>Week</v-list-item-title>
                    </v-list-item>
                    <v-list-item @click="type = 'month'">
                      <v-list-item-title>Month</v-list-item-title>
                    </v-list-item>
                    <v-list-item @click="type = '4day'">
                      <v-list-item-title>4 days</v-list-item-title>
                    </v-list-item>
                  </v-list>
                </v-menu> -->
              </v-toolbar>
            </v-sheet>
            
            <v-sheet height="600">
              <v-calendar
                ref="calendar"
                v-model="focus"
                color="primary"
                :events="getevents"
                :event-more="false"
                :event-color="getEventColor"
                :type="type"
                @click:event="showEvent"
                @click:more="viewSchedule(calendar)"
                @change="updateRange"
                category-show-all
              >
              <template v-slot:event={event}>
                <span class="px-2 myevent"> {{ event.name }}</span>
              </template>
              <!-- <template #day><div class="day mr-10" style="font-size: small;">day-body</div></template>
              <template #day-label><div class="day-label mr-10" style="font-size: small;">day-label</div></template>
              <template #day-month><div class="day-month mr-10" style="font-size: small;">day-month</div></template> -->
              </v-calendar>
                
                <v-menu
                v-model="selectedOpen"
                :close-on-content-click="false"
                :activator="selectedElement"
                min-width="350px"
                offset-x
              >
              <v-card
                class="mx-auto"
                max-width="344"
              >


                <v-card-title v-html="selectedEvent.name"></v-card-title>

                <v-card-subtitle v-html="selectedEvent.details"></v-card-subtitle>

                <v-card-actions>
                  <!-- <v-btn icon>
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn> -->


                <v-btn
                  @click.stop="dialog1 = true"
                  class="bg-transparent"
                  style="box-shadow: none;"
                >
                  <v-icon>
                    mdi-pencil
                  </v-icon>
                </v-btn>
                <!-- v-dialog -->
                <v-dialog v-model="dialog1" max-width="500">
                  <v-card>
                    <v-container>
                      <v-form @submit.prevent="addEvent">
                        <v-text-field v-model="name" type="text" label="schedule"></v-text-field>
                        <v-textarea v-model="details" type="textarea" label="detail"></v-textarea>
                        <v-text-field v-model="start" type="date" label="start"></v-text-field>
                        <v-text-field v-model="end" type="date" label="end"></v-text-field>
                        <!-- <label for="selectgroup">Select Group</label> -->
                        <!-- <div class="form-group"> -->
                          <v-select
                            :items="groups"
                            label="select group"
                            v-model="selectgroup"
                            item-value="group"
                          >
                          
                            <v-chip v-if="group === 0">
                              <span>{{ group }}</span>
                            </v-chip>
                          </v-select>
                        <!-- </div> -->
                        <!-- <v-text-field v-model="color" label="color"></v-text-field> -->
                        <div class="color-picker">
                          <div class="color" v-for="(theme, index) in colors" :key="index" @click="selectColor(theme)" :class="{selected: color === theme, [theme]: theme}"></div>
                        </div>
                        <v-btn type="submit" color="success" class="mr-4" @click.stop="dialog1 = false">
                          submit
                        </v-btn>
                      </v-form>
                    </v-container>
                  </v-card>
                </v-dialog>














                  <v-btn icon>
                    <v-icon>mdi-delete</v-icon>
                  </v-btn>

                  <v-spacer></v-spacer>

                  <v-btn
                    icon
                    @click="show = !show"
                  >
                    <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                  </v-btn>
                </v-card-actions>

                <v-expand-transition>
                  <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text v-html="selectedEvent.details"></v-card-text>
                  </div>
                </v-expand-transition>
              </v-card>
              </v-menu>
              
            </v-sheet>
              

            <!-- calendar end -->


          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <v-navigation-drawer
      v-model="right"
      fixed
      right
      temporary
    ></v-navigation-drawer>

    <Modal :items2="items2"/>
  </v-app>
</template>

<script>
// import Bar from '../components/Bar.vue'
import Modal from '@/components/modal.vue'
// import lunCalList from "../assets/data/output.json"

  export default {
    name: 'Calendar',
    components: {
        // Bar,
        Modal,
    },
    props: {
      source: String,
    },


    data: () => ({
    //   lunCalList: lunCalList,
      calendargroup: null,
      people: null,
      groups: ['Group A', 'Group B', 'Jihyeon'],
      selectgroup: null,
      tasks: [{
        name: "business meeting",
        details: "with Kevin, Topaz Hall",
        start: "2020-08-24",
        end: "2020-08-24",
        color: 'red',
        group: 'Group B'
      }],
      show: false,
      focus: '',
      type: 'month',
      typeToLabel: {
        month: 'Month',
        week: 'Week',
        day: 'Day',
        '4day': '4 Days',
      },
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      events: [{
        name: "business meeting",
        details: "with Kevin, Topaz Hall",
        start: "2020-08-22",
        end: "2020-08-22",
        color: "orange",
        group: "Group A"
      },
      {
        name: "PM project Team Review",
        details: "need to ask for feedback",
        start: "2020-08-10",
        end: "2020-08-13",
        color: "green",
        group: "Group A"
      },
      {
        name: "Dinner w/ Jenny",
        details: "5694 3rd Ave, Ferndale",
        start: "2020-08-13",
        end: "2020-08-13",
        color: "pink",
        group: "Group B"
      },
      {
        name: "Starting Camp",
        details: "Don't forget to bring a kit",
        start: "2020-08-09",
        end: "2020-08-10",
        color: "cyan",
        group: "Jihyeon",
      }],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'red'],
      names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
      dialog: false,
      dialog1: false,
      TaskDialog: false,
      drawerRight: null,
      drawer: null,
      right: false,
      left: false,	
      items2: [		
      { picture: 28, text: '박현우' },		
      { picture: 38, text: '김지현' },		
      { picture: 48, text: '김지연' },		
      { picture: 58, text: '문지혁' },		
      { picture: 78, text: '이상호' },		
      { picture: 79, text: '황호민' },		
      ],	
      title: '',
      weekday: [0, 1, 2, 3, 4, 5, 6],
      weekdays: [
        { text: 'Sun - Sat', value: [0, 1, 2, 3, 4, 5, 6] },
        { text: 'Mon - Sun', value: [1, 2, 3, 4, 5, 6, 0] },
        { text: 'Mon - Fri', value: [1, 2, 3, 4, 5] },
        { text: 'Mon, Wed, Fri', value: [1, 3, 5] },
      ],
      value: '',
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth()+1,
      miniVariant: false,
      name: null,
      details: null,
      start: null,
      end: null,
      color: null,
    }),
    mounted() {
      this.$refs.calendar.checkChange()
    },

    methods: {
      changeToMyPage() {
      this.$emit('changeToMyPage')
      },
      onClickPrevMonth: function(month){
        month--;
        if(month<=0){
          this.currentMonth = 12;
          this.currentYear -= 1;
        }
        else{
          this.currentMonth -= 1;
        }
        this.init();
      },
      onClickNextMonth: function(month){
        month++;
        if(month>12){
          this.currentMonth = 1;
          this.currentYear += 1;
        }
        else{
          this.currentMonth += 1;
        }
        this.init();
      },

      async addEvent () {
        this.start = await new Date(this.start).toISOString().substring(0,10)
        this.end =  await new Date(this.end).toISOString().substring(0,10)
                  this.events.push({name: this.name,
                              details: this.details,
                              start: this.start,
                              end: this.end,
                             color: this.color,
                             group: this.selectgroup})


      },
      
      showEvent ({ nativeEvent, event }) {
          const open = () => {
            this.selectedEvent = event
            this.selectedElement = nativeEvent.target
            setTimeout(() => this.selectedOpen = true, 10)
          }
          if (this.selectedOpen) {
            this.selectedOpen = false
            setTimeout(open, 10)
          } else {
            open()
        }
        nativeEvent.stopPropagation()
      },
      updateRange ({ start, end }) {
        // You could load events from an outside source (like database) now that we have the start and end dates on the calendar
        this.start = start
        this.end = end
      },
      getEventColor (event) {
        return event.color
      },
      viewDay ({ date }) {
        this.focus = date
        this.type = 'day'
      },
      setToday () {
        this.focus = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      viewSchedule(calendar) {
        console.log(calendar)
      },
       selectColor(color){
          this.color = color
        },
      async addTask () {
        this.start = await new Date(this.start).toISOString().substring(0,10)
        this.end =  await new Date(this.end).toISOString().substring(0,10)
        this.tasks.push({name: this.name,
                    details: this.details,
                    start: this.start,
                    end: this.end,
                    color: this.color,
                    group: this.group})
      },
      async addTaskToCal(task){
        this.start = task.start
        this.end = task.end
        this.name = task.name
        this.details = task.details
        this.color = task.color
        this.group = task.group
        this.events.push({
          name: this.name,
          details: this.details,
          start: this.start,
          end: this.end,
          color: this.color,
          group: this.group
        })
      },
    },
    computed: {
      getevents(){
        return this.events.filter((event)=>{
        if (event.group == this.people){
          return event
        }
      })
      }
    },
  }
</script>

<style scoped>
.v-btn {
  outline:none;
}
.v-expansion-panel {
  box-shadow: none;
}
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
.dropdown-item:hover{
  background-color: lightgray;
}
.dropdown-item{
  color: black;
}
.v-menu__content {
  overflow-y: visible;
    overflow-x: visible;
    box-shadow: none;
    contain: inherit;
}
/* .v-calendar-weekly {
  display: grid;
} */
</style>

<style lang="scss">
.v-calendar {
  .v-event{
    margin-left: 4px;
  }
}
// .v-calendar-monthly.v-calendar-weekly.v-calendar.theme--light {
//   min-height: 500px;
// }
// .v-calendar.v-calendar-events {
//   .v-calendar-weekly__day {
//   overflow-x: auto !important;
//   overflow-y: visible !important;
//   // min-height: 500px;
//   }
// }
</style>