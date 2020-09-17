<template>
<v-app id="inspire">
    <!-- Right Drawer: Calendar List -->

    <!-- Right Drawer(Dark Theme): START -->
    <v-navigation-drawer
      v-model="drawerRight"
      app
      clipped
      right
      id="SA"
      class="py-5"
      v-if="goDark"
      color="#131E2E"
    >
      <v-list dense>
        <v-list-item >
          <div class="mx-auto">
            <h4>Group List</h4>
          </div>
        </v-list-item>
        <v-list-item>
          <small class="mx-auto text-secondary">Select a group for schedule you want.</small>
        </v-list-item>

        <!-- 그룹 추가 버튼 (Right Drawer) -->
        <v-tooltip left>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              class="mb-10 mr-2"
              fab
              small
              absolute
              bottom
              right
              icon
              @click="addGroupModal"
              v-bind="attrs"
              v-on="on"
            ><v-icon medium>mdi-plus-thick</v-icon></v-btn>
          </template>
          <span id="SA">New Group</span>
        </v-tooltip>

        <div v-for="group in group_name" :key="group.id" class="ml-5">
          <!-- 그룹 선택 스위치 시작 -->
          <div class="d-flex justify-content-between">
            <v-list-item-action class="d-flex">
              <v-container fluid>
                <v-switch
                  inset
                  v-model="people"
                  :label="group"
                  :value="group"
                ></v-switch>
              </v-container>
            </v-list-item-action>
            <!-- 그룹 선택 스위치 끝 -->

            <!-- 그룹 정보 시작 -->
            <!-- 링크 활성화 -->
            <v-list-item-action
              v-if="people==group"
              class="d-flex mr-5 my-0"
              style="margin-right: 0px;"
              @click="GroupInfoModal"
            >
              <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    v-if="people==group" icon
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon>mdi-calendar-check</v-icon>
                  </v-btn>
                </template>
                <span id="SA">Group Info</span>
              </v-tooltip>
            </v-list-item-action>
            <!-- 그룹 정보 끝 -->
          </div>
        </div>
      </v-list>
    </v-navigation-drawer>
    <!-- Right Drawer(Dark Theme): END -->

    <!-- Right Drawer(Light Theme): START -->
    <v-navigation-drawer
      v-model="drawerRight"
      app
      clipped
      right
      id="SA"
      class="py-5"
      v-else
      color="#FBFCFE"
    >
      <v-list dense>
        <v-list-item >
          <div class="mx-auto">
            <h4>Group List</h4>
          </div>
        </v-list-item>
        <v-list-item>
          <small class="mx-auto text-secondary">Select a group for schedule you want</small>
        </v-list-item>
        <!-- 그룹 추가 버튼 -->
        <v-tooltip left>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              class="mb-10 mr-2"
              fab
              small
              absolute
              bottom
              right
              icon
              @click="addGroupModal"
              v-bind="attrs"
              v-on="on"
            >
              <v-icon medium>mdi-plus-thick</v-icon>
            </v-btn>
          </template>
          <span id="SA">New Group</span>
        </v-tooltip>

        <div v-for="group in group_name" :key="group.id" class="ml-5">

        <!-- 그룹 선택 스위치 시작 -->
          <div class="d-flex justify-content-between">
            <v-list-item-action class="d-flex">
              <v-container fluid>
                <v-switch
                  inset
                  v-model="people"
                  :label="group"
                  :value="group"
                ></v-switch>
              </v-container>
            </v-list-item-action>
            <!-- 그룹 선택 스위치 끝 -->

            <!-- 그룹 정보 시작 -->
            <!-- 링크 활성화 -->
            <v-list-item-action
              v-if="people==group"
              class="d-flex mr-5 my-0"
              style="margin-right: 0px;"
              @click="GroupInfoModal"
            >
              <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    v-if="people==group" icon
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon>mdi-calendar-check</v-icon>
                  </v-btn>
                </template>
                <span id="SA">Group Info</span>
              </v-tooltip>
            </v-list-item-action>
          </div>
          <!-- 그룹 정보 끝 -->
        </div>

      </v-list>
    </v-navigation-drawer>
    <!-- Right Drawer(Light Theme): END -->

    <v-app-bar
      app
      clipped-right
      clipped-left
      dark
      src="https://i.ibb.co/ydzrzGz/banner-bg-1-1.jpg"
    >
      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-app-bar-nav-icon
            v-bind="attrs"
            v-on="on"
            @click.stop="drawer = !drawer"
            class="mr-5"
          >
          </v-app-bar-nav-icon>
        </template>
        <span id="SA">ToDo List</span>
      </v-tooltip>
      
      <v-toolbar-title id="PG" class="pr-5 d-flex align-items-center">
        <h2><span class="text-danger font-weight-bold mr-2">BC+</span>Calendar</h2><v-icon small class="pb-4 ml-2">mdi-calendar-month</v-icon>
      </v-toolbar-title>

      <!-- Dark Theme Switch -->
      <div id="SA">
        <v-switch :label="`Dark Theme`" v-model="goDark" class="darkswitch"></v-switch>
      </div>

      <v-tooltip right>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            v-bind="attrs"
            v-on="on"
            icon
            small
            @click="helphelp"
          >
            <v-icon small>mdi-help</v-icon>
          </v-btn>
        </template>
        <span id=SA>How to use?</span>
      </v-tooltip>

      <div
        class="dropdown p-2 mr-5 d-flex justify-content-center" 
        style="width: 200px; position: absolute; right: 0;"
        id="SA"
        >
        <div class="d-flex" type="button" id="dropdownMenuButton" data-toggle="dropdown">
          <v-list-item-avatar>
            <Gravatar :email="userId" />
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title id="NS" class="h5 text-white mr-10 my-0" style="max-width: 120px; display: block; overflow: hidden; text-overflow:ellipsis;">{{ this.userName }}</v-list-item-title>
          </v-list-item-content>
        </div>

        <!-- Account Drop Down: START -->
        <!-- Dropdown Menu: Dark Theme -->
        <div v-if="goDark" class="dropdown-menu rounded" aria-labelledby="dropdownMenuButton" style="background-color: #131E2E; border: solid white 1px;">
          <button class="btn dropdown-item text-primary" @click="MypageModal">My Page</button>
          <button class="btn dropdown-item text-danger" @click="logout">Logout</button>
        </div>
        <!-- Dropdown Menu: Light Theme -->
        <div v-else class="dropdown-menu rounded" aria-labelledby="dropdownMenuButton" style="border: solid 1px">
          <button class="btn dropdown-item text-primary" @click="MypageModal">My Page</button>
          <button class="btn dropdown-item text-danger" @click="logout">Logout</button>
        </div>
        <!-- Account Drop Down: END -->
      </div>

      <v-spacer></v-spacer>

      <div class="d-flex justify-content-end">
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-app-bar-nav-icon
            v-bind="attrs"
            v-on="on"
            @click.stop="drawerRight = !drawerRight"
            >
            </v-app-bar-nav-icon>
          </template>
          <span id=SA>Group List</span>
        </v-tooltip>
      </div>
    </v-app-bar>
    <!-- left drawer: Tasks -->

    <!-- Left Drawer(Dark Theme): START -->
    <v-navigation-drawer
      v-model="drawer"
      app
      clipped
      left
      class="p-3"
      id="SA"
      width="300px"
      v-if="goDark"
      color="#131E2E"
    >
      <div class="mx-auto my-5 pb-2 d-flex justify-content-between align-items-center">
        <div class="d-flex">
          <!-- task title -->
          <div v-if="this.people!=null">
            <h4 class="ml-4 mb-0"><v-icon class="mr-4">mdi-clipboard-list-outline</v-icon>ToDo List</h4>
            <small class="ml-15">Selected Group: <span class="font-weight-bold">{{this.people}}</span></small>
          </div>
          <div v-else>
            <h4 id="SA" class="ml-4 mb-0"><v-icon class="mr-4">mdi-clipboard-list-outline</v-icon>My ToDo List</h4>
          </div>
        </div>

      <!-- 왼쪽 Drawer 카드 시작 -->
      <v-dialog v-model="TaskDialog" max-width="500">
        <v-card id="SA" class="py-3 px-3">
          <v-container>
            <v-form @submit.prevent="addTask">
              <v-text-field v-model="name" type="text" label="insert schedule title here"></v-text-field>
              <v-textarea v-model="details" type="textarea" label="insert schedule detail here"></v-textarea>
              <v-text-field v-model="start" type="date" label="start date"></v-text-field>
              <v-text-field v-model="end" type="date" label="end date"></v-text-field>
              <div class="color-picker">
                <div class="color" v-for="(theme, index) in colors" :key="index" @click="selectColor(theme)" :class="{selected: color === theme, [theme]: theme}"></div>
              </div>
              <div class="d-flex justify-content-end">
                <button class="btn text-danger mr-2" @click.stop="TaskDialog = false">Cancle</button>
                <button class="btn text-primary" @click.stop="TaskDialog = false">Create</button>
              </div>
            </v-form>
          </v-container>
        </v-card>
      </v-dialog>
      </div>

      <!-- Task Card: START -->
      <v-expansion-panels class="mb-6" v-if="this.TASKS">
        <div
          v-for="group in this.GROUPS" 
          :key="group.groupName"
          class="mb-2 rounded"
        >
          <div v-if="peopleOn == group.groupName">
            <v-icon class="text-warning">mdi-crown</v-icon><span class="text-warning">Master</span>  {{group.groupMaster}}
            <br><small class="text-secondary">Past group schedules are not display here.</small>
          </div>
        </div>
        <v-expansion-panel
        @click="getMember"
          v-for="task in this.TASKS" 
          :key="task.sid"
          class="mb-2 rounded"
        >
          <div class="ml-3 my-1 small" v-if="!peopleOn">
            <div v-if="task.group">
              Group: {{task.group}}
            </div>
            <div v-else>
              My Private Schedule
            </div>
          </div>
          
          <v-expansion-panel-header :color="task.color" v-if="sid=task.sid" expand-icon="mdi-menu-down">
            <span v-if="task.status=='done'" class="task_title py-1" style="width: 150px; text-decoration: line-through white double"><strong>{{ task.title }}</strong></span>
            <span v-else class="task_title py-1" style="width: 150px;"><strong>{{ task.title }}</strong></span>
            <div class="small">
              <strong>Month:</strong>
              <strong>{{task.end.substring(5,7)}}</strong>
            </div>
          </v-expansion-panel-header>
          <v-expansion-panel-content v-if="task.sid">
            <br>
            <div v-if="task.status=='done'" style="text-decoration: line-through white">
              <ul class="p-0">{{ $moment(task.start).format('YYYY-MM-DD') }} - {{ $moment(task.end).add(-1, 'days').format('YYYY-MM-DD') }}</ul>
              <ul class="task_description p-0">Memo: {{ task.description }}</ul>
              <ul class="p-0" v-if="task.group!=null">Group: {{ task.group }}</ul>
            </div>
            <div v-else>
              <ul class="p-0">{{ $moment(task.start).format('YYYY-MM-DD') }} - {{ $moment(task.end).add(-1, 'days').format('YYYY-MM-DD') }}</ul>
              <ul class="task_description p-0">Memo: {{ task.description }}</ul>
              <ul class="p-0" v-if="task.group!=null">Group: {{ task.group }}</ul>
            </div>
            <hr>
            <div class="d-flex" style="flex-wrap: wrap;">
              <div class="p-0 mr-1" v-for="mem in task.members" :key="mem.id">
                <v-tooltip bottom>
                  <template v-slot:activator="{ on, attrs }">
                    <div v-bind="attrs" v-on="on"><Gravatar :email="mem" :size="20" /></div>
                  </template>
                  <span>{{ mem }}</span>
                </v-tooltip>
              </div>
            </div>
            <ul class="d-flex justify-content-end" v-if="foundKing">
              <v-row justify="end">
                <v-tooltip right>
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      @click.stop="dialog = true"
                      fab
                      x-small
                      icon
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-icon>
                        mdi-account-plus
                      </v-icon>
                    </v-btn>
                  </template>
                  <span id="SA">Allocate Schedule</span>
                </v-tooltip>
                <v-dialog
                  v-model="dialog"
                  max-width="500"
                  max-height="500"
                >
                  <v-card class="rounded border p-2">
                    <v-col cols="12">
                      <legend id="SA">Allocate Schedule</legend>
                        <v-col id= "SA" cols="12">
                          <v-autocomplete           
                            v-model="friends"
                            :disabled="false"
                            :items="member_info"
                            filled
                            chips
                            label="Select"
                            item-text="name"
                            item-value="name"
                            multiple
                          >
                            <template v-slot:selection="data">
                              <v-chip
                                id="SA"
                                v-bind="data.attrs"
                                :input-value="data.selected"
                                close
                                @click="data.select"
                                @click:close="remove(data.item)"
                              >
                                <v-avatar left>
                                  <Gravatar :email="data.item.name" />
                                </v-avatar>
                                {{ data.item.name }}
                              </v-chip>                          
                            </template>               
                            <template v-slot:item="data">                 
                              <template v-if="typeof data.item !== 'object'">
                                <v-list-item-content v-text="data.item"></v-list-item-content>                  
                              </template>
                              <template v-else>
                                <v-list-item-avatar>
                                  <Gravatar :email="data.item.name" />
                                </v-list-item-avatar>
                                <v-list-item-content>
                                  <v-list-item-title v-html="data.item.name"></v-list-item-title>
                                  <v-list-item-subtitle v-html="data.item.group"></v-list-item-subtitle>
                                </v-list-item-content>
                              </template>                                  
                            </template>                             
                          </v-autocomplete>
                        </v-col>
                    </v-col>
                    <v-card-actions id="SA" class="mr-5">
                      <v-spacer></v-spacer>
                      <button class="btn text-danger mr-2" @click="dialog = false">Cancle</button>
                      <button class="btn text-primary" @click="addMem({friends: friends, sid: task.sid}); dialog = false;">Allocate</button>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </ul>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    <!-- Task Card: END -->
    </v-navigation-drawer>
    <!-- Left Drawer(Dark Theme): END -->


    <!-- Left Drawer(Light Theme): START -->
    <v-navigation-drawer
      v-model="drawer"
      app
      clipped
      left
      class="p-3"
      id="SA"
      width="300px"
      v-else
      color="#FBFCFE"
    >
      <div class="mx-auto my-5 pb-2 d-flex justify-content-between align-items-center">
        <div class="d-flex">
          <!-- task title -->
          <div v-if="this.people!=null">
            <h4 class="ml-4 mb-0"><v-icon class="mr-4">mdi-clipboard-list-outline</v-icon>ToDo List</h4>
            <small class="ml-15">Selected Group: <span class="font-weight-bold">{{this.people}}</span></small>
          </div>
          
          <div v-else>
            <h4 id="SA" class="ml-4 mb-0"><v-icon class="mr-4">mdi-clipboard-list-outline</v-icon>My ToDo List</h4>
          </div>
        </div>

      <!-- 왼쪽 Drawer 카드 시작 -->
      <v-dialog v-model="TaskDialog" max-width="500">
        <v-card id="SA" class="py-3 px-3">
          <v-container>
            <v-form @submit.prevent="addTask">
              <v-text-field v-model="name" type="text" label="insert schedule title here"></v-text-field>
              <v-textarea v-model="details" type="textarea" label="insert schedule detail here"></v-textarea>
              <v-text-field v-model="start" type="date" label="start date"></v-text-field>
              <v-text-field v-model="end" type="date" label="end date"></v-text-field>
              <div class="color-picker">
                <div class="color" v-for="(theme, index) in colors" :key="index" @click="selectColor(theme)" :class="{selected: color === theme, [theme]: theme}"></div>
              </div>
              <div class="d-flex justify-content-end">
                <button class="btn text-danger mr-2" @click.stop="TaskDialog = false">Cancle</button>
                <button class="btn text-primary" @click.stop="TaskDialog = false">Create</button>
              </div>
            </v-form>
          </v-container>
        </v-card>
      </v-dialog>
      </div>
      <v-expansion-panels class="mb-6" v-if="this.TASKS">
      <div
        v-for="group in this.GROUPS" 
        :key="group.groupName"
        class="mb-2 rounded"
      >
        <div v-if="peopleOn == group.groupName">
          <v-icon class="text-warning">mdi-crown</v-icon><span class="text-warning">Master</span>  {{group.groupMaster}}
          <br><small class="text-secondary">Past group schedules are not display here</small>
        </div>
      </div>
      <v-expansion-panel
       @click="getMember"
        v-for="task in this.TASKS" 
        :key="task.sid"
        class="mb-2 rounded"
      >
        <div class="ml-3 my-1 small" v-if="!peopleOn">
          <div v-if="task.group">
            Group: {{task.group}}
          </div>
          <div v-else>
            My Private Schedule
          </div>
        </div>
        
        <v-expansion-panel-header :color="task.color" v-if="sid=task.sid" expand-icon="mdi-menu-down">
          <span v-if="task.status=='done'" class="task_title py-1" style="width: 150px; text-decoration: line-through black double;"><strong>{{ task.title }}</strong></span>
          <span v-else class="task_title py-1" style="width: 150px;"><strong>{{ task.title }}</strong></span>
          <div class="small">
            <strong>Month:</strong>
            <strong>{{task.end.substring(5,7)}}</strong>
          </div>
        </v-expansion-panel-header>
        <v-expansion-panel-content v-if="task.sid">
          <br>
          <div v-if="task.status=='done'" style="text-decoration: line-through black;">
            <ul class="p-0">{{ $moment(task.start).format('YYYY-MM-DD') }} - {{ $moment(task.end).add(-1, 'days').format('YYYY-MM-DD') }}</ul>
            <ul class="task_description p-0">Memo: {{ task.description }}</ul>
            <ul class="p-0" v-if="task.group!=null">Group: {{ task.group }}</ul>
          </div>
          <div v-else>
            <ul class="p-0">{{ $moment(task.start).format('YYYY-MM-DD') }} - {{ $moment(task.end).add(-1, 'days').format('YYYY-MM-DD') }}</ul>
            <ul class="task_description p-0">Memo: {{ task.description }}</ul>
            <ul class="p-0" v-if="task.group!=null">Group: {{ task.group }}</ul>
          </div>
          <hr>
          <div class="d-flex" style="flex-wrap: wrap;">
            <div class="p-0 mr-1" v-for="mem in task.members" :key="mem.id">
              <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                  <div v-bind="attrs" v-on="on"><Gravatar :email="mem" :size="20" /></div>
                </template>
                <span>{{ mem }}</span>
              </v-tooltip>
            </div>
          </div>
          
          <ul class="d-flex justify-content-end" v-if="foundKing">
            <v-row justify="end">
              <v-tooltip right>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    @click.stop="dialog = true"
                    fab
                    x-small
                    icon
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon>
                      mdi-account-plus
                    </v-icon>
                  </v-btn>
                </template>
                <span id="SA">Allocate Schedule</span>
              </v-tooltip>
              <v-dialog
                v-model="dialog"
                max-width="500"
                max-height="500"
              >
                <v-card>
                  <v-col cols="12">
                    <legend id="SA">Allocate Schedule</legend>
                      <v-col id= "SA" cols="12">
                      <v-autocomplete           
                        v-model="friends"
                        :disabled="false"
                        :items="member_info"
                        filled
                        chips
                        label="Select"
                        item-text="name"
                        item-value="name"
                        multiple
                      >
                        <template v-slot:selection="data">
                          <v-chip
                            id="SA"
                            v-bind="data.attrs"
                            :input-value="data.selected"
                            close
                            @click="data.select"
                            @click:close="remove(data.item)"
                          >
                            <v-avatar left>
                              <Gravatar :email="data.item.name" />
                            </v-avatar>
                            {{ data.item.name }}
                          </v-chip>                          
                        </template>               
                        <template v-slot:item="data">                 
                          <template v-if="typeof data.item !== 'object'">
                            <v-list-item-content v-text="data.item"></v-list-item-content>                  
                          </template>
                          <template v-else>
                            <v-list-item-avatar>
                              <Gravatar :email="data.item.name" />
                            </v-list-item-avatar>
                            <v-list-item-content>
                              <v-list-item-title v-html="data.item.name"></v-list-item-title>
                              <v-list-item-subtitle v-html="data.item.group"></v-list-item-subtitle>
                            </v-list-item-content>
                          </template>                                  
                        </template>                             
                      </v-autocomplete>
                    </v-col>
                  </v-col>
                  <v-card-actions id="SA" class="mr-5">
                    <v-spacer></v-spacer>
                    <button class="btn text-danger mr-2" @click="dialog = false">Cancle</button>
                    <button class="btn text-primary" @click="addMem({friends: friends, sid: task.sid}); dialog = false;">Allocate</button>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </ul>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>

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
            <!-- <v-sheet height="600"> -->
                <div id='SA' v-if="switching" class='demo-app'>
                  <div class='demo-app-main'>
                    <FullCalendar
                        ref="fullCalendar"
                        class='demo-app-calendar'
                        :options='config'
                    >
                    </FullCalendar>
                  </div>
                </div>

                <div id='SA' v-if="!switching" class='demo-app mr-4' style="display: block;">
                  <div style="height: 30px;">
                    <button class="ml-4" style="height: 30px;" @click="switchingcal"><v-icon large>mdi-calendar</v-icon></button>
                  </div>
                  <div id="SA" style="width: 100%;">
                    <div class="container mt-3">
                    <div class="row">
                    </div>
                    <div class="row ml-3">
                      <div class="col-md-4">
                        <div class="p-2 alert alert-secondary" style="opacity: 0.9;">
                          <h3>Todo</h3>
                          <hr>
                          <draggable @change="backlogdnd" class="list-group kanban-column" :list="this.TASKS" group="tasks">
                            <div class="list-group-item d-flex p-0 b-0 m-0" v-for="element in this.TASKS" :key="element.id">
                              <div class="d-flex" v-if="element.status=='backlog'">
                                <v-card class="d-flex justify-content-end mr-2" :color="element.color" style="width: 5px; opacity: 0.7;"></v-card>
                                <div class="p-2 mt-1">
                                    <h5>{{ element.title }}</h5>
                                    <p class="m-0" v-if="element.group!=null">{{ element.group }}</p>
                                    <p class="m-0" v-if="element.group==null">My Schedule</p>
                                    <p class="mb-1">{{ $moment(element.start).format('YYYY-MM-DD') }} - {{ $moment(element.end).add(-1, 'days').format('YYYY-MM-DD') }}</p>
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
                            </div>
                          </draggable>
                        </div>
                      </div>

                      <div class="col-md-4">
                        <div class="p-2 alert alert-primary" style="opacity: 0.9;">
                          <h3>In Progress</h3>
                          <hr>
                          <draggable @change="inprogressdnd" class="list-group kanban-column" :list="this.TASKS" group="tasks">
                            <div class="list-group-item d-flex p-0 b-0 m-0" v-for="element in this.TASKS" :key="element.id" style="color: #383d41;">
                              <div class="d-flex" v-if="element.status=='inprogress'">  
                                <v-card class="d-flex justify-content-end mr-2" :color="element.color" style="width: 5px; opacity: 0.7;"></v-card>
                                <div class="p-2 mt-1">
                                    <h5>{{ element.title }}</h5>
                                    <p class="m-0" v-if="element.group!=null">{{ element.group }}</p>
                                    <p class="m-0" v-if="element.group==null">My Schedule</p>
                                    <p class="mb-1">{{ $moment(element.start).format('YYYY-MM-DD') }} - {{ $moment(element.end).add(-1, 'days').format('YYYY-MM-DD') }}</p>
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
                            </div>
                          </draggable>
                        </div>
                      </div>

                      <div class="col-md-4">
                        <div class="p-2 alert alert-success" style="opacity: 0.9;">
                          <h3>Done</h3>
                          <hr>
                          <draggable @change="donednd" class="list-group kanban-column" :list="this.TASKS" group="tasks">
                            <div class="list-group-item d-flex p-0 b-0 m-0" v-for="element in this.TASKS" :key="element.id" style="text-decoration: line-through; color:gray;">
                              <div class="d-flex" v-if="element.status=='done'">  
                                <v-card class="d-flex justify-content-end mr-2" :color="element.color" style="width: 5px; opacity: 0.7;"></v-card>
                                <div class="p-2 mt-1">
                                  <h5>{{ element.title }}</h5>
                                  <p class="m-0" v-if="element.group!=null">{{ element.group }}</p>
                                  <p class="m-0" v-if="element.group==null">My Schedule</p>
                                  <p class="mb-1">{{ $moment(element.start).format('YYYY-MM-DD') }} - {{ $moment(element.end).add(-1, 'days').format('YYYY-MM-DD') }}</p>
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
                            </div>
                          </draggable>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              
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

  <v-footer
    padless
    v-if="goDark"
  >
    <v-card
      class="flex"
      flat
      tile
      color="#131E2E"
    >
      <v-card-text class="py-2 text-center">
        <strong>ⓒ{{ new Date().getFullYear() }} BC+ All Rights Reserved.</strong>
      </v-card-text>
    </v-card>
  </v-footer>
  <v-footer
    padless
    v-else
  >
    <v-card
      class="flex"
      flat
      tile
      color="#FBFCFE"
    >
      <v-card-text class="py-2 text-center">
        <strong>ⓒ{{ new Date().getFullYear() }} BC+ All Rights Reserved.</strong>
      </v-card-text>
    </v-card>
  </v-footer>
  </v-app>

