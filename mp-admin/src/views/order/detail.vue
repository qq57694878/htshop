<template>
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-body  am-margin-bottom-lg">
                        <div class="am-u-sm-12 widget__order-detail">
                      
                            <ul class="order-detail-progress" :class="progressn">
                                <li>
                                    <span>下单时间</span>
                                    <div class="tip">{{orderDetail['createTime'] |moment('YYYY-MM-DD HH:mm:ss') }}</div>
                                </li>
                                <li>
                                    <span>付款</span>
                                    <div class="tip" v-if="orderDetail['payStatus']=='1'">
                                        付款于 {{ orderDetail['payTime'] | moment('YYYY-MM-DD HH:mm:ss') }}
                                    </div>
                                </li>
                                <li>
                                    <span>完成</span>
                                    <div class="tip" v-if="orderDetail['orderStatus']=='8'">
                                        完成于 {{ orderDetail['updateTime'] | moment('YYYY-MM-DD HH:mm:ss') }}
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">基本信息</div>
                        </div>
                        <div class="am-scrollable-horizontal">
                            <table class="regional-table am-table am-table-bordered am-table-centered
                            am-text-nowrap am-margin-bottom-xs">
                                <tbody>
                                <tr>
                                    <th>订单号</th>
                                    <th>实付款</th>
                                    <th>买家</th>
                                    <th>交易状态</th>
                                </tr>
                                <tr>
                                    <td>{{ orderDetail['orderNo'] }}</td>
                                    <td>
                                        <p>实付款：￥{{ orderDetail['payPrice'] |formatMoney}}</p>
                                        <p class="am-link-muted">(订单金额：￥{{ orderDetail['totalPrice'] |formatMoney}})</p>
                                    </td>
                                    <td>
                                        <p>{{ orderDetail['nickname'] }}</p>
                                        <p class="am-link-muted">(用户电话：{{ orderDetail['mobile'] }})</p>
                                        <p class="am-link-muted">(用户id：{{ orderDetail['userId'] }})</p>
                                    </td>
                                    <td>
                                        <p>付款状态：
                                            <span class="am-badge" :class="[orderDetail['payStatus']=='1'?'am-badge-success':'']">
                                                {{ orderDetail['payStatus'] | code2value('pay_status')}}</span>
                                        </p>

                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">商品信息</div>
                        </div>
                        <div class="am-scrollable-horizontal">
                            <table class="regional-table am-table am-table-bordered am-table-centered am-text-nowrap ">
                                <tbody>
                                <tr>
                                    <th>商品名称</th>
                                    <th>单价</th>
                                    <th>购买数量</th>
                                    <th>商品总价</th>
                                </tr>

                                <tr v-for="$sku in orderDetail['skuList'] ">
                                    <td class="goods-detail am-text-middle">
                                        <div class="goods-image">
                                            <img :src="$sku['mainUrl']" alt="">
                                        </div>
                                        <div class="goods-info">
                                            <p class="goods-title">{{ $sku['skuName'] }}</p>
                                        </div>
                                    </td>

                                    <td>￥{{ $sku['skuPrice'] |formatMoney }}</td>
                                    <td>×{{ $sku['buyNum'] }}</td>
                                    <td>￥{{ $sku['skuPrice']*$sku['buyNum']|formatMoney }}</td>
                                </tr>

                                <tr>
                                    <td colspan="6" class="am-text-right">总计金额：￥{{ orderDetail['totalPrice']|formatMoney }}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>





                        <template v-if="orderDetail.payStatus=='1'">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">付款信息</div>
                            </div>
                            <div class="am-scrollable-horizontal">
                                <table class="regional-table am-table am-table-bordered am-table-centered am-text-nowrap ">
                                    <tbody>
                                    <tr>
                                        <th>应付款金额</th>
                                        <th>付款状态</th>
                                        <th>付款时间</th>
                                    </tr>
                                    <tr>
                                        <td>￥{{ orderDetail['payPrice'] |formatMoney}}</td>
                                        <td>
                                        <span class="am-badge" :class="[orderDetail['payStatus']=='1'?'am-badge-success':'']">
                                                {{ orderDetail['payStatus'] |code2value('pay_status')}}</span>
                                        </td>
                                        <td v-if="orderDetail['payTime']">
                                            {{ orderDetail['payTime'] |moment('YYYY-MM-DD HH:mm:ss') }}
                                        </td>
                                        <td v-else>
                                            --
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </template>



                    </div>
                </div>

            </div>
        </div>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    import {getOrderDetail} from '@/api/order'
    export default {
        name: "orderDetail",
        components: {},
        created:function(){
            this.orderDetail.orderId= this.$route.params.orderId;
            console.log('this.orderDetail.orderId'+this.orderDetail.orderId);
            this.handleGetOrderDetail();
        },
        data() {
            return {
                orderDetail: {
                    orderId:"",
                    mobile:"",
                    nickname:"",
                     orderId:"",
                    order_no:"",
                    totalPrice:0,
                    payPrice:0,
                    payStatus:"",
                    payTime:"",
                    orderStatus:"",
                    userId:"",
                    createTime:"",

                }
            };
        },
        watch: {},
        mounted() {
        },
        computed: {
            progressn:function(){
                let p=["progress-1","progress-2","progress-3"];
                let n = this.orderDetail.payStatus=='1'?this.orderDetail.orderStatus=='8'?2:1:0;
                return p[n];
            }
        },
        props: [],
        methods: {
            handleGetOrderDetail:function(){
                getOrderDetail(this.orderDetail.orderId).then(res=>{
                    this.orderDetail=res.data.data;
                });
            }
        }
    };
</script>

<style lang="scss" scoped>
    .am-table {
        border-collapse: inherit;
        font-size: 1.4rem;
    }
</style>
