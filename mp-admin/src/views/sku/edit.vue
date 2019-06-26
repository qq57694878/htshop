<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="am-g am-cf">
                        <el-form :model="skuForm" :rules="rules" ref="skuForm" label-width="200px"
                                 class="am-form am-u-sm-11 tpl-form-line-form">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">基本信息</div>
                            </div>
                            <el-form-item label="商品名称" prop="skuName">
                                <el-input v-model="skuForm.skuName" class="tpl-form-input"></el-input>
                            </el-form-item>
                            <el-form-item label="商品价格" prop="skuPrice">
                                <el-input type="number" v-model.number="skuForm.skuPrice" class="tpl-form-input"></el-input>
                            </el-form-item>
                            <el-form-item label="消费次数" prop="frequency">
                                <el-input type="number" v-model.number="skuForm.frequency" class="tpl-form-input"></el-input>
                            </el-form-item>
                            <el-form-item label="商品分类" prop="catagory">
                                <el-select v-model="skuForm.catagory" >
                                    <el-option
                                            v-for="item in catagoryOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">商品详情</div>
                            </div>
                            <el-form-item label="商品详情" prop="skuContent">
                                <editor id="skuContent" height="690px" width="350px" :content="skuForm.skuContent"
                                        :uploadJson="website.uploadKindUrl"
                                        pluginsPath="/static/kindeditor/plugins/"
                                        :loadStyleMode="false"
                                        :items="kinditems"
                                        :formatUploadUrl="false"
                                        @on-content-change="onSkuContentChange"></editor>
                            </el-form-item>
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">其他</div>
                            </div>
                            <el-form-item label="商品状态" prop="skuStatus">
                                <el-select v-model="skuForm.skuStatus" >
                                    <el-option
                                            v-for="item in skuStatusOptions"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>

                            <el-form-item label="有效期（月数）" prop="validMonth">
                                <el-input type="number" v-model.number="skuForm.validMonth" class="tpl-form-input"></el-input>
                            </el-form-item>
                            <el-form-item label="排序" prop="sort">
                                <el-input type="number" v-model.number="skuForm.sort" class="tpl-form-input"></el-input>
                                <small>数字越小越靠前</small>
                            </el-form-item>
                            <el-form-item label="创建时间" prop="createTime">
                               {{skuForm.createTime | moment('YYYY-MM-DD HH:mm:ss')}}
                            </el-form-item>

                        </el-form>
                        <div class="am-form-group">
                            <div class="am-u-sm-12 am-u-sm-push-6 am-margin-top-lg">
                                <button type="button" class="j-submit am-btn am-btn-secondary" @click="saveForm('skuForm')">保存
                                </button>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</template>
<style>
    body {
        background-color: #ff0000;
    }

    .my-upload input[type=file] {
        display: none;
    }

</style>
<script>
    import { getSku,updateSku} from '@/api/sku'
    import {type2options} from '@/util/codeTable'
    import { mapGetters } from 'vuex'
    export default {
        created:function(){
            this.skuForm.skuId= this.$route.params.skuId;
            this.handleGetSku();
        },
        data() {
            return {
                catagoryOptions:type2options('catagory'),
                skuStatusOptions: type2options("sku_status"),
                skuForm: {
                    frequency:"",
                    catagory:"",
                    skuName:"",
                    skuContent:"",
                    skuPrice:"",
                    sort:"",
                    skuStatus:"",
                    validMonth:"",
                },
                other:{
                    minusNum:0
                },
                rules: {
                    catalog: [
                        {required: true, message: '请输入商品分类', trigger: 'blur'},
                    ],
                    frequency: [
                        {required: true, message: '请输入次数', trigger: 'blur'},
                        { type: 'number', message: '次数必须为数字值'}
                    ],
                    validMonth:[
                        {required: true, message: '请输入有效期（月数）', trigger: 'blur'},
                        { type: 'number', message: '有效期必须为数字值'}
                    ],
                    skuName:[
                        {required: true, message: '请输入商品名称', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 30 个字符', trigger: 'blur'}
                    ],
                    skuContent:[
                        {required: true, message: '请录入商品详情', trigger: 'blur'}
                    ],
                    skuPrice:[
                        {required: true, message: '请录入商品价格', trigger: 'blur'}
                    ],
                }
            };
        },
        methods: {

            handleGetSku:function(){
                getSku(this.skuForm.skuId).then(res=>{
                        this.skuForm=res.data.data;
                });
            },
            onSkuContentChange:function(content){
                this.skuForm.skuContent = content;
            },
            saveForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        updateSku(this.skuForm).then(res=>{
                            console.log(res);
                            if(res.data.data){
                                this.$notify({
                                    title:'成功',
                                    duration:2000,
                                    message: '保存成功',
                                    type: 'success',
                                });
                                this.$router.push({
                                    path:'/sku/index'
                                });

                            }else{
                                this.$notify({
                                    title:'操作失败',
                                    showClose: true,
                                    message: '保存失败',
                                    type: 'error',
                                });
                            }

                        });

                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            uploadMainUrlSuccess(response){
                this.skuForm.mainUrl = response.data;
            }
        },
        computed: {
          ...mapGetters(['website']),
            kinditems:function () {
                   /* return [
                 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                 'anchor', 'link', 'unlink', '|', 'about'
                 ],*/
                return [
                    'source', '|',   'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'clearhtml', 'quickformat',  '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                    'table', 'hr'
                ]
            }
        },
    }
</script>
