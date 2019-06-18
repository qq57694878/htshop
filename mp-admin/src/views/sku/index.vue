<template>
    <div class="row-content am-cf">

        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title am-cf">商品管理</div>
                    </div>
                    <div class="widget-body am-fr">
                        <div class="page_toolbar am-margin-bottom-xs am-cf">
                            <form class="toolbar-form" action="">
                                <input type="hidden" name="s" value="/store/goods/index">
                                <div class="am-u-sm-12 am-u-md-3">
                                    <div class="am-form-group">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <router-link :to="{path:`/sku/add`}" class="am-btn am-btn-default am-btn-success">
                                                <span class="am-icon-plus"></span> 新增
                                            </router-link>

                                        </div>
                                    </div>
                                </div>
                                <div class="am-u-sm-12 am-u-md-9">
                                    <div class="am fr">

                                        <div class="am-form-group am-fl">
                                            <el-select v-model="query.skuStatus" clearable placeholder="请选择商品状态">
                                                <el-option
                                                        v-for="item in skuStatusOptions"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value">
                                                </el-option>
                                            </el-select>
                                        </div>
                                        <div class="am-form-group am-fl">
                                            <div class="am-input-group ">
                                                <el-input v-model="query.skuName" placeholder="请输入商品名称"></el-input>
                                                <div class="am-input-group-btn">
                                                    <button class="am-btn am-btn-default am-icon-search" type="button" @click="doQuery"></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>



                        <div class="am-scrollable-horizontal am-u-sm-12">
                            <table width="100%" class="am-table am-table-compact am-table-striped
                         tpl-table-black am-text-nowrap" v-loading="loading">
                                <thead>
                                <tr>
                                    <th>商品ID</th>
                                    <th>商品图片</th>
                                    <th>商品名称</th>
                                    <th>实际销量</th>
                                    <th>商品排序</th>
                                    <th>商品状态</th>
                                    <th>添加时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="$item in skuList" :key="$item.skuId">
                                    <td class="am-text-middle">{{$item['skuId']}}</td>
                                    <td class="am-text-middle">
                                        <a :href="$item['main_url']"
                                           title="点击查看大图" target="_blank">
                                            <img :src="$item['mainUrl']"
                                                 width="50" height="50" alt="商品图片">
                                        </a>
                                    </td>
                                    <td class="am-text-middle">
                                        <p class="item-title">{{ $item['skuName'] }}</p>
                                    </td>
                                    <td class="am-text-middle">{{ $item['salesNum'] }}</td>
                                    <td class="am-text-middle">{{ $item['sort'] }}</td>
                                    <td class="am-text-middle">
                                            <span :class="$item['skuStatus'] == 1 ? 'x-color-green'
                                                : 'x-color-red'">
                                            {{ $item['skuStatus'] | code2value('sku_status') }}
                                            </span>
                                    </td>
                                    <td class="am-text-middle">{{ $item['createTime'] | moment('YYYY-MM-DD HH:mm:ss')}}
                                    </td>
                                    <td class="am-text-middle">
                                        <div class="tpl-table-black-operation">
                                            <router-link :to="{path:`/sku/edit/${$item['skuId']}`,query:{skuName:`${$item['skuName']}`}}">
                                                <i class="am-icon-pencil"></i> 编辑
                                            </router-link>

                                            <a href="javascript:;" @click="handleDelete($item['skuId'])" class="item-delete tpl-table-black-operation-del">
                                                <i class="am-icon-trash"></i> 删除
                                            </a>
                                            <router-link :to="{path:`/sku/add`,query:{skuId:`${$item['skuId']}`}}">
                                                <i class="am-icon-copy"></i> 一键复制
                                            </router-link>
                                        </div>
                                    </td>
                                </tr>

                                <tr v-if="skuList.length==0">
                                    <td colspan="9" class="am-text-center">暂无记录</td>
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
    import {getSkuList,deleteSku} from '@/api/sku'
    import {type2options} from '@/util/codeTable'
    export default {
        data() {
            return {
                skuStatusOptions: type2options("sku_status"),
                query: {
                    current: 1,
                    size: 10,
                    skuName: "",
                    skuStatus: ""
                },
                loading: false,
                total: 0,
                skuList: [],
                /*   skuList: [
                 {
                 skuName: "保养机油套餐",
                 createTime: new Date().getTime(),
                 skuStatus: "1",
                 salesNum: 100,
                 sort: 1,
                 skuId: 100001,
                 mainUrl: "http://www.kulongtai.com/1.jpg"
                 },
                 {
                 skuName: "保养机油套餐1",
                 createTime: new Date().getTime(),
                 skuStatus: "2",
                 salesNum: 101,
                 sort: 2,
                 skuId: 100002,
                 mainUrl: "http://www.kulongtai.com/2.jpg"
                 }
                 ]*/
            }
        },
        created() {
            this.handleList();
        },
        components: {},
        methods: {
            doQuery(){
                this.handleList();
            },
            handleDelete(id){
                this.$confirm(`是否确认删除?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    deleteSku(id).then(res =>{
                        if(res.data.data){
                            this.handleList();
                    this.$notify({
                        title:'成功',
                        duration:2000,
                        message: '删除成功',
                        type: 'success',
                    });
                }else{
                    this.$notify({
                        title:'失败',
                        duration:2000,
                        message: '删除失败',
                        type: 'error',
                    });
                }

                });

            }).catch(() => { });
            },
            handleSizeChange(val) {
                this.query.size = val;
                console.log(`每页 ${val} 条`);
                this.handleList();
            },
            handleCurrentChange(val) {
                this.query.current = val;
                console.log(`当前页: ${val}`);
                this.handleList();
            },
            /**
             * @title 获取数据
             * @detail 赋值为tableData表格即可
             *
             **/
            handleList() {
                console.log(this.query);
                this.loading = true;
                getSkuList(this.query)
                    .then(res => {
                        const data = res.data.data;
                        setTimeout(() => {
                            this.skuList = data.records;
                            this.total = data.total;
                            this.loading = false;
                        }, 1000);
                    });
            }
        }
    }


</script>
