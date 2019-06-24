<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title am-cf">卡券消费管理</div>
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
                                                value-format="yyyy-MM-dd HH:mm:ss">
                                        </el-date-picker>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.userId" placeholder="请输入用户id"></el-input>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.mobile" placeholder="请输入用户电话"></el-input>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.carNo" placeholder="请输入车牌号"></el-input>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <el-select v-model="query.validFlag" clearable placeholder="请选择有效标记">
                                        <el-option
                                                v-for="item in validFlagOptions"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.cardName" placeholder="卡名"></el-input>
                                    </div>
                                </div>
                                <div class="am-form-group am-fl">
                                    <el-select v-model="query.catagory" clearable placeholder="卡片类型">
                                        <el-option
                                                v-for="item in catagoryOptions"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </div>
                                <div class="am-form-group am-fl">
                                    <div class="am-input-group ">
                                        <el-input v-model="query.cardNo" placeholder="请输入卡号"></el-input>
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
                                    <th>卡号</th>
                                    <th>卡名</th>
                                    <th>卡片类型</th>
                                    <th>总次数</th>
                                    <th>剩余次数</th>
                                    <th>有效标记</th>
                                    <th>所属用户id</th>
                                    <th>用户电话</th>
                                    <th>用户车牌号</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr  v-for="cardInfo in cardList" :key="cardInfo.cardNo">
                                    <td class="am-text-middle">{{ cardInfo['cardNo'] }}</td>
                                    <td class="am-text-middle">{{ cardInfo['cardName'] }}</td>
                                    <td class="am-text-middle">{{ cardInfo['catagory']|code2value('catagory') }}</td>
                                    <td class="am-text-middle">{{ cardInfo['totalFrequency'] }}</td>
                                    <td class="am-text-middle">{{ cardInfo['restFrequency'] }}</td>
                                    <td class="am-text-middle">{{ cardInfo['validFlag'] |code2value('valid_flag') }}</td>
                                    <td class="am-text-middle">{{ cardInfo['userId'] }}</td>
                                    <td class="am-text-middle">{{ cardInfo['mobile']  }}</td>
                                    <td class="am-text-middle">{{ cardInfo['carNo']  }}</td>
                                    <td class="am-text-middle">{{ cardInfo['createTime']|moment('YYYY-MM-DD HH:mm:ss') }}</td>
                                    <td class="am-text-middle">
                                        <div class="tpl-table-black-operation">
                                            <a v-if="cardInfo['validFlag']==1" href="javascript:;" @click="showDialog(cardInfo)" class="tpl-table-black-operation-green">
                                                <i class="am-icon-money"></i> 消费
                                            </a>
                                           <del v-else> <a  href="javascript:;" class="tpl-table-black-operation-green">
                                                <i class="am-icon-money"></i> 消费
                                            </a>
                                           </del>
                                        </div>
                                    </td>
                                </tr>

                                <tr v-if="cardList.length==0">
                                    <td colspan="10" class="am-text-center">暂无记录</td>
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

        <el-dialog title="次数卡券消费"
                   :visible.sync="show1Dialog"
                   width="50%" :rules="rulesFrequency"
                   append-to-body>
            <el-form :model="frequencyForm" class="tpl-form-line-form"
                     ref="frequencyForm"
                     label-width="120px">
                <div class="am-g ">
                    <div class="am-u-sm-3">
                        <el-form-item label="卡号：" prop="cardNo">
                            {{frequencyForm.cardNo}}
                        </el-form-item>
                    </div>
                    <div class="am-u-sm-3">
                        <el-form-item label="本卡剩余次数：" prop="restFrequency">
                            <b style="color:#FF7D41">{{frequencyForm.restFrequency}}</b>
                        </el-form-item>
                    </div>

                    <div class="am-u-sm-6">
                        <el-form-item label="卡名：" prop="cardName">
                            {{frequencyForm.cardName}}
                        </el-form-item>
                    </div>
                </div>


                <el-form-item label="本次消费次数" prop="usedFrequency">
                    <el-input v-model.number="frequencyForm.usedFrequency"   class="tpl-form-input"
                              placeholder="请输入本次消费次数"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handleConsumeFrequencyCard">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>
<style>

</style>
<script>
    import {getCardList,consumeECard,consumeFrequencyCard} from '@/api/card'
    import {type2options} from '@/util/codeTable'

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
                    carNo:"",
                    cardNo:"",
                    mobile:"",
                    validFlag:"",
                    cardName:""
                },
                show1Dialog:false,
                show2Dialog:false,
                frequencyForm:{
                    cardNo:"",
                    cardName:"",
                    restFrequency:"",
                    usedFrequency:1,
                },

                validFlagOptions:type2options('valid_flag'),
                catagoryOptions:type2options('catagory'),
                loading: false,
                total: 0,
                cardList: [],
                rulesFrequency:{
                    usedFrequency:[{ required: true, message: '本次消费次数不能为空'},
                        { validator:function(rule,value,callback){
                            if(parseInt(value)<0){
                                callback(new Error('本次消费次数必须大于0'));
                            }else{
                                callback();
                            }
                        }, trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.handleList();
        },
        components: {},
        watch:{
        },
        methods: {
            showDialog(cardInfo){

                    this.frequencyForm={
                        cardNo:cardInfo.cardNo,
                        cardName:cardInfo.cardName,
                        restFrequency:cardInfo.restFrequency,
                        usedFrequency:1,
                    };
                    this.show1Dialog=true;

            },
            handleConsumeFrequencyCard(){
                this.$refs['frequencyForm'].validate((valid) => {
                    if (valid) {
                        if(this.frequencyForm.usedFrequency>parseInt(this.frequencyForm.restFrequency)){
                            this.$message.success('本次消费次数大于剩余次数');
                            return;
                        }
                        this.loading = true;
                        consumeFrequencyCard({cardNo:this.frequencyForm.cardNo,usedFrequency:this.frequencyForm.usedFrequency})
                            .then(res => {
                                if(res.data.code==200){
                                    this.$notify({
                                        title:'成功',
                                        duration:3000,
                                        message: '消费成功',
                                        type: 'success',
                                    });
                                    this.handleList();
                                }else{
                                    this.$notify({
                                        showClose: true,
                                        message: '消费失败',
                                        type: 'error',
                                    });
                                }
                                this.show1Dialog=false;
                                this.loading = false;
                            });
                    }
                })
            },

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
                getCardList(this.query)
                    .then(res => {
                        const data = res.data.data;
                            this.cardList = data.records;
                            this.total = data.total;
                            this.loading = false;
                    });
            }
        }
    }


</script>
