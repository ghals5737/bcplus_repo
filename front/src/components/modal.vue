<template>
<div>
  <!-- signup Modal -->
 <div class="modal fade rounded" id="signupModal" tabindex="-1" role="dialog" aria-labelledby="signupModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title pl-4" id="signupModalLabel">SignUp</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body p-5">
          <div class="form-group">
            <div class="input-group mb-1">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
              </div>
                <input v-model="name" type="text" class="form-control" id="name" ref="name" aria-describedby="emailHelp" placeholder="ID" required>
            </div>
          </div>
          <div class="form-group">
            <div class="input-group mb-1">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
              </div>
                <input v-model="userId" type="text" class="form-control" id="userId" ref="userId" aria-describedby="emailHelp" placeholder="Nickname" required>
            </div>
          </div>
          <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text"><i class="fa fa-lock" aria-hidden="true"></i></span>
                </div>
                  <input v-model="pw" type="password" class="form-control" id="pw" ref="pw" placeholder="Password" required>
              </div> 
          </div>
          <div class="form-group">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-lock" aria-hidden="true"></i></span>
              </div>
                <input v-model="pw2" type="password" class="form-control" id="pw2" ref="pw2" placeholder="Conform Password" required>
            </div> 
          </div>      
          <div class="form-group">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text bthday"><i class="fa fa-birthday-cake" aria-hidden="true"></i></span>
              </div>
                <input v-model="birthDay" type="date" class="form-control" id="birthDay" ref="birthDay" placeholder="Birthday" required>
            </div> 
          </div> 
          <div class="text-right">
            <!-- 버튼 클릭 시 checkHandler 메서드 호출 -->
            <button type="submit" class="btn btn-success w-100" @click="checkHandler">Submit</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 그룹 추가 -->
  
  <div class="modal fade" id="addGroupModal" tabindex="-1" role="dialog" aria-labelledby="addGroupModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <!-- <div class="modal-header"> -->
            <v-card>
              <v-card-title>
                <span class="headline">Add Group</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                  
                    <v-col cols="12">
                      <v-text-field label="Name*" required></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field label="Memo*" ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="['0-17', '18-29', '30-54', '54+']"
                        label="Age*"
                        required
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-autocomplete
                        :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
                        label="Interests"
                        multiple
                      ></v-autocomplete>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
              </v-card-actions>
            </v-card>
          </div>
        </div>
      </div>
    <!-- </div> -->

  <!-- 그룹 설정 -->
  
  <div class="modal fade" id="setGroupModal" tabindex="-1" role="dialog" aria-labelledby="setGroupModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <v-card>
          <v-card-title>
            <span class="headline">Set Group</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
              
                <v-col cols="12">
                  <v-text-field label="Name*" required></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field label="Memo*" ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field label="members*" ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field label="group url*" ></v-text-field>
                </v-col>
               

              </v-row>
            </v-container>
          </v-card-text>
          <v-spacer></v-spacer>
          <v-card-actions class="d-flex justify-content-between">
            <!-- alert popup 필요 -->
            <div class="d-flex">
              <v-btn color="red darken-1" text @click="dialog = false">Delete</v-btn>
            </div>

            <div class="d-flex">
              <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
              <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
            </div>
          </v-card-actions>
        </v-card>
      </div>
    </div>
  </div>
    


    <!-- 참가자 목록 -->
    <div class="modal fade" id="memberListModal" tabindex="-1" role="dialog" aria-labelledby="memberListModal" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <v-card>
            <v-card-title class="headline grey lighten-2">
              MEMBER
            </v-card-title>
            <v-card-text>
              <v-list dense>
                <v-list>
                  <v-list-item
                    v-for="item in items2"
                    :key="item.text"
                    link
                  >
                    <v-list-item-avatar>
                      <img
                        :src="`https://randomuser.me/api/portraits/men/${item.picture}.jpg`"
                        alt=""
                      >
                    </v-list-item-avatar>
                    <v-list-item-title v-text="item.text"></v-list-item-title>

                  </v-list-item>
                </v-list>
                <v-list-item
                  class="mt-4"
                  link
                >
                </v-list-item>
              </v-list>
            </v-card-text>
          </v-card>
        </div>
      </div>
    </div>
  

  <!-- Task 추가 -->
    <div class="modal fade" id="addTaskModal" tabindex="-1" role="dialog" aria-labelledby="addTaskModal" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <v-card>
          <v-container>
            <v-form @submit.prevent="addTask">
              <v-text-field v-model="name" type="text" label="schedule"></v-text-field>
              <v-textarea v-model="details" type="textarea" label="detail"></v-textarea>
              <v-text-field v-model="start" type="date" label="start"></v-text-field>
              <v-text-field v-model="end" type="date" label="end"></v-text-field>
              <v-btn type="submit" color="success" class="mr-4">
                submit
              </v-btn>
            </v-form>
          </v-container>
        </v-card>

        </div>
      </div>
    </div>
  
  <!-- 그룹 정보 -->
  <div class="modal fade" id="groupInfoModal" tabindex="-1" role="dialog" aria-labelledby="groupInfoModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        modal
      </div>
    </div>
  </div>
    
  </div>

  
</template>

<script>
import $ from 'jquery';
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
  name: 'Modal',
  props: {
    items2: Array,
    
  },
  data() {
    return {
      // tasks: [],
      birthDay: '',
      groups: [
        
      ],
      id: {
        date: new Date(),
        timestamp: 0
      },
      name: '',
      pw: '',
      pw2: '',
      userId: '',
    }
  },
  methods:{
    signupclose(){
      $('#signupModal .close').click();
    },
    checkHandler() {
      // 사용자 입력값 체크하기
      // 작성자, 제목, 내용 
      let isOk = true;

      if(!this.name || !this.userId || !this.pw || !this.pw2 ){
        isOk = false;
      }
      if ( this.pw != this.pw2 ){
        alert('비밀번호가 다릅니다!')
      }
      if(isOk){ // 글 작성 진행.
        this.createHandler();
      }else{
        alert('비어있는 항목을 입력해주세요!')
      }
    },

    createHandler() {              
      instance.post('/member',
      {
        birthDay: this.birthDay,
        groups: [
            
          ],
        id: {
          date: new Date(),
          timestamp: 0
        },
        name: this.name,
        pw: this.pw,
        userId: this.userId          
      }
      ).then(({data})=>{
          if (data == 'member insert success') {
              $('#signupModal .close').click();  
              alert('등록이 완료되었습니다.');
              this.$emit('close')    
          }else{
              alert('등록 과정에 문제가 발생하였습니다.')
          }
      }).catch(()=>{
          alert("이미 등록된 아이디입니다.")
      });
    },
  }
}
</script>

<style scoped>
#logo{
  width:22px;
  height:22px;
  margin-bottom:3px;
}
.bthday {
  padding-right: 10px;
  padding-left: 9px;
}
</style>