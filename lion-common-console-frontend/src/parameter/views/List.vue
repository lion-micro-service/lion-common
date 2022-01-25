<template>
    <div>
        <a-card class="card" style="border-bottom-width: 5px;">
            <a-form ref="form" :model="searchModel" >
                <a-row>
                    <a-col :span="6">
                        <a-form-item label="编码" name="code" ref="code" >
                            <a-input placeholder="请输入编码" v-model:value="searchModel.code" />
                        </a-form-item>
                    </a-col>
                    <a-col :span="6">
                        <a-form-item label="名称" name="email" ref="name">
                            <a-input placeholder="请输入名称" v-model:value="searchModel.name" />
                        </a-form-item>
                    </a-col>
                    <a-col :span="6">
                        <a-form-item label="值" name="value" ref="value">
                            <a-input-number  placeholder="请输入值" v-model:value="searchModel.value" />
                        </a-form-item>
                    </a-col>
                </a-row>

                <a-row >
                    <a-col :span="24" style="text-align:right;">
                        <a-form-item>
                          <a-space :size="size">
                            <a-button type="primary" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')" @click="()=>{this.searchModel.pageNumber =1;search()}">
                              <template #icon><SearchOutlined /></template>
                              查询
                            </a-button>
                            <a-button type="primary" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_ADD')" @click="add()">
                              <template #icon><PlusOutlined /></template>
                              新增
                            </a-button>
                            <a-button type="danger" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_DELETE')" @click="del(null)">
                              <template #icon><DeleteOutlined /></template>
                              删除
                            </a-button>
                          </a-space>
                        </a-form-item>
                    </a-col>
                </a-row>
            </a-form>
        </a-card>

        <a-card v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')" class="card" :bordered="false">
            <a-table bordered :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }" rowKey="id" :columns="columns" :dataSource="listData" :loading="loading" :pagination="paginationProps">
              <template #bodyCell="{ column ,record}">
                <template v-if="column.key === 'operation'">
                  <a-space :size="size">
                    <a-button size="small" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_UPDATE')" @click="edit(record.id)">
                      <template #icon><EditOutlined /></template>
                      修改
                    </a-button>
                    <a-button size="small" type="danger" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_DELETE')" @click="del(record.id)">
                      <template #icon><DeleteOutlined /></template>
                      删除
                    </a-button>
                  </a-space>
                </template>
              </template>
            </a-table>
        </a-card>

        <add-or-update ref="addOrUpdate"></add-or-update>
    </div>
</template>

<script lang="ts">
    import {Options, Vue, Watch} from 'vue-property-decorator';
    import { SearchOutlined,PlusOutlined,DeleteOutlined,EditOutlined,SecurityScanOutlined,UserOutlined } from '@ant-design/icons-vue';
    import {  ref } from 'vue';
    import axios from "@lion/lion-frontend-core/src/network/axios";
    import { message,Modal } from 'ant-design-vue';
    import addOrUpdate from "@/parameter/components/addOrUpdate.vue";
    import qs from "qs";
    import authority from "@lion/lion-frontend-core/src/security/authority";
    @Options({components:{addOrUpdate,SearchOutlined,PlusOutlined,DeleteOutlined,EditOutlined,SecurityScanOutlined,UserOutlined}})
    export default class list extends Vue{
      private size:any = ref(5);
        //查询数据模型
        private searchModel:any={
            pageNumber:1,
            pageSize:10
        };
        //列表复选框选中的值
        private selectedRowKeys:Array<number> = [];
        //列表数据源
        private listData:Array<any> = [];
        //列表是否加载中（转圈圈）
        private loading:boolean=false;
        //列表表头定义
        private columns :Array<any> = [
            { title: '名称', dataIndex: 'name', key: 'name'},
            { title: '编码', dataIndex: 'code', key: 'code' },
            { title: '值', dataIndex: 'value', key: 'value'},
            { title: '备注', dataIndex: 'remark', key: 'remark'},
            { title: '操作', key: 'operation', width: 200,}
        ];
        //列表分页参数定义
        private paginationProps:any={
            showSizeChanger: false,
            showQuickJumper: true,
            hideOnSinglePage:false,
            pageSizeOptions:['10', '20', '30', '40','50','60','70','80','90','100'],
            total:0,
            current:1,
            pageSize:10,
            showSizeChange: (pageNumber:number, pageSize: number)=>this.paginationSearch(pageNumber,pageSize),
            onChange: (pageNumber:number, pageSize: number)=>this.paginationSearch(pageNumber,pageSize),
        };
        /**
         * 列表复选框改变事件
         * @param selectedRowKeys
         */
        private onSelectChange(selectedRowKeys:Array<number>):void{
            this.selectedRowKeys = selectedRowKeys;
        }
        /**
         * 分页上/下页，跳转到第几页触发事件
         * @param pageNumber
         * @param pageSize
         */
        private paginationSearch(pageNumber:number, pageSize: number):void{
            this.searchModel.pageNumber=pageNumber;
            this.searchModel.pageSize=pageSize;
            this.search();
        }

        /**
         * 查询
         */
        private search():void{
            if (!this.getAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')){
                return;
            }
            this.loading=true;
            axios.get("/lion-common-console-restful/parameter/console/list",{params:this.searchModel})
                .then((data)=>{
                    this.listData=data.data;
                    this.paginationProps.total=Number((Object(data)).totalElements);
                    this.paginationProps.current=(Object(data)).pageNumber;
                    this.paginationProps.pageSize=(Object(data)).pageSize;
                })
                .catch(fail => {
                })
                .finally(()=>{
                    this.loading=false;
                });
        }

        /**
         * 组件挂载后触发事件
         */
        public mounted() {
            this.search();
        }

        /**
         * 弹出新增窗口
         */
        private add():void{
            let child = this.$refs.addOrUpdate as any;
            child.addOrUpdateModel={};
            child.addOrUpdateModal=true;
        }

        /**
         * 弹出修改窗口
         * @param id
         */
        private edit(id:string):void{
            const child = (this.$refs.addOrUpdate as any);
            child.getDetails(id);
        }

        /**
         * 弹出删除警示
         * @param id
         */
        private del(id:any):void{
            const _this =this;
            if (!id){
                if (this.selectedRowKeys.length <=0 ){
                    message.error("请选择要删除的数据");
                    return;
                }else{
                    id = this.selectedRowKeys;
                }
            }
          Modal.confirm({
                title: '是否要删除该数据?',
                // content: '',
                okText: 'Yes',
                okType: 'danger',
                cancelText: 'No',
                onOk() {
                    _this.delete(id);
                },
                onCancel() {
                },
            });

        }

        /**
         * 删除
         * @param id
         */
        private delete(id:any):void{
            axios.delete("/lion-common-console-restful/parameter/console/delete",{params:{id:id},
                paramsSerializer: params => {
                    return qs.stringify(params, { indices: false })
                }})
                .then((data)=>{
                    if((Object(data)).status === 200 && (Object(data)).message){
                        message.success((Object(data)).message);
                        this.search();
                    }
                }).catch((fail)=>{
            }).finally(()=>{
                this.selectedRowKeys=[];
            });
        }

        /**
         * 判断(获取)是否有权限
         */
        private getAuthority(authorityCode:string):any{
            return authority(authorityCode);
        }
    }
</script>

<style lang="css" scoped>
    .ant-form-item >>> .ant-form-item-label{
        width: 50px;
    }
    .ant-form-item{
        width: 100%;
    }
    .ant-row >>> .ant-form-item-control{
        width: calc(100% - 50px);
    }
    .ant-card >>> .ant-card-body{
      padding-bottom: 0px;
    }
</style>