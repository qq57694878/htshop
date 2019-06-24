<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title am-cf">用户列表</div>
                    </div>
                    <div class="page_toolbar am-margin-bottom-xs am-cf">
                        <div class="am-u-sm-12 am-u-md-12">
                            <div class="am fr">
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-date-picker
                                                v-model="queryDateRange"
                                                @change="changeDateRange"
                                                type="daterange"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                :default-time="['00:00:00', '23:59:59']"
                                                :picker-options="pickerOptions"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                        >
                                        </el-date-picker>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.userId" placeholder="用户id"></el-input>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.carnNo" placeholder="车牌号"></el-input>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.mobile" placeholder="用户电话"></el-input>
                                        <div class="am-input-group-btn">
                                            <button class="am-btn am-btn-default am-icon-search" type="button" @click="doQuery"></button>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                    <div class="widget-body am-fr">
                        <div class="am-scrollable-horizontal am-u-sm-12">
                            <table width="100%" class="am-table am-table-compact am-table-striped
                         tpl-table-black am-text-nowrap" v-loading="loading">
                                <thead>
                                <tr>
                                    <th>用户ID</th>
                                    <th>电话</th>
                                    <th>车牌号</th>
                                    <th>注册时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr  v-for="userInfo in userList" :key="userInfo.userId">
                                    <td class="am-text-middle">{{ userInfo['userId'] }}</td>
                                    <td class="am-text-middle">{{ userInfo['mobile'] }}</td>
                                    <td class="am-text-middle">{{ userInfo['carNo'] }}</td>
                                    <td class="am-text-middle">{{ userInfo['createTime']|moment('YYYY-MM-DD HH:mm:ss') }}</td>
                                    <td class="am-text-middle">
                                        <div class="tpl-table-black-operation">

                                            <a href="javascript:;" @click="handleResetPassword($item['userId'])" class="item-delete tpl-table-black-operation-del">
                                                <i class="am-icon-unlock-alt"></i> 重置密码
                                            </a>

                                        </div>
                                    </td>
                                </tr>

                                <tr v-if="userList.length==0">
                                    <td colspan="8" class="am-text-center">暂无记录</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                        <div class="am-u-lg-12 am-cf">
                            <div class="am-fr">
                                <el-pagination
                                        @size-change="handleSizeChange"
                                        @current-change="handleCurrentChange"
                                        :current-page="query.current"
                                        :page-sizes="[10, 20, 50, 100]"
                                        :page-size="query.size"
                                        layout="total, sizes, prev, pager, next, jumper"
                                        :total="total">
                                </el-pagination>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<style>

</style>
<script>
    import {getUserList,resetPassword} from '@/api/wxuser'
    import {type2options,code2value} from '@/util/codeTable'
    export default {
        data() {
            return {
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                queryDateRange:"",
                query: {
                    current: 1,
                    size: 10,
                    startTime:null,
                    endTime:null,
                    userId:"",
                    mobile:"",
                    carnNo:""
                },
                loading: false,
                total: 0,
                userList: [],

            }
        },
        created() {
            this.handleList();
        },
        components: {},
        watch:{
          /*  queryDateRange:function(value){
                if(value){
                    this.query.startTime=value[0];
                    this.query.endTime=value[1];
                }else{
                    this.query.startTime="";
                    this.query.endTime="";
                }
            },*/
        },
        methods: {
            doQuery(){
                this.handleList();
            },
            changeDateRange:function(value){
                if(value){
                    this.query.startTime=value[0];
                    this.query.endTime=value[1];
                }else{
                    this.query.startTime="";
                    this.query.endTime="";
                }
            },
            handleSizeChange(val) {
                this.query.size = val;
                this.handleList();
            },
            handleCurrentChange(val) {
                this.query.current = val;
                this.handleList();
            },
            /**
             * @title 获取数据
             * @detail 赋值为tableData表格即可
             *
             **/
            handleList() {
                this.loading = true;
                getUserList(this.query)
                    .then(res => {
                        const data = res.data.data;
                        setTimeout(() => {
                            this.userList = data.records;
                            this.total = data.total;
                            this.loading = false;
                        }, 1000);
                    });
            },
            /**
             * 重置秘密吗
             */
            handleResetPassword(userId){
                this.loading = true;
                this.$confirm(`是否确认删除?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    resetPassword(userId)
                        .then(res => {
                            this.loading = false;
                            this.$notify({
                                title:'成功',
                                duration:2000,
                                message: '重置密码成功',
                                type: 'success',
                            });
                        });

                }).catch(() => { });

            }
        }
    }


</script>
