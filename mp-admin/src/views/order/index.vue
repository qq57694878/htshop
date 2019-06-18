<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title am-cf">订单列表</div>
                    </div>
                    <div class="widget-body am-fr">
                        <div class="page_toolbar am-margin-bottom-xs am-cf">
                                <div class="am-u-sm-12 am-u-md-12">
                                    <div class="am fr">
                                        <div class="am-form-group am-fl">
                                            <div class="am-input-group ">
                                                <el-input v-model="queryForm.userId" placeholder="请输入用户id"></el-input>
                                            </div>
                                        </div>
                                        <div class="am-form-group am-fl">
                                        <div class="am-input-group ">
                                            <el-input v-model="queryForm.mobile" placeholder="请输入用户电话"></el-input>
                                        </div>
                                    </div>
                                        <div class="am-form-group am-fl">
                                            <div class="am-input-group ">
                                                <el-date-picker
                                                        v-model="queryDateRange"
                                                        type="daterange"
                                                        start-placeholder="开始日期"
                                                        end-placeholder="结束日期"
                                                        :default-time="['00:00:00', '23:59:59']"
                                                        :picker-options="pickerOptions"
                                                        @change="changeDateRange"
                                                        value-format="yyyy-MM-dd HH:mm:ss"
                                                       >
                                                </el-date-picker>
                                            </div>
                                        </div>

                                        <div class="am-form-group am-fl">
                                            <el-select v-model="queryForm.orderStatus" clearable placeholder="请选择订单状态">
                                                <el-option
                                                        v-for="item in orderStatusOptions"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value">
                                                </el-option>
                                            </el-select>
                                        </div>
                                        <div class="am-form-group am-fl">
                                            <div class="am-input-group ">
                                                <el-input v-model="queryForm.orderNo" placeholder="请输入订单编号"></el-input>
                                                <div class="am-input-group-btn">
                                                    <button class="am-btn am-btn-default am-icon-search" type="button" @click="doQuery"></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </div>

                        <div class="order-list am-scrollable-horizontal am-u-sm-12 am-margin-top-xs">
                            <table width="100%" class="am-table am-table-centered
                        am-text-nowrap am-margin-bottom-xs"  v-loading="loading">
                                <thead>
                                <tr>
                                    <th width="40%" class="goods-detail">商品信息</th>
                                    <th>金额</th>
                                    <th>买家</th>
                                    <th>订单状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <template v-for="item_order in orderList" >

                                    <tr class="order-empty" >
                                        <td colspan="6"></td>
                                    </tr>
                                    <tr>
                                        <td class="am-text-middle am-text-left" colspan="6">
                                            <span class="am-margin-right-lg"> 下单时间：{{ item_order.createTime |moment('YYYY-MM-DD HH:mm:ss')}}</span>
                                            <span class="am-margin-right-lg">订单号：{{ item_order.orderNo}}</span>
                                        </td>
                                    </tr>


                                    <tr v-for="(sku,i) in item_order['skuList']" :key="sku.skuId">

                                        <td class="goods-detail am-text-middle">
                                            <div class="goods-image">
                                                <img :src="sku['mainUrl']" alt="">
                                            </div>
                                            <div class="goods-info">
                                                <p class="goods-title">{{ sku['skuName'] }}</p>
                                                <p>￥{{ sku['skuPrice'] }} ×{{ sku['buyNum'] }}</p>
                                            </div>
                                        </td>

                                    <template v-if="i==0">
                                        <td class="am-text-middle" :rowspan="item_order['skuList'].length">
                                            <p>实付款：￥{{ item_order['payPrice']|formatMoney }}</p>
                                            <p class="am-link-muted">(订单金额：￥{{ item_order['totalPrice'] |formatMoney}})</p>
                                        </td>
                                        <td class="am-text-middle" :rowspan="item_order['skuList'].length">
                                            <p>{{item_order['nickname']}}</p>
                                            <p class="am-link-muted">电话：{{item_order['mobile']}}</p>
                                            <p class="am-link-muted">(用户id：{{item_order['userId']}})</p>
                                        </td>
                                        <td class="am-text-middle" :rowspan="item_order['skuList'].length">
                                            <p>订单状态：
                                                <span class="am-badge"
                                                      :class="[item_order.orderStatus=='8' ? 'am-badge-success' : '']">
                                                        {{ item_order.orderStatus | code2value('order_status') }}</span>
                                            </p>
                                        </td>
                                        <td class="am-text-middle" :rowspan="item_order['skuList'].length">
                                            <div class="tpl-table-black-operation">
                                                <router-link class="tpl-table-black-operation-green" :to="{path:`/order/detail/${item_order['orderId']}`,query:{orderNo:`${item_order['orderNo']}`}}">
                                                    订单详情
                                                </router-link>
                                            </div>
                                        </td>
                                    </template>


                                    </tr>


                                </template>

                                <tr v-if="orderList.length==0">
                                    <td colspan="6" class="am-text-center">暂无记录</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                        <div class="am-u-lg-12 am-cf">
                            <div class="am-fr">
                                <el-pagination
                                        @size-change="handleSizeChange"
                                        @current-change="handleCurrentChange"
                                        :current-page="queryForm.current"
                                        :page-sizes="[10, 20, 50, 100]"
                                        :page-size="queryForm.size"
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

<script>
    import {type2options} from '@/util/codeTable'
    import {getOrderList} from '@/api/order'
    export default {
        name: "order",
        components: {},
        data() {
            return {
                queryForm:{
                    startTime:null,
                    endTime:null,
                    orderStatus:"",
                    userId:"",
                    mobile:"",
                    orderNo:""
                },
                loading: false,
                total: 0,
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
                orderStatusOptions: type2options("order_status"),
                page: {
                    total: 0, //总页数
                    currentPage: 1, //当前页数
                    pageSize: 10 //每页显示多少条
                },
                orderList: []
            };
        },
        created() {
            this.handleList();
        },
        watch: {},
        mounted() {
        },
        computed: {},
        props: [],
        methods: {
            doQuery(){
                this.handleList();
            },
            changeDateRange:function(value){
                if(value){
                    this.queryForm.startTime=value[0];
                    this.queryForm.endTime=value[1];
                }else{
                    this.queryForm.startTime="";
                    this.queryForm.endTime="";
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
                console.log(this.queryForm);
                this.loading = true;
                getOrderList(this.queryForm)
                    .then(res => {
                        const data = res.data.data;
                        setTimeout(() => {
                            this.orderList = data.records;
                            this.total = data.total;
                            this.loading = false;
                        }, 1000);
                    });
            }

        }
    };
</script>

<style lang="scss" scoped>
    .table-container {
        padding: 8px 10px;
    }
</style>
