<template>
    <a-modal destroyOnClose v-model="addOrUpdateModal" width="800px" title="添加/修改参数" centered @ok="addOrUpdate" :maskClosable="maskClosable" cancelText="关闭" okText="保存">
        <a-form-model layout="inline" ref="addOrUpdateForm" :model="addOrUpdateModel" :rules="rules" >
            <a-row>
                <a-col :span="12">
                    <a-form-model-item label="名称" prop="name" ref="name">
                        <a-input placeholder="请输入名称" v-model="addOrUpdateModel.name" />
                    </a-form-model-item>
                </a-col>
                <a-col :span="12">
                    <a-form-model-item label="编码" prop="code" ref="code">
                        <a-input placeholder="请输入编码" v-model="addOrUpdateModel.code" />
                    </a-form-model-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="24">
                    <a-form-model-item label="值" prop="value" ref="value">
                        <a-input placeholder="请输入值" v-model="addOrUpdateModel.value" />
                    </a-form-model-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col id="remark" :span="24">
                    <a-form-model-item label="备注" prop="remark" ref="remark">
                        <a-textarea  placeholder="请输入备注" :rows="4" v-model="addOrUpdateModel.remark"/>
                    </a-form-model-item>
                </a-col>
            </a-row>
        </a-form-model>
    </a-modal>
</template>

<script lang="ts">
    import {Component,  Vue} from 'vue-property-decorator';
    import axios from "@lion/lion-front-core/src/network/axios";
    import { message } from 'ant-design-vue';
    @Component({})
    export default class addOrUpdate extends Vue{
        //点击阴影层是否关闭窗口
        private maskClosable:boolean=false;
        //是否显示窗口
        private addOrUpdateModal:boolean=false;
        //添加/修改数据模型
        private addOrUpdateModel:any={}
        //校验规则
        private rules:any={
            code:[{required:true,validator:this.checkCodeIsExist,trigger:'blur'}]
        };

        /**
         * 检查编码是否存在
         * @param rule
         * @param value
         * @param callback
         */
        private checkCodeIsExist(rule :any, value:string, callback:any):void{
            if (!value || value.trim() === ''){
                callback(new Error('请输入编码'));
                return;
            }else if (value && value.trim() !== ''){
                axios.get("/common/parameter/console/check/code/exist",{params:{"code":this.addOrUpdateModel.code,"id":this.addOrUpdateModel.id}})
                    .then((data)=> {
                        if (Object(data).status !== 200){
                            callback(new Error('异常错误！请检查'));
                            return;
                        }
                        if (data.data.isExist) {
                            callback(new Error('编码已存在'));
                        }else {
                            callback();
                        }
                    })
                    .catch(fail => {
                    })
                    .finally(()=>{
                    });
                return;
            }
            callback();
        }

        /**
         * 提交数据
         */
        private addOrUpdate():void{
            (this.$refs.addOrUpdateForm as any).validate((validate: boolean) => {
                if (validate) {
                    if (this.addOrUpdateModel.id){
                        axios.put("/common/parameter/console/update",this.addOrUpdateModel)
                            .then((data) =>{
                                if (Object(data).status === 200){
                                    message.success(Object(data).message);
                                    this.success();
                                }
                            }).catch((fail)=>{
                        }).finally(()=>{
                        })
                    }else {
                        axios.post("/common/parameter/console/add",this.addOrUpdateModel)
                            .then((data) =>{
                                if (Object(data).status === 200){
                                    message.success(Object(data).message);
                                    this.success();
                                }
                            }).catch((fail)=>{
                        }).finally(()=>{
                        })
                    }
                }
            });
        }

        /**
         * 获取详情
         * @param id
         */
        private getDetails(id:string):void{
            axios.get("/common/parameter/console/details",{params:{"id":id}})
                .then((data)=>{
                    if (Object(data).status === 200 && data.data.parameter){
                        let parameter = data.data.parameter;
                        this.addOrUpdateModel=parameter;
                        this.addOrUpdateModal=true;
                    }
                })
                .catch(fail => {
                })
                .finally(()=>{
                });
        }

        /**
         * 提交数据成功后事件
         */
        private success():void{
            this.addOrUpdateModal = false;
            this.addOrUpdateModel={};
            (this.$parent as any).search();
        }

    }
</script>

<style scoped>
    #remark >>> .ant-form-item-control-wrapper{
        width: calc(100% - 80px);
    }
    #remark >>> .ant-form-item{
        width: 100%;
    }
    .ant-form-item >>> .ant-form-item-label{
        width: 80px;
    }
    .ant-form-item{
        width: 100%;
    }
    .ant-row >>> .ant-form-item-control-wrapper{
        width: calc(100% - 80px);
    }
</style>