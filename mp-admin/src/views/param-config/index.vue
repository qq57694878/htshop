<template>
  <div class="row-content am-cf">
    <div class="row">
      <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <div class="widget am-cf">

            <div class="widget-body">
              <fieldset>
                <el-form :model="configForm" class="tpl-form-line-form"
                         ref="configForm" v-loading="loading"
                         label-width="200px">
                <div class="widget-head am-cf">
                  <div class="widget-title am-fl">小程序参数设置</div>
                </div>

                  <el-form-item label="小程序ID（appid）" prop="appid">
                    <el-input v-model="configForm.appid"  class="tpl-form-input" ></el-input>
                  </el-form-item>
                  <el-form-item label="小程序密钥（appsecret）" prop="appsecret">
                    <el-input v-model="configForm.appsecret"  class="tpl-form-input" ></el-input>
                  </el-form-item>


                <div class="widget-head am-cf">
                  <div class="widget-title am-fl">微信支付设置</div>
                </div>
                  <el-form-item label=" 微信支付商户号（mchid）" prop="appid">
                    <el-input v-model="configForm.mchid"  class="tpl-form-input" ></el-input>
                  </el-form-item>
                  <el-form-item label="微信支付密钥（paykey）" prop="appsecret">
                    <el-input v-model="configForm.paykey"  class="tpl-form-input" ></el-input>
                  </el-form-item>
                  <div class="widget-head am-cf">
                    <div class="widget-title am-fl">其他设置</div>
                  </div>
                  <el-form-item label="小程序端服务电话" prop="serverPhoneNumber">
                    <el-input v-model="configForm.serverPhoneNumber"  class="tpl-form-input" ></el-input>
                  </el-form-item>

                <div class="am-form-group">
                  <div class="am-u-sm-9 am-u-sm-push-3 am-margin-top-lg">
                    <button type="button" class="j-submit am-btn am-btn-secondary" @click="handleSaveAllConfig">提交
                    </button>
                  </div>
                </div>
                </el-form>
              </fieldset>
            </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import {getAllConfig,saveAllConfig} from '@/api/param-config'
export default {
  name: "param-config",
  data () {
    return {
        loading:false,
        configForm:{
            appid:"",
            appsecret:"",
            mchid:"",
            paykey:"",
            serverPhoneNumber:""
        }
    };
  },
  created () {
      this.handleGetAllConfig();
  },
  computed: {

  },
  methods: {
      handleGetAllConfig(){
          this.loading = true;
          getAllConfig().then(res => {
                  if(res.data.code==200){
                     this.configForm = res.data.data;
                  }else{
                      this.$notify({
                          showClose: true,
                          message: '数据加载失败',
                          type: 'error',
                      });
                  }
                  this.loading = false;
              });
      },
      handleSaveAllConfig(){
          this.loading = true;
          saveAllConfig(this.configForm)
              .then(res => {
                  if(res.data.data){
                      this.$notify({
                          title:'成功',
                          duration:3000,
                          message: '保存成功',
                          type: 'success',
                      });
                  }else{
                      this.$notify({
                          showClose: true,
                          message: '保存失败',
                          type: 'error',
                      });
                  }
                  this.loading = false;
              });
      },

  }
};
</script>

<style lang="scss">

</style>