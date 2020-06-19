<template>
  <div class="register_box">
    <router-link to="/input">
      <div class="login_close"></div>
    </router-link>
    <div class="register_panel">
      <div class="register_title">
        <img src="../assets/img/logo.png" alt="">
        <p>IPERSONA</p>
      </div>
      <div :class="isEmailValid">
      <label style="margin-top: 50px">Email：</label>
      <input v-model="userId" type="tel" pattern="^\d{11}$" title="请输入账号">
      </div>
      <label>Password：</label>
      <password v-model="userPassword" type="password" title="请输入密码" :placeholder="placeholder" :secureLength="secureLength"></password>
      <label>Confirm Password：</label>
      <input v-model="userPassword" type="password" title="请输入密码">
      <input class="bt" @click="addUser" type="submit" value="Register">

    </div>
  </div>
</template>

<script>
import Password from 'vue-password-strength-meter'
export default {
  name: 'Register',
  data () {
    return {
      userId: '',
      userPassword: '',
      placeholder: 'Enter 8 characters or more',
      secureLength: 8, // minimum password
      userInfoApi: 'http://localhost/register',
      reg: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    }
  },
  components: {
    Password
  },
  methods: {
    addUser () {
      if (this.userId == null || this.userId === '') {
        this.$message.error('Email is required!')
        return
      } else if (!this.reg.test(this.userId)) {
        this.$message.error('Please enter valid email address!')
        return
      }
      this.$ajax({
        method: 'post',
        url: this.userInfoApi,
        data: {
          'user_id': this.userId,
          'user_password': this.userPassword
        }
      }).then((response) => {
        if (response.data.data.userId !== 0) {
          this.$message({
            message: 'Register Success!',
            type: 'success'
          })
        } else {
          this.$message.error('Register Failed, Please Check Account Or Password!')
        }
        console.log(response.data.data)
      }).catch((error) => {
        this.$message.error('Register Failed, Please Check Account Or Password!')
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
  /*登录框*/
  .register_box {
    z-index: 99;
    position: absolute;
    width: 380px;
    height: 540px;
    top: 50%;
    left: 50%;
    margin-left: -190px;
    margin-top: -270px;
    border-radius: 6px;
    background-color: #fff;
    box-shadow: 0 2px 10px #999;
  }

  .login_close {
    position: absolute;
    top: 0;
    right: 0;
    width: 64px;
    height: 64px;
    background: url(../assets/img/qrcode.png) no-repeat right top;
    background-size: 100% 100%;
    border-top-right-radius: 5px;
    cursor: pointer;
    z-index: 99;
  }

  /*登录*/
  .register_panel {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 270px;
    height: 540px;
    padding: 0 55px;
    transform: translate(-50%, -50%);
    /* background: #fff; */
    border-radius: 6px;
    overflow: hidden;
  }

  .register_panel .register_title {
    text-align: center;
  }

  .register_panel .register_title img {
    margin-top: 60px;
    height: 70px;
    width: 70px;
    border-radius: 50%;
    padding: 10px;
    border: 3px solid #d7e8fc;
  }

  .register_panel .register_title p {
    margin-top: 15px;
    color: #999999;
    font-size: 15px;
  }

  .register_panel label {
    display: block;
    font-size: 12px;
    line-height: 18px;
    color: #a9a8a5;
    margin-top: 10px;
  }

  .register_panel input {
    display: inline;
    height: 42px;
    padding: 0 5%;
    line-height: 42px;
    font-size: 14px;
    color: #333333;
    border-radius: 4px;
    outline: 0;
    border: 0;
    width: 90%;
    background: #d7e8fc;
  }

  /* 按钮 */
  .register_panel .bt {
    margin-top: 35px;
    width: 100%;
    color: #ffffff;
    background: #379df6;
    cursor: pointer;
  }

  .register_panel .bt:hover {
    background-color: #2f86f6;
  }

</style>
