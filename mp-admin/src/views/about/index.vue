<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <form id="my-form" class="am-form tpl-form-line-form" enctype="multipart/form-data" method="post">
                        <div class="widget-body">
                            <fieldset>
                                <div class="widget-head am-cf">
                                    <div class="widget-title am-fl">关于我们维护</div>
                                </div>

                                <el-form :model="aboutForm" :rules="rules" ref="aboutForm" label-width="200px" v-loading="isloading"
                                         class="am-form am-u-sm-11 tpl-form-line-form">
                                    <el-form-item label="关于我们" prop="content">
                                        <editor id="content" height="690px" width="350px" :content="aboutForm.content"
                                                :uploadJson="website.uploadKindUrl"
                                                pluginsPath="/static/kindeditor/plugins/"
                                                :loadStyleMode="false"
                                                @on-content-change="onAboutContentChange"></editor>
                                    </el-form-item>
                                </el-form>


                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3 am-margin-top-lg">
                                        <button type="button" class="j-submit am-btn am-btn-secondary" @click="handleSaveAbout">提交
                                        </button>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
<style>

</style>
<script>
    import {getAbout,saveAbout} from '@/api/about';
    import { mapGetters } from 'vuex'
    export default{
        data(){
            return{
                isloading:false,
                aboutForm:{
                    id:"",
                    content:""
                },
                rules: {
                    content: [
                        {required: true, message: '请输入关于我们内容', trigger: 'blur'}
                    ]
                }
            }
        },
        created:function(){
            this.handleGetAbout();
        },
        computed: {
            ...mapGetters(['website'])
        },
        methods:{
            handleGetAbout(){
                getAbout().then(response=>{
                    var r = response.data.data;
                    if(r){
                        this.aboutForm.content = r.content;
                        this.aboutForm.id= r.id;
                    }
                });
            },
            handleSaveAbout(){
                this.$refs['aboutForm'].validate((valid) => {
                    if (valid) {
                        this.isloading = true;
                        saveAbout(this.aboutForm).then(res => {
                            this.isloading = false;
                            if (res.data.data) {
                                this.$notify({
                                    title: '成功',
                                    duration: 2000,
                                    message: '保存成功',
                                    type: 'success',
                                });
                            } else {
                                this.$notify({
                                    title: '操作失败',
                                    showClose: true,
                                    message: '保存失败',
                                    type: 'error',
                                });
                            }
                        });
                    }
                })
            },
            onAboutContentChange(value){
                this.aboutForm.content = value;
            },
        }
    }
</script>
