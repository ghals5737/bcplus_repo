<template>
  <v-app id="inspire" style="min-height: 100vh !important;">
    <div id="SA">
      <div class="container mt-5">
        <div class="row"></div>
        <div class="row ml-3">
          <div class="col-md-4">
            <div class="p-2 alert alert-secondary">
              <h3>Backlog</h3>
              <draggable class="list-group kanban-column" :list="this.TASKS" group="tasks">
                <div class="list-group-item d-flex p-0" v-for="element in this.TASKS" :key="element.id">
                  <v-card class="d-flex justify-content-end mr-2" :color="element.color" style="width: 5px; opacity: 0.7;"></v-card>
                  <div class="p-2 mt-1">
                    <h5>{{ element.title }}</h5>
                    <p v-if="element.group!=null">{{ element.group }}</p>
                    <p v-if="element.group==null">My Schedule</p>
                    <h6>{{ element.description }}</h6>
                    <div class="d-flex" style="flex-wrap: wrap;">
                      <div v-for="groupMem in element.members" :key="groupMem.id" class="mr-1">
                        <v-tooltip bottom>
                          <template v-slot:activator="{ on, attrs }">
                            <div v-bind="attrs" v-on="on"><Gravatar :email="groupMem" :size="20" /></div>
                          </template>
                          <span>{{ groupMem }}</span>
                        </v-tooltip>
                      </div>
                    </div>
                  </div> 
                </div>
              </draggable>
            </div>
          </div>
          <div class="col-md-4">
            <div class="p-2 alert alert-primary">
              <h3>In Progress</h3>
              <draggable class="list-group kanban-column" :list="arrInProgress" group="tasks">
                <div class="list-group-item d-flex p-0" v-for="element in arrInProgress" :key="element.id" style="color: #383d41;">
                  <v-card class="d-flex justify-content-end mr-2" :color="element.color" style="width: 5px; opacity: 0.7;"></v-card>
                  <div class="p-2 mt-1">
                    <h5>{{ element.title }}</h5>
                    <p v-if="element.group!=null">{{ element.group }}</p>
                    <p v-if="element.group==null">My Schedule</p>
                    <h6>{{ element.description }}</h6>
                    <div class="d-flex" style="flex-wrap: wrap;">
                      <div v-for="groupMem in element.members" :key="groupMem.id" class="mr-1">
                        <v-tooltip bottom>
                          <template v-slot:activator="{ on, attrs }">
                            <div v-bind="attrs" v-on="on"><Gravatar :email="groupMem" :size="20" /></div>
                          </template>
                          <span>{{ groupMem }}</span>
                        </v-tooltip>
                      </div>
                    </div>
                  </div>
                </div>
              </draggable>
            </div>
          </div>
          <div class="col-md-4">
            <div class="p-2 alert alert-success">
              <h3>Done</h3>
              <draggable class="list-group kanban-column" :list="arrDone" group="tasks">
                <div class="list-group-item d-flex p-0" v-for="element in arrDone" :key="element.id" style="text-decoration: line-through; color:gray;">
                  <v-card class="d-flex justify-content-end mr-2" :color="element.color" style="width: 5px; opacity: 0.7;"></v-card>
                  <div class="p-2 mt-1">
                    <h5>{{ element.title }}</h5>
                    <p v-if="element.group!=null">{{ element.group }}</p>
                    <p v-if="element.group==null">My Schedule</p>
                    <h6>{{ element.description }}</h6>
                    <div class="d-flex" style="flex-wrap: wrap;">
                      <div v-for="groupMem in element.members" :key="groupMem.id" class="mr-1">
                        <v-tooltip bottom>
                          <template v-slot:activator="{ on, attrs }">
                            <div v-bind="attrs" v-on="on"><Gravatar :email="groupMem" :size="20" /></div>
                          </template>
                          <span>{{ groupMem }}</span>
                        </v-tooltip>
                      </div>
                    </div>
                  </div>        
                </div>
              </draggable>
            </div>
          </div>
        </div>
      </div>
    </div>
  </v-app>
</template>

<script>
import draggable from 'vuedraggable'
import { mapGetters } from 'vuex'
import Gravatar from 'vue-gravatar'

export default {
  name: 'App',
  components: {
    draggable,
    Gravatar,
  },
  computed: {
    ...mapGetters(["TASKS"]),
    peopleOn() {
      const peopleOn = this.people
      return peopleOn
    },
  },
  data(){
    return {
      newTask: "",
      arrBacklog: [],
      arrInProgress:[],
      arrDone: [],
    }
  },
  methods: {
    add() {
      if(this.newTask) {
        this.arrBacklog.push({name: this.newTask})
        this.newTask = ""
      }
    },
  }
}
</script>

<style scoped>
.kanban-column {
  min-height: 300px;
}
</style>