</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import googleCalendarPlugin from '@fullcalendar/google-calendar';

import { mapGetters } from 'vuex'

import EventDetailModal from '@/components/EventDetailModal'
import NewEventModal from '@/components/NewEventModal'
import AddGroupModal from '@/components/AddGroupModal.vue'
import AddMembersModal from '@/components/AddMembersModal.vue'
import GroupInfoModal from '@/components/GroupInfoModal.vue'
import MypageModal from '@/components/MypageModal.vue'
import swal from 'sweetalert'
import helphelp from '@/components/helphelp.vue'
import draggable from 'vuedraggable'

import { BPopover } from 'bootstrap-vue'
import Gravatar from 'vue-gravatar'

import $ from 'jquery'
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
    name: "Agenda",
    components: {
        FullCalendar,        
        Gravatar,
        draggable,
    },
    props: {
      source: String,
    },  
    computed: {
        ...mapGetters(["EVENTS", "GROUPS", "USER", "group_name", "LUN_DAYS", "TASKS", "MEMBER_INFO" ]),

        peopleOn() {
          const peopleOn = this.people
          return peopleOn
        },

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

        matchingScheduleGroup() {
          let scheduleGroup
          for(var i in this.EVENTS) {
            for(var j in this.TASKS) {
              if(this.EVENTS[i].sid == this.TASKS[j].sid) {
                scheduleGroup = this.EVENTS[i].group
              }
            }
          }
          return scheduleGroup
        },

        changeFilter() {              
          return this.EVENTS.filter((event) => {            
            if ( event.group == this.people || event.color == "transparent"){    
              return event
            } else if (this.people == null ){
              return this.EVENTS
            }
          })
        },

        config() {
          return {
            ...this.configOptions,
            ...this.eventHandlers,
          }
        },

        configOptions () {
          return {
            editable: true,
            selectable: true,
            dayMaxEvents: 3,
            events: this.changeFilter,
            weekends: true,
            plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin, googleCalendarPlugin],
            headerToolbar: {
              left: 'prev,next today',
              center: 'title',
              right: 'GoogleBtn btn2',
            },
            allDaySlot: true,
            eventDrop: this.reSchedule,
            eventResize: this.resizeEvent,
            select: this.handleSelect,
            eventClick: this.handleEventClick,
            displayEventTime: false,
            googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",
             customButtons: { 
              GoogleBtn: {
                text: '',
                click: ()=> {                  
                  this.googleCal()
                },
              },
              btn2: {
                text: '',
                click: () => {
                  this.switching = !this.switching
                },
              },
               prev: { // this overrides the prev button
          text: "PREV",
          click: () => {
            this.$store.commit("monthMinus")
            if(this.people===null){                
              this.$store.dispatch("updateMySchedule", {people: sessionStorage.getItem("username")})
            }else{
              this.$store.dispatch("updateSchedule", {people: this.people}).then(()=>{
              this.member_info=this.MEMBER_INFO;         
            });
            }    
            let calendarApi = this.$refs.fullCalendar.getApi();
            calendarApi.prev();
            this.$store.dispatch("getLunInfo").then(()=>{
             
        this.LUN_DAYS.forEach(el=>{
          $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').children('.holiday-text').remove()
       $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').prepend("<div class='holiday-text'>"+el.lunDay+"</ div>");
        })
       })
        this.$store.dispatch("getHolidayInfo")
        this.changeNumColor();   
          }
        },
        next: { // this overrides the next button
          text: "next",
          click: () => {
            this.$store.commit("monthAdd")
            if(this.people===null){                 
              this.$store.dispatch("updateMySchedule", {people: sessionStorage.getItem("username")})
            }else{
              this.$store.dispatch("updateSchedule", {people: this.people}).then(()=>{
              this.member_info=this.MEMBER_INFO;         
            });
            }    
            let calendarApi = this.$refs.fullCalendar.getApi();
            calendarApi.next();
            this.$store.dispatch("getLunInfo").then(()=>{
               
        this.LUN_DAYS.forEach(el=>{
           $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').children('.holiday-text').remove()
       $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').prepend("<div class='holiday-text'>"+el.lunDay+"</ div>");
        })
       })
        this.changeNumColor();
           this.$store.dispatch("getHolidayInfo")   
          }
        },
        today: { // this overrides the next button
          text: "Today",
          click: () => {
            this.$store.commit("monthInit")
           if(this.people===null){                   
              this.$store.dispatch("updateMySchedule", {people: sessionStorage.getItem("username")})
            }else{
              this.$store.dispatch("updateSchedule", {people: this.people}).then(()=>{
                 this.member_info=this.MEMBER_INFO;         
               });
            }    
            let calendarApi = this.$refs.fullCalendar.getApi();
            calendarApi.today();
            this.$store.dispatch("getLunInfo").then(()=>{
               
        this.LUN_DAYS.forEach(el=>{
           $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').children('.holiday-text').remove()
       $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').prepend("<div class='holiday-text'>"+el.lunDay+"</ div>");
        })
       })
        this.changeNumColor(); 
           this.$store.dispatch("getHolidayInfo")  
          }
        }

            }
          }
       },

       eventHandlers () {
         return {
           eventDidMount: this.renderEvent,
         }
       },
    },

    data: function() {       
      return {
        newTask: "",
        arrBacklog: [],
        arrInProgress:[],
        arrDone: [],
        switching: true,
        absolute: true,
        overlay: false,
        detailgroup: '',
        master: '',
        calendargroup: null,
        people: null,
        groups: this.GROUPS,
        // events: this.EVENTS,
        selectgroup: null,
        show: false,
        focus: '',
        selectedEvent: {},
        selectedElement: null,
        selectedOpen: false,
        goDark: false,
        colors: ['#1976d2', '#3F51B5', '#673AB7', '#00BCD4', '#4CAF50', '#FF9800', '#F44336'],
        names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Conference', 'Party'],
        dialog: false,
        dialog1: false,
        TaskDialog: false,
        drawerRight: null,
        drawer: null,
        right: false,
        left: false,
        friends: [],
        member_info:this.MEMBER_INFO,
        sid:'',
        title: '',
        value: '',
        miniVariant: false,
        name: null,
        details: null,
        start: null,
        end: null,
        color: null,
        token: null,
        userId: null,
        lun_days:this.LUN_DAYS,
        userName: this.USERNAME,
      }
    },

    watch: {
    switching: function() {
      this.switchingKey = 0
    },

    people: function () {
      this.$store.dispatch("getHolidayInfo")
      if(this.people===null){      
        this.$store.dispatch("updateMySchedule", {people: sessionStorage.getItem("username")})
      }else{
        this.$store.dispatch("updateSchedule", {people: this.people}).then(()=>{
        this.member_info=this.MEMBER_INFO;         
      });
      }
      this.changeNumColor();
      for(var idx in this.GROUPS) {
        if(this.GROUPS[idx].groupName == this.people) {
          if(sessionStorage.getItem("username")==this.GROUPS[idx].groupMaster) {
            this.master = this.GROUPS[idx].groupMaster
            break;
          } else {
            this.master = ''
          }
        }
      }
    },

    goDark: function() {       
      this.$vuetify.theme.dark = this.goDark
       this.changeNumColor();
    },

    MEMBER_INFO:function(){
       this.member_info=this.MEMBER_INFO;
    }

    },
  methods: {

    helphelp() {
      this.$modal.show(helphelp, {
        modal: this.$modal},{
          width: '1000px',
          height: 'auto',
      })
    },

    googleCal(){
       this.$gAuth.getAuthCode()
                  .then(authCode => {
                    return instance.post('/googleCal', { code: authCode, redirect_uri: 'postmessage' }).then(res=>{
                      this.$store.commit("addGoogle",{googleSch:res.data});
                    })
                  })
    },

    gg() {
      let scheduleGroup
      for(var i in this.EVENTS) {
        for(var j in this.TASKS) {
          if(this.EVENTS[i].sid == this.TASKS[j].sid) {
            scheduleGroup = this.EVENTS[i].group
          }
        }
      }
      return scheduleGroup
    },

    changeNumColor() {
       if(this.goDark==true){
         $("td.fc-daygrid-day.fc-day")
          .children(".fc-daygrid-day-frame.fc-scrollgrid-sync-inner")
            .children(".fc-daygrid-day-top")
              .children(".fc-daygrid-day-number").css("color","white")

         $("td.fc-daygrid-day.fc-day.fc-day-sat")
          .children(".fc-daygrid-day-frame.fc-scrollgrid-sync-inner")
            .children(".fc-daygrid-day-top")
              .children(".fc-daygrid-day-number").css("color","#6666ff")

        $("td.fc-daygrid-day.fc-day.fc-day-sun")
          .children(".fc-daygrid-day-frame.fc-scrollgrid-sync-inner")
            .children(".fc-daygrid-day-top")
              .children(".fc-daygrid-day-number").css("color","hotpink")

        $("th.fc-col-header-cell.fc-day.fc-day-sun")
          .children(".fc-scrollgrid-sync-inner")
            .children('.fc-col-header-cell-cushion').css("color","hotpink")

        $("th.fc-col-header-cell.fc-day.fc-day-sat")
          .children(".fc-scrollgrid-sync-inner")
            .children('.fc-col-header-cell-cushion').css("color","#6666ff")

        $("a.fc-daygrid-day-number.koreaholday")
          .attr('style','color: hotpink !important')

        $("div.fc-event-title.fc-sticky.koreaholday")
        .attr('style','color: hotpink !important')

        $(".fa-trello").attr('style', 'color: white !important')

      } else {
          $("td.fc-daygrid-day.fc-day")
            .children(".fc-daygrid-day-frame.fc-scrollgrid-sync-inner")
              .children(".fc-daygrid-day-top")
                .children(".fc-daygrid-day-number").css("color","black")

          $("td.fc-daygrid-day.fc-day.fc-day-sat")
            .children(".fc-daygrid-day-frame.fc-scrollgrid-sync-inner")
              .children(".fc-daygrid-day-top")
                .children(".fc-daygrid-day-number").css("color","blue")

          $("td.fc-daygrid-day.fc-day.fc-day-sun")
            .children(".fc-daygrid-day-frame.fc-scrollgrid-sync-inner")
              .children(".fc-daygrid-day-top")
                .children(".fc-daygrid-day-number").css("color","red")

          $("th.fc-col-header-cell.fc-day.fc-day-sun")
            .children(".fc-scrollgrid-sync-inner")
              .children('.fc-col-header-cell-cushion').css("color","red")

          $("div.fc-event-title.fc-sticky.koreaholday").attr('style','color: red !important')

          $("th.fc-col-header-cell.fc-day.fc-day-sat")
            .children(".fc-scrollgrid-sync-inner")
              .children('.fc-col-header-cell-cushion').css("color","blue")

          $(".fa-trello").attr('style', 'color: black !important')
      }
    },

    newEvent() {
        this.$modal.show(NewEventModal, {
            text: 'Text',
            group_name: this.group_name,
            people: this.people,
            goDark: this.goDark,
            modal: this.$modal},{
                height: 'auto'
        })
    },

    addMem(data) {
      this.$store.dispatch("addMem",{members:data.friends,sid:data.sid,people:this.people})
    },

    renderEvent(arg) {
      let titleStr = arg.event.title
      let contentStr = arg.event.extendedProps.description
      
      new BPopover({
        propsData: {
          title: titleStr,
          content: contentStr,
          placement: 'top',
          boundary: 'scrollParent',
          boundaryPadding: 5,
          delay: 500,
          offset: 0,
          triggers: 'hover',
          html: true,
          target: arg.el,    
        },
      }).$mount()

      if (arg.event.extendedProps.group == "koholday"){
         $('.fc-daygrid-day[data-date="'+arg.event.startStr+'"]')
          .children('.fc-daygrid-day-frame')
            .children('.fc-daygrid-day-top')
              .children('.fc-daygrid-day-number')
                .addClass( 'koreaholday' );
         $('.fc-daygrid-day[data-date="'+arg.event.startStr+'"]')
          .children('.fc-daygrid-day-frame')
            .children('.fc-daygrid-day-events')
              .children('.fc-daygrid-event-harness')
                .children('.fc-daygrid-event.hol')
                  .children('.fc-event-main')
                    .children('.fc-event-main-frame')
                      .children('.fc-event-title-container')
                        .children('.fc-event-title.fc-sticky')
                          .addClass( 'koreaholday' );        
        if(this.goDark==true){
          $("a.fc-daygrid-day-number.koreaholday")
            .attr('style','color: hotpink !important')
          $("div.fc-event-title.fc-sticky.koreaholday")
            .attr('style','color: hotpink !important')
        }
      }

      if (arg.event.extendedProps.status == "done") {
        arg.el.className+=arg.el.className+" wow";
        $('.fc-daygrid-day-frame').children('.fc-daygrid-day-events')
          .children('.fc-daygrid-event-harness')
            .children('.fc-daygrid-event.wow')
              .children('.fc-event-main')
                .children('.fc-event-main-frame')
                  .children('.fc-event-title-container')
                    .children('.fc-event-title.fc-sticky')
                      .addClass( 'wow' );  
        $('.fc-popover-body').children('.fc-daygrid-event-harness')
          .children('.fc-daygrid-event.wow')
            .children('.fc-event-main')
              .children('.fc-event-main-frame')
                .children('.fc-event-title-container')
                  .children('.fc-event-title.fc-sticky')
                    .addClass( 'wow' );
        $("div.fc-event-title.fc-sticky.wow")
          .attr('style','text-decoration: line-through black double')
      }
      
    },

    updateEvent(arg) {
        this.$store.commit("UPDATE_EVENT", arg.event)
    },

    handleSelect(arg) {
        if (this.userId == this.master) {
          this.$modal.show(NewEventModal, {
            group_name: this.group_name,
            start_cal: arg.start,
            end_cal: arg.end,
            event: arg.event,
            people: this.people,
            dark:this.goDark,
            modal: this.$modal},{
                height: 'auto'
        })
        } else if (this.people == null) {
          this.$modal.show(NewEventModal, {
            group_name: this.group_name,
            start_cal: arg.start,
            end_cal: arg.end,
            event: arg.event,
            people: this.people,
            goDark:this.goDark,
            modal: this.$modal},{
                height: 'auto'
        })
        } else {
          swal('Warning', 'Only the head of the group can register the schedule!', 'warning')
          $('.fc-daygrid-bg-harness').children('.fc-highlight').addClass( 'rmvh' )
          // $('.fc').children('$fc-highlight.rmvh').css("background", 'none')
          $('.fc.fc-highlight').removeClass('rmvh')
          // $('.fc').children('$fc-highlight').css("background", 'none')
          // this.$router().go()
        }
    },

    handleEventClick (arg) {
      //console.log(arg.event)
      if (arg.event.extendedProps.group != 'koholday' && arg.event.extendedProps.sid != 'google') {
        this.$modal.show(EventDetailModal, {
            // text: "This is from the component",
            groupInfo: this.GROUPS,
            group_name: this.group_name,
            event: arg.event,
            events: this.EVENTS,
            people: this.people,
            master: this.master,
            // detailgroup: this.detailgroup,
            userId: this.userId,
            modal: this.$modal},{
                height: 'auto',
                width: '344px'
        })
      }
    },

    resizeEvent(arg) {
      // arg.event.realend = arg.event.end
      this.$store.dispatch("RE_EVENT", {id:arg.event.id,start:arg.event.start,end:arg.event.end,people:this.people})
    },

    reSchedule(arg) {             
        this.$store.dispatch("RE_EVENT", {id:arg.event.id,start:arg.event.start,end:arg.event.end,people:this.people})
        
    },

    viewDay ({ date }) {
      this.focus = date
      this.type = 'day'
    },

    setToday () {
      this.focus = ''
    },

    selectColor(color){
      this.color = color
    },

    addGroupModal() {
      this.$modal.show(AddGroupModal, {
        groups: this.GROUPS,
        group_name_for_modal: this.group_name,
        goDark: this.goDark,
        modal: this.$modal},{
            height: 'auto'
      })
    },

    GroupInfoModal() {
      this.$modal.show(GroupInfoModal, {
        groups: this.GROUPS,
        group_name: this.group_name,
        people: this.people,
        goDark: this.goDark,
        modal: this.$modal},{
            height: 'auto'
      })
    },

    logout() {
      this.$store.dispatch("logout", {
        token: sessionStorage.getItem("access-token"),
        userId: sessionStorage.getItem("username")
      })
      .then(() => this.$router.push("/"))
    },

    AddMembersModal() {
      this.$modal.show(AddMembersModal,
      {modal: this.$modal},
      {height: '700px', width: '700px'})
    },

    remove (item) {
        const index = this.friends.indexOf(item.name)
        if (index >= 0) this.friends.splice(index, 1)
    },

    MypageModal () {
      this.$modal.show(MypageModal,
      { userId: this.userId,
        ggl: sessionStorage.getItem("isGoogle"),
        modal: this.$modal},
      {height: 'auto'})
    },

    getMember(){     
      this.$store.dispatch('getMemberList',{groupName:this.people});
    },

    switchingcal() {
      this.switching =! this.switching 
    },

    backlogdnd: function(evt) {
      if(!(evt.added===undefined)){
        evt.added.element.status = 'backlog'     
        this.$store.dispatch('changestatus',{schedule: evt.added.element,people:this.people});
      }       
    },

    inprogressdnd: function(evt) {
       if(!(evt.added===undefined)){
          console.log("dawdaw",evt.added)
          evt.added.element.status = 'inprogress'     
          this.$store.dispatch('changestatus',{schedule: evt.added.element,people:this.people});
       }     
    },

    donednd: function(evt) {
      if(!(evt.added===undefined)){
        evt.added.element.status = 'done'    
        this.$store.dispatch('changestatus',{schedule: evt.added.element,people:this.people});
      }      
    },
  },

  created: function() {
    this.token=sessionStorage.getItem("access-token")
    this.userId=sessionStorage.getItem("username")
    this.userName=sessionStorage.getItem("name")
    this.$store.dispatch("getHolidayInfo")
    this.$store.dispatch("getGroupInfo")       
    this.$store.commit("monthInit")
    this.$store.dispatch("updateMySchedule", {people: sessionStorage.getItem("username")})
    this.switchingKey = 0
    },

  mounted(){
      this.member_info=this.MEMBER_INFO;
      this.$store.dispatch("getLunInfo").then(()=>{
        this.LUN_DAYS.forEach(el=>{
          $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').prepend("<div class='holiday-text'>"+el.lunDay+"</ div>");
        })
      })
  },

  updated() {
    this.switchingKey += 1
    if (this.switchingKey == 1) {
      $('.fc-GoogleBtn-button')
        .prepend('<div data-toggle="tooltip" data-placement="top" title="Connect with Google Calendar"><i class="fa fa-google fa-2x"></i></div>')
      $('.fc-btn2-button')
        .prepend('<div data-toggle="tooltip" data-placement="top" title="Go to Kanban"><i class="fa fa-trello fa-2x"></i></div>')
      if (this.goDark==true) {
        $(".fa-trello")
          .attr('style', 'color: white !important')
      } else {
        $(".fa-trello")
          .attr('style', 'color: black !important')
      }
      this.$store.commit("monthInit")
      if(this.people===null) {                   
        this.$store.dispatch("updateMySchedule", {people: sessionStorage.getItem("username")})
      } else {
        this.$store.dispatch("updateSchedule", {people: this.people}).then(()=>{
          this.member_info=this.MEMBER_INFO;         
        });
      }    

      this.$store.dispatch("getLunInfo").then(()=>{
          this.LUN_DAYS.forEach(el=>{
            $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').children('.holiday-text').remove()
            $('.fc-daygrid-day[data-date="'+el.solDay+'"]').children('.fc-daygrid-day-frame').children('.fc-daygrid-day-top').prepend("<div class='holiday-text'>"+el.lunDay+"</ div>");
            }
          )
        }
      )
      this.changeNumColor(); 
      this.$store.dispatch("getHolidayInfo") 
    }
  },
}
</script>

