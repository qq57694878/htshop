<template>
    <div>
        <basic-container>
            <avue-tabs :option="option"
                       v-model="form"
                       @change="handleChange"
                       @submit="handleSubmit"></avue-tabs>
        </basic-container>
    </div>
</template>

<script>
    import option from '@/const/info/form'
    import {modifyPassword} from '@/api/user'

    export default {
        data() {
            return {
                type: 'info',
                option: option,
                form: {},
            }
        },
        created() {
            this.handleWitch();
        },
        methods: {
            handleSubmit() {
                if(this.type === 'info'){
                    this.$message({
                        message: "操作成功",
                        type: "success"
                    });
                    return;
                }
                if (this.form.newpassword != this.form.newpasswords) {
                    this.$message({
                        message: "两次输入的密码不一致",
                        type: "error"
                    });
                    return;
                }
                if (this.form.newpassword.length < 8) {
                    this.$message({
                        message: "新密码至少8位",
                        type: "error"
                    });
                    return;
                }


                this.$confirm(`是否确定修改密码?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }).then(() => {
                    this.loading = true;
                    modifyPassword({oldpassword:this.form.oldpassword,newpassword:this.form.newpassword}).then(res => {
                            this.loading = false;
                            if(res.data.code==200){
                                this.$message({
                                    message: '修改密码成功',
                                    type: "success"
                                });
                            }else{
                                this.$notify({
                                    showClose: true,
                                    message: res.data.msg,
                                    type: 'error',
                                });
                            }
                        });

                }).catch(() => {
                });


            },
            handleWitch() {
                if (this.type === 'info') {
                    this.form = {
                        username: 'admin',
                        name: 'admin',
                        phone: '1888888888888',
                        detail: '这是一个个性签名'
                    }

                } else if (this.type === 'password') {
                    this.form = {
                        oldpassword: "",
                        newpassword: "",
                        newpasswords: ""
                    }
                }
            },
            handleChange(item) {
                this.type = item.prop;
                this.handleWitch();
            },
        }
    };
</script>

<style>
</style>
