<template>
    <a-modal v-model:visible="addOrUpdateModal" width="800px" title="添加/修改参数" @cancel="cancel" centered @ok="addOrUpdate" :maskClosable="maskClosable" cancelText="关闭" okText="保存">
        <a-form  ref="addOrUpdateForm" :model="addOrUpdateModel" :rules="rules" >
            <a-row>
                <a-col :span="12">
                    <a-form-item label="名称" name="name" ref="name">
                        <a-input placeholder="请输入名称" v-model:value="addOrUpdateModel.name" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="编码" name="code" ref="code">
                        <a-input placeholder="请输入编码" v-model:value="addOrUpdateModel.code" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="24">
                    <a-form-item label="值" name="value" ref="value">
                        <a-input placeholder="请输入值" v-model:value="addOrUpdateModel.value" />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row>
                <a-col id="remark" :span="24">
                    <a-form-item label="备注" name="remark" ref="remark">
                        <a-textarea  placeholder="请输入备注" :rows="4" v-model:value="addOrUpdateModel.remark"/>
                    </a-form-item>
                </a-col>
            </a-row>
        </a-form>
    </a-modal>
</template>

<script lang="ts">
    import {Options,  Vue} from 'vue-property-decorator';
    import axios from "@lion/lion-frontend-core/src/network/axios";
    import { message } from 'ant-design-vue';
    @Options({components:{}})
    export default class addOrUpdate extends Vue{
        //点击阴影层是否关闭窗口
        private maskClosable:boolean=false;
        //是否显示窗口
        private addOrUpdateModal:boolean=false;
        //添加/修改数据模型
        private addOrUpdateModel:any={}
        //校验规则
        private rules:any={
            code:[{required:true,validator: (rule,value)=>{return this.checkCodeIsExist(rule,value,this)},trigger:'blur'}]
        };

        /**
         * 检查编码是否存在
         * @param rule
         * @param value
         * @param callback
         */
        private async checkCodeIsExist(rule :any, value:string, _this:any){
          let promise:any = null;
          if (!value || value.trim() === ''){
            promise = Promise.reject('请输入编码');
          }else if (value && value.trim() !== ''){
            await axios.get("/lion-common-console-restful/parameter/console/check/code/exist",{params:{"code":_this.addOrUpdateModel.code,"id":_this.addOrUpdateModel.id}})
              .then((data)=> {
                  if (Object(data).status !== 200){
                    promise= Promise.reject("异常错误！请检查")
                  }
                  if (data.data) {
                    promise= Promise.reject("编码已存在")
                  }else {
                    promise = Promise.resolve();
                  }
              })
              .catch(fail => {
              })
              .finally(()=>{
              });
          }
          return promise;
        }

        /**
         * 提交数据
         */
        private addOrUpdate():void{
          (this.$refs.addOrUpdateForm as any).validate().then(()=>{
            if (this.addOrUpdateModel.id){
              axios.put("/lion-common-console-restful/parameter/console/update",this.addOrUpdateModel)
                  .then((data) =>{
                    if (Object(data).status === 200){
                      message.success(Object(data).message);
                      this.success();
                    }
                  }).catch((fail)=>{
              }).finally(()=>{
              })
            }else {
              axios.post("/lion-common-console-restful/parameter/console/add",this.addOrUpdateModel)
                  .then((data) =>{
                    if (Object(data).status === 200){
                      message.success(Object(data).message);
                      this.success();
                    }
                  }).catch((fail)=>{
              }).finally(()=>{
              })
            }
          }).catch(fail=>{}).finally(()=>{})
        }

        /**
         * 获取详情
         * @param id
         */
        private getDetails(id:string):void{
            axios.get("/lion-common-console-restful/parameter/console/details",{params:{"id":id}})
                .then((data)=>{
                    if (Object(data).status === 200){
                        let parameter = data.data;
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

      /**
       * 关闭弹窗时清空数据，以免数据污染
       * @private
       */
      private cancel():void {
        (this.$refs.addOrUpdateForm as any).clearValidate();
        (this.$refs.addOrUpdateForm as any).resetFields();
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