<style lang='css' scoped>
.wow {
  text-decoration: line-through;
}

.kanban-column {
  min-height: 450px;
  max-height: 450px;
  overflow: auto;
  text-align: left;
}

h2 {
  margin: 0;
  font-size: 24px;
  text-shadow: 2px 2px 5px black;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b { /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  font-size: 14px;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  margin: 1em;
  margin-top: 0;
}

.fc { /* the calendar root */
  margin: 0 auto;
  margin-left: 5px;
}

select.filter {
  width: 500px !important;
}

.v-expansion-panel-header {
  border-radius: inherit;
}
</style>

<style lang="scss">
main.v-main {
  min-height: 100vh;
  height: 100%;
}


label.v-label.theme--light {
  margin-left: 10px !important;
  margin-bottom: 0 !important;
}

div.v-input__slot {
  margin-bottom: 0;
  margin-top: 15px;
}

label.v-label.theme--dark {  
  margin-left: 10px !important;
  margin-bottom: 0 !important;
}

button.fc-today-button.fc-button.fc-button-primary{
  padding-bottom: 0px;
  background-color: green;
  width: 60px;
  text-align: center;
  vertical-align: middle;
}

.fc {
  .fc-button-primary {
    background-color: white;
    color: black;
    border: none;
    width: 40px;
    height: 30px;
    padding-top: 0;
    margin: 3px;
    margin-top: 10px;
    margin-bottom: 20px;
  }
}

th.fc-col-header-cell.fc-day.fc-day-sat{
  .fc-scrollgrid-sync-inner{
    .fc-col-header-cell-cushion{
      color: blue;
    }
  }
}

th.fc-col-header-cell.fc-day.fc-day-sun{
  .fc-scrollgrid-sync-inner{
    .fc-col-header-cell-cushion{
      color: red;
    }
  }
}

th.fc-col-header-cell.fc-day{
  .fc-scrollgrid-sync-inner{
    .fc-col-header-cell-cushion{
      color: grey;
    }
  }
}

td.fc-daygrid-day.fc-day.fc-day-sun{
  .fc-daygrid-day-frame.fc-scrollgrid-sync-inner{
    .fc-daygrid-day-top{
      .fc-daygrid-day-number{
        color: red;
      };
    }
  }
}

td.fc-daygrid-day.fc-day.fc-day-sat{
  .fc-daygrid-day-frame.fc-scrollgrid-sync-inner{
    .fc-daygrid-day-top{
      .fc-daygrid-day-number{
        color: blue;
      };
    }
  }
}

td.fc-daygrid-day.fc-day{
  .fc-daygrid-day-frame.fc-scrollgrid-sync-inner{
    .fc-daygrid-day-top{
      .fc-daygrid-day-number{
          color: gray;
          
      };
    }
  }
}

td.fc-daygrid-day.fc-day.fc-day-sun{
  .fc-daygrid-day-frame.fc-scrollgrid-sync-inner{
    .fc-daygrid-day-top{
      justify-content: space-between;
      flex-direction: row;
      };
    }
  }

td.fc-daygrid-day{
  .fc-daygrid-day-frame.fc-scrollgrid-sync-inner.birthday{
    .fc-daygrid-day-top{
      justify-content: space-between;
      flex-direction: row;
      };
    }
  }

.holiday-text{
  padding-top: 7px;
  padding-left: 5px;
  font-size: 11px;
}

.fc-daygrid-event{
  opacity: 0.75;
}

.v-expansion-panel{
    opacity: 0.75;
}

.koreaholday {
  color: red !important;
}

div.fc-header-toolbar.fc-toolbar.fc-toolbar-ltr {
  margin-bottom: 0;
}

.theme--dark.v-application {
  background: #0B1D38;
  /* #0B1D38 */
  /* #131E2E */
  /* #0F274A */
}

i.fa.fa-birthday-cake {
  margin-left: 5px;
  margin-top: 5px;
}

.v-application--wrap {
  min-height: auto;
}

.notholday {
  color: white !important;
}

.rmvh {
  background: none !important;
}

.ui-datepicker-trigger{border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  background: transparent;
    border: none;
    margin: 0!important;
    padding: 0!important;
    height: 30px!important;}
  .ui-datepicker-trigger .input-group-addon:last-child {
    border-left: 1px solid #ccc;
  border-left: 1px solid #ccc;
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;
}

.fa-google {
  background: conic-gradient(from -45deg, #ea4335 110deg, #4285f4 90deg 180deg, #34a853 180deg 270deg, #fbbc05 270deg) 73% 55%/150% 150% no-repeat;
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  -webkit-text-fill-color: transparent;
}

.fc-GoogleBtn-button {
  margin-right: 5px !important;
  margin-bottom: 10px !important;
  border: none !important;
  outline: none !important;
  background-color: transparent !important;
}

.fc-event-title {
  display: block; 
  overflow: hidden; 
  text-overflow:ellipsis;
}

.fc-popover {
  color: black;
}

.fc-popover-body {
  background-color: white;
}

.task_description {
  display: block; 
  overflow: hidden; 
  text-overflow:ellipsis;
}

.task_title {
  display: block; 
  overflow: hidden; 
  text-overflow:ellipsis;
}

.fc-btn2-button {
  padding: 0 !important;
  margin-bottom: 10px !important;
  background-color: transparent !important;
}

.list-group-item {
  border: 0 !important;
}

</style>