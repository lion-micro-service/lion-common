<template>
    <div>
        <a-card class="card" style="border-bottom-width: 5px;">
            <a-form-model layout="inline" ref="form" :model="searchModel" >
                <a-row>
                    <a-col :span="6">
                        <a-form-model-item label="编码" prop="code" ref="code" >
                            <a-input placeholder="请输入编码" v-model="searchModel.code" />
                        </a-form-model-item>
                    </a-col>
                    <a-col :span="6">
                        <a-form-model-item label="名称" prop="email" ref="name">
                            <a-input placeholder="请输入名称" v-model="searchModel.name" />
                        </a-form-model-item>
                    </a-col>
                    <a-col :span="6">
                        <a-form-model-item label="值" prop="value" ref="value">
                            <a-input-number  placeholder="请输入值" v-model="searchModel.value" />
                        </a-form-model-item>
                    </a-col>
                </a-row>

                <a-row >
                    <a-col :span="24" style="text-align:right;">
                        <a-form-item>
                            <a-button style="margin-left: 5px;" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')" type="primary" icon="search"  @click="()=>{this.searchModel.pageNumber =1;search()}">查询</a-button>
                            <a-button style="margin-left: 5px;" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_ADD')" type="primary" icon="file-add" @click="add()">新增</a-button>
                            <a-button style="margin-left: 5px;" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_DELETE')" type="danger" icon="delete"  @click="del(null)">删除</a-button>
                        </a-form-item>
                    </a-col>
                </a-row>
            </a-form-model>
        </a-card>

        <a-card v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')" class="card" :bordered="false">
            <a-table bordered :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }" rowKey="id" :columns="columns" :dataSource="data" :loading="loading" :pagination="paginationProps">
                <span slot="action" slot-scope="text, record">
                    <a-button style="margin-left: 5px;" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_UPDATE')" icon="edit" size="small" @click="edit(record.id)">修改</a-button>
                    <a-button style="margin-left: 5px;" v-if="getAuthority('SYSTEM_SETTINGS_PARAMETER_DELETE')" type="danger" icon="delete" size="small" @click='del(record.id)'>删除</a-button>
                </span>
            </a-table>
        </a-card>

        <add-or-update ref="addOrUpdate"></add-or-update>
    </div>
</template>

<script lang="ts">
    import {Component, Vue, Watch} from 'vue-property-decorator';
    import axios from "@lion/lion-frontend-core/src/network/axios";
    import { message } from 'ant-design-vue';
    import addOrUpdate from "@/parameter/components/addOrUpdate.vue";
    import qs from "qs";
    import authority from "@lion/lion-frontend-core/src/security/authority";
    @Component({components:{addOrUpdate}})
    export default class list extends Vue{
        //查询数据模型
        private searchModel:any={
            pageNumber:1,
            pageSize:10
        };
        //列表复选框选中的值
        private selectedRowKeys:Array<number> = [];
        //列表数据源
        private data:Array<any> = [];
        //列表是否加载中（转圈圈）
        private loading:boolean=false;
        //列表表头定义
        private columns :Array<any> = [
            { title: '名称', dataIndex: 'name', key: 'name'},
            { title: '编码', dataIndex: 'code', key: 'code' },
            { title: '值', dataIndex: 'value', key: 'value'},
            { title: '备注', dataIndex: 'remark', key: 'remark'},
            { title: '操作', key: 'action', scopedSlots: { customRender: 'action' },width: 200,}
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
                    this.data=data.data;
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
        private mounted() {
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
            this.$confirm({
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
    .ant-row >>> .ant-form-item-control-wrapper{
        width: calc(100% - 50px);
    }
</style>