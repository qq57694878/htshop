<template>
  <div class="page-login-v3">
    <div class="container">
      <div id="wrapper" class="login-body">
        <div class="login-content">
          <div class="brand">
            <img alt="logo" class="brand-img" src="data:image/png;base64,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">
            <h2 class="brand-text">小程序admin系统</h2>
          </div>
          <el-form class="login-form"
                   status-icon
                   :rules="loginRules"
                   ref="loginForm"
                   :model="loginForm"
                   label-width="0">
            <el-form-item prop="username">
              <el-input size="small"
                        @keyup.enter.native="handleLogin"
                        v-model="loginForm.username"
                        auto-complete="off"
                        placeholder="请输入用户名">
                <i slot="prefix"
                   class="icon-yonghu"></i>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="small"
                        @keyup.enter.native="handleLogin"
                        :type="passwordType"
                        v-model="loginForm.password"
                        auto-complete="off"
                        placeholder="请输入密码">
                <i class="el-icon-view el-input__icon"
                   slot="suffix"
                   @click="showPassword"></i>
                <i slot="prefix"
                   class="icon-mima"></i>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary"
                         size="small"
                         @click.native.prevent="handleLogin"
                         class="login-submit">登录</el-button>
            </el-form-item>
          </el-form>

        </div>
      </div>
    </div>
  </div>

</template>
<script>

import color from "@/mixins/color";
import { mapGetters } from "vuex";
import { validatenull } from '@/util/validate'
export default {
  name: "login",
    data() {

        return {
            loginForm: {
                username: "admin",
                password: "123456",
                code: "",
                redomStr: ""
            },
            checked: false,
            code: {
                src: "",
                value: "",
                len: 4,
                type: "text"
            },
            loginRules: {
                username: [
                    { required: true, message: "请输入用户名", trigger: "blur" }
                ],
                password: [
                    { required: true, message: "请输入密码", trigger: "blur" },
                    { min: 6, message: "密码长度最少为6位", trigger: "blur" }
                ],

            },
            passwordType: "password"
        };
    },
    created() {

    },
    mounted() {},
    computed: {
        ...mapGetters(["tagWel"])
    },
    props: [],
    methods: {

        showPassword() {
            this.passwordType === "text"
                ? (this.passwordType = "password")
                : (this.passwordType = "text");
        },
        handleLogin() {
            this.$refs.loginForm.validate(valid => {
                if (valid) {
                    this.$store.dispatch("LoginByUsername", this.loginForm).then((res) => {
                        console.log(res);
                        if(res.data.code==200){
                            this.$router.push({ path: this.tagWel.value });
                        }else{
                            this.$notify({
                                title: '登录失败',
                                showClose: true,
                                message: res.data.msg,
                                type: 'error',
                            });
                        }
                    });
                }
            });
        }
    }
};
</script>

<style lang="scss">
  @keyframes fade-in {
    0% { opacity: 0; }

    /*初始状态 透明度为0*/
    40% { opacity: 0; }

    /*过渡状态 透明度为0*/
    100% { opacity: 1; }

    /*结束状态 透明度为1*/ }
  @-webkit-keyframes fade-in {

    /*针对webkit内核*/
    0% { opacity: 0; }
    40% { opacity: 0; }
    100% { opacity: 1; }
  }
  #wrapper { animation: fade-in;/*动画名称*/ animation-duration: .6s;/*动画持续时间*/ -webkit-animation: fade-in .6s;/*针对webkit内核*/ }
  body { line-height: 1; font: 14px/1.5 "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", Roboto, Arial, sans-serif; }
  a { margin: 0; padding: 0; border: 0; font-size: 100%; vertical-align: baseline; background: transparent }
  a:hover, a:focus { text-decoration: none; bblr: expression(this.onFocus=this.blur()); outline-style: none }
  table { border-collapse: collapse; border-spacing: 0 }
  input, select { vertical-align: middle }
  * { -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; }
  .clearfix::before, .clearfix::after { content: ""; height: 0; line-height: 0; display: block; visibility: hidden; clear: both }
  .clearfix:after { clear: both }
  .clearfix { *zoom: 1 }

  /* login */
  html, body { height: 100%; }
  .page-login-v3:before { position: fixed; top: 0; left: 0; z-index: -1; width: 100%; height: 100%; content: ''; background: #62a8ea; background: url(/img/login_bg.jpg) center center/cover no-repeat !important; }
  .container { text-align: center; height: 100%; }
  .container:before { display: inline-block; height: 100%; vertical-align: middle; content: ""; }
  .login-body { display: inline-block; vertical-align: middle; width: 400px; margin: 200px 0; background: #fff; border-radius: 4px; padding: 50px 40px 40px; -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; }
  .login-content { }
  .login-content .brand .brand-img { width: 50px; }
  .login-content .brand .brand-text { margin-top: 20px; margin-bottom: 11px; font-size: 20px !important; font-family: "Microsoft YaHei"; text-shadow: rgba(0, 0, 0, .15) 0 0 1px; font-weight: 400; color: #37474f; }
  .login-form { margin: 45px 0 30px; }
  .login-form .form-group { margin: 30px 0; }
  .login-form input { display: block; border: 0; border-radius: 0; -webkit-box-shadow: none; box-shadow: none; width: 100%; border-bottom: 1px solid #E4EAEC; font-size: 14px; height: 42px; line-height: 1.5; outline: none; padding: 0 5px; color: #a3afb7; font: 14px/1.5 "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", Roboto, Arial, sans-serif; transition: all 0.3s ease-in-out; -moz-transition: all 0.3s ease-in-out; -webkit-transition: all 0.3s ease-in-out; -o-transition: all 0.3s ease-in-out; }
  .login-form input:focus { border-bottom: 1px solid #ff5060; }
  .login-form button { width: 100%; margin-top: 20px; padding: 10px 18px; font-size: 18px; line-height: 1.3333333; border-radius: 4px; white-space: normal; -webkit-transition: border .2s linear, color .2s linear, width .2s linear, background-color .2s linear; -o-transition: border .2s linear, color .2s linear, width .2s linear, background-color .2s linear; transition: border .2s linear, color .2s linear, width .2s linear, background-color .2s linear; -webkit-font-smoothing: subpixel-antialiased; color: #fff; background-color: #ff5060; border-color: #ff5060; background-image: none; border: 1px solid transparent; cursor: pointer; outline: none; }
  .login-form button[disabled], .login-form button[disabled]:hover { color: #fff; background-color: #a2caee; border-color: #a2caee; cursor: not-allowed; opacity: .65; }
  .login-form button:hover { background-color: #fd717e; border-color: #fd717e; }
  @media screen and (max-width: 404px) {
    .login-body{
      width: 310px;
      padding: 50px 30px 40px;
    }
  }
</style>