<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <form id="my-form" class="am-form tpl-form-line-form" enctype="multipart/form-data" method="post">
                        <div class="widget-body ">
                            <fieldset>
                                <div class="widget-head am-cf">
                                    <div class="widget-title am-fl">通知通告维护</div>
                                </div>
                                <div class="am-u-sm-6">
                                    <el-form :model="noticeForm" :rules="rules" ref="noticeForm" label-width="200px" v-loading="isloading"
                                             class="am-form am-u-sm-11 tpl-form-line-form">
                                        <el-form-item label="通告内容" prop="content">
                                            <el-input v-model="noticeForm.content" class="tpl-form-input"></el-input>
                                        </el-form-item>
                                        <el-form-item label="开始时间" prop="startTime">
                                            <el-date-picker
                                                    v-model="noticerangetime"
                                                    type="datetimerange"
                                                    range-separator="至"
                                                    start-placeholder="开始日期"
                                                    end-placeholder="结束日期"
                                                    :default-time="['00:00:00', '23:59:59']"
                                                    @change="changeDateRange"
                                                    value-format="yyyy-MM-dd HH:mm:ss">
                                            </el-date-picker>
                                        </el-form-item>
                                    </el-form>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3 am-margin-top-lg">
                                        <button type="button" class="j-submit am-btn am-btn-secondary" @click="handleSaveNotice">提交
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
    import {getNotice,saveNotice} from '@/api/notice';
    import { mapGetters } from 'vuex'
    import moment from 'moment'
    export default{
        data(){
            return{
                noticerangetime:[],
                isloading:false,
                noticeForm:{
                    id:"",
                    content:"",
                    startTime:"",
                    endTime:""
                },
                rules: {
                    content: [
                        {required: true, message: '请输入关于通告内容', trigger: 'blur'}
                    ]
                }
            }
        },
        created:function(){
            this.handleGetNotice();
        },
        computed: {
            ...mapGetters(['website'])
        },
        methods:{
            changeDateRange:function(value){
                if(value){
                    this.noticeForm.startTime=value[0];
                    this.noticeForm.endTime=value[1];
                }else{
                    this.noticeForm.startTime="";
                    this.noticeForm.endTime="";
                }
            },
            handleGetNotice(){
                getNotice().then(response=>{
                    var r =response.data.data;
                    console.log(r);
                    console.log(this.moment(new Date(r.startTime)).format('YYYY-MM-DD HH:mm:ss'));
                    if(r){
                        this.noticeForm.content = r.content;
                        this.noticeForm.id=r.id;
                        this.noticerangetime=[new Date(r.startTime),new Date(r.endTime)],
                        this.noticeForm.startTime=this.moment(new Date(r.startTime)).format('YYYY-MM-DD HH:mm:ss');
                        this.noticeForm.endTime=this.moment(new Date(r.endTime)).format('YYYY-MM-DD HH:mm:ss');
                    }
                });
            },
            handleSaveNotice(){
                this.$refs['noticeForm'].validate((valid) => {
                    if (valid) {
                        if(!this.noticeForm.startTime){
                            this.$message.error('开始时间不能为空');
                            return;
                        }
                        if(!this.noticeForm.endTime){
                            this.$message.error('结束时间不能为空');
                            return;
                        }
                        this.isloading = true;
                        saveNotice(this.noticeForm).then(res => {
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
            }
        }
    }
</script>
